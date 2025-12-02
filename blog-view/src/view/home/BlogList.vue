<template>
  <div>
   <BlogItem :blog-list="blogList"/>

    <Pagination/>
  </div>
</template>
<script setup >
 import BlogItem from "@/components/BlogItem.vue";
 import Pagination from "@/components/Pagination.vue";
 import {reactive} from "vue";

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
     description: '<p>Mô tả bài viết</p><p>Mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết</p><p>Mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết</p><p>Mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết</p><p><img src="img/avatar.jpg" style="max-width: 300px"></p>',
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
     description: '<p>Mô tả bài viết</p><p>Mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết</p><p>Mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết</p><p>Mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết</p><p><img src="img/avatar.jpg" style="max-width: 300px"></p>',
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
     description: '<p>Mô tả bài viết</p><p>Mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết</p><p>Mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết</p><p>Mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết mô tả bài viết</p><p><img src="img/avatar.jpg" style="max-width: 300px"></p>',
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