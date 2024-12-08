import request from '@/utils/request'

// 获取学生列表
export function getStudentList(params) {
  return request({
    url: '/Student/getStudentList',
    method: 'get',
    params // 传递查询参数
  })
}

// 创建学生
export function createStudent(studentData) {
  return request({
    url: '/Student/createStudent',
    method: 'post',
    data: studentData // 发送学生数据
  })
}

// 更新学生信息
export function updateStudent(id, studentData) {
  return request({
    url: `/Student/updateStudent/${id}`,
    method: 'put',
    data: studentData // 发送更新的学生数据
  })
}

// 删除学生
export function deleteStudent(id) {
  return request({
    url: `/Student/deleteStudent/${id}`,
    method: 'delete'
  })
}
