import { BasicColumn, FormSchema } from '/@/components/Table'


export const columns: BasicColumn[] = [
  {
    title: '昵称',
    dataIndex: 'nickName',
    width: 120,
  },
  {
    title: 'QQ',
    dataIndex: 'qq',
    width: 120,
  },
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
    width: 120
  },
  {
    sorter: true,
    title: '已使用LP',
    dataIndex: 'lpUse',
    width: 120
  },
  {
    sorter: true,
    title: '剩余LP',
    dataIndex: 'lpNow',
    width: 120
  },
]

export const searchFormSchema: FormSchema[] = [
  {
    field: 'blurry',
    component: 'Input',
    colProps: { span: 6 },
    componentProps: {
      placeholder: '输入角色名/昵称/QQ/军团/联盟搜索',
    }
  },
]

export const crudFormSchema: FormSchema[] = [
  {
    field: 'lpUsers',
    label: '发放名单',
    component: 'InputTextArea',
    required: true,
    componentProps: {
      rows: 8
    }
  },
  {
    field: 'lp',
    label: '发放数量',
    component: 'InputNumber',
    required: true,
    defaultValue: 1,
    componentProps: {
      rows: 8
    }
  },
  {
    field: 'where',
    label: '发放原因',
    component: 'InputTextArea',
    componentProps: {
      rows: 3
    }

  },
  {
    field: 'corp',
    label: '军团成员',
    component: 'RadioGroup',
    helpMessage: '选择后,系统中注册的非主军团成员无法获得LP',
    componentProps: {
      options: [
        { label: '是', value: true },
        { label: '否', value: false },
      ]
    },
    defaultValue: true

  },
]
