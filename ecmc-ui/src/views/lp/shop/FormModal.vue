<template>
  <BasicModal v-bind="$attrs" @register="registerModal" :title="getTitle" @ok="handleSubmit">
    <BasicForm @register="registerForm"/>
  </BasicModal>
</template>
<script lang="ts" setup>
import { ref, unref } from 'vue'
import { BasicModal, useModalInner } from '/@/components/Modal'
import { BasicForm, useForm } from '/@/components/Form/index'
import { crudFormSchema } from './data'
import { useMessage } from '/@/hooks/web/useMessage'
import { buyGoods } from '/@/api/lp/goods'

const emit = defineEmits(['success', 'register'])
const isUpdate = ref(true)
const goodsId = ref('')

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
const getTitle = '商品购买'
const [registerModal, { setModalProps, closeModal }] = useModalInner(async (data) => {
  await resetFields()
  setModalProps({ confirmLoading: false })
  goodsId.value = data.goodsId
  await setFieldsValue({
    ...data,
    status: true
  })
})


async function handleSubmit() {
  try {
    const values = await validate()
    setModalProps({ confirmLoading: true })
    await buyGoods({ ...values, goodsId: goodsId.value })
    createMessage.success('LP商品兑换成功！')
    closeModal()
    emit('success', { isUpdate: unref(isUpdate), values: { ...values, goodsId: goodsId.value } })
  } finally {
    setModalProps({ confirmLoading: false })
  }
}

</script>
