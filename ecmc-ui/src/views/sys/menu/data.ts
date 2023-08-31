import { BasicColumn, FormSchema } from '/@/components/Table'
import { MenuTypeEnum } from '/@/enums/menuTypeEnum'

const isDir = (menuType: MenuTypeEnum) => menuType === MenuTypeEnum.CATALOG
const isMenu = (menuType: MenuTypeEnum) => menuType === MenuTypeEnum.MENU
const isButton = (menuType: MenuTypeEnum) => menuType === MenuTypeEnum.BUTTON
const isLinkExternal = (isLink: boolean) => isLink

export const columns: BasicColumn[] = [
  {
    title: '名称',
    dataIndex: 'name',
    width: 120,
  },
  {
    title: '图标',
    dataIndex: 'icon',
    width: 80,
  },
  {
    title: '类型',
    dataIndex: 'type',
    width: 80,
  },
  {
    title: '排序',
    dataIndex: 'sort',
    width: 80,
  },
  {
    title: '权限',
    dataIndex: 'permission',
    width: 80,
  },
  {
    title: '外链',
    dataIndex: 'isLink',
    width: 80,
  },
  {
    title: '隐藏',
    dataIndex: 'hidden',
    width: 80,
  },
  {
    title: '缓存',
    dataIndex: 'cache',
    width: 80,
  },
  {
    title: '路由地址',
    dataIndex: 'path',
    width: 80,
  },
  {
    title: '组件地址',
    dataIndex: 'component',
    width: 120,
  },

]

export const searchFormSchema: FormSchema[] = [
  {
    field: 'blurry',
    component: 'Input',
    colProps: { span: 4 },
    componentProps: {
      placeholder: '模糊搜索',
    }
  },
  {
    field: 'createTime',
    component: 'RangePicker',
    componentProps: {
      format: 'YYYY-MM-DD HH:mm:ss',
      placeholder: ['开始时间', '结束时间'],
      showTime: { format: 'HH:mm:ss' },
    },
    colProps: {
      span: 5,
    },
  },
]

export const crudFormSchema: FormSchema[] = [
  {
    field: 'type',
    label: '菜单类型',
    component: 'RadioButtonGroup',
    required: true,
    componentProps: {
      options: [
        { label: '目录', value: MenuTypeEnum.CATALOG },
        { label: '菜单', value: MenuTypeEnum.MENU },
        { label: '按钮', value: MenuTypeEnum.BUTTON },
      ]
    },
    defaultValue: MenuTypeEnum.CATALOG,
  },
  {
    field: 'name',
    label: '菜单名称',
    component: 'Input',
    required: true,
    componentProps: {
      placeholder: '请输入菜单名称',
      maxlength: 100,
    },
  },
  {
    field: 'pid',
    label: '上级菜单',
    component: 'TreeSelect',
    defaultValue: null,
    componentProps: {
      placeholder: '请选择上级菜单',
      fieldNames: {
        label: 'name',
        key: 'id',
        value: 'id',
      },
      getPopupContainer: () => document.body,
    },
  },
  {
    field: 'sort',
    label: '排序',
    component: 'InputNumber',
    required: true,
    defaultValue: '1',
    componentProps: {
      placeholder: '请输入排序',
      min: 1,
    },
  },
  {
    field: 'icon',
    label: '图标',
    component: 'IconPicker',
    componentProps: {
      mode: 'svg',
      readonly: 'readonly'
    },
    ifShow: ({ values }) => !isButton(values.type),
  },
  {
    field: 'hidden',
    label: '是否隐藏',
    component: 'RadioGroup',
    required: true,
    componentProps: {
      options: [
        { label: '显示', value: false },
        { label: '隐藏', value: true },
      ]
    },
    defaultValue: false,
    ifShow: ({ values }) => isDir(values.type) || isMenu(values.type),
  },
  {
    field: 'path',
    label: '路由地址',
    component: 'Input',
    required: true,
    componentProps: {
      placeholder: '请输入路由地址',
    },
    ifShow: ({ values }) => !isButton(values.type),
  },
  {
    field: 'component',
    label: '组件路径',
    component: 'Input',
    required: true,
    componentProps: {
      placeholder: '请输入组件路径',
    },
    ifShow: ({ values }) => isMenu(values.type) && !isLinkExternal(values.isLink),
  },
  {
    field: 'linkUrl',
    label: '外部链接',
    component: 'Input',
    required: true,
    componentProps: {
      placeholder: '请输入外部链接',
    },
    ifShow: ({ values }) => isMenu(values.type) && isLinkExternal(values.isLink),
  },
  {
    field: 'cache',
    label: '是否缓存',
    component: 'RadioGroup',
    required: true,
    componentProps: {
      options: [
        { label: '缓存', value: true },
        { label: '不缓存', value: false },
      ]
    },
    defaultValue: true,
    ifShow: ({ values }) => isMenu(values.type),
  },
  {
    field: 'permission',
    label: '权限标识',
    component: 'Input',
    required: true,
    componentProps: {
      placeholder: '请输入权限标识',
    },
    ifShow: ({ values }) => isButton(values.type),
  },
  {
    field: 'isLink',
    label: '是否外链',
    component: 'RadioGroup',
    required: true,
    componentProps: {
      options: [
        { label: '外链', value: true },
        { label: '非外链', value: false },
      ]
    },
    defaultValue: false,
    ifShow: ({ values }) => isMenu(values.type),
  },
  {
    field: 'frame',
    label: '是否内嵌',
    component: 'RadioGroup',
    required: true,
    componentProps: {
      options: [
        { label: '内嵌', value: true },
        { label: '非内嵌', value: false },
      ]
    },
    defaultValue: false,
    ifShow: ({ values }) => isMenu(values.type) && isLinkExternal(values.isLink),
  },
]
