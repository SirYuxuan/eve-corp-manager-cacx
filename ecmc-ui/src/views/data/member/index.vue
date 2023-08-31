<template>
  <PageWrapper dense contentFullHeight contentClass="flex">
    <BasicTable @register="registerTable" :searchInfo="searchInfo">
      <template #toolbar>
        <a-button color="warning" @click="handleExport" preIcon="ant-design:download-outlined">
          导出
        </a-button>
      </template>
    </BasicTable>
  </PageWrapper>
</template>
<script lang="ts" setup>
import {  reactive } from 'vue'
import { BasicTable,  useTable } from '/@/components/Table'
import {  exportList, list } from '/@/api/data/member'
import { PageWrapper } from '/@/components/Page'
import { columns, searchFormSchema } from './data'
import { useMessage } from '/@/hooks/web/useMessage'
import { downloadByData } from '/@/utils/file/download'

const {  createConfirm } = useMessage()
const searchInfo = reactive<Recordable>({})
const [registerTable, {  getForm }] = useTable({
  title: '军团成员列表',
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
  showIndexColumn: false,
  bordered: true,

})




function handleExport() {
  createConfirm({
    iconType: 'warning',
    title: '提示',
    content: '确认导出？',
    onOk: async () => {
      const params = getForm().getFieldsValue()
      const res = await exportList(params)
      const blob = res.data
      downloadByData(blob, '军团成员列表.xlsx')
    },
  })
}


</script>
