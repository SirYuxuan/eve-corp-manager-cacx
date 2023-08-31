interface GroupItem {
  title: string;
  icon: string;
  color: string;
  desc: string;
  date: string;
  group: string;
}

interface NavItem {
  title: string;
  icon: string;
  color: string;
}

interface DynamicInfoItem {
  avatar: string;
  name: string;
  date: string;
  desc: string;
}

export const navItems: NavItem[] = [
  {
    title: '绑定小号',
    icon: 'ion:home-outline',
    color: '#1fdaca',
  },
  {
    title: '提交补损',
    icon: 'ion:grid-outline',
    color: '#bf0c2c',
  },
  {
    title: '修改信息',
    icon: 'ion:layers-outline',
    color: '#e18525',
  },
  {
    title: '技能规划',
    icon: 'ion:settings-outline',
    color: '#3fb27f',
  },
  {
    title: '合同估价',
    icon: 'ion:key-outline',
    color: '#4daf1bc9',
  },
  {
    title: '跳跃规划',
    icon: 'ion:bar-chart-outline',
    color: '#00d8ff',
  },
  {
    title: 'DScan',
    icon: 'ion:bar-chart-outline',
    color: '#00d8ff',
  },
  {
    title: '收支统计',
    icon: 'ion:bar-chart-outline',
    color: '#00d8ff',
  },
]

export const dynamicInfoItems: DynamicInfoItem[] = [
  {
    avatar: 'dynamic-avatar-1|svg',
    name: '威廉',
    date: '刚刚',
    desc: '有人出xxxx吗',
  },
  {
    avatar: 'dynamic-avatar-2|svg',
    name: '艾文',
    date: '1个小时前',
    desc: '：打远征必红：烧香拜佛远离可乐8跳外 ',
  },
  {
    avatar: 'dynamic-avatar-3|svg',
    name: '克里斯',
    date: '1天前',
    desc: 'xxxxxxxxxxxxx',
  },
  {
    avatar: 'dynamic-avatar-5|svg',
    name: '皮特',
    date: '3天前',
    desc: '回复了 <a>杰克</a> 的问题 <a>如何进行配置优化？</a>',
  }
]

export const groupItems: GroupItem[] = [
  {
    title: 'Github',
    icon: 'carbon:logo-github',
    color: '',
    desc: '不要等待机会，而要创造机会。',
    group: '开源组',
    date: '2021-04-01',
  },
  {
    title: 'Vue',
    icon: 'ion:logo-vue',
    color: '#3fb27f',
    desc: '现在的你决定将来的你。',
    group: '算法组',
    date: '2021-04-01',
  },
  {
    title: 'Html5',
    icon: 'ion:logo-html5',
    color: '#e18525',
    desc: '没有什么才能比努力更重要。',
    group: '上班摸鱼',
    date: '2021-04-01',
  },
  {
    title: 'Angular',
    icon: 'ion:logo-angular',
    color: '#bf0c2c',
    desc: '热情和欲望可以突破一切难关。',
    group: 'UI',
    date: '2021-04-01',
  },
  {
    title: 'React',
    icon: 'bx:bxl-react',
    color: '#00d8ff',
    desc: '健康的身体是实目标的基石。',
    group: '技术牛',
    date: '2021-04-01',
  },
  {
    title: 'Js',
    icon: 'ion:logo-javascript',
    color: '#4daf1bc9',
    desc: '路是走出来的，而不是空想出来的。',
    group: '架构组',
    date: '2021-04-01',
  },
]
