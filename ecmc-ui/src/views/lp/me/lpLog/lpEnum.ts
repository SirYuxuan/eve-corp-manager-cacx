export enum LpSource {
  /**
   * PAP转换
   */
  PAP = 1,
  /**
   * 手动发放
   */
  MANUAL_RELEASE = 2,
  /**
   * 用户转账
   */
  USER_TRANSFER = 3,
  /**
   * 兑换商品
   */
  EXCHANGE_GOOD = 4,
  /**
   * 兑换退款
   */
  EXCHANGE_REFUND = 5,
  /**
   * 物品兑换
   */
  ITEM_EXCHANGE = 6,
  /**
   * 超网订单
   */
  ULTRA_NET_ORDER = 7
}

export enum LpType {
  // 支出
  EXPENDITURE = 1,
  // 收入
  INCOME = 2
}

// 获取LP的来源
export function getSource(source){
  switch (source){
    case LpSource.PAP:
      return 'PAP转换'
    case LpSource.ULTRA_NET_ORDER:
      return '超网购买'
    case LpSource.EXCHANGE_GOOD:
      return '兑换商品'
    case LpSource.EXCHANGE_REFUND:
      return '兑换退款'
    case LpSource.MANUAL_RELEASE:
      return '手动发放'
    case LpSource.ITEM_EXCHANGE:
      return '物品兑换'
    case LpSource.USER_TRANSFER:
      return '用户转账'
  }
}
