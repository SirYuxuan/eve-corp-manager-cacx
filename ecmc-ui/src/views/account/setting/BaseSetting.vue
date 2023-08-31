<template>
  <CollapseContainer title="基本设置" :canExpan="false">
    <a-row :gutter="24">
      <a-col :span="14">
        <BasicForm @register="register"/>
      </a-col>
      <a-col :span="10">
        <div class="change-avatar">
          <div class="mb-2"> 头像</div>
          <CropperAvatar
            :uploadApi="uploadApi"
            :value="avatar"
            btnText="更换头像"
            :btnProps="{ preIcon: 'ant-design:cloud-upload-outlined' }"
            @change="updateAvatar"
            width="150"
          />
        </div>
      </a-col>
    </a-row>
    <a-row :gutter="24">
      <a-col :span="10" style="text-align: center">
        <Button type="primary" @click="handleSubmit">保存</Button>
      </a-col>
    </a-row>
  </CollapseContainer>
</template>
<script lang="ts">
import { Button, Col, Row } from 'ant-design-vue'
import { computed, defineComponent, onMounted } from 'vue'
import { BasicForm, useForm } from '/@/components/Form/index'
import { CollapseContainer } from '/@/components/Container'
import { CropperAvatar } from '/@/components/Cropper'

import { useMessage } from '/@/hooks/web/useMessage'

import headerImg from '/@/assets/images/header.jpg'
import { getLoginUser, editLogin } from '/@/api/sys/user'
import { baseSetschemas } from './data'
import { useUserStore } from '/@/store/modules/user'
import { uploadApi } from '/@/api/sys/upload'

export default defineComponent({
  components: {
    BasicForm,
    CollapseContainer,
    Button,
    ARow: Row,
    ACol: Col,
    CropperAvatar,
  },
  setup() {
    const { createMessage } = useMessage()
    const userStore = useUserStore()

    const [register, { setFieldsValue, validate }] = useForm({
      labelWidth: 120,
      schemas: baseSetschemas,
      showActionButtonGroup: false,
    })

    onMounted(async () => {
      const data = await getLoginUser()
      await setFieldsValue(data)
    })

    const avatar = computed(() => {
      const { avatar } = userStore.getUserInfo
      return avatar || headerImg
    })

    function updateAvatar(data: any) {
      const userinfo = userStore.getUserInfo
      userinfo.avatar = data.data
      userStore.setUserInfo(userinfo)
      editLogin({ avatar: userinfo?.avatar || null })
    }

    return {
      avatar,
      register,
      uploadApi,
      updateAvatar,
      handleSubmit: async () => {
        const values = await validate()
        const userinfo = userStore.getUserInfo
        await editLogin({ ...values, avatar: userinfo?.avatar || null })
        userinfo.realName = values['nickName']
        userStore.setUserInfo(userinfo)
        createMessage.success('更新成功！')
      },
    }
  },
})
</script>

<style lang="less" scoped>
.change-avatar {
  img {
    display: block;
    margin-bottom: 15px;
    border-radius: 50%;
  }
}
</style>
