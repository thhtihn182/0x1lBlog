
import { createApp } from 'vue'
import { createPinia } from "pinia";
import App from './App.vue'
import router from 'router/index.js'

import '@/util/dateTimeFormatUtils.js'
import '@/assets/css/base.css'
import '@/assets/css/badge.css'
import PrimeVuePlugin from "@/plugins/primevueConfig/primePluginVue.js";

const app = createApp(App)
const pinia = createPinia()
app.use(router)
app.use(pinia)
app.use(PrimeVuePlugin)
app.mount('#app')
