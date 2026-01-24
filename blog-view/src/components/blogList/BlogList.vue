<template>
  <div>
   <BlogItem :blog-list="blogList"/>

    <Pagination :getBlogList="getBlogList" :totalPage="totalPage"/>
  </div>
</template>
<script setup >
 import BlogItem from "@/components/blogList/BlogItem.vue";
 import Pagination from "@/components/blogList/Pagination.vue";
 import {useToast} from "@/plugins/primevueConfig/primePluginVue.js";

 const toast = useToast()

 const props = defineProps({
   getBlogList:{
     type: Function,
     required: true
   },
   blogList:{
     type: Array,
     required: true,
   },
   totalPage:{
     type: Number,
     required: true,
   }
 })
</script>