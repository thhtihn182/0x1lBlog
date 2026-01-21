<template>
  <div class="fixed-nav">
    <nav  ref="navRef" class="text-white" style="background-color: rgb(27, 28, 29); border: none;">
      <div class="container  ">
        <div class="flex-nav">
            <router-link class="no-underline nav-line" to="/">
              <h3 class="m-blue text-base-c t-m-bold  "
                  style="margin: 0; padding: 0.900007143em ;font-size: 1.28571429rem;">{{ blogName }}
              </h3>
            </router-link>
            <router-link :class="mobileMenuOpen? 'm-mobile-show':'m-mobile-hide'"
                class=" nav-line text-white md/:hidden no-underline p-3
                hover:text-blue-300 text-base-c t-m-bold transition-colors
                transition-duration-200" to="">
                <i class="pi pi-home mr-2"></i>Trang chủ
            </router-link>
            <router-link :class="mobileMenuOpen? 'm-mobile-show':'m-mobile-hide'"
                class=" nav-line text-white md/:hidden no-underline hover:text-blue-300 text-base-c
                t-m-bold transition-colors transition-duration-200 p-3" to="">
                  <i class="pi pi-tags mr-2"></i>Thể Loại
            </router-link>
          <router-link :class="mobileMenuOpen? 'm-mobile-show':'m-mobile-hide'"
                       class=" nav-line text-white md/:hidden no-underline hover:text-blue-300
                text-base-c t-m-bold transition-colors transition-duration-200 p-3" to="/archives">
            <i class="pi pi-copy mr-2"></i>Lưu trữ
          </router-link>
            <router-link :class="mobileMenuOpen? 'm-mobile-show':'m-mobile-hide'"
                class=" nav-line text-white md/:hidden no-underline hover:text-blue-300
                text-base-c t-m-bold transition-colors transition-duration-200 p-3" to="">
              <i class="pi pi-copy mr-2"></i>Tag
            </router-link>
            <router-link :class="mobileMenuOpen? 'm-mobile-show':'m-mobile-hide'"
                class=" nav-line text-white md/:hidden no-underline hover:text-blue-300 text-base-c
                t-m-bold transition-colors transition-duration-200 p-3" to="">
              <i class="pi pi-info-circle mr-2"></i>Về tôi
            </router-link>

          <div class=" nav-line  " :class="mobileMenuOpen? 'm-mobile-show':'m-mobile-hide'"
          style="margin-left: auto !important;">
                  <i class="pi pi-search absolute left-3 top-1/2 transform-translate-y-1/2 text-gray-400"></i>
                  <InputText
                      type="text"
                      placeholder="Search..."
                      class="bg-surface-800 border-surface-700 text-white w-12rem lg:w-15rem"
                  />
          </div>
        </div>
        <Button
            icon="pi pi-bars"
            class="p-button-text p-2 m-2 m-right-top md:hidden text-white"
            @click="toggle"
        />
      </div>
    </nav>
  </div>
</template>

<script setup>
import { ref} from 'vue'
import { defineProps } from 'vue'
const props = defineProps({
  blogName: {
    type: String,
    required: false
  }
})
const mobileMenuOpen = ref(false);
const toggle = () => {
  mobileMenuOpen.value = !mobileMenuOpen.value;
};


</script>

<style scoped>

.fixed-nav {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
  width: 100%;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3);
}
.t-m-bold{
  font-weight: 700;
  color: rgba(255, 255, 255, .9);
}
.container {
  max-width: 1400px;
  margin-left: auto;
  margin-right: auto;

}

.fixed-nav {
  transition: all 0.3s ease;
}
.fixed-nav.scrolled {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  background-color: rgba(27, 28, 29, 0.95) !important;
  backdrop-filter: blur(100 px);
}
.nav-line{
  align-items: center;
  position: relative;
}
@media (min-width: 768px) {
  .nav-line:before {
    position: absolute;
    content: '';
    top: 0;
    right: 0;
    height: 100%;
    width: 1px;
    background: rgba(255, 255, 255, .08);
  }
  .nav-line{
    display: flex !important;
  }
  .flex-nav{
    display: flex; flex-direction: row;
  }
}
@media (max-width: 767px) {
  .nav-line:before {
    position: absolute;
    content: '';
    top: auto;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 1px;
    background: rgba(255, 255, 255, .08);
  }
  .flex-nav{
    display: flex; flex-direction: column;
  }
}
.md\:hidden {
  z-index: 1001;
}


</style>