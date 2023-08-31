import { BasicColumn, FormSchema } from '/@/components/Table'
import { Tag } from 'ant-design-vue'

export const columns: BasicColumn[] = [
  {
    title: '角色名',
    dataIndex: 'characterName',
    width: 120,
    align: 'left'
  },
  {
    title: '所属用户',
    dataIndex: 'nickName',
    width: 120,
  },
  {
    title: '上次登录时间',
    dataIndex: 'lastLoginTime',
    width: 120,
  },
  {
    sorter: true,
    title: '未上线天数',
    dataIndex: 'notLoginDay',
    width: 80,
  },
  {
    title: '军团系统',
    dataIndex: 'corpSystem',
    width: 100,
    customRender({ record }) {
      return <Tag color={record.corpSystem ? 'green':'red'}>{record.corpSystem ? '注册' : '未注册'}</Tag>
    }
  },
  {
    title: '联盟系统',
    dataIndex: 'seatSystem',
    width: 100,
    customRender({ record }) {
      return <Tag color={record.seatSystem ? 'green':'red'}>{record.seatSystem ? '注册' : '未注册'}</Tag>
    }
  },
]

export const searchFormSchema: FormSchema[] = [
  {
    field: 'blurry',
    component: 'Input',
    colProps: {span: 4},
    componentProps: {
      placeholder: '模糊搜索',
    },
  },
  {
    field: 'day',
    component: 'InputNumber',
    colProps: { span: 3 },
    componentProps: {
      placeholder: '天数内上线',
    },
    defaultValue: 30
  },
  {
    field: 'corpSystem',
    component: 'CheckboxGroup',
    colProps: { span: 4 },
    label: '军团系统',
    labelWidth: 80,
    componentProps: {
      placeholder: '',
      options: [
        { label: '注册', value: true },
        { label: '未注册', value: false },
      ]
    },
  },
  {
    field: 'seatSystem',
    component: 'CheckboxGroup',
    colProps: { span: 4 },
    label: '联盟系统',
    labelWidth: 80,
    componentProps: {
      placeholder: '',
      options: [
        { label: '注册', value: true },
        { label: '未注册', value: false },
      ]
    },
  },

]

