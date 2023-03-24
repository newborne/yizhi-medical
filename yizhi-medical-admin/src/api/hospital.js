import request from '@/utils/request'

export default {
  // 医院列表
  findPage(page, limit, searchObj) {
    return request({
      url: `/admin/medical/hospital/page/${page}/${limit}`,
      method: 'get',
      params: searchObj
    })
  },
  // 更新医院上线状态
  updateStatus(id, status) {
    return request({
      url: `/admin/medical/hospital/updateStatus/${id}/${status}`,
      method: 'get'
    })
  },
  // 查看医院详情
  findDetailById(id) {
    return request({
      url: `/admin/medical/hospital/detail/${id}`,
      method: 'get'
    })
  },
  // 查看医院科室
  getDeptByHoscode(hoscode) {
    return request({
      url: `/admin/medical/department/getDeptList/${hoscode}`,
      method: 'get'
    })
  }
}
