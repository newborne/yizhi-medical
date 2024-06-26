package com.yizhi.service.medical.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yizhi.service.medical.repository.ScheduleRepository;
import com.yizhi.util.common.exception.YizhiException;
import com.yizhi.util.common.result.ResultCodeEnum;
import com.yizhi.model.pojo.medical.BookingRule;
import com.yizhi.model.pojo.medical.Department;
import com.yizhi.model.pojo.medical.Hospital;
import com.yizhi.model.pojo.medical.Schedule;
import com.yizhi.model.vo.medical.BookingRuleVo;
import com.yizhi.model.vo.medical.ScheduleOrderVo;
import com.yizhi.model.vo.medical.ScheduleQueryVo;
import com.yizhi.service.medical.mapper.ScheduleMapper;
import com.yizhi.service.medical.service.DepartmentService;
import com.yizhi.service.medical.service.HospitalService;
import com.yizhi.service.medical.service.ScheduleService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl extends
        ServiceImpl<ScheduleMapper, Schedule> implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private DepartmentService departmentService;

    //上传排班接口
    @Override
    public void save(Map<String, Object> paramMap) {
        //paramMap 转换department对象
        String paramMapString = JSONObject.toJSONString(paramMap);
        Schedule schedule = JSONObject.parseObject(paramMapString, Schedule.class);
        //根据医院编号 和 排班编号查询
        Schedule scheduleExist = scheduleRepository.
                getScheduleByHospitalCodeAndHospitalScheduleId(schedule.getHospitalCode(), schedule.getHospitalScheduleId());
        //判断
        if (scheduleExist != null) {
            scheduleExist.setUpdateTime(new Date());
            scheduleExist.setIsDeleted(0);
            scheduleExist.setStatus(1);
            scheduleRepository.save(scheduleExist);
        } else {
            schedule.setCreateTime(new Date());
            schedule.setUpdateTime(new Date());
            schedule.setIsDeleted(0);
            schedule.setStatus(1);
            scheduleRepository.save(schedule);
        }
    }

    //查询排班接口
    @Override
    public Page<Schedule> findPage(int page, int limit, ScheduleQueryVo scheduleQueryVo) {
        // 创建Pageable对象，设置当前页和每页记录数
        //0是第一页
        Pageable pageable = PageRequest.of(page - 1, limit);
        // 创建Example对象
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleQueryVo, schedule);
        schedule.setIsDeleted(0);
        schedule.setStatus(1);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        Example<Schedule> example = Example.of(schedule, matcher);
        Page<Schedule> all = scheduleRepository.findAll(example, pageable);
        return all;
    }

    //删除排班
    @Override
    public void delete(String hospitalCode, String hospitalScheduleId) {
        //根据医院编号和排班编号查询信息
        Schedule schedule = scheduleRepository.getScheduleByHospitalCodeAndHospitalScheduleId(hospitalCode, hospitalScheduleId);
        if (schedule != null) {
            scheduleRepository.deleteById(schedule.getId());
        }
    }

    //根据医院编号 和 科室编号 ，查询排班规则数据
    @Override
    public Map<String, Object> findBookingRule(long page, long limit, String hospitalCode, String departCode) {
        //1 根据医院编号 和 科室编号 查询
        Criteria criteria = Criteria.where("hospitalCode").is(hospitalCode).and("departCode").is(departCode);
        //2 根据工作日workDate期进行分组
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(criteria),//匹配条件
                Aggregation.group("workDate")//分组字段
                        .first("workDate").as("workDate")
                        //3 统计号源数量
                        .count().as("doctorCount")
                        .sum("reservedNumber").as("reservedNumber")
                        .sum("availableNumber").as("availableNumber"),
                //排序
                Aggregation.sort(Sort.Direction.DESC, "workDate"),
                //4 实现分页
                Aggregation.skip((page - 1) * limit),
                Aggregation.limit(limit)
        );
        //调用方法，最终执行
        AggregationResults<BookingRuleVo> aggResults =
                mongoTemplate.aggregate(agg, Schedule.class, BookingRuleVo.class);
        List<BookingRuleVo> bookingRuleVoList = aggResults.getMappedResults();
        //分组查询的总记录数
        Aggregation totalAgg = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group("workDate")
        );
        AggregationResults<BookingRuleVo> totalAggResults =
                mongoTemplate.aggregate(totalAgg, Schedule.class, BookingRuleVo.class);
        int total = totalAggResults.getMappedResults().size();
        //把日期对应星期获取
        for (BookingRuleVo bookingRuleVo : bookingRuleVoList) {
            Date workDate = bookingRuleVo.getWorkDate();
            String dayOfWeek = this.getDayOfWeek(new DateTime(workDate));
            bookingRuleVo.setDayOfWeek(dayOfWeek);
        }
        //设置最终数据，进行返回
        Map<String, Object> result = new HashMap<>();
        result.put("bookingRuleList", bookingRuleVoList);
        result.put("total", total);
        //获取医院名称
        String hospitalName = hospitalService.getHospitalNameByHospitalCode(hospitalCode);
        //其他基础数据
        Map<String, String> baseMap = new HashMap<>();
        baseMap.put("hospitalName", hospitalName);
        result.put("baseMap", baseMap);
        return result;
    }

    //根据医院编号 、科室编号和工作日期，查询排班详细信息
    @Override
    public List<Schedule> findList(String hospitalCode, String departCode, String workDate) {
        //根据参数查询mongodb
        List<Schedule> scheduleList =
                scheduleRepository.findScheduleByHospitalCodeAndDepartCodeAndWorkDate(hospitalCode, departCode, new DateTime(workDate).toDate());
        //把得到list集合遍历，向设置其他值：医院名称、科室名称、日期对应星期
        scheduleList.stream().forEach(item -> {
            this.packageSchedule(item);
        });
        return scheduleList;
    }

    //获取可预约的排班数据
    @Override
    public Map<String, Object> findBookingScheduleRule(int page, int limit, String hospitalCode, String departCode) {
        Map<String, Object> result = new HashMap<>();
        //获取预约规则
        //根据医院编号获取预约规则
        Hospital hospital = hospitalService.findByHospitalCode(hospitalCode);
        if (hospital == null) {
            throw new YizhiException(ResultCodeEnum.DATA_ERROR);
        }
        BookingRule bookingRule = hospital.getBookingRule();
        //获取可预约日期的数据（分页）
        IPage iPage = this.findDateList(page, limit, bookingRule);
        //当前可预约日期
        List<Date> dateList = iPage.getRecords();
        //获取可预约日期里面科室的剩余预约数
        Criteria criteria = Criteria.where("hospitalCode").is(hospitalCode).and("departCode").is(departCode)
                .and("workDate").in(dateList);
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group("workDate").first("workDate").as("workDate")
                        .count().as("doctorCount")
                        .sum("availableNumber").as("availableNumber")
                        .sum("reservedNumber").as("reservedNumber")
        );
        AggregationResults<BookingRuleVo> aggregateResult =
                mongoTemplate.aggregate(agg, Schedule.class, BookingRuleVo.class);
        List<BookingRuleVo> scheduleVoList = aggregateResult.getMappedResults();
        //合并数据  map集合 key日期  value预约规则和剩余数量等
        Map<Date, BookingRuleVo> scheduleVoMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(scheduleVoList)) {
            scheduleVoMap = scheduleVoList.stream().
                    collect(
                            Collectors.toMap(BookingRuleVo::getWorkDate,
                                    BookingRuleVo -> BookingRuleVo));
        }
        //获取可预约排班规则
        List<BookingRuleVo> bookingRuleVoList = new ArrayList<>();
        for (int i = 0, len = dateList.size(); i < len; i++) {
            Date date = dateList.get(i);
            //从map集合根据key日期获取value值
            BookingRuleVo bookingRuleVo = scheduleVoMap.get(date);
            //如果当天没有排班医生
            if (bookingRuleVo == null) {
                bookingRuleVo = new BookingRuleVo();
                //就诊医生人数
                bookingRuleVo.setDoctorCount(0);
                //科室剩余预约数  -1表示无号
                bookingRuleVo.setAvailableNumber(-1);
            }
            bookingRuleVo.setWorkDate(date);
            bookingRuleVo.setWorkDateMd(date);
            //计算当前预约日期对应星期
            String dayOfWeek = this.getDayOfWeek(new DateTime(date));
            bookingRuleVo.setDayOfWeek(dayOfWeek);
            //最后一页最后一条记录为即将预约   状态 0：正常 1：即将放号 -1：当天已停止挂号
            if (i == len - 1 && page == iPage.getPages()) {
                bookingRuleVo.setStatus(1);
            } else {
                bookingRuleVo.setStatus(0);
            }
            //当天预约如果过了停号时间， 不能预约
            if (i == 0 && page == 1) {
                DateTime stopTime = this.getDateTime(new Date(), bookingRule.getStopTime());
                if (stopTime.isBeforeNow()) {
                    //停止预约
                    bookingRuleVo.setStatus(-1);
                }
            }
            bookingRuleVoList.add(bookingRuleVo);
        }
        //可预约日期规则数据
        result.put("bookingScheduleList", bookingRuleVoList);
        result.put("total", iPage.getTotal());
        //其他基础数据
        Map<String, String> baseMap = new HashMap<>();
        //医院名称
        baseMap.put("hospitalName", hospitalService.getHospitalNameByHospitalCode(hospitalCode));
        //科室
        Department department = departmentService.findDepartment(hospitalCode, departCode);
        //大科室名称
        baseMap.put("bigDepartName", department.getBigDepartName());
        //科室名称
        baseMap.put("departName", department.getDepartName());
        //月
        baseMap.put("workDateString", new DateTime().toString("yyyy年MM月"));
        //放号时间
        baseMap.put("releaseTime", bookingRule.getReleaseTime());
        //停号时间
        baseMap.put("stopTime", bookingRule.getStopTime());
        result.put("baseMap", baseMap);
        return result;
    }

    ////获取排班id获取排班数据
    @Override
    public Schedule findById(String scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        return this.packageSchedule(schedule);
    }

    //根据排班id获取预约下单数据
    @Override
    public ScheduleOrderVo findScheduleOrderVoById(String scheduleId) {
        ScheduleOrderVo scheduleOrderVo = new ScheduleOrderVo();
        //获取排班信息
        Schedule schedule = this.findById(scheduleId);
        //Schedule schedule = baseMapper.selectById(scheduleId);
        if (schedule == null) {
            throw new YizhiException(ResultCodeEnum.PARAM_ERROR);
        }
        //获取预约规则信息
        Hospital hospital = hospitalService.findByHospitalCode(schedule.getHospitalCode());
        if (hospital == null) {
            throw new YizhiException(ResultCodeEnum.PARAM_ERROR);
        }
        BookingRule bookingRule = hospital.getBookingRule();
        if (bookingRule == null) {
            throw new YizhiException(ResultCodeEnum.PARAM_ERROR);
        }
        //把获取数据设置到scheduleOrderVo
        scheduleOrderVo.setHospitalCode(schedule.getHospitalCode());
        scheduleOrderVo.setHospitalName(hospitalService.getHospitalNameByHospitalCode(schedule.getHospitalCode()));
        scheduleOrderVo.setDepartCode(schedule.getDepartCode());
        scheduleOrderVo.setDepartName(departmentService.getDepartName(schedule.getHospitalCode(), schedule.getDepartCode()));
        scheduleOrderVo.setHospitalScheduleId(schedule.getHospitalScheduleId());
        scheduleOrderVo.setAvailableNumber(schedule.getAvailableNumber());
        scheduleOrderVo.setTitle(schedule.getTitle());
        scheduleOrderVo.setReserveDate(schedule.getWorkDate());
        scheduleOrderVo.setReserveTime(schedule.getWorkTime());
        scheduleOrderVo.setAmount(schedule.getAmount());
        //退号截止天数（如：就诊前一天为-1，当天为0）
        int quitDay = bookingRule.getQuitDay();
        DateTime quitTime = this.getDateTime(new DateTime(schedule.getWorkDate()).plusDays(quitDay).toDate(), bookingRule.getQuitTime());
        scheduleOrderVo.setQuitTime(quitTime.toDate());
        //预约开始时间
        DateTime startTime = this.getDateTime(new Date(), bookingRule.getReleaseTime());
        scheduleOrderVo.setStartTime(startTime.toDate());
        //预约截止时间
        DateTime endTime = this.getDateTime(new DateTime().plusDays(bookingRule.getCycle()).toDate(), bookingRule.getStopTime());
        scheduleOrderVo.setEndTime(endTime.toDate());
        //当天停止挂号时间
        DateTime stopTime = this.getDateTime(new Date(), bookingRule.getStopTime());
        scheduleOrderVo.setStartTime(startTime.toDate());
        return scheduleOrderVo;
    }

    //更新排班信息 用于mp
    @Override
    public void update(Schedule schedule) {
        schedule.setUpdateTime(new Date());
        scheduleRepository.save(schedule);
    }

    //获取可预约日志分页数据
    private IPage findDateList(int page, int limit, BookingRule bookingRule) {
        //获取当天放号时间  年 月 日 小时 分钟
        DateTime releaseTime = this.getDateTime(new Date(), bookingRule.getReleaseTime());
        //获取预约周期
        Integer cycle = bookingRule.getCycle();
        //如果当天放号时间已经过去了，预约周期从后一天开始计算，周期+1
        if (releaseTime.isBeforeNow()) {
            cycle += 1;
        }
        //获取可预约所有日期，最后一天显示即将放号
        List<Date> dateList = new ArrayList<>();
        for (int i = 0; i < cycle; i++) {
            DateTime curDateTime = new DateTime().plusDays(i);
            String dateString = curDateTime.toString("yyyy-MM-dd");
            dateList.add(new DateTime(dateString).toDate());
        }
        //因为预约周期不同的，每页显示日期最多7天数据，超过7天分页
        List<Date> pageDateList = new ArrayList<>();
        int start = (page - 1) * limit;
        int end = (page - 1) * limit + limit;
        //如果可以显示数据小于7，直接显示
        if (end > dateList.size()) {
            end = dateList.size();
        }
        for (int i = start; i < end; i++) {
            pageDateList.add(dateList.get(i));
        }
        //如果可以显示数据大于7，进行分页
        IPage<Date> iPage = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(page, 7, dateList.size());
        iPage.setRecords(pageDateList);
        return iPage;
    }

    /**
     * 将Date日期（yyyy-MM-dd HH:mm）转换为DateTime
     */
    private DateTime getDateTime(Date date, String timeString) {
        String dateTimeString = new DateTime(date).toString("yyyy-MM-dd") + " " + timeString;
        DateTime dateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm").parseDateTime(dateTimeString);
        return dateTime;
    }

    //封装排班详情其他值 医院名称、科室名称、日期对应星期
    private Schedule packageSchedule(Schedule schedule) {
        //设置医院名称
        schedule.getParam().put("hospitalName", hospitalService.getHospitalNameByHospitalCode(schedule.getHospitalCode()));
        //设置科室名称
        schedule.getParam().put("departName", departmentService.getDepartName(schedule.getHospitalCode(), schedule.getDepartCode()));
        //设置日期对应星期
        schedule.getParam().put("dayOfWeek", this.getDayOfWeek(new DateTime(schedule.getWorkDate())));
        return schedule;
    }

    /**
     * 根据日期获取周几数据
     *
     * @param dateTime
     * @return
     */
    private String getDayOfWeek(DateTime dateTime) {
        String dayOfWeek = "";
        switch (dateTime.getDayOfWeek()) {
            case DateTimeConstants.SUNDAY:
                dayOfWeek = "周日";
                break;
            case DateTimeConstants.MONDAY:
                dayOfWeek = "周一";
                break;
            case DateTimeConstants.TUESDAY:
                dayOfWeek = "周二";
                break;
            case DateTimeConstants.WEDNESDAY:
                dayOfWeek = "周三";
                break;
            case DateTimeConstants.THURSDAY:
                dayOfWeek = "周四";
                break;
            case DateTimeConstants.FRIDAY:
                dayOfWeek = "周五";
                break;
            case DateTimeConstants.SATURDAY:
                dayOfWeek = "周六";
            default:
                break;
        }
        return dayOfWeek;
    }
}
