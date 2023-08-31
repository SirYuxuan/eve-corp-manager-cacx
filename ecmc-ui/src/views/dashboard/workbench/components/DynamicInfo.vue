<template>
  <Card title="最新留言" v-bind="$attrs">
    <template #extra>
      <a-button type="link" @click="more" size="small">更多</a-button>
    </template>
    <div style="height: 305px;">
      <ScrollContainer>
        <List item-layout="horizontal" :data-source="listData">
          <template #renderItem="{ item }">
            <ListItem>
              <ListItemMeta>
                <template #description>
                  {{ item.createTime }}
                </template>
                <!-- eslint-disable-next-line -->
                <template #title>  {{ item.createBy + '(' +item.characterName+ ')' }} <span v-html="item.content"> </span> </template>
                <template #avatar>
                  <img  :src="`https://images.evetech.net/characters/${item.characterId}/portrait?size=32`" style="display: inline-block;width: 32px;height: 32px;vertical-align: top;border-radius: 16px"  />
                </template>
              </ListItemMeta>
            </ListItem>
          </template>
        </List>
      </ScrollContainer>
    </div>

  </Card>
</template>
<script lang="ts" setup>
  import { Card, List } from 'ant-design-vue'
  import { onMounted, ref } from 'vue'
  const ListItem = List.Item
  const ListItemMeta = List.Item.Meta
  const listData = ref()
  import { ScrollContainer } from '/@/components/Container'
  import {  list } from '/@/api/utils/talk'
  import { useGo } from '/@/hooks/web/usePage'
  const go = useGo()
  onMounted(() => {
    list({ page: 1, size: 10 }).then(res => {
      listData.value = res.items
    })
  })
  function more(){
    go('/utils/talk')
  }
</script>
