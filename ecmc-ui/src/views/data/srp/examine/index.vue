<template>
  <PageWrapper dense contentFullHeight contentClass="flex">
    <BasicTable @register="registerTable" :searchInfo="searchInfo">
      <template #expandedRowRender="{ record }">
        <p style="margin: 0">
          <Descriptions :column="2" bordered size="small">
            <DescriptionsItem label="所属军团">
              <img class="table-head"
                   :src="`https://images.evetech.net/corporations/${record.accountKillMail.corporationId}/logo?size=32`"
                   alt=""/>
              <span style="margin-left: 10px">{{ record.accountKillMail.corporationName }}</span>
            </DescriptionsItem>
            <DescriptionsItem label="所属联盟">
              <img class="table-head"
                   :src="`https://images.evetech.net/alliances/${record.accountKillMail.allianceId}/logo?size=32`"
                   alt=""/>
              <span style="margin-left: 10px">{{ record.accountKillMail.allianceName }}</span>
            </DescriptionsItem>
            <DescriptionsItem label="审批时间">{{ record.updateTime }}</DescriptionsItem>
            <DescriptionsItem label="审批人">{{ record.updateBy }}</DescriptionsItem>
            <DescriptionsItem label="审批备注">{{ record.spContent }}</DescriptionsItem>
            <DescriptionsItem label="集结信息">{{ record.content }}</DescriptionsItem>
          </Descriptions>
        </p>
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'action'">
          <TableAction
            :actions="[
              {
                icon: 'ant-design:check-outlined',
                tooltip: '通过',
                disabled:record.status !== 1,
                onClick: handleCheck.bind(null, record),
              },
              {
                icon: 'ant-design:close-outlined',
                tooltip: '拒绝',
                disabled:record.status !== 1,
                onClick: handleClose.bind(null, record),
              },
            ]"
          />
        </template>
        <template v-if="column.key === 'characterName'">
          <img class="table-head"
               :src="`https://images.evetech.net/characters/${record.accountKillMail.characterId}/portrait?size=32`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.accountKillMail.characterName }}</span>
        </template>
        <template v-if="column.key === 'shipName'">
          <img class="table-head"
               :src="`https://images.evetech.net/Type/${record.accountKillMail.shipTypeId}_32.png`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.accountKillMail.shipTypeName }}</span>
        </template>
      </template>
    </BasicTable>
    <RoleModal @register="registerModal" @success="handleSuccess"/>
  </PageWrapper>
</template>
<script lang="ts" setup>

import { reactive } from 'vue'
import { BasicTable, TableAction, useTable } from '/@/components/Table'
import { PageWrapper } from '/@/components/Page'
import { useModal } from '/@/components/Modal'
import { columns, searchFormSchema } from './data'
import RoleModal from './FormModal.vue'
import { Descriptions } from 'ant-design-vue'
import { list } from '/@/api/utils/srpLog'

const DescriptionsItem = Descriptions.Item
const [registerModal, { openModal }] = useModal()
const searchInfo = reactive<Recordable>({})


const [registerTable, { reload }] = useTable({
  title: '补损列表',
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
  showIndexColumn: false,
  defSort: {
    order: 'csl.createTime',
    orderBy: 'desc'
  },
  useSearchForm: true,
  showTableSetting: true,
  bordered: true,
  beforeFetch(params) {
    return { ...params, all: true }
  },
  actionColumn: {
    width: 120,
    title: '操作',
    dataIndex: 'action',
  },
})



function handleCheck(record: Recordable) {
  openModal(true, {
    record,
    status: 2,
    characterName: record.accountKillMail.characterName,
    isUpdate: true,
  })
}


function handleClose(record: Recordable) {
  openModal(true, {
    record,
    status: 3,
    characterName: record.accountKillMail.characterName,
    isUpdate: true,
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
