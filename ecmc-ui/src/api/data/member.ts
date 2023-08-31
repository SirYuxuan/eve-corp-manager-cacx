import { defHttp } from '/@/utils/http/axios'

enum Api {
  Base = '/member',
}

/**
 * 获取军团成员列表
 */
export const list = (params: object) => {
  return defHttp.get({ url: Api.Base, params })
}

/**
 * 导出列表
 * @param params
 */
export function exportList(params?: object) {
  return defHttp.request(
    { url: Api.Base+'/download', params: params, method: 'get', responseType: 'blob' },
    { isReturnNativeResponse: true },
  )
}
