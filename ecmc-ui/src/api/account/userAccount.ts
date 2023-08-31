import { defHttp } from '/@/utils/http/axios'

enum Api {
  Base = '/userAccount',
}

/**
 * 添加或更新一个账户，根据授权Code
 */
export const addOrUpdateAccount = (code: string) => {
  return defHttp.post<string>({
    url: Api.Base + '/addOrUpdateAccount/' + code,
    timeout: 9999999
  }, { errorMessageMode: 'none' })
}

/**
 * 获取角色列表
 */
export const list = (params: object) => {
  return defHttp.get<object>({ url: Api.Base, params })
}

/**
 * 获取全部角色列表
 */
export const listAll = (params: object) => {
  return defHttp.get<object>({ url: Api.Base + '/listAll', params })
}

/**
 * 根据角色名模糊搜索角色列表
 * @param name
 */
export const listAccount = (name: object) => {
  return defHttp.get<object>({ url: Api.Base + '/listAccount/' + name })
}
/**
 * 获取主页的统计数据
 */
export const getHomeData = () => {
  return defHttp.get({ url: Api.Base + '/getHomeData' })
}

/**
 * 获取统计页面上方的表Grid数据
 */
export const analysisGrid = () => {
  return defHttp.get({ url: Api.Base + '/analysisGrid/' + (localStorage.getItem('analysis') === 'true') })
}


/**
 * 获取当前登录用户的KM记录
 * @param params
 */
export const listKillMail = (params: object) => {
  return defHttp.get({ url: Api.Base + '/listKillMail', params })
}
/**
 * 获取一个击毁报告的详细信息
 * @param killMailId
 */
export const listKillMailItem = (killMailId: any) => {
  return defHttp.get({ url: Api.Base + '/listKillMailItem/' + killMailId })
}

/**
 * 获取当前登录用户的所有角色
 */
export const all = () => {
  return defHttp.get<object>({ url: Api.Base + '/all' })
}

/**
 * 获取当前剩余LP数量
 */
export const getNowLp = () => {
  return defHttp.get<object>({ url: Api.Base + '/getNowLp' })
}


/**
 * 删除角色
 * @param ids
 */
export const del = (ids?: object) => {
  return defHttp.delete<boolean>({ url: Api.Base, params: ids })
}

/**
 * 导出列表
 * @param params
 */
export function exportList(params?: object) {
  return defHttp.request(
    { url: Api.Base + '/download', params: params, method: 'get', responseType: 'blob' },
    { isReturnNativeResponse: true },
  )
}

/**
 * 设置主角色
 * @param id 角色记录ID
 */
export function setMainAccount(id) {
  return defHttp.put({ url: Api.Base + '/setMainAccount/' + id })
}


/**
 * 刷新当前登录用户的所有角色KM信息
 */
export function refreshKm() {
  return defHttp.put({ url: Api.Base + '/refreshKm' })
}
