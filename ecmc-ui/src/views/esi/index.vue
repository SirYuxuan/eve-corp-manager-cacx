<template>
  <PageWrapper dense contentFullHeight contentClass="flex">
    <BasicTable @register="registerTable" :searchInfo="searchInfo">
      <template #toolbar>
        <Alert type="success" style="height: 32px">
          <template #message>
            您可以通过绑定小号按钮添加角色授权,弹出授权网站为CPP官方网站,如果无法打开请自行梯子,如果登录发生异常情况,请联系
            QQ:
            <A href="tencent://message/?uin=1718018032&amp;Site=军团网站&amp; Menu=yes">
              1718018032</A>
          </template>
        </Alert>
        <a-button type="primary" @click="handleCreate" preIcon="ant-design:plus-outlined">绑定小号
        </a-button>
        <a-button color="warning" @click="handleExport" preIcon="ant-design:download-outlined">
          导出
        </a-button>
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'characterName'">
          <img class="table-head"
               :src="`https://images.evetech.net/characters/${record.characterId}/portrait?size=32`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.characterName }}</span>
        </template>
        <template v-if="column.key === 'corpName'">
          <img class="table-head"
               :src="`https://images.evetech.net/corporations/${record.corpId}/logo?size=32`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.corpName }}</span>
        </template>
        <template v-if="column.key === 'allianceName'">
          <img class="table-head"
               :src="`https://images.evetech.net/alliances/${record.allianceId}/logo?size=32`"
               alt=""/>
          <span style="margin-left: 10px">{{ record.allianceName }}</span>
        </template>
        <template v-if="column.key === 'esi'">
          <Tag :color="record.accessToken ? 'green' : 'blue'">
            {{ record.accessToken ? 'CACX FULL' : 'ERROR' }}
          </Tag>
        </template>
        <template v-if="column.key === 'isMain'">
          <Switch v-model:checked="record.isMain" :disabled="record.isMain"
                  @change="changeMainAccount(record)"/>
        </template>
        <template v-if="column.key === 'action'">
          <TableAction
            :actions="[
              {
                icon: 'ant-design:delete-outlined',
                color: 'error',
                disabled:!isDel || record.isMain,
                tooltip: '删除账号，最少保留一个角色',
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
  </PageWrapper>
</template>
<script lang="ts" setup>
import { computed, reactive } from 'vue'
import { Alert, Switch, Tag } from 'ant-design-vue'
import { BasicTable, TableAction, useTable } from '/@/components/Table'
import { del, exportList, list, setMainAccount } from '/@/api/account/userAccount'
import { PageWrapper } from '/@/components/Page'
import { columns, searchFormSchema } from './data'
import { useMessage } from '/@/hooks/web/useMessage'
import { downloadByData } from '/@/utils/file/download'
import { useUserStore } from '/@/store/modules/user'
import { useLoading } from '/@/components/Loading'
import { useGlobSetting } from '/@/hooks/setting'

const globSetting = useGlobSetting()
const { createMessage, createConfirm } = useMessage()
const searchInfo = reactive<Recordable>({})
const userStore = useUserStore()
const [registerTable, { reload, getForm, getDataSource }] = useTable({
  title: 'ESI列表',
  api: list,
  rowKey: 'id',
  columns,
  formConfig: {
    labelWidth: 120,
    schemas: searchFormSchema,
    autoSubmitOnEnter: true,
  },
  defSort: {
    order: 'is_main',
    orderBy: 'desc'
  },
  useSearchForm: true,
  showTableSetting: true,
  showIndexColumn: false,
  bordered: true,
  actionColumn: {
    width: 120,
    title: '操作',
    dataIndex: 'action',
  },
})
const isDel = computed(() => {
  return getDataSource().length > 1
})
const [openFullLoading, closeFullLoading] = useLoading({
  tip: '正在处理中...',
  absolute: false
})


function changeMainAccount(record) {
  createConfirm({
    iconType: 'warning',
    title: '提示',
    content: '确认设置此角色为主角色吗？',
    onOk: async () => {
      openFullLoading()
      setMainAccount(record.id).then(() => reload()).finally(() => closeFullLoading())
    },
    onCancel() {
      record.isMain = !record.isMain
    }
  })
}

function handleCreate() {
  createConfirm({
    iconType: 'warning',
    title: '提示',
    content: '请注意由于ESI接口缓存,如果您尚未加入军团便授权ESI可能会导致无法准确获取军团数据。(本提示仅对新人有效)',
    onOk: async () => {
      const token = userStore.getToken
      if (!token) {
        createMessage.warn('登录状态失效，请刷新后重试', 1750, () => location.reload())
        return
      }
      openFullLoading()
      let win = window.open(globSetting.apiUrl + '/esi/auth/' + token, 'q', 'width=500,height=600')
      const loop = setInterval(function () {

        if (win != null && win.closed) {
          // TODO 判断授权状态
          closeFullLoading()
          createMessage.success('授权完成')
          reload()
          clearInterval(loop)
        }
      }, 200)

    },
  })
}


function handleExport() {
  createConfirm({
    iconType: 'warning',
    title: '提示',
    content: '确认导出？',
    onOk: async () => {
      const params = getForm().getFieldsValue()
      params['order'] = 'is_main'
      params['orderBy'] = 'desc'
      const res = await exportList(params)
      const blob = res.data
      downloadByData(blob, '角色列表.xlsx')
    },
  })
}

function handleDelete(record: Recordable) {
  del([record.id]).then((_) => {
    createMessage.success('删除成功！')
    reload()
  })
}


</script>
