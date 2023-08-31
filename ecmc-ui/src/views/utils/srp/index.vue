<template>
  <PageWrapper dense contentFullHeight contentClass="flex">
    <MySrp class="w-3/5" @srp-change="handleSrpChange" />
    <Details class="w-2/5" :srpId="srpId" />
  </PageWrapper>
</template>
<script lang="ts" setup>
import { ref } from 'vue'
import { PageWrapper } from '/@/components/Page'
import MySrp from './mysrp/index.vue'
import Details from './details/index.vue'

const srpId = ref()
// 字典选中事件
function handleSrpChange(value) {
  srpId.value = value
}
</script>
