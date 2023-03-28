import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/admin/medical/acl/user/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/admin/medical/acl/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/admin/medical/acl/user/logout',
    method: 'post'
  })
}
