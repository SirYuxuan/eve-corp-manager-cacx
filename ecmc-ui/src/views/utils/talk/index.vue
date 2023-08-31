<template>
  <PageWrapper :class="prefixCls">
    <template #headerContent>
        <Tinymce v-model="content" height="230px" width="100%">
        <template #btn>
          <a-button type="primary" @click="handleFly" :loading="showFlyLoading"
                    style="position: absolute;top: 4px;right: 10px;z-index: 20;" preIcon="fly|svg">
            发布留言
          </a-button>
        </template>
      </Tinymce>

    </template>

    <div :class="`${prefixCls}__container scroll-wrap`">
      <ScrollContainer>
        <List :pagination="paginationProp">
          <template v-for="item in listData">
            <ListItem>
              <ListItemMeta>
                <template #description>
                  <div :class="`${prefixCls}__content`" v-html="item.content">
                  </div>
                  <div :class="`${prefixCls}__action`">
                    <div :class="`${prefixCls}__action-item`">
                      <Icon
                        :class="`${prefixCls}__action-icon`"
                        icon="bx:bxs-like"
                        color="#459ae8"
                      />
                      {{ item.likes }}
                    </div>
                    <span :class="`${prefixCls}__time`">{{ item.createTime }}</span>
                  </div>
                </template>
                <template #title>
                  <p :class="`${prefixCls}__title`">
                    <img  :src="`https://images.evetech.net/characters/${item.characterId}/portrait?size=32`" style="display: inline-block;width: 32px;height: 32px;vertical-align: top;border-radius: 16px" />
                    {{ item.createBy + '(' +item.characterName+ ')' }}
                  </p>
                </template>
              </ListItemMeta>
            </ListItem>
          </template>
        </List>
      </ScrollContainer>
    </div>
  </PageWrapper>
</template>
<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import Icon from '/@/components/Icon/index'
import { searchList } from './data'
import { PageWrapper } from '/@/components/Page'
import { ScrollContainer } from '/@/components/Container'
import { Tinymce } from '/@/components/Tinymce'
import { List } from 'ant-design-vue'
import { useMessage } from '/@/hooks/web/useMessage'
import { add, list } from '/@/api/utils/talk'

const content = ref('')
const prefixCls = ref('list-search')
const showFlyLoading = ref(false)
const listData = ref(searchList)
const ListItem = List.Item
const ListItemMeta = List.Item.Meta
const { createMessage, createConfirm } = useMessage()

function handleFly() {
  if (content.value.length < 10) {
    createMessage.error('请输入至少10个字符才能发布动态.')
    return
  }
  createConfirm({
    iconType: 'warning',
    title: '提示',
    content: '确认发布留言吗？请确保您发布的内容合法合规。如您的内容不符合规范可能会被删除留言并冻结账号',
    onOk: async () => {
      showFlyLoading.value = true
      await add({ content: content.value })
      createMessage.success('留言发布成功')
      await fetch()
      content.value = ''
      showFlyLoading.value = false
    },
    onCancel() {
      showFlyLoading.value = false
    }
  })
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
  fetch()
}

function pageSizeChange(_current, size) {
  pageSize.value = size
  fetch()
}

async function fetch(p = {}) {
  const res = await list({ page: page.value, size: pageSize.value, ...p })
  listData.value = res.items
  total.value = res.total
}

onMounted(() => {
  fetch()
})

</script>
<style lang="less" scoped>
.list-search {
  &__header {
    &-form {
      margin-bottom: -16px;
    }
  }

  &__container {
    padding: 12px;
    background-color: @component-background;
  }

  &__title {
    margin-bottom: 12px;
    font-size: 18px;
    img{
      transition: 0.2s linear;
    }
    img:hover{
      transform: rotate(360deg);
    }
  }

  &__content {
    color: @text-color-secondary;
  }

  &__action {
    margin-top: 10px;

    &-item {
      display: inline-block;
      padding: 0 16px;
      color: @text-color-secondary;

      &:nth-child(1) {
        padding-left: 0;
      }

      &:nth-child(1),
      &:nth-child(2) {
        border-right: 1px solid @border-color-base;
      }
    }

    &-icon {
      margin-right: 3px;
    }
  }

  &__time {
    position: absolute;
    right: 20px;
    color: rgb(0 0 0 / 45%);
  }
}
</style>
<style lang="less" scoped>
.scroll-wrap {
  height: 540px;
  background-color: @component-background;
}
</style>

