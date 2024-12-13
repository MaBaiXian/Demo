import request from '@/utils/request'
import axios from 'axios'
// 根据学生ID获取费用信息
// export function getFeeByStudentId(stuId) {
//   return request({
//     url: `/api/fees/${stuId}`,
//     method: 'get'
//   })
// }
export function getFeeByStudentId(stuId) {
  console.log(axios.get(`http://localhost:8080/api/fees/${stuId}`, { stuId: stuId }))
  return axios.get(`http://localhost:8080/api/fees/${stuId}`, { stuId: stuId })
}
// 获取所有费用信息
// export function getAllFees() {
//   return request({
//     url: 'api/fees',
//     method: 'get'
//   })
// }
export function getAllFees() {
  console.log(axios.get(`http://localhost:8080/api/fees`))
  return axios.get(`http://localhost:8080/api/fees`)
}
// 创建新的费用信息
export function createFee(feeData) {
  return request({
    url: '/api/fees',
    method: 'post',
    data: feeData
  })
}

// 更新费用信息
export function updateFee(feeId, feeData) {
  return request({
    url: `/api/fees/${feeId}`,
    method: 'put',
    data: feeData
  })
}

// 删除费用信息
export function deleteFee(feeId) {
  return request({
    url: `/api/fees/${feeId}`,
    method: 'delete'
  })
}
