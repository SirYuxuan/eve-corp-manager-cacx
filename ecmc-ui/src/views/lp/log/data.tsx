import { BasicColumn, FormSchema } from '/@/components/Table'
import { Tag } from 'ant-design-vue'
import { defaultSearchFormSchema } from '/@/components/Form/src/defaultFormSchema'
import { getSource, LpSource, LpType } from '/@/views/lp/me/lpLog/lpEnum'

export const columns: BasicColumn[] = [
  {
    title: '角色',
    dataIndex: 'characterName',
    width: 100,
  }, {
    title: '军团',
    dataIndex: 'corpName',
    width: 100,
  }, {
    title: '联盟',
    dataIndex: 'allianceName',
    width: 100,
  },
  {
    sorter: true,
    title: 'LP数量',
    dataIndex: 'lp',
    width: 100,
  },
  {
    title: '来源',
    dataIndex: 'type',
    width: 100,
    customRender: ({ record }) => {
      return <Tag color="blue">{getSource(record.source)}</Tag>
    },
  },
  {
    title: '类型',
    dataIndex: 'type',
    width: 100,
    customRender: ({ record }) => {
      return <Tag
        color={record.type === LpType.EXPENDITURE ? 'yellow' : 'green'}>{record.type === LpType.EXPENDITURE ? '支出' : '收入'}</Tag>
    },
  },
  {
    sorter: true,
    title: '备注',
    dataIndex: 'num',
    width: 120,
  },
  {
    sorter: true,
    title: '操作人',
    dataIndex: 'createBy',
    width: 120,
  },
  {
    sorter: true,
    title: '操作时间',
    dataIndex: 'createTime',
    width: 120,
  }
]

export const searchFormSchema: FormSchema[] = [
  ...defaultSearchFormSchema,
  {
    field: 'source',
    component: 'Select',
    colProps: { span: 2 },
    componentProps: {
      placeholder: 'LP来源',
      options: [
        { label: 'PAP转换', value: LpSource.PAP },
        { label: '用户转账', value: LpSource.USER_TRANSFER },
        { label: '物品交换', value: LpSource.ITEM_EXCHANGE },
        { label: '兑换退款', value: LpSource.EXCHANGE_REFUND },
        { label: '超网购买', value: LpSource.ULTRA_NET_ORDER },
        { label: '手动发放', value: LpSource.MANUAL_RELEASE },
        { label: '兑换商品', value: LpSource.EXCHANGE_GOOD },
      ]
    }
  },
  {
    field: 'type',
    component: 'Select',
    colProps: { span: 2 },
    componentProps: {
      placeholder: '收支类型',
      options: [
        { label: '收入', value: LpType.INCOME },
        { label: '支出', value: LpType.EXPENDITURE },
      ]
    }
  }
]

