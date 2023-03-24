package com.yizhi.hospital.controller;

import com.yizhi.hospital.mapper.HospitalSettingMapper;
import com.yizhi.hospital.model.HospitalSetting;
import com.yizhi.hospital.service.ApiService;
import com.yizhi.hospital.util.YyghException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author qy
 *
 */
@Api(tags = "医院管理接口")
@Controller
@RequestMapping
public class ApiController extends BaseController {

	@Autowired
	private ApiService apiService;

	@Autowired
	private HospitalSettingMapper hospitalSettingMapper;

	@RequestMapping("/hospitalSetting/index")
	public String getHospitalSetting(ModelMap model,RedirectAttributes redirectAttributes) {
		HospitalSetting hospitalSetting = hospitalSettingMapper.selectById(1);
		model.addAttribute("hospitalSetting", hospitalSetting);
		return "hospitalSetting/index";
	}

	@RequestMapping(value="/hospitalSetting/save")
	public String createHospitalSetting(ModelMap model,HospitalSetting hospitalSetting) {
		hospitalSettingMapper.updateById(hospitalSetting);
		return "redirect:/hospitalSetting/index";
	}

	@RequestMapping("/hospital/index")
	public String getHospital(ModelMap model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		try {
			HospitalSetting hospitalSetting = hospitalSettingMapper.selectById(1);
			if(null == hospitalSetting || StringUtils.isEmpty(hospitalSetting.getHospitalCode()) || StringUtils.isEmpty(hospitalSetting.getSignKey())) {
				this.failureMessage("先设置医院code与签名key", redirectAttributes);
				return "redirect:/hospitalSetting/index";
			}

			model.addAttribute("hospital", apiService.getHospital());
		} catch (YyghException e) {
			this.failureMessage(e.getMessage(), request);
		} catch (Exception e) {
			this.failureMessage("数据异常", request);
		}
		return "hospital/index";
	}

	@RequestMapping(value="/hospital/create")
	public String createHospital(ModelMap model) {
		return "hospital/create";
	}

	@RequestMapping(value="/hospital/save",method=RequestMethod.POST)
	public String saveHospital(String data, HttpServletRequest request) {
		try {
			apiService.saveHospital(data);
		} catch (YyghException e) {
			return this.failurePage(e.getMessage(),request);
		} catch (Exception e) {
			return this.failurePage("数据异常",request);
		}
		return this.successPage(null,request);
	}

	@RequestMapping("/department/list")
	public String findDepartment(ModelMap model,
								 @RequestParam(defaultValue = "1") int pageNum,
								 @RequestParam(defaultValue = "10") int pageSize,
								 HttpServletRequest request,RedirectAttributes redirectAttributes) {
		try {
			HospitalSetting hospitalSetting = hospitalSettingMapper.selectById(1);
			if(null == hospitalSetting || StringUtils.isEmpty(hospitalSetting.getHospitalCode()) || StringUtils.isEmpty(hospitalSetting.getSignKey())) {
				this.failureMessage("先设置医院code与签名key", redirectAttributes);
				return "redirect:/hospitalSetting/index";
			}

			model.addAllAttributes(apiService.findDepartment(pageNum, pageSize));
		} catch (YyghException e) {
			this.failureMessage(e.getMessage(), request);
		} catch (Exception e) {
			this.failureMessage("数据异常", request);
		}
		return "department/index";
	}

	@RequestMapping(value="/department/create")
	public String create(ModelMap model) {
		return "department/create";
	}

	@RequestMapping(value="/department/save",method=RequestMethod.POST)
	public String save(String data, HttpServletRequest request) {
		try {
			apiService.saveDepartment(data);
		} catch (YyghException e) {
			return this.failurePage(e.getMessage(),request);
		} catch (Exception e) {
			return this.failurePage("数据异常",request);
		}
		return this.successPage(null,request);
	}

	@RequestMapping("/schedule/list")
	public String findSchedule(ModelMap model,
								 @RequestParam(defaultValue = "1") int pageNum,
								 @RequestParam(defaultValue = "10") int pageSize,
							   HttpServletRequest request,RedirectAttributes redirectAttributes) {
		try {
			HospitalSetting hospitalSetting = hospitalSettingMapper.selectById(1);
			if(null == hospitalSetting || StringUtils.isEmpty(hospitalSetting.getHospitalCode()) || StringUtils.isEmpty(hospitalSetting.getSignKey())) {
				this.failureMessage("先设置医院code与签名key", redirectAttributes);
				return "redirect:/hospitalSetting/index";
			}

			model.addAllAttributes(apiService.findSchedule(pageNum, pageSize));
		} catch (YyghException e) {
			this.failureMessage(e.getMessage(), request);
		} catch (Exception e) {
			this.failureMessage("数据异常", request);
		}
		return "schedule/index";
	}

	@RequestMapping(value="/schedule/create")
	public String createSchedule(ModelMap model) {
		return "schedule/create";
	}

	@RequestMapping(value="/schedule/save",method=RequestMethod.POST)
	public String saveSchedule(String data, HttpServletRequest request) {
		try {
			//data = data.replaceAll("\r\n", "").replace(" ", "");
			apiService.saveSchedule(data);
		} catch (YyghException e) {
			return this.failurePage(e.getMessage(),request);
		} catch (Exception e) {
			e.printStackTrace();
			return this.failurePage("数据异常："+e.getMessage(),request);
		}
		return this.successPage(null,request);
	}

	@RequestMapping(value="/hospital/createBatch")
	public String createHospitalBatch(ModelMap model) {
		return "hospital/createBatch";
	}

	@RequestMapping(value="/hospital/saveBatch",method=RequestMethod.POST)
	public String saveBatchHospital(HttpServletRequest request) {
		try {
			apiService.saveBatchHospital();
		} catch (YyghException e) {
			return this.failurePage(e.getMessage(),request);
		} catch (Exception e) {
			return this.failurePage("数据异常",request);
		}
		return this.successPage(null,request);
	}

	@RequestMapping(value="/department/remove/{departCode}",method=RequestMethod.GET)
	public String removeDepartment(ModelMap model, @PathVariable String departCode, RedirectAttributes redirectAttributes) {
		apiService.removeDepartment(departCode);

		this.successMessage(null, redirectAttributes);
		return "redirect:/department/list";
	}

	@RequestMapping(value="/schedule/remove/{hospitalScheduleId}",method=RequestMethod.GET)
	public String removeSchedule(ModelMap model, @PathVariable String hospitalScheduleId, RedirectAttributes redirectAttributes) {
		apiService.removeSchedule(hospitalScheduleId);

		this.successMessage(null, redirectAttributes);
		return "redirect:/schedule/list";
	}

}

