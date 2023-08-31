<template>
  <PageWrapper dense contentFullHeight contentClass="flex">
    <BasicTable @register="registerTable" :searchInfo="searchInfo">
      <template #toolbar>
        <a-button type="primary" @click="handleCreate" preIcon="ant-design:plus-outlined">新增配置
        </a-button>
        <a-button type="warning" @click="handleDelete(null)"
                  :disabled="getSelectRowKeys().length === 0" :loading="showDelLoading" preIcon="ant-design:delete-outlined">
          删除
        </a-button>
        <a-button type="success" @click="handleRefreshCache" :loading="showRefreshLoading" preIcon="ant-design:redo-outlined">刷新缓存
        </a-button>
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'action'">
          <TableAction
            :actions="[
              {
                icon: 'clarity:note-edit-line',
                tooltip: '编辑配置',
                onClick: handleEdit.bind(null, record),
              },
               {
                icon: 'ant-design:delete-outlined',
                color: 'error',
                auth:'user:del',
                tooltip: '删除配置',
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
import { del, list, refresh } from '/@/api/sys/config'
import { PageWrapper } from '/@/components/Page'
import { useModal } from '/@/components/Modal'
import { columns } from './data'
import FormModal from './FormModal.vue'
import { useMessage } from '/@/hooks/web/useMessage'
import { defaultSearchFormSchema } from '/@/components/Form/src/defaultFormSchema'

const { createMessage, createConfirm } = useMessage()
const [registerModal, { openModal }] = useModal()
const searchInfo = reactive<Recordable>({})
const showRefreshLoading = ref(false)
const showDelLoading = ref(false)
const [registerTable, { reload, getSelectRowKeys }] = useTable({
  title: '配置列表',
  api: list,
  rowKey: 'id',
  columns,
  formConfig: {
    labelWidth: 120,
    schemas: defaultSearchFormSchema,
    autoSubmitOnEnter: true,
  },
  showIndexColumn: false,
  useSearchForm: true,
  showTableSetting: true,
  bordered: true,
  rowSelection: {
    type: 'checkbox'
  },
  actionColumn: {
    width: 120,
    title: '操作',
    dataIndex: 'action',
  },
})

function handleRefreshCache(){

  createConfirm({
    iconType: 'warning',
    title: '提示',
    content: '确定刷新系统所有缓存吗？',
    onOk: async () => {
      showRefreshLoading.value = true
      await refresh()
      createMessage.success('系统所有缓存刷新成功')
      showRefreshLoading.value = false
    },
    onCancel(){
      showRefreshLoading.value = false
    }
  })
}
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
  }).finally(() => showDelLoading.value = false)
}

function handleCreate() {
  openModal(true, {
    isUpdate: false,
  })
}

function handleEdit(record: Recordable) {
  openModal(true, {
    record,
    isUpdate: true,
  })
}

function handleSuccess() {
  reload()
}


</script>
