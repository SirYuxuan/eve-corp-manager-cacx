import { BasicColumn, FormSchema } from '/@/components/Table'

export const columns: BasicColumn[] = [
  {
    title: '角色名称',
    dataIndex: 'name',
    width: 120,
  },
  {
    title: '描述',
    dataIndex: 'remark',
    width: 120,
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
    width: 180,
  },
]

export const searchFormSchema: FormSchema[] = [
  {
    field: 'blurry',
    component: 'Input',
    colProps: { span: 4 },
    componentProps: {
      placeholder: '输入名称备注搜索',
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

export const crudFormSchema: FormSchema[] = [
  {
    field: 'name',
    label: '角色名',
    component: 'Input',
    required: true,
  },
  {
    field: 'remark',
    label: '备注',
    component: 'InputTextArea',
    componentProps: {
      rows: 7,
    },
  },
]
