import { FormSchema } from '/@/components/Form'
import { all } from '/@/api/account/userAccount'
export const crudFormSchema: FormSchema[] = [
  {
    field: 'title',
    label: '商品标题',
    component: 'Input',
    componentProps: {
      disabled: true
    }
  },
  {
    field: 'accountId',
    label: '兑换角色',
    component: 'ApiSelect',
    componentProps: () => {
      return {
        placeholder: '请选择角色',
        api: all,
        labelField: 'characterName',
        valueField: 'id',
      }
    },
    required: true,
  },
  {
    field: 'realLp',
    label: '商品单价',
    component: 'ShowContext',
    componentProps: {
      type: 'tag',
    }
  },
  {
    field: 'num',
    label: '兑换数量',
    component: 'InputNumber',
    required: true,
    defaultValue: 1,
    componentProps: ({ formModel }) => {
      return {
        min: 1,
        onChange: (e: any) => {
          formModel.lp = formModel.realLp * e
          formModel.surplusLp =  formModel.nowLp - formModel.lp
        }
      }
    }
  },
  {
    field: 'content',
    label: '兑换说明',
    component: 'InputTextArea',
    componentProps: {
      row: 3
    },
  },
  {
    field: 'nowLp',
    label: '我的LP',
    component: 'ShowContext',
    componentProps: {
      type: 'tag',
    }
  },
  {
    field: 'lp',
    label: '所需LP',
    component: 'ShowContext',
    componentProps: {
      type: 'tag',
    }
  },
  {
    field: 'surplusLp',
    label: '剩余LP',
    component: 'ShowContext',
    componentProps: {
      type: 'tag',
      tag: {
        color: (x) => x > 0 ? 'blue' : 'red'
      }
    },
    rules: [
      {
        validator(_, value) {
          return new Promise((resolve, reject) => {
            if (value>0){
              resolve()
              return
            }
            reject('您的LP余额不足')
          })
        }
      }
    ]
  },

]
