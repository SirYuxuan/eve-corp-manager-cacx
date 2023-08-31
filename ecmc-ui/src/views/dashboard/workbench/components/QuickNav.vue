<template>
  <Card title="快捷导航" v-bind="$attrs">
    <template v-for="item in navItems" :key="item">
      <CardGrid style="width: 25%" @click="doAction(item.title)">
        <span class="flex flex-col items-center">
          <Icon :icon="item.icon" :color="item.color" size="20" />
          <span class="text-md mt-2">{{ item.title }}</span>
        </span>
      </CardGrid>
    </template>
  </Card>
</template>
<script lang="ts" setup>
import { Card } from 'ant-design-vue'
import { navItems } from './data'
import { Icon } from '/@/components/Icon'
import { useGo } from '/@/hooks/web/usePage'
import { useMessage } from '/@/hooks/web/useMessage'
const CardGrid = Card.Grid
const go = useGo()
const { createMessage } = useMessage()
function doAction(title){
  switch (title){
    case '绑定小号':
      go('/esi')
      break
    case '提交补损':
      go('/utils/srp')
      break
    case '修改信息':
      go('/account/setting')
      break
    case '收支统计':
      go('/account/analysis')
      break
    default:
      createMessage.warning('此功能正在开发中.')
  }
}
</script>
