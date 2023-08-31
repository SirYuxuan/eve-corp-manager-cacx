import { defHttp } from '/@/utils/http/axios'

enum Api {
  Base = '/sde',
}

/**
 * 根据舰船名称查询舰船列表
 */
export const shipList = (name: object) => {
  return defHttp.get({ url: Api.Base + '/shipList/' + name })
}
