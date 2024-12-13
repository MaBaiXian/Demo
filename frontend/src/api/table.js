import request from '@/utils/request'

export function getList(current, size, data) {
  debugger
  return request({
    url: `/repair/list/${current}/${size}`,
    method: 'post',
    data
  })
}

/**
 * 获取报修详情
 * @param repairId
 * @returns {*}
 */
export function getRepairInfo(repairId) {
  return request({
    url: '/repair/info/' + repairId,
    method: 'get'
  })
}
/**
 * 删除维修申请
 * @param {*} id
 * @returns 操作结果
 */
export function deleteRepair(id) {
  return request({
    url: '/repair/delete/' + id,
    method: 'get'
  })
}
