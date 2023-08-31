import { checkFieldExist, getAllRoleList } from '/@/api/sys/user'
import { BasicColumn, FormSchema } from '/@/components/Table'
import { useGlobalStore } from '/@/store/modules/global'
import { isEmail } from '/@/utils/vaildator'
import { UserStatusEnum } from '/@/enums/userEnum'

const globalStore = useGlobalStore()

export const columns: BasicColumn[] = [
  {
    title: '用户名',
    dataIndex: 'username',
    width: 120,
  },
  {
    title: '昵称',
    dataIndex: 'nickName',
    width: 120,
  },
  {
    title: '邮箱',
    dataIndex: 'email',
    width: 180,
  },
  {
    title: '角色',
    dataIndex: 'roleNames',
    width: 200,
  },
  {
    title: 'QQ',
    dataIndex: 'qq',
    width: 100,
  },
  {
    title: '所在城市',
    dataIndex: 'city',
    width: 100,
  },
  {
    title: '登录时间',
    dataIndex: 'loginTime',
    width: 150,
  },
  {
    title: '登录城市',
    dataIndex: 'loginCity',
    width: 180,
  },
  {
    title: '用户状态',
    dataIndex: 'status',
    width: 80,
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
    width: 150,
  },
]

export const searchFormSchema: FormSchema[] = [
  {
    field: 'blurry',
    component: 'Input',
    colProps: { span: 4 },
    componentProps: {
      placeholder: '输入用户名/昵称/QQ搜索',
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

export const accountFormSchema: FormSchema[] = [
  {
    field: 'username',
    label: '用户名',
    component: 'Input',
    rules: [
      {
        required: true,
        message: '请输入用户名',
      },
      {
        validator(_, value) {
          return new Promise((resolve, reject) => {
            if (!value) {
              resolve()
              return
            }
            checkFieldExist('username', value, globalStore.getCheckFieldUserId || null)
              .then((res) => {
                if (res) {
                  reject('用户名已被使用')
                } else {
                  resolve()
                }
              })
              .catch((err) => {
                reject(err.message || '验证失败')
              })
          })
        },
      },
    ],
  },
  {
    field: 'password',
    label: '密码',
    component: 'InputPassword',
    required: true
  },
  {
    label: '角色',
    field: 'roleIds',
    component: 'ApiSelect',
    componentProps: () => {
      return {
        placeholder: '请选择角色',
        api: getAllRoleList,
        mode: 'multiple',
        labelField: 'name',
        valueField: 'id',
      }
    },
    required: true,
  },
  {
    field: 'nickName',
    label: '昵称',
    component: 'Input',
    required: true,
  },
  {
    label: '邮箱',
    field: 'email',
    component: 'Input',
    required: true,
    rules: [
      {
        validator(_, value) {
          return new Promise((resolve, reject) => {
            if (value && !isEmail(value)) {
              reject('请输入正确的邮箱格式')
              return
            }
            if (!value) {
              resolve()
              return
            }
            checkFieldExist('email', value, globalStore.getCheckFieldUserId || null)
              .then((res) => {
                if (res) {
                  reject('邮箱已被使用')
                } else {
                  resolve()
                }
              })
              .catch((err) => {
                reject(err.message || '验证失败')
              })
          })
        },
      },
    ],
  },
  {
    field: 'qq',
    label: 'QQ',
    component: 'Input',
    required: true
  },
  {
    field: 'city',
    label: '所在城市',
    component: 'Input',
  },
  {
    field: 'status',
    label: '状态',
    component: 'RadioButtonGroup',
    required: true,
    componentProps: {
      options: [
        { label: '正常', value: UserStatusEnum.NORMAL },
        { label: '冻结', value: UserStatusEnum.FROZEN },
        { label: '锁定', value: UserStatusEnum.LOCKING },
      ]
    },
    defaultValue: UserStatusEnum.NORMAL
  },
]
