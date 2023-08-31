<template>
  <PageWrapper dense contentFullHeight contentClass="flex mt6">

    <BasicTable class="hide-radio" @register="registerTable" @row-click="handleRowClick"
                @selection-change="handleSelectionChange" @fetch-success="handlerSuccess"
                @query-reset-func="restFun"
                :searchInfo="searchInfo">
      <template #top>
        <Card title="新的补损申请" style="margin-bottom: 10px;margin-top: 10px">
          <Alert type="success" style="height: 32px;margin-bottom: 5px">
            <template #message>
              在下框中选择欲提交的击杀报告。如果在列表中找不到击杀报告，请确认您对应的帐号已正确完成绑定后等待最多
              1 小时刷新。 <Button type="error" style="height: 26px;float: right;line-height: 18px" :loading="refreshLoading" @click="onRefresh">强制刷新</Button>
            </template>
          </Alert>
          <Form ref="formRef" layout="vertical" :model="formData">
            <FormItem name="kmId" label="选择一个击杀报告"
                      :rules="[{ required: true, message: '请选择一个击杀报告' }]">
              <Select @change="selectChange" v-model:value="formData.kmId" >
                <Option :disabled="item.srp" v-for="item in kmList" :key="item.id" :value="item.id">
                  {{
                    item.killMailId + ':' + item.shipTypeName + '(' + item.characterName + ') - ' + item.killMailTime + '@' + item.solarSystemName
                  }}
                </Option>
              </Select>
            </FormItem>
            <FormItem name="content"
                      label="集结信息(请明确说明指挥/教官ID，集结性质等信息，否则拒绝补损)"
                      :rules="[{ required: true, message: '请输入集结信息或备注' }]">
              <InputTextArea v-model:value="formData.content" placeholder="请输入集结信息或备注"/>
            </FormItem>
            <FormItem style="text-align: right">
              <Button type="primary" :loading="submitLoading" @click="onSubmit">提交</Button>
            </FormItem>
          </Form>
        </Card>
      </template>
      <template #expandedRowRender="{ record }">
        <p style="margin: 0">
          <Descriptions :column="2" bordered size="small">
            <DescriptionsItem label="损失时间">{{record.accountKillMail.killMailTime}}</DescriptionsItem>
            <DescriptionsItem label="提交时间">{{record.createTime}}</DescriptionsItem>
            <DescriptionsItem label="审批时间">{{record.updateTime}}</DescriptionsItem>
            <DescriptionsItem label="审批人">{{record.updateBy}}</DescriptionsItem>
            <DescriptionsItem label="审批备注">{{record.spContent}}</DescriptionsItem>
          </Descriptions>
        </p>
      </template>
      <template #bodyCell="{ column, record }">
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
  </PageWrapper>
</template>
<script lang="ts" setup>
import { onMounted, reactive, ref } from 'vue'
import { BasicTable, useTable } from '/@/components/Table'
import {listKillMail, refreshKm} from '/@/api/account/userAccount'
import { add, list } from '/@/api/utils/srpLog'
import { PageWrapper } from '/@/components/Page'
import { columns } from './data'
import { Alert, Button, Card, Form, FormInstance, FormItem, Input, Select,Descriptions } from 'ant-design-vue'
import { useMessage } from '/@/hooks/web/useMessage'
const DescriptionsItem = Descriptions.Item
const submitLoading = ref(false)
const refreshLoading = ref(false)
const emit = defineEmits(['srp-change'])
const searchInfo = reactive<Recordable>({})
const InputTextArea = Input.TextArea
const Option = Select.Option
const [registerTable, { setSelectedRowKeys, getSelectRows, reload }] = useTable({
  title: '我的补损申请',
  api: list,
  rowKey: 'id',
  columns,
  defSort: {
    order: 'csl.createTime',
    orderBy: 'desc'
  },
  rowSelection: {
    type: 'radio',
    columnWidth: 0
  },
  showIndexColumn: false,
  useSearchForm: false,
  showTableSetting: true,
  bordered: true,
  clickToRowSelect: true,
  afterFetch: handleAfterFetch,
})
const formData = reactive({
  kmId: null,
  content: ''
})

const formRef = ref<FormInstance>()
const kmList = ref([])
const { createMessage } = useMessage()

function onSubmit() {
  formRef.value?.validate().then(() => {
    submitLoading.value = true
    add({ killMailId: formData.kmId, content: formData.content }).then(() => {
      createMessage.success('补损提交成功')
      reload()
    }).finally(() => submitLoading.value = false)
  })
}
function onRefresh() {
  refreshLoading.value = true
  refreshKm().then(() => {
    createMessage.success('KM手动刷新成功')
    reload()
  }).finally(() => refreshLoading.value = false)
}


// 表格行点击事件
function handleRowClick(record: Recordable) {
  setSelectedRowKeys([record.id])
  emitSrpChange()
}

// 表格行选中事件
function handleSelectionChange() {
  emitSrpChange()
}

function selectChange(val) {
  emit('srp-change', val)
}

function emitSrpChange() {
  const selectedKeys = getSelectRows()
  emit('srp-change', selectedKeys.length > 0 ? selectedKeys[0].killMailId : '')
}

// 表格请求之后，对返回值进行处理
function handleAfterFetch() {
  setSelectedRowKeys([])
  emitSrpChange()
}

function restFun() {
  setSelectedRowKeys([])
  emitSrpChange()
}

onMounted(() => {
  listKillMail({}).then(res => {
    kmList.value = res
  })
})


</script>

<style>
.mt6 {
  margin: 6px !important;
}
</style>
