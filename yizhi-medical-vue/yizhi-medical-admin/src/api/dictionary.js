import request from '@/utils/request'

export default {
  // 查子节点-by-id
  dictionaryList(id) {
    return request({
      url: `/admin/dictionary/findChildData/${id}`,
      method: 'get'
    })
  }
}
