import { defHttp } from '/@/utils/http/axios'

enum Api {
  Base = '/srpBlacklist',
}

/**
 * 获取补损黑名单列表
 */
export const list = (params: object) => {
  return defHttp.get({ url: Api.Base, params })
}


/**
 * 删除补损黑名单
 * @param ids
 */
export const del = (ids?: object) => {
  return defHttp.delete<boolean>({ url: Api.Base, params: ids })
}
/**
 * 添加一个补损黑名单
 * @param data
 */
export const add = (data?: object) => {
  return defHttp.post({ url: Api.Base, params: data })
}

/**
 * 编辑一个补损黑名单
 * @param data
 */
export const edit = (data?: object) => {
  return defHttp.put({ url: Api.Base, params: data })
}
