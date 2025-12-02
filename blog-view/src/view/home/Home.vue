<template>
  <div class="m-padded-tb-big">
    <div class="p-container">
      <div class="p-grid p-gutter">
        <div class="p-col-12 p-md-8 p-lg-9">
          <BlogList/>
        </div>
      </div>
    </div>
  </div>
</template>


<script setup>
import BlogList from "@/view/home/BlogList.vue";
</script>


<style scoped>
.p-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 1rem;
}
</style>

