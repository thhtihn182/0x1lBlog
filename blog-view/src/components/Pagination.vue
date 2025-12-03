<template>
  <div v-if="queryInfo.pageTotal > 1" style="text-align:center; margin-top: 1em;">
    <el-pagination
        @current-change="handleCurrentPage"
        :current-page="queryInfo.pageNum"
        :page-size="queryInfo.pageSize"
        layout="total, prev, pager, next"
        :total="queryInfo.pageSize * queryInfo.pageTotal"
        background
        small
    />
  </div>
</template>

<script setup>
import {reactive, ref} from "vue";

const props = defineProps({
  getBlogList: {
    type: Function,
    required: true,
  },
  totalPage: {
    type: Number,
    default: 2
  },
  pageNum: {
    type: Number,
    default: 2
  }
})

const queryInfo = reactive({
  pageNum: 1,
  pageSize: 10,
  pageTotal: 10,
})

const handleCurrentPage = (newPage) => {
  queryInfo.pageNum = newPage
  props.getBlogList(newPage)
}
</script>

<style>
.el-pagination.is-background .btn-next,
.el-pagination.is-background .btn-prev,
.el-pagination.is-background .el-pager li {
  background-color: #ffffff;
}

.el-pagination.is-background .el-pager li:not(.disabled).active {
  background-color: #409EFF !important;
}
</style>