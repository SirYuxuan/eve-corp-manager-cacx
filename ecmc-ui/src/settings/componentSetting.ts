// 用于在不修改组件的情况下配置某些组件的常规配置

import type { SorterResult } from '../components/Table'

export default {
  // 基础表格配置
  table: {
    // 表单接口请求常规配置
    fetchSetting: {
      // 传递到后台的当前页的字段名
      pageField: 'page',
      // 传递到后台的每页大小的字段名
      sizeField: 'size',
      // Field name of the form data returned by the interface
      listField: 'items',
      // Total number of tables returned by the interface field name
      totalField: 'total',
    },
    // Number of pages that can be selected
    pageSizeOptions: ['10', '50', '80', '100'],
    // Default display quantity on one page
    defaultPageSize: 50,
    // Default Size
    defaultSize: 'small',
    // Custom general sort function
    defaultSortFn: (sortInfo: SorterResult) => {
      const { field, order } = sortInfo
      if (field && order) {
        return {
          // The sort field passed to the backend you
          'order': field,
          // Sorting method passed to the background asc/desc
          'orderBy': order === 'descend' ? 'desc' : 'asc',
        }
      } else {
        return {}
      }
    },
    // Custom general filter function
    defaultFilterFn: (data: Partial<Recordable<string[]>>) => {
      return data
    },
  },
  // scrollbar setting
  scrollbar: {
    // Whether to use native scroll bar
    // After opening, the menu, modal, drawer will change the pop-up scroll bar to native
    native: false,
  },
}
