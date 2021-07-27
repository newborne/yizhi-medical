import request from '@/utils/request'

export default {
  // 查子节点-by-id
  findChildrenListById(id) {
    return request({
      url: `/admin/dictionary/children/id/${id}`,
      method: 'get'
    })
  },
  //根据dictcode查询所有子节点 （所有省）
  findChildrenListByDictionaryCode(dictionaryCode) {
    return request({
      url: `/admin/dictionary/children/dictionaryCode/${dictionaryCode}`,
      method: 'get'
    })
  },
}
