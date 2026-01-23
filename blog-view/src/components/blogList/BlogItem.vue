<template>
  <div style="z-index: 10">
    <div class="m-padded-tb-large p-4 surface-card shadow-2 mb-5 relative m-box"
         v-for="item in blogList"
         :key="item.id">


      <div class="gradient-badge gold">
        <div class="badge-triangle"></div>
        <div class="badge-content">
          <span class="badge-text">TOP</span>
          <!--          <div class="arrow-up"></div>-->
        </div>
        <div class="badge-shine"></div>
      </div>

      <!-- Container với flex layout -->
      <div class="flex flex-column">
        <div >
          <!-- Tiêu đề -->
          <div class="col-12 text-center" style="padding-top: 0">
            <h2 class="header m-scaleup">
              <a href="#" class="text-900 hover:text-primary transition-colors no-underline">
                {{ item.title }}
              </a>
            </h2>
          </div>

          <!-- Thông tin bài viết -->
          <div class="col-12 text-center">
            <div class="flex flex-wrap justify-content-center gap-4">
              <div class="flex align-items-center m-datetime">
                <font-awesome-icon icon="calendar-alt" class="mr-2" />
                <span>{{ formatDate(item.date) }}</span>
              </div>

              <div class="flex align-items-center m-views">
                <font-awesome-icon icon="eye" class="mr-2" />
                <span>{{ item.views }}</span>
              </div>

              <div class="flex align-items-center m-common-black">
                <font-awesome-icon icon="pencil-alt" class="mr-2" />
                <span>Số chữ ≈ {{ item.words }} từ</span>
              </div>

              <div class="flex align-items-center m-common-black">
                <font-awesome-icon icon="clock" class="mr-2" />
                <span>Thời gian đọc ≈ {{ item.readTime }} phút</span>
              </div>
            </div>
          </div>

          <!-- Category button -->
          <a :href="item.category?.id" class="col-12 mb-3">
            <Button
                :label="item.category.name"
                icon="pi pi-folder-open"
                class="ribbon-label m-text-500 text-base-ct "

            />
          </a>

          <!-- Mô tả bài viết -->
          <div class="m-padded-tb-small m-markdown" v-html="item.description"></div>

          <!-- Nút đọc toàn bộ -->
          <div class="col-12">
            <div class="flex align-items-center">
              <a :href="item.id" class="color-btn">
                <span >Đọc toàn bộ</span>
              </a>
            </div>
          </div>

          <!-- Divider -->
          <div class="col-12">
            <div class="border-top-1 surface-border my-4"></div>
          </div>

          <!-- Tags -->
          <div class="m-padded-tb-no">
            <div class="flex flex-wrap gap-2">
              <a
                  v-for="tag in item.tags"
                  :key="tag.id"
                  :href="tag.id"
                  class="inline-flex align-items-center px-3 py-2 border-round font-medium"
                  :style="{
                  backgroundColor: tag.color,
                  color: 'white'
                }"
              >
                {{ tag.name }}
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from 'vue'
import {formatDate} from "@/util/dateTimeFormatUtils.js";


defineProps({
  blogList: {
    type: Array,
    required: true
  }
})
</script>

<style scoped>
.header{
  border: none;
  margin: 0 1rem;
  top: 13px;
  padding: 0 0;
  font-size: 1.71428571rem;
  font-family: Lato, 'Helvetica Neue', Arial, Helvetica, sans-serif;
  font-weight: 700;
  line-height: 1.28571429em;
  text-transform: none;
  color: rgba(0, 0, 0, .87);
}
</style>