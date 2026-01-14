<template>
  <div class="w-full z-5 shadow-2">
    <nav class="surface-900 text-white ">
      <div class="container mx-auto px-4" style="max-width: 1200px; margin: 0 auto;">
        <div class="flex align-items-center justify-content-between py-3">
          <!-- Logo/Brand -->
          <a href="#" class="no-underline">
            <div class="text-xl font-bold text-blue-300">{{ blogName }}</div>
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
              <i class="pi pi-lightbulb mr-3"></i>Trang Chủ
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
import { ref } from 'vue';
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';

const blogName = 'Your Blog Name';
const mobileMenuOpen = ref(false);

const toggle = () => {
  mobileMenuOpen.value = !mobileMenuOpen.value;
};
</script>

<style scoped>

</style>