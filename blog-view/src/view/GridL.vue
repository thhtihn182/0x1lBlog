<template>
  <section class="counter">
    <h1 class="title">🚀 Vue 3 + TypeScript</h1>
    <PinTop/>
    <p class="count">
      Count:
      <span>{{ count }}</span>
    </p>

    <div class="actions">
      <button @click="increase">Increase</button>
      <button @click="decrease" :disabled="count === 0">
        Decrease
      </button>
      <button @click="reset">Reset</button>
    </div>
  </section>
</template>

<script setup lang="ts">
import PinTop from "@/components/blogList/PinTop.vue";
import { ref, computed } from 'vue'

const count = ref<number>(0)


const isEven = computed<boolean>(() => count.value % 2 === 0)


const increase = (): void => {
  count.value++
}

const decrease = (): void => {
  if (count.value > 0) count.value--
}

const reset = (): void => {
  count.value = 0
}
</script>

<style scoped>
.counter {
  max-width: 400px;
  margin: 60px auto;
  text-align: center;
  font-family: system-ui;
}

.title {
  font-size: 28px;
  margin-bottom: 20px;
}

.count span {
  font-weight: bold;
  font-size: 22px;
  margin-left: 6px;
}

.actions button {
  margin: 6px;
  padding: 8px 14px;
  border-radius: 8px;
  border: none;
  cursor: pointer;
  background: #42b883;
  color: white;
  font-weight: 600;
}

.actions button:hover {
  opacity: 0.85;
}

.actions button:disabled {
  background: gray;
  cursor: not-allowed;
}
</style>
