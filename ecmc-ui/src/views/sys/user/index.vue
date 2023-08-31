<template>
  <PageWrapper dense contentFullHeight contentClass="flex">
    <BasicTable @register="registerTable" :searchInfo="searchInfo">
      <template #toolbar>
        <a-button v-auth="['user:add']" type="primary" @click="handleCreate" preIcon="ant-design:plus-outlined">新增账号</a-button>
        <a-button color="warning" @click="handleExport" preIcon="ant-design:download-outlined" >导出</a-button>
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'action'">
          <TableAction
            :actions="[
              {
                icon: 'clarity:note-edit-line',
                tooltip: '编辑用户资料',
                auth:'user:edit',
                onClick: handleEdit.bind(null, record),
              },
              {
                icon: 'ant-design:delete-outlined',
                color: 'error',
                auth:'user:del',
                tooltip: '删除此账号',
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
    <AccountModal @register="registerModal" @success="handleSuccess" />
  </PageWrapper>
</template>
<script lang="ts" setup>
  import { reactive } from 'vue'

  import { BasicTable, useTable, TableAction } from '/@/components/Table'
  import { list, del, exportList } from '/@/api/sys/user'
  import { PageWrapper } from '/@/components/Page'
  import { Tag } from 'ant-design-vue'
  import { useModal } from '/@/components/Modal'
  import { columns, searchFormSchema } from './account.data'
  import { useGo } from '/@/hooks/web/usePage'
  import AccountModal from './AccountModal.vue'
  import { useGlobalStore } from '/@/store/modules/global'
  import { useMessage } from '/@/hooks/web/useMessage'
  import { downloadByData } from '/@/utils/file/download'
  const { createMessage, createConfirm  } = useMessage()
  const go = useGo()
  const [registerModal, { openModal }] = useModal()
  const searchInfo = reactive<Recordable>({})
  const [registerTable, { reload, getForm }] = useTable({
    title: '账号列表',
    api: list,
    rowKey: 'id',
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

  function handleExport(){
    createConfirm({
      iconType: 'warning',
      title: '提示',
      content: '确认导出？',
      onOk: async () => {
        const params = getForm().getFieldsValue()
        const res = await exportList(params)
        const blob = res.data
        downloadByData(blob, '用户列表.xlsx')
      },
    })
  }

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

  function handleView(record: Recordable) {
    go('/system/account_detail/' + record.id)
  }
</script>
