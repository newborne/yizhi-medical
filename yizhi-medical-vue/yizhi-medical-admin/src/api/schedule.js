
import request from '@/utils/request'

export default {
  //查询预约规则
  findBookingRule(page,limit,hospitalCode,departCode) {
    return request ({
      url: `/admin/medical/schedule/bookingRule/${page}/${limit}/${hospitalCode}/${departCode}`,
      method: 'get'
    })
  },
  //查询排班详情
  findList(hospitalCode,departCode,workDate) {
    return request ({
      url: `/admin/medical/schedule/list/${hospitalCode}/${departCode}/${workDate}`,
      method: 'get'
    })
  }
}
