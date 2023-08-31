import { defHttp } from '/@/utils/http/axios'

enum Api {
  Base = '/accountContract',
}

/**
 * 获取自己的合同数据
 */
export const list = (params: object) => {
  return defHttp.get<object>({ url: Api.Base, params })
}


/**
 * 查询一个合同的具体内容
 */
export const listContractItem = (id) => {
  return defHttp.get<object>({ url: Api.Base + '/listContractItem/' + id })
}
