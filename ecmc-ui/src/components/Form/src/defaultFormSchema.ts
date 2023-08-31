import { FormSchema } from '/@/components/Form'

export const defaultSearchFormSchema: FormSchema[] = [
  {
    field: 'blurry',
    component: 'Input',
    colProps: { span: 3 },
    componentProps: {
      placeholder: '模糊搜索',
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
