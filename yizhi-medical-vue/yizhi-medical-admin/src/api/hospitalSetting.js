import request from '@/utils/request'

// 增
export function saveHospitalSetting(hospitalSetting) {
  return request({
    url: `/admin/hospital/hospitalSetting/saveHospitalSetting`,
    method: 'post',
    data: hospitalSetting // 使用json
  })
}

// 删-by-id
export function deleteHospitalSetting(id) {
  return request({
    url: `/admin/hospital/hospitalSetting/${id}`,
    method: 'delete'
  })
}

// 删-批量
export function batchRemoveHospitalSetting(idList) {
  return request({
    url: `/admin/hospital/hospitalSetting/batchRemove`,
    method: 'delete',
    data: idList
  })
}

// 改-状态
export function lockHospitalSetting(id, status) {
  return request({
    url: `/admin/hospital/hospitalSetting/lockHospitalSetting/${id}/${status}`,
    method: 'put'
  })
}

// 改-全部
export function updateHospitalSetting(hospitalSetting) {
  return request({
    url: `/admin/hospital/hospitalSetting/updateHospitalSetting`,
    method: 'post',
    data: hospitalSetting // 使用json
  })
}

// 查-by-id
export function getHospitalSetting(id) {
  return request({
    url: `/admin/hospital/hospitalSetting/getHospitalSetting/${id}`,
    method: 'get'
  })
}

// 查-条件&分页
export function getHospitalSettingList(current, limit, searchObj) {
  return request({
    url: `/admin/hospital/hospitalSetting/findPageHospitalSetting/${current}/${limit}`,
    method: 'post',
    data: searchObj // 使用json
  })
}
