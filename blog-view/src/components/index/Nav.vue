<template>
  <div class="fixed-nav">
    <nav class="text-white" style="background-color: rgb(27, 28, 29); border: none; min-height: 1.85714286em;">
      <div class="container mx-auto px-4">
        <div class="flex align-items-center justify-content-between py-1">
          <!-- Logo/Brand -->
          <a href="#" class="no-underline">
            <h2 class="m-blue rollText" style="margin: 0; padding: 0.5rem;">{{ blogName }}</h2>
          </a>

          <!-- Desktop Menu Items -->
          <div class="hidden md:flex align-items-center gap-6">
            <a href="#" class="text-white no-underline hover:text-blue-300 transition-colors transition-duration-200">
              <i class="pi pi-lightbulb mr-2"></i>Trang chủ
            </a>
            <a href="#" class="text-white no-underline hover:text-blue-300 transition-colors transition-duration-200">
              <i class="pi pi-tags mr-2"></i>Thể Loại
            </a>
            <a href="#" class="text-white no-underline hover:text-blue-300 transition-colors transition-duration-200">
              <i class="pi pi-copy mr-2"></i>Tag
            </a>
            <a href="#" class="text-white no-underline hover:text-blue-300 transition-colors transition-duration-200">
              <i class="pi pi-info-circle mr-2"></i>Về tôi
            </a>

            <!-- Search Bar -->
            <div class="relative">
              <span class="p-input-icon-left">
                <i class="pi pi-search absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400"></i>
                <InputText
                    type="text"
                    placeholder="Search..."
                    class="bg-surface-800 border-surface-700 text-white w-12rem lg:w-15rem"
                />
              </span>
            </div>
          </div>

          <!-- Mobile Menu Button -->
          <Button
              icon="pi pi-bars"
              class="p-button-text p-button-lg md:hidden text-white"
              @click="toggle"
          />
        </div>

        <!-- Mobile Menu (Dropdown) -->
        <div v-if="mobileMenuOpen" class="md:hidden surface-900 py-3 mt-2 rounded">
          <div class="flex flex-column gap-3 px-4">
            <a href="#" class="text-white no-underline hover:text-blue-300 py-2">
              <i class="pi pi-lightbulk mr-3"></i>Trang Chủ
            </a>
            <a href="#" class="text-white no-underline hover:text-blue-300 py-2">
              <i class="pi pi-tags mr-3"></i>Thể Loại
            </a>
            <a href="#" class="text-white no-underline hover:text-blue-300 py-2">
              <i class="pi pi-copy mr-3"></i>Tag
            </a>
            <a href="#" class="text-white no-underline hover:text-blue-300 py-2">
              <i class="pi pi-info-circle mr-3"></i>Về Tôi
            </a>

            <!-- Mobile Search -->
            <div class="pt-2">
              <span class="p-input-icon-left w-full">
                <i class="pi pi-search"></i>
                <InputText
                    type="text"
                    placeholder="Search..."
                    class="w-full bg-surface-700 border-surface-600 text-white"
                />
              </span>
            </div>
          </div>
        </div>
      </div>
    </nav>
  </div>
</template>

<script setup>
import { ref } from 'vue'
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
/* Fixed Navigation */
.fixed-nav {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
  width: 100%;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3);
}


.container {
  max-width: 1400px;
  margin-left: auto;
  margin-right: auto;
}

/* Hiệu ứng scroll cho nav */
.fixed-nav {
  transition: all 0.3s ease;
}

/* Thêm shadow khi scroll */
.fixed-nav.scrolled {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  background-color: rgba(27, 28, 29, 0.95) !important;
  backdrop-filter: blur(10px);
}

/* Tối ưu cho mobile */
@media (max-width: 768px) {
  .fixed-nav {
    padding: 0 1rem;
  }

  body {
    padding-top: 52px; /* Chiều cao nhỏ hơn trên mobile */
  }
}

/* Đảm bảo mobile menu nằm trên các element khác */
.md\:hidden {
  z-index: 1001;
}

/* Hiệu ứng hover cho menu items */
a:hover {
  transform: translateY(-1px);
}
</style>