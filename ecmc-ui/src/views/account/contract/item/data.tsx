import { BasicColumn } from '/@/components/Table'
import { toThousands } from '/@/utils'
import { Tag } from 'ant-design-vue'


export const columns: BasicColumn[] = [
  {
    title: '物品名称',
    dataIndex: 'typeName',
    width: 150,
  },
  {
    title: '数量',
    dataIndex: 'quantity',
    width: 80,
    format(str) {
      return toThousands(str)
    }
  },
  {
    title: '合同内容',
    dataIndex: 'isIncluded',
    width: 80,
    customRender({ record }) {
      return <Tag
        color={record.isIncluded ? 'green' : 'red'}>{record.isIncluded ? '是' : '需求'}</Tag>
    }
  },


]

