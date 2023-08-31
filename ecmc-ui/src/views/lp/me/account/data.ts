import { BasicColumn, FormSchema } from '/@/components/Table'


export const columns: BasicColumn[] = [
  {
    title: '角色名',
    dataIndex: 'characterName',
    width: 120,
  },
  {
    title: '军团',
    dataIndex: 'corpName',
    width: 120,
  },
  {
    title: '联盟',
    dataIndex: 'allianceName',
    width: 120,
  },
  {
    sorter: true,
    title: 'LP总数',
    dataIndex: 'lpTotal',
    width: 80
  },
  {
    sorter: true,
    title: '已使用LP',
    dataIndex: 'lpUse',
    width: 80
  },
  {
    sorter: true,
    title: '剩余LP',
    dataIndex: 'lpNow',
    width: 80
  },
]

export const searchFormSchema: FormSchema[] = [
  {
    field: 'blurry',
    component: 'Input',
    colProps: { span: 8 },
    componentProps: {
      placeholder: '输入角色名/军团/联盟搜索',
    }
  },
]

