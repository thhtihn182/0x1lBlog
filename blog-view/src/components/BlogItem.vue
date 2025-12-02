<template>
  <div>
    <div class="p-segment m-padded-tb-large m-margin-bottom-big m-box" v-for="item in blogList" :key="item.id">
      <div class="p-container">
        <div class="p-grid">
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

          <Button class="ribbon-label" type="button" :label="item.category.name" icon="pi pi-folder-open" badge="2" />
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