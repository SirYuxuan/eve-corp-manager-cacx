import { BasicColumn, FormSchema } from '/@/components/Table'
import { toThousands } from '/@/utils'
import { Tag } from 'ant-design-vue'
import { all } from '/@/api/account/userAccount'

export const columns: BasicColumn[] = [
  {
    sorter: true,
    title: '物品名称',
    dataIndex: 'typeName',
    width: 150,
  },
  {
    sorter: true,
    title: '所属角色',
    dataIndex: 'characterName',
    width: 130,
  },
  {
    sorter: true,
    title: '客户',
    dataIndex: 'clientName',
    width: 130,
  },
  {
    sorter: true,
    title: '订单类型',
    dataIndex: 'refType',
    customRender({ record }) {
      return <Tag color={'green'}>{record.isBuy?'收单':'卖单'}</Tag>
    },
    width: 70,
  }
  ,
  {
    sorter: true,
    title: '单价',
    dataIndex: 'unitPrice',
    width: 120,
    format(str) {
      return toThousands(str) + ' ISK'
    }
  },
  {
    sorter: true,
    title: '交易数量',
    dataIndex: 'quantity',
    width: 80,
    format(str) {
      return toThousands(str).split('.')[0]
    }
  },
  {
    title: '总价',
    dataIndex: 'unitPrice',
    width: 120,
    customRender({ record }){
      return toThousands(record.unitPrice * record.quantity).split('.')[0] + ' ISK'
    }
  },
  {
    sorter: true,
    title: '交易地点',
    dataIndex: 'locationName',
    width: 250,
  },
  {
    sorter: true,
    title: '交易时间',
    dataIndex: 'date',
    width: 150,
  }
]


export const searchFormSchema: FormSchema[] = [
  {
    field: 'accountId',
    component: 'ApiSelect',
    colProps: { span: 3 },
    componentProps: () => {
      return {
        placeholder: '请选择角色',
        api: all,
        labelField: 'characterName',
        valueField: 'id',
      }
    },
  },
  {
    field: 'clientName',
    component: 'Input',
    colProps: { span: 3 },
    componentProps: {
      placeholder: '客户名称',
    }
  },
  {
    field: 'typeName',
    component: 'Input',
    colProps: { span: 3 },
    componentProps: {
      placeholder: '物品名称',
    }
  },
  {
    field: 'isBuy',
    component: 'Select',
    colProps: { span: 2 },
    componentProps: {
      placeholder: '订单类型',
      options: [
        {
          label: '收单',
          value: true,
          key: 1,
        },
        {
          label: '卖单',
          value: false,
          key: 2,
        }
      ]
    }
  },
  {
    field: 'createTime',
    component: 'RangePicker',
    componentProps: {
      format: 'YYYY-MM-DD HH:mm:ss',
      placeholder: ['开始时间', '结束时间'],
      showTime: { format: 'HH:mm:ss' },
    },
    colProps: {
      span: 5,
    },
  },
]

