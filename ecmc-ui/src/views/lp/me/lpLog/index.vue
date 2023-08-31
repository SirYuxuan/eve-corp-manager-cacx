<template>
  <div style="margin-top: 16px">
    <BasicTable @register="registerTable">
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'source'">
          <Tag color="processing">
            {{ getSource(record.source) }}
          </Tag>
        </template>
        <template v-if="column.key === 'type'">
          <Tag :color="record.type === LpType.EXPENDITURE ? 'warning' : 'green'">
            {{ record.type === LpType.EXPENDITURE ? '支出' : '收入' }}
          </Tag>
        </template>
      </template>
    </BasicTable>
  </div>
</template>
<script lang="ts" setup>
import { Tag } from 'ant-design-vue'
import { nextTick, watch } from 'vue'
import { BasicTable, useTable } from '/@/components/Table'
import { columns } from './data'
import { listLpLogByAccountId } from '/@/api/lp/lp'
import { getSource, LpType } from './lpEnum'

const props = defineProps({
  accountId: { type: String },
})

const [registerTable, { reload }] = useTable({
  title: 'LP记录',
  titleHelpMessage: '默认查询全部角色',
  api: listLpLogByAccountId,
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
function handleBeforeFetch(params) {
  return { ...params, accountId: props.accountId  }
}

watch(
  () => props.accountId,
  () => {
    nextTick(() => {
      reload()
    })
  },
)
</script>
