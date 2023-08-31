<template>
  <PageWrapper dense contentFullHeight contentClass="flex">
    <BasicTable @register="registerTable"  :searchInfo="searchInfo">
      <template #expandedRowRender="{ record }">
        <p style="margin: 0">
          <Descriptions :column="1" bordered size="small">
            <DescriptionsItem label="描述">{{record.description}}</DescriptionsItem>
            <DescriptionsItem label="原因">{{record.reason}}</DescriptionsItem>
          </Descriptions>
        </p>
      </template>
      <template #bodyCell="{ column, record }">

        <template v-if="column.key === 'characterName'">
          <img class="table-head"
               :src="`https://images.evetech.net/characters/${record.firstPartyId}/portrait?size=32`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.characterName }}</span>
        </template>
        <template v-if="column.key === 'firstPartyName'">
           <img v-if="record.firstPartyType === 'character'" class="table-head"
                :src="`https://images.evetech.net/characters/${record.firstPartyId}/portrait?size=32`"
                alt=""/>
          <img v-if="record.firstPartyType === 'corporation'" class="table-head"
               :src="`https://images.evetech.net/corporations/${record.firstPartyId}/logo?size=32`"
               alt=""/>
          <img v-if="record.firstPartyType === 'inventory_type'" class="table-head"
               :src="`https://images.evetech.net/inventory_type/${record.firstPartyId}/portrait?size=32`"
               alt=""/>
          <img v-if="record.firstPartyType === 'faction'" class="table-head"
               :src="`https://images.evetech.net/faction/${record.first_party_id}/portrait?size=32`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.firstPartyName }}</span>
        </template>
        <template v-if="column.key === 'secondPartyName'">
           <img v-if="record.secondPartyType === 'character'" class="table-head"
                :src="`https://images.evetech.net/characters/${record.secondPartyId}/portrait?size=32`"
                alt=""/>
          <img v-if="record.secondPartyType === 'corporation'" class="table-head"
               :src="`https://images.evetech.net/corporations/${record.secondPartyId}/logo?size=32`"
               alt=""/>
          <img v-if="record.secondPartyType === 'inventory_type'" class="table-head"
               :src="`https://images.evetech.net/inventory_type/${record.secondPartyId}/portrait?size=32`"
               alt=""/>
          <img v-if="record.secondPartyType === 'faction'" class="table-head"
               :src="`https://images.evetech.net/faction/${record.secondPartyId}/portrait?size=32`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.secondPartyName }}</span>
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

import { list } from '/@/api/account/accountWallet'
import { Descriptions } from 'ant-design-vue'
const searchInfo = reactive<Recordable>({})
const DescriptionsItem = Descriptions.Item


const [registerTable] = useTable({
  title: '钱包流水',
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
