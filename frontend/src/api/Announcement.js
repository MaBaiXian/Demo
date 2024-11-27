import request from '@/utils/request'

export function getAllAnnouncements(params) {
  return request({
    // url: '/vue-admin-template/table/list',
    url: '/Announcement/getAllAnnouncements',
    method: 'get',
    params
  })
}

export function deleteAnnouncement(id) {
  return request({
    url: `/Announcement/deleteAnnouncement/${id}`, // 将 id 附加到 URL 上
    method: 'delete'
  })
}

