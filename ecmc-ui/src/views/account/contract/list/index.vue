<template>
  <PageWrapper dense contentFullHeight contentClass="flex">
    <BasicTable class="hide-radio" @register="registerTable" @row-click="handleRowClick"
                @selection-change="handleSelectionChange"  @query-reset-func="restFun"
                :searchInfo="searchInfo">
      <template #expandedRowRender="{ record }">
        <p style="margin: 0">
          <Descriptions :column="2" bordered size="small">
            <DescriptionsItem label="标题">{{record.title}}</DescriptionsItem>

            <DescriptionsItem label="所属角色">
              <img class="table-head"
                   :src="`https://images.evetech.net/characters/${record.characterId}/portrait?size=32`"
                   alt=""/>
              <span style="margin-left: 10px">{{ record.characterName }}</span>
            </DescriptionsItem>
            <DescriptionsItem label="接受时间">{{record.dateAccepted}}</DescriptionsItem>
            <DescriptionsItem label="地点">{{record.startLocationName}}</DescriptionsItem>
          </Descriptions>
        </p>
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'characterName'">
          <img class="table-head"
               :src="`https://images.evetech.net/characters/${record.characterId}/portrait?size=32`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.characterName }}</span>
        </template>
        <template v-if="column.key === 'issuerName'">
          <img class="table-head"
               :src="`https://images.evetech.net/characters/${record.issuerId}/portrait?size=32`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.issuerName }}</span>
        </template>
        <template v-if="column.key === 'acceptorName'">
          <img v-if="record.acceptorType === 'character'" class="table-head"
               :src="`https://images.evetech.net/characters/${record.acceptorId}/portrait?size=32`"
               alt=""/>
          <img v-if="record.acceptorType === 'corporation'" class="table-head"
               :src="`https://images.evetech.net/corporations/${record.acceptorId}/logo?size=32`"
               alt=""/>
          <img v-if="record.acceptorType === 'inventory_type'" class="table-head"
               :src="`https://images.evetech.net/inventory_type/${record.acceptorId}/portrait?size=32`"
               alt=""/>
          <img v-if="record.acceptorType === 'faction'" class="table-head"
               :src="`https://images.evetech.net/faction/${record.acceptorId}/portrait?size=32`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.acceptorName }}</span>
        </template>
        <template v-if="column.key === 'assigneeName'">
          <img v-if="record.assigneeType === 'character'" class="table-head"
               :src="`https://images.evetech.net/characters/${record.assigneeId}/portrait?size=32`"
               alt=""/>
          <img v-if="record.assigneeType === 'corporation'" class="table-head"
               :src="`https://images.evetech.net/corporations/${record.assigneeId}/logo?size=32`"
               alt=""/>
          <img v-if="record.assigneeType === 'inventory_type'" class="table-head"
               :src="`https://images.evetech.net/inventory_type/${record.assigneeId}/portrait?size=32`"
               alt=""/>
          <img v-if="record.assigneeType === 'faction'" class="table-head"
               :src="`https://images.evetech.net/faction/${record.assigneeId}/portrait?size=32`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.assigneeName }}</span>
        </template>
      </template>
    </BasicTable>
  </PageWrapper>
</template>
<script lang="ts" setup>
import { reactive } from 'vue'
import { BasicTable, useTable } from '/@/components/Table'
import { list } from '/@/api/account/accountContract'
import { PageWrapper } from '/@/components/Page'
import { columns, searchFormSchema } from './data'
import { Descriptions } from 'ant-design-vue'
import { ContractAvailability } from '/@/utils/esi'
const DescriptionsItem = Descriptions.Item

const emit = defineEmits(['contract-change'])
const searchInfo = reactive<Recordable>({})
const [registerTable, { getSelectRowKeys, setSelectedRowKeys, getSelectRows }] = useTable({
  title: '合同列表',
  api: list,
  rowKey: 'id',
  columns,
  rowSelection: {
    type: 'radio',
    columnWidth: 0
  },
  formConfig: {
    labelWidth: 120,
    schemas: searchFormSchema,
    autoSubmitOnEnter: true,
  },
  showIndexColumn: false,
  useSearchForm: true,
  showTableSetting: true,
  bordered: true,
  clickToRowSelect: true,
  afterFetch: handleAfterFetch,
})


// 表格行点击事件
function handleRowClick(record: Recordable) {
  setSelectedRowKeys([record.id])
  emitContractChange()
}

// 表格行选中事件
function handleSelectionChange() {
  emitContractChange()
}

// 角色变化事件
function emitContractChange() {
  const selectedKeys = getSelectRowKeys()
  emit('contract-change', selectedKeys.length > 0 ? getSelectRows()[0] : '')
}

// 表格请求之后，对返回值进行处理
function handleAfterFetch() {
  setSelectedRowKeys([])
  emitContractChange()
}
function restFun(){
  setSelectedRowKeys([])
  emitContractChange()
}

</script>

<style>

</style>
