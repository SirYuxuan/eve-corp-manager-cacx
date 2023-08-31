import { defHttp } from '/@/utils/http/axios'

enum Api {
  Base = '/role',
}

/**
 * 获取角色列表
 */
export const list = (params: object) => {
  return defHttp.get<object>({ url: Api.Base, params })
}

/**
 * 保存角色菜单
 * @param params 菜单数据
 */
export const saveMenu = (params: object) => {
  return defHttp.put<object>({ url: Api.Base + '/saveMenu', params })
}

/**
 * 删除角色
 * @param ids
 */
export const del = (ids?:object) => {
  return defHttp.delete<boolean>({ url: Api.Base, params: ids })
}
/**
 * 添加一个角色
 * @param role
 */
export const add = (role?: object) => {
  return defHttp.post({ url: Api.Base, params: role })
}

/**
 * 编辑一个角色
 * @param role
 */
export const edit = (role?: object) => {
  return defHttp.put({ url: Api.Base, params: role })
}
