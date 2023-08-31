<template>
  <PageWrapper dense contentFullHeight contentClass="flex">
    <Account class="w-3/5" @account-change="handleAccountChange" />
    <LpLog class="w-2/5" :accountId="accountId" />
  </PageWrapper>
</template>
<script lang="ts" setup>
import { ref } from 'vue'
import { PageWrapper } from '/@/components/Page'
import Account from './account/index.vue'
import LpLog from './lpLog/index.vue'

const accountId = ref()
// 字典选中事件
function handleAccountChange(value) {
  accountId.value = value
}
</script>
