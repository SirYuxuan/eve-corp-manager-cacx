<template>
  <div class="md:flex">
    <Card
      size="small"
      :loading="loading"
      title="市场收入"
      class="md:w-1/4 w-full !md:mt-0"
      :class="{ '!md:mr-4': 0 + 1 < 4, '!mt-4': 0 > 0 }"
    >
      <template #extra>
        <Tag color="green">ISK</Tag>
      </template>

      <div class="py-4 px-4 flex justify-between items-center">
        <CountTo prefix="" :startVal="1" :endVal="data?.orderPriceDay" class="text-2xl"/>
        <Icon icon="visit-count|svg" :size="40"/>
      </div>

      <div class="p-2 px-4 flex justify-between">
        <span>30天收入</span>
        <CountTo prefix="" :startVal="1" :endVal="data?.orderPrice"/>
      </div>
    </Card>
    <Card
      size="small"
      :loading="loading"
      title="刷怪量"
      class="md:w-1/4 w-full !md:mt-0"
      :class="{ '!md:mr-4': 0 + 1 < 4, '!mt-4': 0 > 0 }"
    >
      <template #extra>
        <Tag color="purple">ISK</Tag>
      </template>

      <div class="py-4 px-4 flex justify-between items-center">
        <CountTo prefix="" :startVal="1" :endVal="data?.ratPriceDay" class="text-2xl"/>
        <Icon icon="transaction|svg" :size="40"/>
      </div>

      <div class="p-2 px-4 flex justify-between">
        <span>30天刷怪量</span>
        <CountTo prefix="" :startVal="1" :endVal="data?.ratPrice"/>
      </div>
    </Card>
    <Card
      size="small"
      :loading="loading"
      title="在售订单"
      class="md:w-1/4 w-full !md:mt-0"
      :class="{ '!md:mr-4': 0 + 1 < 4, '!mt-4': 0 > 0 }"
    >
      <template #extra>
        <Tag color="blue">ISK</Tag>
      </template>

      <div class="py-4 px-4 flex justify-between items-center">
        <CountTo prefix="" :startVal="1" :endVal="data?.saleOrderPrice" class="text-2xl"/>
        <Icon icon="total-sales|svg" :size="40"/>
      </div>

      <div class="p-2 px-4 flex justify-between">
        <span>收单价值</span>
        <CountTo prefix="" :startVal="1" :endVal="data?.saleOrderPriceBuy"/>
      </div>
    </Card>
    <Card
      size="small"
      :loading="loading"
      title="工业指数"
      class="md:w-1/4 w-full !md:mt-0"
      :class="{ '!md:mr-4': 0 + 1 < 4, '!mt-4': 0 > 0 }"
    >
      <template #extra>
        <Tag color="orange">数量</Tag>
      </template>

      <div class="py-4 px-4 flex justify-between items-center">
        <span>进行中: <Tag color="blue">暂未开放</Tag></span>
        <Icon icon="download-count|svg" :size="40"/>
      </div>

      <div class="p-2 px-4 flex justify-between">
        <span>已完成: <Tag color="blue">暂未开放</Tag></span>
      </div>
    </Card>

  </div>
</template>
<script lang="ts" setup>
import { CountTo } from '/@/components/CountTo'
import { Icon } from '/@/components/Icon'
import { Card, Tag } from 'ant-design-vue'

defineProps({
  loading: {
    type: Boolean,
  },
  data: {
    type: Object
  }
})



</script>
