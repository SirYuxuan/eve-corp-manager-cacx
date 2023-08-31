<template>
  <BasicModal v-bind="$attrs" @register="registerModal" :title="getTitle" @ok="handleSubmit">
    <BasicForm @register="registerForm"/>
  </BasicModal>
</template>
<script lang="ts" setup>
import { computed, ref, unref } from 'vue'
import { BasicModal, useModalInner } from '/@/components/Modal'
import { BasicForm, useForm } from '/@/components/Form/index'
import { crudFormSchema as accountFormSchema } from './data'
import { useMessage } from '/@/hooks/web/useMessage'
import { add, edit, getTree } from '/@/api/sys/menu'

const emit = defineEmits(['success', 'register'])
const isUpdate = ref(true)
const rowId = ref('')

const [registerForm, { setFieldsValue, resetFields, validate, updateSchema }] = useForm({
  labelWidth: 100,
  baseColProps: { span: 24 },
  schemas: accountFormSchema,
  showActionButtonGroup: false,
  actionColOptions: {
    span: 23,
  },
})
const { createMessage } = useMessage()
const getTitle = computed(() => (!unref(isUpdate) ? '新增菜单' : '编辑菜单'))
const [registerModal, { setModalProps, closeModal }] = useModalInner(async (data) => {
  await resetFields()
  setModalProps({ confirmLoading: false })
  isUpdate.value = !!data?.isUpdate

  const treeData = await getTree()
  await updateSchema([
    {
      field: 'pid',
      componentProps: { treeData },
    },
  ])

  if (unref(isUpdate)) {
    rowId.value = data.record.id
    await setFieldsValue({
      ...data.record,
      pid: data.record.pid === 0 ? null : data.record.pid
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
