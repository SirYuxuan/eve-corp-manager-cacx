import { BasicColumn, FormSchema } from '/@/components/Table'
import { Tag } from 'ant-design-vue'
import { listAccount } from '/@/api/account/userAccount'

export const columns: BasicColumn[] = [
  {
    title: '角色名',
    dataIndex: 'characterName',
    width: 130,
  },
  {
    title: '连坐用户',
    dataIndex: 'isFull',
    width: 100,
    customRender({ record }) {
      return <Tag color={record.isFull ? 'green' : 'blue'}>{record.isFull ? '连坐' : '不连坐'}</Tag>
    }
  },
  {
    title: '开始时间',
    dataIndex: 'startTime',
    width: 150,
  },
  {
    title: '结束时间',
    dataIndex: 'endTime',
    width: 150,
  },
  {
    title: '备注',
    dataIndex: 'remark',
    width: 150,
  },
  {
    title: '创建人',
    dataIndex: 'createBy',
    width: 100,
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
    width: 150,
  },
]

export const searchFormSchema: FormSchema[] = [
  {
    field: 'blurry',
    component: 'Input',
    colProps: { span: 4 },
    componentProps: {
      placeholder: '模糊搜索',
    }
  }
]

export const crudFormSchema: FormSchema[] = [
  {
    field: 'characterId',
    label: '角色名',
    component: 'SearchApiSelect',
    required: true,
    componentProps() {
      return {
        showSearch: true,
        showArrow: false,
        filterOption: false,
        placeholder: '请输入角色选择',
        api: listAccount,
        labelField: 'characterName',
        valueField: 'characterId',
      }
    }
  },
  {
    field: 'isFull',
    label: '是否连坐',
    component: 'Checkbox',
    helpMessage: '勾选后此角色对应的用户下所有角色均无法提交',
    componentProps: {
      options: [
        { label: '连坐', value: true },
        { label: '不连坐', value: false }
      ]
    },
    defaultValue: false
  },
  {
    field: 'startTime',
    label: '开始时间',
    component: 'DatePicker',
    required: true,
    componentProps: {
      showTime: true
    }
  },
  {
    field: 'endTime',
    label: '结束时间',
    component: 'DatePicker',
    required: true,
    componentProps: {
      showTime: true
    }
  },
  {
    field: 'remark',
    label: '备注',
    component: 'InputTextArea',
    componentProps: {
      rows: 5
    },
    required: true
  }
]
