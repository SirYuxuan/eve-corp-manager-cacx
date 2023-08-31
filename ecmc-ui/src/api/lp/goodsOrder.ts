import { defHttp } from '/@/utils/http/axios'

enum Api {
  Base = '/lpGoodsOrder',
}

/**
 * 获取LP商品订单列表
 */
export const list = (params: object) => {
  return defHttp.get<object>({ url: Api.Base, params })
}


/**
 * 删除LP商品订单
 * @param ids
 */
export const del = (ids?:object) => {
  return defHttp.delete<boolean>({ url: Api.Base, params: ids })
}


/**
 * 批量审批LP订单
 * @param approval
 */
export const batchApproval = (approval?: object) => {
  return defHttp.put({ url: Api.Base+'/batchApproval', params: approval })
}
