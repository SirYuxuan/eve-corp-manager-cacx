<template>
  <PageWrapper dense contentFullHeight contentClass="flex">
    <BasicTable @register="registerTable" :searchInfo="searchInfo">
      <template #toolbar>
        <a-button type="primary" @click="handleApproval(null)"
                  :disabled="getSelectRows().filter(item=>item.status === 1).length === 0"
                  preIcon="approval|svg">批量审批
        </a-button>
        <a-button type="warning" @click="handleDelete(null)"
                  :disabled="getSelectRows().filter(item=>item.status !== 1).length === 0"
                  :loading="showDelLoading"
                  preIcon="ant-design:delete-outlined">
          删除
        </a-button>
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'action'">
          <TableAction
            :actions="[
              {
                icon: 'approval|svg',
                disabled: record.status !== 1,
                tooltip: '审批LP商品订单',
                onClick: handleApproval.bind(null, record),
              },
               {
                icon: 'ant-design:delete-outlined',
                color: 'error',
                tooltip: '删除LP商品订单',
                disabled: record.status === 1,
                popConfirm: {
                  title: '是否确认删除',
                  placement: 'left',
                  confirm: handleDelete.bind(null, record),
                },
              },
            ]"
          />
        </template>
      </template>
    </BasicTable>
    <FormModal @register="registerModal" @success="handleSuccess"/>
  </PageWrapper>
</template>
<script lang="ts" setup>
import { reactive, ref } from 'vue'

import { BasicTable, TableAction, useTable } from '/@/components/Table'
import { del, list } from '/@/api/lp/goodsOrder'
import { PageWrapper } from '/@/components/Page'
import { useModal } from '/@/components/Modal'
import { columns, searchFormSchema } from './data'
import FormModal from './FormModal.vue'
import { useMessage } from '/@/hooks/web/useMessage'

const { createMessage, createConfirm } = useMessage()
const [registerModal, { openModal }] = useModal()
const searchInfo = reactive<Recordable>({})
const showDelLoading = ref(false)
const [registerTable, { reload, getSelectRowKeys, getSelectRows }] = useTable({
  title: '订单列表',
  api: list,
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
  clickToRowSelect: false,
  defSort: {
    order: 'status',
    orderBy: 'asc'
  },
  rowSelection: {
    type: 'checkbox'
  },
  actionColumn: {
    width: 120,
    title: '操作',
    dataIndex: 'action',
  },
})

function handleDelete(record: Recordable) {
  if (!record) {
    createConfirm({
      iconType: 'warning',
      title: '提示',
      content: '确认删除选中的数据吗？',
      onOk: async () => {
        showDelLoading.value = true
        doDel(record)
      }
    })
    return
  }
  showDelLoading.value = true
  doDel(record)
}

function doDel(record: Recordable) {
  del(record ? [record.id] : getSelectRowKeys()).then(() => {
    createMessage.success('删除成功！')
    reload()
  }).finally(() => showDelLoading.value = true)
}

function handleApproval(record: Recordable) {

  if (record) {
    record['ids'] = [record.id]
    record['orderList'] = (record.characterName + ` [${record.title}]`) as string
  } else {
    record = {
      orderList: getSelectRows().map(item => item.characterName + ` [${item.title}]`).join('<br>'),
      ids: getSelectRowKeys()
    }
  }
  openModal(true, {
    record,
    isUpdate: false,
  })
}


function handleSuccess() {
  reload()
}


</script>
