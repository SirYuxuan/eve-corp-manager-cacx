<template>
  <PageWrapper dense contentFullHeight contentClass="flex">
    <div class="shop-wrapper">
      <div class="form">
        <BasicForm style="width: 100%" @register="registerForm"/>
      </div>
      <div class="shop-list">
        <List
          :grid="{ gutter: 10,column: 8 }"
          :data-source="data"
          :pagination="paginationProp"
        >
          <template #header>
            <div class="flex item-center">
              <span class="title">LP商品</span>
              <div style="display: flex;flex: 1;justify-content: end">
                <Tooltip @click="fetch">
                  <template #title>刷新</template>
                  <Button style="cursor: pointer">
                    <RedoOutlined/>
                  </Button>
                </Tooltip>
              </div>
            </div>
          </template>
          <template #renderItem="{ item }">
            <ListItem>
              <Card ref="card">
                <template #title></template>
                <template #cover>
                  <div :class="height" style="text-align: center">
                    <Image height="140px" width="140px" :src="item.pics.split(',')[0]"/>
                  </div>
                </template>
                <template #actions>
                  <Tag color="blue">库存:{{ item.num - item.shopNum }}</Tag>
                  <Tag color="blue">{{ item.lp }} LP</Tag>
                  <Button type="primary" size="small" @click="onBuy(item)">购买</Button>
                </template>

                <CardMeta>
                  <template #title>
                    <TypographyText :content="item.title" :ellipsis="{ tooltip: item.address }"/>
                  </template>
                  <template #description>{{ item.type }}</template>
                </CardMeta>
              </Card>
            </ListItem>
          </template>
        </List>
      </div>
    </div>
    <FormModal @register="registerModal" @success="handleSuccess"/>
  </PageWrapper>
</template>
<script lang="ts" setup>
import { RedoOutlined, } from '@ant-design/icons-vue'
import { Card, Image, List, Tag, Tooltip, Typography } from 'ant-design-vue'
import { Button } from '/@/components/Button'
import { PageWrapper } from '/@/components/Page'
import { list } from '/@/api/lp/goods'
import { BasicForm, useForm } from '/@/components/Form'
import { useModal } from '/@/components/Modal'
import FormModal from './FormModal.vue'
const [registerModal, { openModal }] = useModal()
import { computed, onMounted, ref } from 'vue'
import { getNowLp } from '/@/api/account/userAccount'


const ListItem = List.Item
const CardMeta = Card.Meta
const TypographyText = Typography.Text
const nowLp = ref(0)
const data = ref([])
const [registerForm, { getFieldsValue }] = useForm({
  schemas: [
    {
      field: 'blurry',
      component: 'Input',
      colProps: { span: 3 },
      componentProps: {
        placeholder: '模糊搜索',
      }
    },
  ],
  labelWidth: 120,
  actionColOptions: {
    span: 21
  },
  autoSubmitOnEnter: true,
  submitFunc: onSearch
})

async function onSearch() {
  await fetch(getFieldsValue())
}
function handleSuccess() {
  onSearch()
}
const page = ref(1)
const pageSize = ref(16)
const total = ref(0)
const paginationProp = ref({
  showSizeChanger: false,
  showQuickJumper: true,
  pageSize,
  current: page,
  total,
  showTotal: (total) => `总 ${total} 条`,
  onChange: pageChange,
  onShowSizeChange: pageSizeChange,
})

function pageChange(p, pz) {
  page.value = p
  pageSize.value = pz
  onSearch()
}

function pageSizeChange(_current, size) {
  pageSize.value = size
  onSearch()
}

async function fetch(p = {}) {
  const res = await list({ page: page.value, size: pageSize.value, ...p })
  data.value = res.items
  total.value = res.total
}

// 自动请求并暴露内部方法
onMounted(() => {
  fetch()
  // 获取当前LP剩余数量
  getNowLp().then(res => {
    nowLp.value = Number(res)
  })
})



const height = computed(() => {
  return `h-${120 - 8 * 6}`
})


function onBuy(goods){
  openModal(true, {
   nowLp: nowLp.value,
    title: goods.title,
    lp: goods.lp,
    realLp: goods.lp,
    goodsId: goods.id,
    surplusLp: nowLp.value - goods.lp,
  })
}



</script>

<style lang="less" scoped>
.shop-wrapper {
  padding: 16px;
  width: 100%;
  height: 100%;

  .form {
    background: rgb(255, 255, 255);
    padding: 12px 10px;
    height: 58px;
    margin-bottom: 16px
  }

  .shop-list {
    background: #fff;
    padding: 6px;

    .title {
      position: relative;
      display: flex;
      padding-left: 7px;
      font-size: 16px;
      font-weight: 500;
      line-height: 24px;
      color: rgba(0, 0, 0, 0.85);
      cursor: pointer;
      -webkit-user-select: none;
      -moz-user-select: none;
      user-select: none;
    }
  }
}

</style>
