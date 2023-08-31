import { BasicColumn, FormSchema } from '/@/components/Table'
import { Tag } from 'ant-design-vue'
import { toThousands } from '/@/utils'

export const columns: BasicColumn[] = [
  {
    title: '角色名称',
    dataIndex: 'characterName',
    width: 150,
  },
  {
    title: '舰船名称',
    dataIndex: 'shipName',
    width: 120,
  },
  {
    title: 'KMID',
    dataIndex: 'killMailId',
    width: 90,
    customRender({ record }){
      return <a target={'_blank'} href={'https://zkillboard.com/kill/'+record.accountKillMail.killMailId}>{record.accountKillMail.killMailId}</a>
    }
  },
  {
    title: '价值',
    width: 150,
    align: 'left',
    customRender({ record }){
      return toThousands(record.accountKillMail.damageTaken) + ' ISK'
    }
  },
  {
    title: '损失地点',
    dataIndex: 'solarSystemName',
    width: 100,
    customRender({ record }){
      return record.accountKillMail.solarSystemName
    }
  },
  {
    title: '损失时间',
    dataIndex: 'killMailTime',
    width: 150,
    customRender({ record }){
      return record.accountKillMail.killMailTime
    }
  },
  {
    title: '集结信息',
    dataIndex: 'content',
    width: 150,
  },
  {
    title: '状态',
    dataIndex: 'lpNow',
    width: 80,
    customRender({ record }) {
      return <Tag
        color={record.status === 2 ? 'green' : (record.status === 1 ? 'yellow' : 'red')}>{record.status === 1 ? '等待' : (record.status === 2 ? '通过' : '拒绝')}</Tag>
    }
  },
  {
    title: '提交人',
    dataIndex: 'createBy',
    width: 100,
  },
  {
    title: '提交时间',
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
  },
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
    field: 'spContent',
    label: '审批备注',
    component: 'InputTextArea',
    componentProps: {
      rows: 5
    }
  },
]
