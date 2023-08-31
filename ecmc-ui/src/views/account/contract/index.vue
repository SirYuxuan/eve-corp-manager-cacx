<template>
  <PageWrapper dense contentFullHeight contentClass="flex">
    <ContractList class="w-4/6" @contract-change="handleContractChange" />
    <ContractItem class="w-2/6" :contractId="contractId" />
  </PageWrapper>
</template>
<script lang="ts" setup>
import { ref } from 'vue'
import { PageWrapper } from '/@/components/Page'
import ContractList from './list/index.vue'
import ContractItem from './item/index.vue'

const contractId = ref()

function handleContractChange(value) {
  contractId.value = value
}
</script>
