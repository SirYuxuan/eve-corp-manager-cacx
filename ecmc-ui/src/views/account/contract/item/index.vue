<template>
  <div style="margin-top: 16px">
    <BasicTable  @register="registerTable">
      <template #top>
       <div style="background: #fff" v-if="props.contractId?.type === 'courier'">
         <Descriptions :column="1" bordered size="small">
           <DescriptionsItem label="起点">{{ props.contractId?.startLocationName }}</DescriptionsItem>
           <DescriptionsItem label="终点">{{ props.contractId?.endLocationName }}</DescriptionsItem>
           <DescriptionsItem label="保证金">{{ toThousands(props.contractId?.collateral) }}</DescriptionsItem>
           <DescriptionsItem label="体积">{{ toThousands(props.contractId?.volume) }}</DescriptionsItem>
           <DescriptionsItem label="奖金">{{ toThousands(props.contractId?.reward) }}</DescriptionsItem>
           <DescriptionsItem label="完成天数">{{ props.contractId?.daysToComplete }}</DescriptionsItem>
           <DescriptionsItem label="发起时间">{{ props.contractId?.dateIssued }}</DescriptionsItem>
           <DescriptionsItem label="到期时间">{{ props.contractId?.dateExpired }}</DescriptionsItem>
           <DescriptionsItem label="完成时间">{{ props.contractId?.dateCompleted }}</DescriptionsItem>
         </Descriptions>
       </div>
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'typeName'">
          <img class="table-head"
               :src="`https://images.evetech.net/Type/${record.typeId}_32.png`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.typeName }}</span>
        </template>

      </template>
    </BasicTable>
  </div>
</template>
<script lang="ts" setup>
import { nextTick, watch } from 'vue'
import { BasicTable, useTable } from '/@/components/Table'
import { columns } from './data'
import { listContractItem } from '/@/api/account/accountContract'
import { Descriptions } from 'ant-design-vue'
import { toThousands } from '/@/utils/index'
const DescriptionsItem = Descriptions.Item
const props = defineProps({
  contractId: { type: Object },
})

const [registerTable, { reload }] = useTable({
  title: '合同内容',
  titleHelpMessage: '快递合同无法查看具体内容',
  api: listContractItem,
  rowKey: 'id',
  columns,
  immediate: false,
  showIndexColumn: false,
  useSearchForm: false,
  showTableSetting: true,
  bordered: true,
  beforeFetch: handleBeforeFetch,
})

// 表格请求之前，对参数进行处理
function handleBeforeFetch() {
  return props.contractId?.id || -1
}

watch(
  () => props.contractId,
  () => {
    nextTick(() => {
      reload()
    })
  },
)
</script>
