import { BasicColumn, FormSchema } from '/@/components/Table'


export const columns: BasicColumn[] = [
  {
    sorter: true,
    title: 'LP数量',
    dataIndex: 'lp',
    width: 70,
  },
  {
    sorter: true,
    title: '所属角色',
    dataIndex: 'characterName',
    width: 100,
  },
  {
    title: '来源',
    dataIndex: 'source',
    width: 80,
  },
  {
    title: '操作类型',
    dataIndex: 'type',
    width: 80,
  },
  {
    title: '备注',
    dataIndex: 'content',
    width: 120
  },
  {
    sorter: true,
    title: '发放时间',
    dataIndex: 'createTime',
    width: 140
  }
]

export const searchFormSchema: FormSchema[] = [
  {
    field: 'blurry',
    component: 'Input',
    colProps: { span: 6 },
    componentProps: {
      placeholder: '输入角色名/军团/联盟搜索',
    }
  },
]

