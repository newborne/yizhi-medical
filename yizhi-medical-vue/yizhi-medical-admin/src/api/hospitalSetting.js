import request from '@/utils/request'

export default {
  // 增
  save(hospitalSetting) {
    return request({
      url: `/admin/medical/hospitalSetting/save`,
      method: 'post',
      data: hospitalSetting // 使用json
    })
  },

  // 删-by-id
  deleteById(id) {
    return request({
      url: `/admin/medical/hospitalSetting/delete/${id}`,
      method: 'delete'
    })
  },

  // 删-批量
  deleteByIdInBatch(idList) {
    return request({
      url: `/admin/medical/hospitalSetting/delete/batch`,
      method: 'delete',
      data: idList
    })
  },

  // 改-状态
  updateStatus(id, status) {
    return request({
      url: `/admin/medical/hospitalSetting/updateStatus/${id}/${status}`,
      method: 'put'
    })
  },

  // 改-全部
  update(hospitalSetting) {
    return request({
      url: `/admin/medical/hospitalSetting/update`,
      method: 'post',
      data: hospitalSetting // 使用json
    })
  },

  // 查-by-id
  findById(id) {
    return request({
      url: `/admin/medical/hospitalSetting/find/${id}`,
      method: 'get'
    })
  },

  // 查-条件&分页
  findPage(current, limit, searchObj) {
    return request({
      url: `/admin/medical/hospitalSetting/page/${current}/${limit}`,
      method: 'post',
      data: searchObj // 使用json
    })
  }
}
