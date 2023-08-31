<template>
  <div ref="wrapEl">
    <Loading :loading="compState.loading" :absolute="compState.absolute" :tip="compState.tip"/>
  </div>
</template>

<script lang="ts" setup>
import { Loading } from '/@/components/Loading'
import { reactive } from 'vue'
import { addOrUpdateAccount } from '/@/api/account/userAccount'
import { useMessage } from '/@/hooks/web/useMessage'

const { createErrorModal } = useMessage()
const compState = reactive({
  absolute: false,
  loading: true,
  tip: '登录完成,正在更新角色数据 请不要退出...',
})

function getQuery(val) {

  const w = location.hash.indexOf('?')
  const query = location.hash.substring(w + 1)

  const vars = query.split('&')
  for (let i = 0; i < vars.length; i++) {
    const pair = vars[i].split('=')
    if (pair[0] == val) { return pair[1] }
  }

  return (false)
}

addOrUpdateAccount(getQuery('code') as string).then(() => {
  closeThis()
}).catch((msg) => {
  createErrorModal({
    title: '操作', content: (msg as Error).message, onOk: () => {
      closeThis()
    }
  })
})

function closeThis() {
  window.opener = null
  window.open('', '_self')
  window.close()
}

</script>

