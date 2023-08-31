<template>
  <BasicModal v-bind="$attrs" @register="registerModal" :title="getTitle" @ok="handleSubmit">
    <BasicForm @register="registerForm" />
  </BasicModal>
</template>
<script lang="ts" setup>
  import { ref, computed, unref } from 'vue'
  import { BasicModal, useModalInner } from '/@/components/Modal'
  import { BasicForm, useForm } from '/@/components/Form/index'
  import { crudFormSchema } from './data'
  import { useMessage } from '/@/hooks/web/useMessage'
  import { add, edit } from '/@/api/data/srpRules'
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
  const getTitle = computed(() => (!unref(isUpdate) ? '新增规则' : '编辑规则'))
  const [registerModal, { setModalProps, closeModal }] = useModalInner(async (data) => {
    await resetFields()
    setModalProps({ confirmLoading: false })
    isUpdate.value = !!data?.isUpdate

    if (unref(isUpdate)) {
      rowId.value = data.record.id
      await setFieldsValue({
        ...data.record,
      })
    }
  })



  async function handleSubmit() {
    try {
      const values = await validate()
      setModalProps({ confirmLoading: true })
      !unref(isUpdate)
        ? await add({ ...values })
        : await edit({ ...values, id: rowId.value })
      !unref(isUpdate) ? createMessage.success('新增成功！') : createMessage.success('编辑成功！')
      closeModal()
      emit('success', { isUpdate: unref(isUpdate), values: { ...values, id: rowId.value } })
    } finally {
      setModalProps({ confirmLoading: false })
    }
  }

</script>
