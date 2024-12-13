import request from '@/utils/request'

export function saveRepair(data) {
  return request({
    url: '/repair/add',
    method: 'post',
    data
  })
}

export function updateRepair(data) {
  return request({
    url: '/repair/update',
    method: 'post',
    data
  })
}
