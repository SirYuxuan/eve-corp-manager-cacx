import { defHttp } from '/@/utils/http/axios'

enum Api {
  Base = '/accountOrder',
}

/**
 * 获取钱市场交易订单
 */
export const list = (params: object) => {
  return defHttp.get<object>({ url: Api.Base, params })
}

