<template>
  <template v-if="getShow">
    <LoginFormTitle class="enter-x"/>
    <Form class="p-4 enter-x" :model="formData" :rules="getFormRules" ref="formRef">
      <FormItem name="account" class="enter-x">
        <Input
          class="fix-auto-fill"
          size="large"
          v-model:value="formData.account"
          :placeholder="t('sys.login.userName')"
        />
      </FormItem>
      <FormItem name="email" class="enter-x">
        <Input
          size="large"
          v-model:value="formData.email"
          placeholder="邮箱"
          class="fix-auto-fill"
        />
      </FormItem>
      <FormItem name="sms" class="enter-x">
        <CountdownInput
          size="large"
          :sendCodeApi="sendCode"
          class="fix-auto-fill"
          v-model:value="formData.sms"
          placeholder="邮箱验证码"
        />
      </FormItem>
      <FormItem name="password" class="enter-x">
        <StrengthMeter
          size="large"
          v-model:value="formData.password"
          :placeholder="t('sys.login.password')"
        />
      </FormItem>
      <FormItem name="confirmPassword" class="enter-x">
        <InputPassword
          size="large"
          visibilityToggle
          v-model:value="formData.confirmPassword"
          :placeholder="t('sys.login.confirmPassword')"
        />
      </FormItem>

      <FormItem class="enter-x" name="policy">
        <!-- No logic, you need to deal with it yourself -->
        <Checkbox v-model:checked="formData.policy" size="small">
          我同意混沌仲裁者注册协议
        </Checkbox>
      </FormItem>

      <Button
        type="primary"
        class="enter-x"
        size="large"
        block
        @click="handleRegister"
        :loading="loading"
      >
        {{ t('sys.login.registerButton') }}
      </Button>
      <Button size="large" block class="mt-4 enter-x" @click="handleBackLogin">
        {{ t('sys.login.backSignIn') }}
      </Button>
    </Form>
  </template>
</template>
<script lang="ts" setup>
import { computed, reactive, ref, unref } from 'vue'
import LoginFormTitle from './LoginFormTitle.vue'
import { Button, Checkbox, Form, Input } from 'ant-design-vue'
import { StrengthMeter } from '/@/components/StrengthMeter'
import { CountdownInput } from '/@/components/CountDown'
import { useI18n } from '/@/hooks/web/useI18n'
import { LoginStateEnum, useFormRules, useFormValid, useLoginState } from './useLogin'
import { registerMail, register } from '/@/api/sys/user'

const FormItem = Form.Item
const InputPassword = Input.Password
const { t } = useI18n()
const { handleBackLogin, getLoginState } = useLoginState()

const formRef = ref()
const loading = ref(false)

const formData = reactive({
  account: '',
  password: '',
  confirmPassword: '',
  email: '',
  sms: '',
  policy: false,
})

const { getFormRules } = useFormRules(formData)
const { validForm } = useFormValid(formRef)

const getShow = computed(() => unref(getLoginState) === LoginStateEnum.REGISTER)

async function handleRegister() {
  const data = await validForm()
  if (!data) return
  await register({
    code: data.sms,
    username: data.account,
    email: data.email,
    password: data.password
  })
  handleBackLogin()
}

async function sendCode() {
  const data = await unref(formRef).validateFields('email')
  if (data){
    await registerMail(formData.email)
    return true
  }
  return false
}
</script>
