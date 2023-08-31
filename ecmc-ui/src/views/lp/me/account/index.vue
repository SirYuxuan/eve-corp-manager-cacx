<template>
  <PageWrapper dense contentFullHeight contentClass="flex">
    <BasicTable class="hide-radio" @register="registerTable" @row-click="handleRowClick"
                @selection-change="handleSelectionChange" @fetch-success="handlerSuccess" @query-reset-func="restFun"
                :searchInfo="searchInfo">
      <template #toolbar>
        <Alert type="success" style="height: 32px">
          <template #message>
            （仅统计本页面, 角色过多请手动选择分页数据） 总获取LP：{{ totalLP }}，剩余LP：{{
              nowLP
            }}，已使用LP：{{ useLP }}
          </template>
        </Alert>
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'characterName'">
          <img class="table-head"
               :src="`https://images.evetech.net/characters/${record.characterId}/portrait?size=32`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.characterName }}</span>
        </template>
        <template v-if="column.key === 'corpName'">
          <img class="table-head"
               :src="`https://images.evetech.net/corporations/${record.corpId}/logo?size=32`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.corpName }}</span>
        </template>
        <template v-if="column.key === 'allianceName'">
          <img class="table-head"
               :src="`https://images.evetech.net/alliances/${record.allianceId}/logo?size=32`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.allianceName }}</span>
        </template>
      </template>
    </BasicTable>
  </PageWrapper>
</template>
<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { Alert } from 'ant-design-vue'
import { BasicTable, useTable } from '/@/components/Table'
import { listAll } from '/@/api/account/userAccount'
import { PageWrapper } from '/@/components/Page'
import { columns, searchFormSchema } from './data'

const emit = defineEmits(['account-change'])
const searchInfo = reactive<Recordable>({})
const [registerTable, { getSelectRowKeys, setSelectedRowKeys }] = useTable({
  title: '角色列表',
  api: listAll,
  rowKey: 'id',
  columns,
  defSort: {
    order: 'ua.createTime',
    orderBy: 'desc'
  },
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
const totalLP = ref(0)
const nowLP = ref(0)
const useLP = ref(0)

function handlerSuccess(data) {
  totalLP.value = 0
  nowLP.value = 0
  useLP.value = 0
  data.items.forEach(item => {
    totalLP.value += item.lpTotal
    useLP.value += item.lpUse
    nowLP.value += item.lpNow
  })
}

// 表格行点击事件
function handleRowClick(record: Recordable) {
  setSelectedRowKeys([record.id])
  emitAccountChange()
}

// 表格行选中事件
function handleSelectionChange() {
  emitAccountChange()
}

// 角色变化事件
function emitAccountChange() {
  const selectedKeys = getSelectRowKeys()
  emit('account-change', selectedKeys.length > 0 ? selectedKeys[0] : '')
}

// 表格请求之后，对返回值进行处理
function handleAfterFetch() {
  setSelectedRowKeys([])
  emitAccountChange()
}
function restFun(){
  setSelectedRowKeys([])
  emitAccountChange()
}

</script>

<style>

</style>
