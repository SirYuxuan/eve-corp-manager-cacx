import { BasicFetchResult, BasicPageParams } from '/@/api/model/baseModel'

/**
 * 登录接口参数
 */
export interface LoginParams {
  username: string;
  password: string;
}

/**
 * 角色信息
 */
export interface RoleInfo {
  roleName: string;
  value: string;
}

/**
 * 登录接口返回信息
 */
export interface LoginResultModel {
  userId: string | number;
  token: string;
  role: RoleInfo;
}

/**
 * 用户信息
 */
export interface GetUserInfoModel {
  roles: RoleInfo[];
  // 用户id
  userId: string | number;
  // 用户名
  username: string;
  // 真实名字
  realName: string;
  // 头像
  avatar: string;
  // 介绍
  desc?: string;
}
/**
 * 用户列表查询参数
 */
export type ListParam = BasicPageParams & {
  account?: string;
  nickname?: string;
};
/**
 * 用户列表返回值
 */
export interface AccountListItem {
  id: string;
  account: string;
  email: string;
  nickname: string;
  role: number;
  createTime: string;
  remark: string;
  status: number;
}

/**
 * 角色列表返回值
 */
export interface RoleListItem{

}

/**
 *请求列表的返回值
 */

export type UserListGetResultModel = BasicFetchResult<AccountListItem>;

export type RoleListGetResultModel = BasicFetchResult<RoleListItem>
