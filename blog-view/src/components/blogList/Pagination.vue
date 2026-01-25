<template>
  <div class="" style="text-align: center">
    <Paginator
        :rows="pageSize"
        :totalRecords="totalRecords"
        @page="handlePageChange"
        :template="{
          layout: 'PrevPageLink PageLinks NextPageLink',

        }"
        class="custom-paginator"
    />
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue'

const props = defineProps({
  getBlogList: {
    type: Function,
    required: true
  },
  totalPage: {
    type: Number,
    required: true
  }
})

const pageNum = ref(1)
const first = ref(0)
const pageSize = ref(5)

const totalRecords = computed(() => props.totalPage * pageSize.value)



const handlePageChange = (event) => {
  console.log(event)
  console.log(first.value)
  pageNum.value = event.page
  props.getBlogList(event.page+1)
}
</script>

<style scoped>
:deep(.p-paginator){
  background: none!important;
}
:deep(.p-paginator-page),
:deep(.p-paginator-next),
:deep(.p-paginator-last),
:deep(.p-paginator-first),
:deep(.p-paginator-prev){
  border-radius: 0 !important;
  color: #000000 ;
  background: #ffffff ;
  margin-left: 4px !important;
  margin-right: 4px !important;
}
:deep(.p-paginator-page.p-disabled),
:deep(.p-paginator-next.p-disabled),
:deep(.p-paginator-last.p-disabled),
:deep(.p-paginator-first.p-disabled),
:deep(.p-paginator-prev.p-disabled) {
  background: rgba(0, 0, 0, 0.18) !important;
  color: #000000 !important;
  border-color: #bdbdbd !important;
}
:deep(.p-paginator-page):hover,
:deep(.p-paginator-next):hover,
:deep(.p-paginator-last):hover,
:deep(.p-paginator-first):hover,
:deep(.p-paginator-prev):hover {
  background: #e0e0e0 !important;
  color: #00a7e0 !important;
  border-color: #bdbdbd !important;
}
:deep(.p-paginator-page.p-paginator-page-selected){
  background: #00a7e0 !important;
  color: #ffffff !important;
}
</style>