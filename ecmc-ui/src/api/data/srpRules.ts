import { defHttp } from '/@/utils/http/axios'

enum Api {
  Base = '/srpRules',
}

/**
 * 获取补损规则列表
 */
export const list = (params: object) => {
  return defHttp.get({ url: Api.Base, params })
}


/**
 * 删除补损规则
 * @param ids
 */
export const del = (ids?: object) => {
  return defHttp.delete<boolean>({ url: Api.Base, params: ids })
}
/**
 * 添加一个补损规则
 * @param data
 */
export const add = (data?: object) => {
  return defHttp.post({ url: Api.Base, params: data })
}

/**
 * 编辑一个补损规则
 * @param data
 */
export const edit = (data?: object) => {
  return defHttp.put({ url: Api.Base, params: data })
}
