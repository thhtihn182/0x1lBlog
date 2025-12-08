<template>
  <div>
    <div class="p-segment m-padded-tb-large m-margin-bottom-big m-box" v-for="item in blogList" :key="item.id">
      <div class="p-container">
        <div class="p-grid">
<!--          <svg viewBox="0 0 1024 1024" fill="currentColor">-->
<!--            <path d="M0 520.9c0-69.4 13.5-135.7 40.5-199 26.9-63.3 63.3-117.8 109-163.5 45.6-45.6
100-82 163.5-109A502 502 0 0 1 512 8.9c69.3 0 135.7 13.5 199 40.5 63.4 27 117.9 63.3 163.5 109 45.7 45.6 82
100.2 109 163.5A502 502 0 0 1 1024 520.9c0 111.3-32.5 211.5-97.5 300.5-65 89-148.5 150.8-250.5 185.5-5.3
 0-9.3-1-12-3-2.7-2-4.2-4-4.5-6a60 60 0 0 1-.5-9 8 8 0 0 1-1-3v-129c0-40.6-14.3-75.3-43-104 76.7-13.3 134-41.8
 172-85.5 38-43.7 57-96.5 57-158.5 0-58-18.7-108.3-56-151 13.3-42.7 11-87.7-7-135-18.7-1.3-39 1.8-61 9.5-22
 7.7-38.3 14.8-49 21.5-10.6 6.7-20.3 13-29 19-38.7-10.7-81.8-16-129.5-16-47.7 0-90.5 5.4-128.5 16-7.3-5.3-15.9-11.2-25.5-17.5-9.7-6.3-26.5-14-50.5-23-24-9-45.7-12.8-65-11.5-18.7 48-20.6 93.6-6 137-36.7 42.7-55 93-55 151 0 62 18.7 114.7 56 158 37.3 43.3 94.7 72 172 86a142 142 0 0 0-40 70c-56.7 14-96.3 4-119-30-42-61.3-79.7-83.3-113-66-4.7 4.7-4 9.5 2 14.5 6 5 15 11.7 27 20 12 8.4 21 17.5 27 27.5.7 1.3 2.5 6.2 5.5 14.5a832 832 0 0 0 9 24c3 7.7 8.7 16.2 17 25.5 8.3 9.3 18 17.2 29 23.5 11 6.3 26.2 11 45.5 14 19.3 3 41.3 3.1 66 .5v100.5c0 1-.2 2.6-.5 5-.3 2.3-1 4-2 5-1 1-2.3 2-4 3a13 13 0 0 1-6.5 1.5c-2.7 0-6-.3-10-1-101.3-35.3-183.8-97.2-247.5-185.5C31.8 731 0 631.6 0 520.9z"/>-->
<!--          </svg>-->
          <!-- Tiêu đề -->
          <div class="p-row m-padded-tb-small">
            <h2 class="p-header m-center m-scaleup">
              <a href="#" class="m-black">{{ item.title }}</a>
            </h2>
          </div>
          <!-- Thông tin bài viết -->
          <div class="p-row m-padded-tb-small">
            <div class="p-list m-center">
              <div class="p-item m-datetime">
                <i class="pi pi-calendar"></i><span>{{ item.date }}</span>
              </div>
<!--              <div class="p-item m-category">-->
<!--                <i class="pi pi-folder"></i><a :href="item.id">{{ item.category.name }}</a>-->
<!--              </div>-->
              <div class="p-item m-views">
                <i class="pi pi-eye"></i><span>{{ item.views }}</span>
              </div>
              <div class="p-item m-common-black">
                <i class="pi pi-pencil"></i><span>Số chữ≈{{ item.words }} từ</span>
              </div>
              <div class="p-item m-common-black">
                <i class="pi pi-clock"></i><span>Thời gian đọc≈{{ item.readTime }} phút</span>
              </div>
            </div>
          </div>

          <Button class="ribbon-label m-text-500" type="button"
                  :label="item.category.name" icon="pi pi-folder-open" badge="2" />
          <!-- Mô tả bài viết -->
          <div class="m-padded-tb-small m-description" v-html="item.description"></div>
          <!-- Nút đọc toàn bộ -->
          <div class="p-row m-padded-tb-small">
            <a :href="item.id" class="color-btn">Đọc toàn bộ</a>
          </div>
          <!-- Đường kẻ ngang -->
          <div class="p-divider"></div>
          <!-- Thẻ -->
          <div class="p-row m-padded-tb-no">
            <div class="p-column m-padding-left-no">
              <a :href="tag.id" class="p-tag m-text-500 m-margin-small"
                 :style="{ backgroundColor: tag.color }"
                  style="color: white"
                 :class="tag.color" v-for="tag in item.tags" :key="tag.id">
                {{ tag.name }}</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
defineProps({
  blogList: {
    type: Array,
    required: true
  }
})
</script>

<style scoped>
.ribbon-label {
  max-height: 30px;
  margin-bottom: 15px !important;
  left: -46px !important;
  position: relative;
  text-decoration: none;
  display: inline-flex;
  align-items: center;

  background: linear-gradient(135deg, #f2711c 0%, #fbbd08 100%) !important;
  border: none !important;
  color: white !important;
}

/* Hiệu ứng hover (cho thẻ a và button) */
.ribbon-label:hover {
  opacity: 0.9;
  transform: translateY(-1px);
  transition: all 0.2s ease;
}
.m-datetime {
  color: #00a7e0 !important;
}

.m-datetime * {
  vertical-align: middle !important;
}

.m-datetime i {
  margin-right: 3px !important;
}

.m-views {
  color: #ff3f1f !important;
}

.m-views * {
  vertical-align: middle !important;
}

.m-views i {
  margin-right: 3px !important;
}

.m-common-black {
  color: #000 !important;
}

.m-common-black * {
  vertical-align: middle !important;
}

.m-common-black i {
  margin-right: 3px !important;
}
</style>