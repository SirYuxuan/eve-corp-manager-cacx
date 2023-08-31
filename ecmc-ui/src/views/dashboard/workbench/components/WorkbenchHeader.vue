<template>
  <div class="lg:flex">
    <Avatar :src="userinfo.avatar || headerImg" :size="72" class="!mx-auto !block"/>
    <div class="md:ml-6 flex flex-col justify-center md:mt-0 mt-2">
      <h1 class="md:text-lg text-md"
      >{{ nowHello }}, {{ userinfo.realName }}, 开始您一天的工作吧！</h1
      >
      <span class="text-secondary"> EVE时间：{{ eveTime }} , {{ oneDay }}</span>
    </div>
    <div class="flex flex-1 justify-end md:mt-0 mt-4">
      <div class="flex flex-col justify-center text-right">
        <span class="text-secondary"> ISK </span>
        <span class="text-2xl"><CountTo prefix="" :startVal="1" :endVal="homeData.isk"/></span>
      </div>

      <div class="flex flex-col justify-center text-right md:mx-16 mx-12">
        <span class="text-secondary"> PAP </span>
        <span class="text-2xl">无</span>
      </div>
      <div class="flex flex-col justify-center text-right md:mr-10 mr-4">
        <span class="text-secondary"> LP </span>
        <span class="text-2xl">{{ homeData.lp }}</span>
      </div>
      <div class="flex flex-col justify-center text-right md:mr-10 mr-4">
        <span class="text-secondary"> SKILL </span>
        <span class="text-2xl"><CountTo prefix="" :startVal="1" :endVal="homeData.skill"/></span>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
import {computed, onMounted, onUnmounted, reactive, ref} from 'vue'
import {Avatar} from 'ant-design-vue'
import {useUserStore} from '/@/store/modules/user'
import headerImg from '/@/assets/images/header.jpg'
import {CountTo} from '/@/components/CountTo'
import moment from 'moment'
import {getOneDay} from '/@/api/api/openApi'
import {getHomeData} from '/@/api/account/userAccount'

const userStore = useUserStore()

const nowDate = new Date()
const nowHour = nowDate.getHours()
nowDate.setHours(nowHour - 8)

// 实时刷新显示EVE时间
const eveTime = ref(moment(nowDate).format('YYYY-MM-DD HH:mm:ss'))

const index = setInterval(() => {
  nowDate.setSeconds(nowDate.getSeconds() - 1)
  eveTime.value = moment(nowDate).format('YYYY-MM-DD HH:mm:ss')
}, 1000)

onUnmounted(() => {
  clearInterval(index)
})
// 计算当前时间显示问候语
const nowHello = ref(
  nowHour < 8
    ? '早上好'
    : nowHour <= 11
      ? '上午好'
      : nowHour <= 13
        ? '中午好'
        : nowHour < 18
          ? '下午好'
          : '晚上好',
)

const oneDay = ref('')
const homeData = reactive({
  isk: 0,
  skill: 0,
  lp: 0,
  pap: ''
})

onMounted(() => {
  getOneDay().then((res) => {
    oneDay.value = res
  })
  getHomeData().then(res => {
    homeData.isk = res.isk
    homeData.skill = res.skill
    homeData.lp = res.lp
    homeData.pap = res.pap
  })
})

const userinfo = computed(() => userStore.getUserInfo)
</script>
