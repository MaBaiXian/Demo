import request from '@/utils/request'

// 获取管理员列表
export function getAdminList(params) {
  return request({
    url: '/Admin/getAdminList',
    method: 'get',
    params // 传递查询参数
  })
}

// 创建管理员
export function createAdmin(adminData) {
  return request({
    url: '/Admin/createAdmin',
    method: 'post',
    data: adminData // 发送管理员数据
  })
}

// 更新管理员信息
export function updateAdmin(id, adminData) {
  return request({
    url: `/Admin/updateAdmin/${id}`,
    method: 'put',
    data: adminData // 发送更新的管理员数据
  })
}

// 删除管理员
export function deleteAdmin(id) {
  return request({
    url: `/Admin/deleteAdmin/${id}`,
    method: 'delete'
  })
}
