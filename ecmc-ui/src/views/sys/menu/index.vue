<template>
  <PageWrapper dense contentFullHeight contentClass="flex">
    <BasicTable @register="registerTable" :searchInfo="searchInfo">
      <template #toolbar>
        <a-button
          v-auth="['user:add']"
          type="primary"
          @click="handleCreate"
          preIcon="ant-design:plus-outlined"
        >新增菜单
        </a-button
        >
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'action'">
          <TableAction
            :actions="[
              {
                icon: 'clarity:note-edit-line',
                tooltip: '编辑菜单',
                auth: 'user:edit',
                onClick: handleEdit.bind(null, record),
              },
              {
                icon: 'ant-design:delete-outlined',
                color: 'error',
                auth: 'user:del',
                tooltip: '删除菜单',
                popConfirm: {
                  title: '是否确认删除',
                  placement: 'left',
                  confirm: handleDelete.bind(null, record),
                },
              },
            ]"
          />
        </template>
        <template v-if="column.key === 'icon'">
          <Icon :icon="record.icon" :size="16"/>
        </template>
        <template v-if="column.key === 'type'">
          <Tag color="blue">
            {{ record.type === 0 ? '目录' : record.type === 1 ? '菜单' : '按钮' }}
          </Tag>
        </template>
        <template v-if="column.key === 'isLink'">
          <Tag color="blue">
            {{ record.isLink ? '是' : '否' }}
          </Tag>
        </template>
        <template v-if="column.key === 'hidden'">
          <Tag :color="record.hidden ? 'warning' : 'blue'">
            {{ record.hidden ? '隐藏' : '显示' }}
          </Tag>
        </template>
        <template v-if="column.key === 'cache'">
          <Tag :color="record.cache ? 'blue' : 'warning'">
            {{ record.cache ? '缓存' : '不缓存' }}
          </Tag>
        </template>
      </template>
    </BasicTable>
    <MenuModal @register="registerModal" @success="handleSuccess"/>
  </PageWrapper>
</template>
<script lang="ts" setup>
import { reactive } from 'vue'
import { Tag } from 'ant-design-vue'
import { BasicTable, TableAction, useTable } from '/@/components/Table'
import { del, list } from '/@/api/sys/menu'
import { PageWrapper } from '/@/components/Page'
import Icon from '/@/components/Icon'
import { useModal } from '/@/components/Modal'
import { columns, searchFormSchema } from './data'
import MenuModal from './FormModal.vue'
import { useGlobalStore } from '/@/store/modules/global'
import { useMessage } from '/@/hooks/web/useMessage'

const { createMessage } = useMessage()
const [registerModal, { openModal }] = useModal()
const searchInfo = reactive<Recordable>({})
const [registerTable, { reload }] = useTable({
  title: '菜单列表',
  api: list,
  rowKey: 'id',
  isTreeTable: true,
  columns,
  formConfig: {
    labelWidth: 120,
    schemas: searchFormSchema,
    autoSubmitOnEnter: true,
  },
  pagination: false,
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
  del([record.id]).then((_) => {
    createMessage.success('删除成功！')
    reload()
  })
}

function handleSuccess() {
  // 由于角色无法刷新，直接刷新整个表格
  reload()
}
</script>
