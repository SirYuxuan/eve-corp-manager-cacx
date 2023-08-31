<template>
  <PageWrapper dense contentFullHeight contentClass="flex">
    <BasicTable @register="registerTable"  :searchInfo="searchInfo">
      <template #toolbar>
        <a-button v-auth="['srpRules:add']" type="primary" @click="handleCreate"
                  preIcon="ant-design:plus-outlined">新增规则
        </a-button>
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'action'">
          <TableAction
            :actions="[
              {
                icon: 'clarity:note-edit-line',
                tooltip: '编辑规则',
                auth:'srpRules:edit',
                onClick: handleEdit.bind(null, record),
              },
              {
                icon: 'ant-design:delete-outlined',
                color: 'error',
                auth:'srpRules:del',
                tooltip: '删除此规则',
                popConfirm: {
                  title: '是否确认删除',
                  placement: 'left',
                  confirm: handleDelete.bind(null, record),
                },
              },
            ]"
          />
        </template>
        <template v-if="column.key === 'shipName'">
          <img class="table-head"
               :src="`https://images.evetech.net/Type/${record.shipId}_32.png`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.shipName }}</span>
        </template>
      </template>
    </BasicTable>
    <RoleModal @register="registerModal" @success="handleSuccess" />
  </PageWrapper>
</template>
<script lang="ts" setup>

import {  reactive } from 'vue'
import { BasicTable, TableAction, useTable } from '/@/components/Table'
import { PageWrapper } from '/@/components/Page'
import { useModal } from '/@/components/Modal'
import { columns, searchFormSchema } from './data'
import RoleModal from './FormModal.vue'
import { useGlobalStore } from '/@/store/modules/global'
import { useMessage } from '/@/hooks/web/useMessage'

import { list, del } from '/@/api/data/srpRules'

const { createMessage } = useMessage()
const [registerModal, { openModal }] = useModal()
const searchInfo = reactive<Recordable>({})



const [registerTable, { reload }] = useTable({
  title: '补损规则列表',
  api: list,
  rowKey: 'id',
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
  actionColumn: {
    width: 120,
    title: '操作',
    dataIndex: 'action',
  },
})

const globalStore = useGlobalStore()



function handleCreate() {
  openModal(true, {
    isUpdate: false,
  })
  globalStore.removeCheckFieldUserId()
}

function handleEdit(record: Recordable) {
  openModal(true, {
    record,
    isUpdate: true,
  })
  globalStore.setCheckFieldUserId(record['id'])
}

function handleDelete(record: Recordable) {
  del([record.id]).then(() => {
    createMessage.success('删除成功！')
    reload()
  })
}

function handleSuccess() {
  // 由于角色无法刷新，直接刷新整个表格
  reload()
}
</script>

<style lang="less" scoped>
.role-span {
  font-size: 14px;
}

.distribution {
  float: right;
}
</style>
