import { BasicColumn } from '/@/components/Table'
import { Tag } from 'ant-design-vue'
import { toThousands } from '/@/utils'


export const columns: BasicColumn[] = [
  {
    title: 'ID',
    dataIndex: 'killMailId',
    width: 90,
    customRender({ record }){
      return <a target={'_blank'} href={'https://zkillboard.com/kill/'+record.accountKillMail.killMailId}>{record.accountKillMail.killMailId}</a>
    }
  },
  {
    title: '角色',
    dataIndex: 'characterName',
    width: 120,
    align: 'left'
  },
  {
    title: '船型',
    dataIndex: 'shipName',
    width: 120,
    align: 'left'
  },
  {
    title: '地点',
    dataIndex: 'lpTotal',
    width: 80,
    align: 'left',
    customRender({ record }){
      return record.accountKillMail.solarSystemName
    }
  },
  {
    title: '价值',
    dataIndex: 'lpUse',
    width: 100,
    align: 'left',
    customRender({ record }){
      return toThousands(record.accountKillMail.damageTaken) + ' ISK'
    }
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
]



