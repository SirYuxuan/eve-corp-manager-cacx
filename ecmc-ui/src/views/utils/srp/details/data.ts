import { BasicColumn, FormSchema } from '/@/components/Table'
import { toThousands } from '/@/utils'


export const columns: BasicColumn[] = [
  {
    title: '名称',
    dataIndex: 'name',
    width: 200,
    align: 'left'
  },
  {
    title: '数量',
    dataIndex: 'num',
    width: 80,
  },
  {
    title: '掉落',
    dataIndex: 'type',
    width: 80,
  },
  {
    title: '价格',
    dataIndex: 'price',
    width: 120,
    align: 'left',
    format(str){
      return toThousands(str) + ' ISK'
    }
  }
]

export const searchFormSchema: FormSchema[] = [
  {
    field: 'blurry',
    component: 'Input',
    colProps: { span: 6 },
    componentProps: {
      placeholder: '输入角色名/军团/联盟搜索',
    }
  },
]

