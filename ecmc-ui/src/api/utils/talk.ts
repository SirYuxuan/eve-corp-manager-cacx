import { defHttp } from '/@/utils/http/axios'

enum Api {
  Base = '/messageBoard',
}

/**
 * 添加一个动态
 * @param talk
 */
export const add = (talk?: object) => {
  return defHttp.post({ url: Api.Base, params: talk })
}

/**
 * 获取留言列表
 */
export const list = (params: object) => {
  return defHttp.get({ url: Api.Base, params })
}
