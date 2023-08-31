import { BasicColumn, FormSchema, TableImg } from '/@/components/Table'
import { uploadApi } from '/@/api/sys/upload'
import { Tag } from 'ant-design-vue'

export const columns: BasicColumn[] = [
  {
    title: '商品名称',
    dataIndex: 'title',
    width: 100,
  },
  {
    title: '商品图片',
    dataIndex: 'pics',
    width: 100,
    customRender: ({ record }) => {
      return <TableImg size={60} simpleShow={true} imgList={record.pics.split(',')}/>
    },
  },
  {
    title: '商品类型',
    dataIndex: 'type',
    width: 100,
    customRender: ({ record }) => {
      return <Tag color="blue">{record.type}</Tag>
    },
  },
  {
    sorter: true,
    title: 'LP数量',
    dataIndex: 'lp',
    width: 120,
  },
  {
    sorter: true,
    title: '库存',
    dataIndex: 'num',
    width: 120,
  },
  {
    sorter: true,
    title: '销量',
    dataIndex: 'shopNum',
    width: 120,
  }, {
    title: '剩余',
    dataIndex: 'nowNum',
    width: 120,
    customRender: ({ record }) => {
      return record.num - record.shopNum
    },
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
  }
]

export const searchFormSchema: FormSchema[] = [
  {
    field: 'blurry',
    component: 'Input',
    colProps: { span: 6 },
    componentProps: {
      placeholder: '输入商品名称/类型搜索',
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
  }
]

export const crudFormSchema: FormSchema[] = [
  {
    field: 'title',
    label: '商品标题',
    component: 'Input',
    required: true,
  },
  {
    field: 'type',
    label: '商品类型',
    component: 'Input',
    required: true,
  },
  {
    field: 'lp',
    label: 'LP数量',
    component: 'InputNumber',
    required: true,
    defaultValue: 1,
  },
  {
    field: 'num',
    label: '库存',
    required: true,
    component: 'InputNumber',
    defaultValue: 999
  },
  {
    field: 'pics',
    label: '图片',
    component: 'Upload',
    rules: [
      {
        required: true,
        message: '请上传商品图片',
      }
    ],
    componentProps: {
      api: uploadApi,
      maxSize: 20,
      maxNumber: 10,
      name: 'files',
      accept: ['image/*']
    }
  },
]
