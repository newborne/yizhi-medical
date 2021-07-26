import request from '@/utils/request'

export default {
  // 查子节点-by-id
  findChildrenList(id) {
    return request({
      url: `/admin/dictionary/children/id/${id}`,
      method: 'get'
    })
  }
}
