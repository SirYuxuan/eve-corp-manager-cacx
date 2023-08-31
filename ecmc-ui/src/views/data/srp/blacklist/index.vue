<template>
  <PageWrapper dense contentFullHeight contentClass="flex">
    <BasicTable @register="registerTable"  :searchInfo="searchInfo">
      <template #toolbar>
        <a-button v-auth="['srpBlacklist:add']" type="primary" @click="handleCreate"
                  preIcon="ant-design:plus-outlined">新增黑名单
        </a-button>
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'action'">
          <TableAction
            :actions="[
              {
                icon: 'clarity:note-edit-line',
                tooltip: '编辑黑名单',
                auth:'srpBlacklist:edit',
                onClick: handleEdit.bind(null, record),
              },
              {
                icon: 'ant-design:delete-outlined',
                color: 'error',
                auth:'srpBlacklist:del',
                tooltip: '删除此黑名单',
                popConfirm: {
                  title: '是否确认删除',
                  placement: 'left',
                  confirm: handleDelete.bind(null, record),
                },
              },
            ]"
          />
        </template>
        <template v-if="column.key === 'characterName'">
          <img class="table-head"
               :src="`https://images.evetech.net/characters/${record.characterId}/portrait?size=32`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.name }}</span>
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

import { list, del } from '/@/api/data/srpBlacklist'

const { createMessage } = useMessage()
const [registerModal, { openModal }] = useModal()
const searchInfo = reactive<Recordable>({})



const [registerTable, { reload }] = useTable({
  title: '补损黑名单列表',
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
