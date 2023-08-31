import { BasicColumn, FormSchema } from '/@/components/Table'
import { toThousands } from '/@/utils'
import { Tag } from 'ant-design-vue'
import { refData } from '/@/utils/refData'

export const columns: BasicColumn[] = [
  {
    sorter: true,
    title: '第一方',
    dataIndex: 'firstPartyName',
    width: 150,
  },
  {
    sorter: true,
    title: '第三方',
    dataIndex: 'secondPartyName',
    width: 150,
  },
  {
    sorter: true,
    title: '交易类型',
    dataIndex: 'refType',
    customRender({ record }) {
      return <Tag color={'green'}>{refData[record.refType]}</Tag>
    },
    width: 90,
  }
  ,
  {
    sorter: true,
    title: '交易金额',
    dataIndex: 'amount',
    width: 120,
    format(str) {
      return toThousands(str) + ' ISK'
    }
  },
  {
    sorter: true,
    title: '余额',
    dataIndex: 'balance',
    width: 150,
    format(str) {
      return toThousands(str) + ' ISK'
    }
  },
  {
    sorter: true,
    title: '交易时间',
    dataIndex: 'date',
    width: 140,
  },
  {
    sorter: true,
    title: '纳税',
    dataIndex: 'tax',
    width: 120,
    format(str) {
      return toThousands(str || '0') + ' ISK'
    }
  },
]
const options:object[] = []
Object.keys(refData).forEach((key) => {
  options.push({
    label: refData[key],
    value: key
  })
})


export const searchFormSchema: FormSchema[] = [
  {
    field: 'firstPartyName',
    component: 'Input',
    colProps: { span: 3 },
    componentProps: {
      placeholder: '第一方',
    }
  },
  {
    field: 'secondPartyName',
    component: 'Input',
    colProps: { span: 3 },
    componentProps: {
      placeholder: '第三方',
    }
  },
  {
    field: 'refType',
    component: 'Select',
    colProps: { span: 3 },
    componentProps: {
      placeholder: '交易类型',
      options: options
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

