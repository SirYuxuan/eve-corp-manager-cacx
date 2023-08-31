import { defineStore } from 'pinia'
import { store } from '/@/store'

interface GlobalState {
  // 校验字段
  checkFieldUserId: number | undefined;
}

export const useGlobalStore = defineStore({
  id: 'app-global',
  state: (): GlobalState => ({
    checkFieldUserId: undefined
  }),
  getters: {
    getCheckFieldUserId(): number | undefined{
      return this.checkFieldUserId
    },
  },
  actions: {
    setCheckFieldUserId(userId: number) {
      this.checkFieldUserId = userId
    },
    removeCheckFieldUserId(){
      this.checkFieldUserId = undefined
    }
  }
})

export function useGlobalStoreWithOut() {
  return useGlobalStore(store)
}

