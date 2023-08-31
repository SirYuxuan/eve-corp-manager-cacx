<template>
  <BasicModal v-bind="$attrs" @register="registerModal" :title="getTitle" @ok="handleSubmit">
    <BasicForm @register="registerForm" />
  </BasicModal>
</template>
<script lang="ts" setup>
import { computed, ref, unref } from 'vue'
import { BasicModal, useModalInner } from '/@/components/Modal'
import { BasicForm, useForm } from '/@/components/Form/index'
import { crudFormSchema } from './data'
import { useMessage } from '/@/hooks/web/useMessage'
import { add, edit } from '/@/api/lp/goods'
const emit = defineEmits(['success', 'register'])
const isUpdate = ref(true)
const rowId = ref('')

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
const getTitle = computed(() => (!unref(isUpdate) ? '新增商品' : '编辑商品'))
const [registerModal, { setModalProps, closeModal }] = useModalInner(async (data) => {
  await resetFields()
  setModalProps({ confirmLoading: false })
  isUpdate.value = !!data?.isUpdate

  if (unref(isUpdate)) {
    rowId.value = data.record.id
    await setFieldsValue({
      ...data.record,
      pics: data.record.pics.split(',')
    })
  }
 
})



async function handleSubmit() {
  try {
    const values = await validate()
    setModalProps({ confirmLoading: true })
    !unref(isUpdate)
      ? await add({ ...values, pics: values.pics.join(',') })
      : await edit({ ...values, pics: values.pics.join(','), id: rowId.value })
    !unref(isUpdate) ? createMessage.success('新增成功！') : createMessage.success('编辑成功！')
    closeModal()
    emit('success', { isUpdate: unref(isUpdate), values: { ...values, id: rowId.value } })
  } finally {
    setModalProps({ confirmLoading: false })
  }
}

</script>
