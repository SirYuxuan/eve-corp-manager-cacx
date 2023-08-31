import { BasicColumn, FormSchema } from '/@/components/Table'
import { Tag } from 'ant-design-vue'
import { shipList } from '/@/api/data/sde'

export const columns: BasicColumn[] = [
  {
    title: '舰船名称',
    dataIndex: 'shipName',
    width: 200,
  },
  {
    title: '是否允许提交',
    dataIndex: 'noSrp',
    width: 120,
    customRender({ record }) {
      return <Tag color={record.notSrp ? 'red' : 'green'}>{record.notSrp ? '不允许' : '允许'}</Tag>
    }
  },
  {
    title: '是否允许怪损',
    dataIndex: 'isNpc',
    width: 120,
    customRender({ record }) {
      return <Tag color={record.isNpc ? 'green' : 'red'}>{record.isNpc ? '允许' : '不允许'}</Tag>
    }
  },
  {
    title: '加团天数',
    dataIndex: 'joinTime',
    width: 120,
    customRender({ record }) {
      return <Tag
        color={record.joinTime ? 'yellow' : 'blue'}>{record.joinTime ? record.joinTime : '未设置'}</Tag>
    }
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
    field: 'shipId',
    label: '舰船名称',
    component: 'SearchApiSelect',
    required: true,
    componentProps() {
      return {
        showSearch: true,
        showArrow: false,
        filterOption: false,
        placeholder: '请输入舰船选择',
        api: shipList,
        labelField: 'name',
        valueField: 'id',
      }
    }
  },
  {
    field: 'notSrp',
    label: '禁止提交',
    component: 'Checkbox',
    helpMessage: '勾选后用户无法提交此舰船进行补损',
    componentProps: {
      options: [
        { label: '无法提交此舰船', value: true },
        { label: '可以提交', value: false }
      ]
    },
    defaultValue: false
  },
  {
    field: 'isNpc',
    label: '支持怪损',
    component: 'Checkbox',
    helpMessage: '勾选后用户可以提交NPC击毁的损失',
    componentProps: {
      options: [
        { label: '支持', value: true },
        { label: '不支持', value: false }
      ]
    },
    defaultValue: false
  },
  {
    field: 'joinTime',
    label: '加团天数',
    component: 'InputNumber',
  },
]
