import { BasicColumn, FormSchema } from '/@/components/Table'

export const columns: BasicColumn[] = [
  {
    title: '配置标题',
    dataIndex: 'title',
    width: 150,
  },
  {
    title: '配置Key',
    dataIndex: 'name',
    width: 130,
  },
  {
    title: '配置Value',
    dataIndex: 'value',
    width: 130,
  },
  {
    title: '备注',
    dataIndex: 'remark',
    width: 300,
  },
  {
    sorter: true,
    title: '创建人',
    dataIndex: 'createBy',
    width: 120,
  },
  {
    sorter: true,
    title: '创建时间',
    dataIndex: 'createTime',
    width: 120,
  },
  {
    sorter: true,
    title: '更新人',
    dataIndex: 'updateBy',
    width: 120,
  },
  {
    sorter: true,
    title: '更新时间',
    dataIndex: 'updateTime',
    width: 120,
  }
]

export const crudFormSchema: FormSchema[] = [
  {
    field: 'title',
    label: '配置标题',
    component: 'Input',
    required: true,
  },
  {
    field: 'name',
    label: '配置Key',
    component: 'Input',
    required: true,
  }, {
    field: 'value',
    label: '配置Value',
    component: 'InputTextArea',
    required: true,
    componentProps: {
      row: 3
    }
  },
  {
    field: 'remark',
    label: '配置描述',
    component: 'InputTextArea',
    componentProps: {
      row: 5
    }
  }

]
