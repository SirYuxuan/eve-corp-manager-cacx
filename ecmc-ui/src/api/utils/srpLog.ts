import { defHttp } from '/@/utils/http/axios'

enum Api {
  Base = '/srpLog',
}


/**
 * 提交一个新的补损记录
 * @param srpLog
 */
export const add = (srpLog?: object) => {
  return defHttp.post({ url: Api.Base, params: srpLog })
}
/**
 * 审批一个补损申请
 * @param srpLog
 */
export const edit = (srpLog?: object) => {
  return defHttp.put({ url: Api.Base, params: srpLog })
}

/**
 * 获取补损记录列表
 */
export const list = (params: object) => {
  return defHttp.get({ url: Api.Base, params })
}
