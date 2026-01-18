<template>
  <div>
   <BlogItem :blog-list="blogList"/>

    <Pagination :get-blog-list="getBlogList" total-page="20"/>
  </div>
</template>
<script setup >
 import BlogItem from "@/components/blogList/BlogItem.vue";
 import Pagination from "@/components/blogList/Pagination.vue";
 import {reactive, onMounted} from "vue";
 import {useToast} from "@/plugins/primevueConfig/primePluginVue.js";

 const getBlogList = (val) => {
   console.log('getBlogList '+ val)
 }
 const toast = useToast()
 onMounted(() => {
   getBlogList(2)
 })
 const blogList = reactive([
   {
     id: 1,
     title: 'Tiêu đề bài viết có thể dài hơn một chút111!',
     date: '2025-08-07',
     category: {
       id: 1,
       name: 'Java',
     },
     views: 1234,
     words: 1234,
     readTime: 1234,
     description: '<h2>Mô tả bài viết</h2><p>Mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết</p><p>Mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết</p><p>Mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết</p><p><img src="/src/assets/img/pic.svg" style="max-width: 300px"></p>',
     tags: [
       {
         id: 1,
         name: 'Java',
         color:'red'
       },
       {
         id: 2,
         name: 'Nginx',
         color:'blue'
       },
       {
         id: 3,
         name: 'Spring Boot',
         color:'purple'
       },
       {
         id: 4,
         name: 'Vue',
         color:'red'
       },
       {
         id: 5,
         name: 'Mạng nơ-ron tích chập',
         color:'purple'
       },
     ]
   },
   {
     id: 2,
     title: 'Tiêu đề bài viết có thể dài hơn một chút111!',
     date: '2025-08-07',
     category: {
       id: 1,
       name: 'Java',
     },
     views: 1234,
     words: 1234,
     readTime: 1234,
     description: '<p>Mô tả bài viết</p><p>Mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết</p><p>Mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết</p><p>Mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết</p><p><img src="/src/assets/img/pic.svg" style="max-width: 300px"></p>',
     tags: [
       {
         id: 1,
         name: 'Java',
         color:'red'
       },
       {
         id: 2,
         name: 'Nginx',
         color:'blue'
       },
       {
         id: 3,
         name: 'Spring Boot',
         color:'purple'
       },
       {
         id: 4,
         name: 'Vue',
         color:'red'
       },
       {
         id: 5,
         name: 'Mạng nơ-ron tích chập',
         color:'purple'
       },
     ]
   },
   {
     id: 3,
     title: 'Tiêu đề bài viết có thể dài hơn một chút111!',
     date: '2025-08-07',
     category: {
       id: 1,
       name: 'Java',
     },
     views: 1234,
     words: 1234,
     readTime: 1234,
     description: '<p>Mô tả bài viết</p><p>Mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết</p><p>Mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết</p><p>Mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết</p><p><img src="/src/assets/img/pic.svg" style="max-width: 300px"></p>',
     tags: [
       {
         id: 1,
         name: 'Java',
         color:'red'
       },
       {
         id: 2,
         name: 'Nginx',
         color:'blue'
       },
       {
         id: 3,
         name: 'Spring Boot',
         color:'purple'
       },
       {
         id: 4,
         name: 'Vue',
         color:'red'
       },
       {
         id: 5,
         name: 'Mạng nơ-ron tích chập',
         color:'#722ED1'
       },
     ]
   }
 ])
</script>