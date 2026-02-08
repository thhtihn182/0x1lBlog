<template>
  <Paginator
    :rows="pageSize"
    :total-records="total"
    @page="goToPage"
    :pageLinkSize="7"
    :alwaysShow="false"
  />
</template>

<script setup>
import {computed, onMounted, ref} from "vue";

const props = defineProps({
  page: {
    type: Number,
    required: true
  },
  blogId: {
    type: Number,
    required: true
  },
  detailPaginator:{
    type: Object,
    required: true
  }
})

const pageSize = computed(() => props.detailPaginator.pageSize)
const total = computed(() => props.detailPaginator.total)

const emit = defineEmits(['page-change',])

const pageNum = ref(1);
const query = computed(() => ({
  page: props.page,
  blogId: props.blogId,
  pageNum: pageNum.value,
  pageSize: 5,
}))

const goToPage = (pageState) => {
  pageNum.value = pageState.page+1
  emit('page-change',query.value)
}

onMounted(() => emit('page-change',query.value))

</script>

<style scoped>
:deep(.p-paginator-page),
:deep(.p-paginator-next),
:deep(.p-paginator-first),
:deep(.p-paginator-last),
:deep(.p-paginator-prev) {
  font-weight: bolder;
  border-radius: 0;
}
:deep(.p-paginator-page.p-paginator-page-selected){
  color: #005cd5;
  background: #c1efff;
  pointer-events: none ;
  cursor: default ;
}
:deep(.p-paginator-page:not(.p-disabled):not(.p-paginator-page-selected):hover),
:deep(.p-paginator-next:not(.p-disabled):hover),
:deep(.p-paginator-first:not(.p-disabled):hover),
:deep(.p-paginator-last:not(.p-disabled):hover),
:deep(.p-paginator-prev:not(.p-disabled):hover) {
  background: rgba(200, 240, 255, 0.35);
}


</style>

