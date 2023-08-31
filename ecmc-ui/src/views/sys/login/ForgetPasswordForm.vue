<template>
  <template v-if="getShow">
    <LoginFormTitle class="enter-x" />
    <Form class="p-4 enter-x" :model="formData" :rules="getFormRules" ref="formRef">
      <FormItem name="account" class="enter-x">
        <Input
          size="large"
          v-model:value="formData.account"
          placeholder="账号/邮箱"
        />
      </FormItem>

      <FormItem name="sms" class="enter-x">
        <CountdownInput
          size="large"
          :sendCodeApi="sendCode"
          v-model:value="formData.sms"
          placeholder="验证码"
        />
      </FormItem>
      <FormItem name="password" class="enter-x">
        <InputPassword
          size="large"
          visibilityToggle
          v-model:value="formData.password"
          placeholder="新密码"
        />
      </FormItem>
      <FormItem class="enter-x">
        <Button type="primary" size="large" block @click="handleReset" :loading="loading">
          {{ t('common.resetText') }}
        </Button>
        <Button size="large" block class="mt-4" @click="handleBackLogin">
          {{ t('sys.login.backSignIn') }}
        </Button>
      </FormItem>
    </Form>
  </template>
</template>
<script lang="ts" setup>
  import { reactive, ref, computed, unref } from 'vue'
  import LoginFormTitle from './LoginFormTitle.vue'
  import { Form, Input, Button } from 'ant-design-vue'
  import { CountdownInput } from '/@/components/CountDown'
  import { useI18n } from '/@/hooks/web/useI18n'
  import {useLoginState, useFormRules, LoginStateEnum, useFormValid} from './useLogin'
  import {register, registerMail, resetPassword, sendResetPasswordEmail} from "/@/api/sys/user";

  const InputPassword = Input.Password
  const FormItem = Form.Item
  const { t } = useI18n()
  const { handleBackLogin, getLoginState } = useLoginState()
  const { getFormRules } = useFormRules()


  const formRef = ref()
  const loading = ref(false)

  const { validForm } = useFormValid(formRef)

  const formData = reactive({
    account: '',
    sms: '',
    password: ''
  })

  const getShow = computed(() => unref(getLoginState) === LoginStateEnum.RESET_PASSWORD)
  async function sendCode() {
    const data = await unref(formRef).validateFields('account')
    if (data){
      await sendResetPasswordEmail(formData.account)
      return true
    }
    return false
  }
  async function handleReset() {
    const data = await validForm()
    if (!data) return
    await resetPassword({
      sms: data.sms,
      account: data.account,
      password: data.password
    })
    handleBackLogin()
  }
</script>
