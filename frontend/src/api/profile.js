import request from '@/utils/request'

export function getAnnouncements(params) {
  return request({
    // url: '/vue-admin-template/table/list',
    url: '/profile/getAnnouncements',
    method: 'get',
    params
  })
}
