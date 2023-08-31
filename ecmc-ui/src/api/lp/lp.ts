import { defHttp } from '/@/utils/http/axios'

enum Api {
  Base = '/lp',
}

/**
 * 发放LP
 * @param sendLPInfo lp发放信息
 */
export const sendLP = (sendLPInfo?: object) => {
  return defHttp.post({ url: Api.Base+'/sendLP', params: sendLPInfo })
}

/**
 * 根据角色ID获取lp历史
 */
export const listLpLogByAccountId = (params: object) => {
  return defHttp.get<object>({ url: Api.Base+'/listLpLogByAccountId', params })
}


/**
 * 查询当前登录用的LP发放记录
 */
export const listLpLog = (params: object) => {
  return defHttp.get<object>({ url: Api.Base+'/listLpLog', params })
}
