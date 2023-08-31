<template>
  <BasicModal v-bind="$attrs" @register="registerModal" title="订单审批" @ok="handleSubmit">
    <BasicForm @register="registerForm" />
  </BasicModal>
</template>
<script lang="ts" setup>
import { ref, unref } from 'vue'
import { BasicModal, useModalInner } from '/@/components/Modal'
import { BasicForm, useForm } from '/@/components/Form/index'
import { crudFormSchema } from './data'
import { useMessage } from '/@/hooks/web/useMessage'
import { batchApproval } from '/@/api/lp/goodsOrder'
const emit = defineEmits(['success', 'register'])
const isUpdate = ref(true)
const rowIds = ref([])

const [registerForm, { setFieldsValue, resetFields, validate }] = useForm({
  labelWidth: 100,
  baseColProps: { span: 24 },
  schemas: crudFormSchema,
  showActionButtonGroup: false,
  actionColOptions: {
    span: 23,
  },
})
const { createMessage } = useMessage()
const [registerModal, { setModalProps, closeModal }] = useModalInner(async (data) => {
  await resetFields()
  setModalProps({ confirmLoading: false })
  await setFieldsValue({
    ...data.record,
    status: true
  })
  rowIds.value = data.record.ids

})



async function handleSubmit() {
  try {
    const values = await validate()
    setModalProps({ confirmLoading: true })
    await batchApproval({ ...values, ids: rowIds.value })
    console.log(values)
    createMessage.success('审批完成!')
    closeModal()
    emit('success', { isUpdate: unref(isUpdate), values: { ...values, ids: rowIds.value } })
  } finally {
    setModalProps({ confirmLoading: false })
  }
}

</script>
