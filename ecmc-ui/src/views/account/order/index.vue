<template>
  <PageWrapper dense contentFullHeight contentClass="flex">
    <BasicTable @register="registerTable"  :searchInfo="searchInfo">
        <template #bodyCell="{ column, record }">

        <template v-if="column.key === 'typeName'">
          <img class="table-head"
               :src="`https://images.evetech.net/Type/${record.typeId}_32.png`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.typeName }}</span>
        </template>
        <template v-if="column.key === 'characterName'">
           <img  class="table-head"
                :src="`https://images.evetech.net/characters/${record.characterId}/portrait?size=32`"
                alt=""/>
          <span style="margin-left: 10px">{{ record.characterName }}</span>
        </template>
        <template v-if="column.key === 'clientName'">
           <img v-if="record.clientType === 'character'" class="table-head"
                :src="`https://images.evetech.net/characters/${record.clientId}/portrait?size=32`"
                alt=""/>
          <img v-if="record.clientType === 'corporation'" class="table-head"
               :src="`https://images.evetech.net/corporations/${record.clientId}/logo?size=32`"
               alt=""/>
          <img v-if="record.clientType === 'inventory_type'" class="table-head"
               :src="`https://images.evetech.net/inventory_type/${record.clientId}/portrait?size=32`"
               alt=""/>
          <img v-if="record.clientType === 'faction'" class="table-head"
               :src="`https://images.evetech.net/faction/${record.clientId}/portrait?size=32`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.clientName }}</span>
        </template>
      </template>
    </BasicTable>
  </PageWrapper>
</template>
<script lang="ts" setup>

import {  reactive } from 'vue'
import { BasicTable, useTable } from '/@/components/Table'
import { PageWrapper } from '/@/components/Page'
import { columns, searchFormSchema } from './data'

import { list } from '/@/api/account/accountOrder'

const searchInfo = reactive<Recordable>({})



const [registerTable] = useTable({
  title: '市场订单',
  api: list,
  rowKey: 'id',
  showIndexColumn: false,
  clickToRowSelect: true,
  striped: false,
  columns,
  formConfig: {
    labelWidth: 120,
    schemas: searchFormSchema,
    autoSubmitOnEnter: true,
  },
  useSearchForm: true,
  showTableSetting: true,
  bordered: true,

})






</script>

<style lang="less" scoped>
.role-span {
  font-size: 14px;
}

.distribution {
  float: right;
}
</style>
