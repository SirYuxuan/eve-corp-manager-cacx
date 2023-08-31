import { defHttp } from '/@/utils/http/axios'

enum Api {
  Base = '/config',
}

/**
 * 获取全部配置列表
 */
export const list = (params: object) => {
  return defHttp.get<object>({ url: Api.Base, params })
}


/**
 * 删除配置
 * @param ids
 */
export const del = (ids?: object) => {
  return defHttp.delete<boolean>({ url: Api.Base, params: ids })
}
/**
 * 添加一个配置
 * @param config
 */
export const add = (config?: object) => {
  return defHttp.post({ url: Api.Base, params: config })
}

/**
 * 编辑一个配置
 * @param config
 */
export const edit = (config?: object) => {
  return defHttp.put({ url: Api.Base, params: config })
}

/**
 * 刷新所有配置
 * @param role
 */
export const refresh = () => {
  return defHttp.put({ url: Api.Base + '/refresh' })
}
