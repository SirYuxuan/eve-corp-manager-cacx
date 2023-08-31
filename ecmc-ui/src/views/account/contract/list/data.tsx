import { BasicColumn, FormSchema } from '/@/components/Table'
import { toThousands } from '/@/utils'
import { Tag } from 'ant-design-vue'
import { ContractAvailability, ContractStatus, ContractType } from '/@/utils/esi'


export const columns: BasicColumn[] = [
  {
    title: '发起人',
    dataIndex: 'issuerName',
    width: 140,
  },
  {
    title: '接收人',
    dataIndex: 'assigneeName',
    width: 140,
  },
  {
    title: '地点',
    dataIndex: 'startLocationName',
    width: 140,
  },
  {
    sorter: true,
    title: '价格',
    dataIndex: 'price',
    width: 130,
    format(str){
      return toThousands(str) + ' ISK'
    }
  },
  {
    sorter: true,
    title: '体积',
    dataIndex: 'volume',
    width: 100,
    format(str){
      return toThousands(str)
    }
  },
  {
    sorter: true,
    title: '状态',
    dataIndex: 'status',
    width: 80,
    customRender({ record }){
      return <Tag color={'blue'}>{ContractStatus[record.status]}</Tag>
    }
  },
  {
    sorter: true,
    title: '类型',
    dataIndex: 'type',
    width: 80,
    customRender({ record }){
      return <Tag color={'blue'}>{ContractType[record.type]}</Tag>
    }
  },
  {
    sorter: true,
    title: '范围',
    dataIndex: 'availability',
    width: 70,
    customRender({ record }){
      return <Tag color={'blue'}>{ContractAvailability[record.availability]}</Tag>
    }
  },
  {
    sorter: true,
    title: '发起时间',
    dataIndex: 'dateIssued',
    width: 150,
  },

]

const statusOptions:object[] = []
Object.keys(ContractStatus).forEach((key) => {
  statusOptions.push({
    label: ContractStatus[key],
    value: key
  })
})
const typeOptions:object[] = []
Object.keys(ContractType).forEach((key) => {
  typeOptions.push({
    label: ContractType[key],
    value: key
  })
})


export const searchFormSchema: FormSchema[] = [
  {
    field: 'issuerName',
    component: 'Input',
    colProps: { span: 3 },
    componentProps: {
      placeholder: '发起人',
    }
  },
  {
    field: 'assigneeName',
    component: 'Input',
    colProps: { span: 3 },
    componentProps: {
      placeholder: '接收人',
    }
  },
  {
    field: 'startLocationName',
    component: 'Input',
    colProps: { span: 2 },
    componentProps: {
      placeholder: '地点',
    }
  },
  {
    field: 'status',
    component: 'Select',
    colProps: { span: 2 },
    componentProps: {
      placeholder: '状态',
      options: statusOptions
    }
  },
  {
    field: 'type',
    component: 'Select',
    colProps: { span: 3 },
    componentProps: {
      placeholder: '类型',
      options: typeOptions
    }
  },
  {
    field: 'dateIssued',
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

