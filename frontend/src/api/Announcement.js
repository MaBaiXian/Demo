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

export function updateAnnouncement(id, noticeData) {
  return request({
    url: `/Announcement/updateAnnouncement/${id}`, // 将 id 附加到 URL 上
    method: 'put',
    data: { message: noticeData.message } // 发送通知数据
  })
}

export function createAnnouncement(noticeData) {
  return request({
    url: `/Announcement/createAnnouncement`, // 创建通知的URL
    method: 'post',
    data: { message: noticeData.message }// 发送通知数据
  })
}

