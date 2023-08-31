<template>
  <div>
    <span v-html="currentContext" v-if="type === 'span'"></span>
    <Tag v-if="type === 'tag'" style="width: 100%" :color="tagColor">{{ currentContext }}</Tag>
  </div>
</template>

<script lang="ts" setup>
import { Tag } from 'ant-design-vue'
import { ref, watchEffect } from 'vue'
import { propTypes } from '/@/utils/propTypes'
const currentContext = ref('')
const type = ref('')
const tagColor = ref('blue')
const props = defineProps({
  value: propTypes.any,
  type: propTypes.string,
  tag: propTypes.object
})
watchEffect(() => {
  currentContext.value = props.value
  type.value = props.type || 'span'
  if (type.value === 'tag' && props.tag){
    if (props.tag.color){
      if (typeof props.tag.color === 'string'){
        tagColor.value = props.tag.color
      } else {
        tagColor.value = props.tag.color(props.value)
      }
    }
  }
})
</script>

<style scoped>

</style>
