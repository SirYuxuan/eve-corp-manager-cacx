import { BasicColumn, FormSchema } from '/@/components/Table'
import { Tag } from 'ant-design-vue'
import { defaultSearchFormSchema } from '/@/components/Form/src/defaultFormSchema'

export const columns: BasicColumn[] = [
  {
    title: '商品名称',
    dataIndex: 'title',
    width: 100,
  },
  {
    title: '兑换角色',
    dataIndex: 'characterName',
    width: 120,
  },
  {
    title: '状态',
    dataIndex: 'status',
    width: 100,
    customRender({ record }) {
      return <Tag
        color={record.status === 2 ? 'green' : (record.status === 1 ? 'yellow' : 'red')}>{record.status === 1 ? '等待' : (record.status === 2 ? '通过' : '拒绝')}</Tag>
    }
  },
  {
    title: '数量',
    dataIndex: 'num',
    width: 80,
  },
  {
    title: '备注',
    dataIndex: 'content',
    width: 120,
  },
  {
    sorter: true,
    title: '申请人',
    dataIndex: 'createBy',
    width: 120,
  },
  {
    sorter: true,
    title: '申请时间',
    dataIndex: 'createTime',
    width: 120,
  },
  {
    title: '审批备注',
    dataIndex: 'examineContent',
    width: 120,
  }
  ,
  {
    sorter: true,
    title: '审批人',
    dataIndex: 'updateBy',
    width: 120,
  }
  ,
  {
    sorter: true,
    title: '审批时间',
    dataIndex: 'updateTime',
    width: 120,
  }
]

export const searchFormSchema: FormSchema[] = [
  ...defaultSearchFormSchema,
  {
    field: 'status',
    component: 'Select',
    colProps: { span: 2 },
    defaultValue: 1,
    componentProps: {
      placeholder: '审批状态',
      options: [
        {
          label: '等待',
          value: 1,
          key: 1,
        },
        {
          label: '通过',
          value: 2,
          key: 2,
        },
        {
          label: '拒绝',
          value: 3,
          key: 3,
        },
      ]
    }
  },
]

export const crudFormSchema: FormSchema[] = [
  {
    field: 'status',
    label: '审批结果',
    component: 'RadioButtonGroup',
    required: true,
    componentProps: {
      options: [
        { label: '通过', value: true },
        { label: '拒绝', value: false },
      ]
    },
    defaultValue: true
  },
  {
    field: 'orderList',
    label: '审批内容',
    component: 'ShowContext',
    componentProps: {
      row: 5,
    }
  },
  {
    field: 'spContent',
    label: '审批意见',
    component: 'InputTextArea',
    componentProps: {
      row: 5
    }
  }
]
