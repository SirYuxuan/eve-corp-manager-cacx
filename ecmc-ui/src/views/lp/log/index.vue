<template>
  <PageWrapper dense contentFullHeight contentClass="flex">
    <BasicTable @register="registerTable" :searchInfo="searchInfo">
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'characterName'">
          <img class="table-head"
               :src="`https://images.evetech.net/characters/${record.userAccount.characterId}/portrait?size=32`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.userAccount.characterName }}</span>
        </template>
        <template v-if="column.key === 'corpName'">
          <img class="table-head"
               :src="`https://images.evetech.net/corporations/${record.userAccount.corpId}/logo?size=32`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.userAccount.corpName }}</span>
        </template>
        <template v-if="column.key === 'allianceName'">
          <img class="table-head"
               :src="`https://images.evetech.net/alliances/${record.userAccount.allianceId}/logo?size=32`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.userAccount.allianceName }}</span>
        </template>


      </template>
    </BasicTable>
  </PageWrapper>
</template>
<script lang="ts" setup>
import { reactive } from 'vue'
import { PageWrapper } from '/@/components/Page'
import {  listLpLog } from '/@/api/lp/lp'
import { columns, searchFormSchema } from './data'
import { BasicTable, useTable } from '/@/components/Table'
const searchInfo = reactive<Recordable>({})
const [registerTable] = useTable({
  title: 'LP发放记录',
  api: listLpLog,
  rowKey: 'id',
  columns,
  formConfig: {
    labelWidth: 120,
    schemas: searchFormSchema,
    autoSubmitOnEnter: true,
  },
  showIndexColumn: false,
  useSearchForm: true,
  showTableSetting: true,
  bordered: true,
})



</script>
