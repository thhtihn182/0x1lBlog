<template>
  <div class="m-padded-tb-big">
    <div class="p-container">
      <!-- Thêm style inline override -->
      <div style="display: flex; flex-direction: row; width: 100%;">

        <div style="flex: 0 0 25%;">
          <Introduction/>
        </div>

        <div style="flex: 0 0 75%;">
          <BlogList/>
        </div>

      </div>
    </div>
  </div>
</template>


<script setup>
import BlogList from "@/view/home/BlogList.vue";
import Introduction from "@/components/sidebar/Introduction.vue";
</script>


<style scoped>
.p-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 1rem;
}
.p-grid > div {
  border: 1px solid #41c5ff;
  min-height: 400px;
  padding: 20px;
}
</style>

