/**
 * 校验email格式是否合法
 * @param email email
 */
export function isEmail(email:string):boolean{
  return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(email)
}
