<template>
  adfsa
  <Breadcrumb :parent-title="'ádá'"/>
  <div>{{a}}</div>
  <button @click="increase">tăng</button>
</template>

<script setup lang="ts">
import Breadcrumb from "@/components/Breadcrumb.vue";
import {ref} from "vue";
const a = ref(0)
const b = ref()
const c: string = "hello"
function increase() {
  a.value++
}
function sum(a: number, b: number) {
  return a + b
}
</script>