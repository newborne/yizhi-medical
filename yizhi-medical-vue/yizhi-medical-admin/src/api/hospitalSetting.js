import request from '@/utils/request'

export function getHospitalSettingList(current, limit, searchObj) {
  return request({
    url: `/admin/hospital/hospitalSetting/findPageHospitalSetting/${current}/${limit}`,
    method: 'post',
    data: searchObj // 使用json
  })
}
