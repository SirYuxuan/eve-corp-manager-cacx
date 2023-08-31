<template>
  <div class="p-4">
    <GrowCard :data="data" :loading="loading" class="enter-y"/>
    <SiteAnalysis :data="data" class="!my-4 enter-y" :loading="loading"/>
    <div class="md:flex enter-y">
      <VisitRadar class="md:w-1/3 w-full" :loading="true"/>
      <VisitSource class="md:w-1/3 !md:mx-4 !md:my-0 !my-4 w-full" :loading="true"/>
      <SalesProductPie class="md:w-1/3 w-full" :loading="true"/>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import GrowCard from './components/GrowCard.vue'
import SiteAnalysis from './components/SiteAnalysis.vue'
import VisitSource from './components/VisitSource.vue'
import VisitRadar from './components/VisitRadar.vue'
import SalesProductPie from './components/SalesProductPie.vue'
import { analysisGrid } from '/@/api/account/userAccount'

const loading = ref(true)
const data = ref()

onMounted(() => {
  analysisGrid().then(res => {
    data.value = res
    loading.value = false
  })
})


</script>
