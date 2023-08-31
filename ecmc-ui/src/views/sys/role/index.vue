<template>
  <PageWrapper dense contentFullHeight contentClass="flex">
    <BasicTable @register="registerTable" class="w-5/6 xl:w-5/6 hide-radio" @query-reset-func="restFun"
                @row-click="handleRowClick" :searchInfo="searchInfo">
      <template #toolbar>
        <a-button v-auth="['role:add']" type="primary" @click="handleCreate"
                  preIcon="ant-design:plus-outlined">新增角色
        </a-button>
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'action'">
          <TableAction
            :actions="[
              {
                icon: 'clarity:note-edit-line',
                tooltip: '编辑角色',
                auth:'role:edit',
                onClick: handleEdit.bind(null, record),
              },
              {
                icon: 'ant-design:delete-outlined',
                color: 'error',
                auth:'role:del',
                tooltip: '删除此角色',
                popConfirm: {
                  title: '是否确认删除',
                  placement: 'left',
                  confirm: handleDelete.bind(null, record),
                },
              },
            ]"
          />
        </template>

        <template v-if="column.key === 'status'">
          <Tag :color="record.status === 0 ? 'processing' : 'error'">
            {{ record.status === 0 ? '正常' : (record.status === 1 ? '冻结' : '锁定') }}
          </Tag>
        </template>
      </template>
    </BasicTable>
    <Card class="w-1/6 xl:w-1/6" style="margin: 16px">
      <template #title>
        <span class="role-span">{{ !rowName ? '菜单分配' : rowName + '的菜单' }}</span>
        <a-button class="distribution" v-auth="['role:edit']" size="small" type="primary"
                  :disabled="!showButton" :loading="showLoading" @click="saveMenuFun" preIcon="ant-design:check-outlined">
          保存
        </a-button>
      </template>
      <BasicTree
        :checkedKeys="checkedKeys"
        :treeData="treeData"
        checkable
        @check="handleCheck"
        :fieldNames="{ key: 'id', title: 'name' }"
      />
    </Card>
    <RoleModal @register="registerModal" @success="handleSuccess" />
  </PageWrapper>
</template>
<script lang="ts" setup>

import { computed, onBeforeMount, reactive, ref, unref } from 'vue'
import { Card, Tag } from 'ant-design-vue'
import { BasicTable, TableAction, useTable } from '/@/components/Table'
import { PageWrapper } from '/@/components/Page'
import { useModal } from '/@/components/Modal'
import { columns, searchFormSchema } from './role.data'
import RoleModal from './RoleModal.vue'
import { useGlobalStore } from '/@/store/modules/global'
import { useMessage } from '/@/hooks/web/useMessage'
import { BasicTree } from '/@/components/Tree'

import { getTree } from '/@/api/sys/menu'
import { list, del, saveMenu } from '/@/api/sys/role'

const { createMessage } = useMessage()
const [registerModal, { openModal }] = useModal()
const searchInfo = reactive<Recordable>({})
const checkedKeys = ref([])
const treeData = ref([])
const halfCheckedKeys = ref([])
const treeCheck = ref(false)
const rowId = ref(undefined)
const rowName = ref(undefined)
const showLoading = ref(false)

onBeforeMount(() => {
  getTree().then(res => {
    treeData.value = res
  })
})

// 重置搜索表单，重置表格选中
function restFun() {
  rowId.value = undefined
  rowName.value = undefined
  checkedKeys.value = []
}

// 树节点选中事件
function handleCheck(keys, e) {
  if (!unref(treeCheck)) {
    treeCheck.value = true
  }
  checkedKeys.value = keys
  halfCheckedKeys.value = e.halfCheckedKeys
}

// 表格行点击事件
function handleRowClick(record: Recordable) {
  rowId.value = record.id
  rowName.value = record.name
  let ids = unref<any>([])
  record.menus.forEach(function (data) {
    if (data.id) {
      ids.push(data.id)
    }
  })
  checkedKeys.value = ids
}

// 保存菜单
function saveMenuFun() {
  showLoading.value = true
  saveMenu({
    roleId: rowId.value,
    menuIds: checkedKeys.value,
    virtuallyMenuIds: halfCheckedKeys.value
  }).then(_ => {
    createMessage.success('菜单保存成功')
    reload()
  }).finally(() => showLoading.value = false)
}

const [registerTable, { reload }] = useTable({
  title: '角色列表',
  api: list,
  rowKey: 'id',
  clickToRowSelect: true,
  striped: false,
  columns,
  rowSelection: {
    type: 'radio',
    columnWidth: 0
  },
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

const showButton = computed(() => {
  return rowId.value
})


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
  del([record.id]).then(res => {
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
