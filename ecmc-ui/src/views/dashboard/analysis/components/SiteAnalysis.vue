<template>
  <Card
    :tab-list="tabListTitle"
    v-bind="$attrs"
    :active-tab-key="activeKey"
    @tab-change="onTabChange"
  title="数据报表"
  >
    <template #extra><span v-auth="'analysis:all'"> 查询全部：<Switch v-model:checked="admin" @change="changeALl"/></span></template>

    <p v-if="activeKey === 'tab1'">
      <VisitAnalysis :data="data" />
    </p>
    <p v-if="activeKey === 'tab2'">
      <VisitAnalysisBar :data="data" />
    </p>
  </Card>
</template>
<script lang="ts" setup>
  import { ref } from 'vue'
  import { Card, Switch } from 'ant-design-vue'
  import VisitAnalysis from './VisitAnalysis.vue'
  import VisitAnalysisBar from './VisitAnalysisBar.vue'
  import { useRedo } from '/@/hooks/web/usePage'
  const redo = useRedo()
  const activeKey = ref('tab1')
  const admin = ref(localStorage.getItem('analysis') === 'true')
  function changeALl(val){
    localStorage.setItem('analysis', val)
    redo()
  }
  defineProps({
    data: {
      type: Object
    }
  })
  const tabListTitle = [
    {
      key: 'tab1',
      tab: '市场收入',
    },
    {
      key: 'tab2',
      tab: '刷怪量',
    },
  ]

  function onTabChange(key) {
    activeKey.value = key
  }
</script>
