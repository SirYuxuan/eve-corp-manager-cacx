import { defHttp } from '/@/utils/http/axios'

enum Api {
  OneDay = '/openApi/oneDay',
}

/**
 * 获取每日一言
 */
export function getOneDay() {
  return defHttp.get<string>({
    url: Api.OneDay,
  })
}
