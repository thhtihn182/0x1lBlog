<template>
  <div class="grid">
    <!-- Tạo 6 columns với màu tự động -->
    <div
        v-for="n in 6"
        :key="n"
        class="col-2 p-6 text-center"
        :class="getColorClass(n)"
    >
      Column {{ n }}
    </div>
  </div>
</template>

<script setup>
const getColorClass = (index) => {
  const colors = [

    'bg-primary text-green-800 border-green-300',
    'bg-blue-100 text-blue-800 border-blue-300',
    'bg-yellow-100 text-yellow-800 border-yellow-300',
    'bg-orange-100 text-orange-800 border-orange-300',
    'bg-red-100 text-red-800 border-red-300',
    'bg-purple-100 text-purple-800 border-purple-300',
    'bg-pink-100 text-pink-800 border-pink-300',
    'bg-primary-100 text-cyan-800 border-cyan-300'
  ]
  return colors[index % colors.length]
}
</script>