<template>
  <div style="z-index: 10">
    <div class="pb-2 px-4 pt-4 surface-card shadow-2 mb-5 relative m-box">

      <div class="gradient-badge gold">
        <div class="badge-triangle"></div>
        <div class="badge-content">
          <span class="badge-text">TOP</span>
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
                {{ blog.title }}
              </a>
            </h2>
          </div>

          <!-- Thông tin bài viết -->
          <div class="col-12 text-center">
            <div class="flex flex-wrap justify-content-center gap-4">
              <div class="flex align-items-center m-datetime">
                <font-awesome-icon icon="calendar-alt" class="mr-2" />
                <span>{{ formatDate(blog.updateTime) }}</span>
              </div>

              <div class="flex align-items-center m-views">
                <font-awesome-icon icon="eye" class="mr-2" />
                <span>{{ blog.views }}</span>
              </div>

              <div class="flex align-items-center m-common-black">
                <font-awesome-icon icon="pencil-alt" class="mr-2" />
                <span>Số chữ ≈ {{ blog.words }} từ</span>
              </div>

              <div class="flex align-items-center m-common-black">
                <font-awesome-icon icon="clock" class="mr-2" />
                <span>Thời gian đọc ≈ {{ blog.readTime }} phút</span>
              </div>
            </div>
          </div>
          <!-- Category button -->
          <a :href="blog.category.id" class="col-12 mb-3" v-if="blog.category">
            <Button
                :label="blog.category?.name"
                icon="pi pi-folder-open"
                class="ribbon-label m-text-500 text-base-ct "

            />
          </a>
          <div class="px-3 py-2">
            <!-- Mô tả bài viết -->
            <div class="typo m-padded-tb-small px-3 line-numbers match-braces rainbow-braces" v-html="blog.content"></div>
            <!-- Divider -->
            <div class="col-12">
              <div class="border-top-1 surface-border my-2"></div>
            </div>
            <!-- Tags -->
            <div class="m-padded-tb-no">
              <Tag v-if="blog.tags" :list-tag="blog.tags"></Tag>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref, onMounted, nextTick} from 'vue'
import Tag from '@/components/tag/Tag.vue'
import { getBlogById } from '@/network/blog'
import {useToast} from "@/plugins/primevueConfig/primePluginVue.js";
import { formatDate } from "@/util/dateTimeFormatUtils.js";
import {useRoute} from "vue-router";
const route = useRoute()
const toast = useToast()
const blog = ref({})
const category = ref()


const getTagSeverity = (semanticColor) => {
  const colorMap = {
    'red': 'danger',
    'orange': 'warning',
    'yellow': 'warning',
    'olive': 'success',
    'green': 'success',
    'teal': 'info',
    'blue': 'info',
    'violet': 'secondary',
    'purple': 'secondary',
    'pink': 'danger',
    'brown': 'warning',
    'grey': 'secondary',
    'black': 'contrast'
  }
  return colorMap[semanticColor] || 'info'
}


const fetchBlog = async () => {
  try {
    const response = await getBlogById(route.query.id)
    if (response.code === 200) {
      blog.value = response.data
      console.log(blog.value)

    } else {

    }
  } catch (error) {

  }
}


// ========== IMPORT THEME ==========

onMounted(async () => {
  await fetchBlog();
  await nextTick();

  // Sử dụng highlightAll để highlight tất cả code trong DOM
  Prism.highlightAll();
});
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