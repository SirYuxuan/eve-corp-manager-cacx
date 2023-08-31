import { defHttp } from '/@/utils/http/axios'
import { getMenuListResultModel } from './model/menuModel'

enum Api {
  Base = '/menu',
  GetMenuList = '/menu/build',
}

/**
 * @description: Get user menu based on id
 */

export const getMenuList = () => {
  return defHttp.get<getMenuListResultModel>({ url: Api.GetMenuList })
}

/**
 * 获取菜单树
 */
export function getTree() {
  return defHttp.get({ url: Api.Base + '/tree' })
}

/**
 * 获取菜单列表
 */
export const list = (params: object) => {
  return defHttp.get<object>({ url: Api.Base+'/tree', params })
}


/**
 * 删除菜单
 * @param ids
 */
export  const del = (ids?:object) => {
  return defHttp.delete<boolean>({ url: Api.Base, params: ids })
}
/**
 * 添加一个菜单
 * @param menu
 */
export const add = (menu?: object) => {
  return defHttp.post({ url: Api.Base, params: menu })
}

/**
 * 编辑一个菜单
 * @param user
 */
export const edit = (menu?: object) => {
  return defHttp.put({ url: Api.Base, params: menu })
}
