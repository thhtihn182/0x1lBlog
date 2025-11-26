<template>
  <nav class="m-padded-tb-mini m-shadow-small" style="background: #1b1c1d; border: none;">
    <div style="max-width: 1200px; margin: 0 auto;">
      <div style="background: transparent; border: none;">
        <div>
          <h2 class="m-blue" style=" margin: 0; padding: 0.5rem;">{{ blogName }}</h2>
        </div>
        <div>
          <a href="#" :class="{'m-mobile-hide': mobileHide}" style="color: rgba(255,255,255,0.9);">
            <i class="pi pi-home"></i>
            <span>Trang chủ</span>
          </a>
          <a href="#" :class="{'m-mobile-hide': mobileHide}" style="color: rgba(255,255,255,0.9);">
            <i class="pi pi-lightbulb"></i>
            <span>Thể loại</span>
          </a>
          <a href="#" :class="{'m-mobile-hide': mobileHide}" style="color: rgba(255,255,255,0.9);">
            <i class="pi pi-tags"></i>
            <span>Tag</span>
          </a>
          <a href="#" :class="{'m-mobile-hide': mobileHide}" style="color: rgba(255,255,255,0.9);">
            <i class="pi pi-copy"></i>
            <span>Lưu trữ</span>
          </a>
          <a href="#" :class="{'m-mobile-hide': mobileHide}" style="color: rgba(255,255,255,0.9);">
            <i class="pi pi-info-circle"></i>
            <span>Về tôi</span>
          </a>
        </div>
        <div>
          <div :class="{'m-mobile-hide': mobileHide}" style="background: transparent;">
            <InputText
                type="text"
                placeholder="Tìm kiếm..."
                style="background: transparent; border: 1px solid rgba(255,255,255,0.3); color: white;"
            />
            <Button
                icon="pi pi-search"
                style="color: rgba(255,255,255,0.8);"
            />
          </div>
        </div>
      </div>
    </div>
    <Button
        icon="pi pi-bars"
        class="m-right-top m-mobile-show"
        style="color: #b3fff6;"
        @click="toggle"
    />
  </nav>
</template>

<script setup>
import { ref } from 'vue'
import { defineProps } from 'vue'
import InputText from 'primevue/inputtext'
import Button from 'primevue/button'

const props = defineProps({
  blogName: {
    type: String,
    required: false
  }
})

const mobileHide = ref(true)

const toggle = () => {
  mobileHide.value = !mobileHide.value
}
</script>

<style scoped>

</style>