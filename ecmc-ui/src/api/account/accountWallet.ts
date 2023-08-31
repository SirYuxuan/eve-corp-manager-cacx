import { defHttp } from '/@/utils/http/axios'

enum Api {
  Base = '/accountWallet',
}

/**
 * 获取钱包交易流水
 */
export const list = (params: object) => {
  return defHttp.get<object>({ url: Api.Base, params })
}

