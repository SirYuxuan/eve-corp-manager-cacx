import { BasicColumn, FormSchema } from '/@/components/Table'
import { toThousands } from '/@/utils'


export const columns: BasicColumn[] = [
  {
    title: '角色名',
    dataIndex: 'characterName',
    width: 120,
    align: 'left'
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
    title: 'ESI状态',
    dataIndex: 'esi',
    width: 120,
  },
  {
    title: 'ISK',
    dataIndex: 'isk',
    width: 120,
    format(str){
      return toThousands(str) + ' ISK'
    }
  },
  {
    title: '技能点数',
    dataIndex: 'skill',
    width: 120,
    format(str){
      return toThousands(str) + ' SP'
    }
  },
  {
    title: '主角色',
    dataIndex: 'isMain',
    width: 120,
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

