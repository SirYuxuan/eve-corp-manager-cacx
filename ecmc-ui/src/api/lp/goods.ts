import { defHttp } from '/@/utils/http/axios'

enum Api {
  Base = '/lpGoods',
}

/**
 * 获取LP商品列表
 */
export const list = (params: object) => {
  return defHttp.get({ url: Api.Base, params })
}


/**
 * 删除LP商品
 * @param ids
 */
export const del = (ids?: object) => {
  return defHttp.delete<boolean>({ url: Api.Base, params: ids })
}
/**
 * 添加一个LP商品
 * @param goods
 */
export const add = (goods?: object) => {
  return defHttp.post({ url: Api.Base, params: goods })
}

/**
 * 编辑一个LP商品
 * @param goods
 */
export const edit = (goods?: object) => {
  return defHttp.put({ url: Api.Base, params: goods })
}
/**
 * 购买一个LP商品
 * @param goods
 */
export const buyGoods = (goods?: object) => {
  return defHttp.put({ url: Api.Base + '/buyGoods', params: goods })
}
