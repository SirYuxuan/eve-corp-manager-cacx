<template>
  <div style="margin-top: 16px;">

    <BasicTable @register="registerTable">
      <template #top>
        <Card>
         <div v-if="kmData" style="margin-bottom: 10px">
           <img :src="'https://images.evetech.net/Type/'+kmData.shipTypeId+'_64.png'" alt="" style="display: block;float: left"/>
           <div style="float: left;margin-top: 3px;margin-left: 10px;">
             <Tag color="green">舰船: {{ kmData.shipTypeName }}，价格: {{  toThousands(kmData.damageTaken) }} ISK，地点: {{ kmData.solarSystemName }}</Tag>
             <br/>
             <Tag style="margin-top: 5px" color="green">时间: {{ kmData.kmTime }}，怪损: {{ kmData.isNpc ? '是' : '否' }}，角色: {{ kmData.characterName }}</Tag>
           </div>
         </div>
        </Card>
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'name'">
          <img class="table-head"
               :src="`https://images.evetech.net/Type/${record.typeId}_32.png`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.name }}</span>
        </template>
      </template>
    </BasicTable>
  </div>
</template>
<script lang="ts" setup>
import { nextTick, ref, watch } from 'vue'
import { BasicTable, useTable } from '/@/components/Table'
import { columns } from './data'
import { listKillMailItem } from '/@/api/account/userAccount'
import { Card, Tag } from 'ant-design-vue'
import { toThousands } from '/@/utils'
const props = defineProps({
  srpId: { type: String },
})
import { useLoading } from '/@/components/Loading'
const [openFullLoading, closeFullLoading] = useLoading({
  tip: '正在读取KM信息...',
})
const [registerTable, { reload, setTableData }] = useTable({
  title: '击毁报告详情',
  api: fetch,
  rowKey: 'id',
  columns,
  immediate: false,
  showIndexColumn: false,
  useSearchForm: false,
  showTableSetting: true,
  bordered: true,
  pagination: false
})
const kmData = ref()

async function fetch() {
  if (props.srpId) {
    openFullLoading()
    listKillMailItem(props.srpId).then(res => {
      setTableData(res.list)
      kmData.value = res.data
    }).finally(() => closeFullLoading())
  } else {
    kmData.value = null
  }

}


watch(
  () => props.srpId,
  () => {
    nextTick(() => {
      reload()
    })
  },
)
</script>
