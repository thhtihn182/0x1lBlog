<template>
  <div class="" style="text-align: center">
    <Paginator
        :rows="pageSize"
        :totalRecords="totalRecords"
        @page="handlePageChange"
        :template="{
        layout: 'PrevPageLink PageLinks NextPageLink'
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

</style>