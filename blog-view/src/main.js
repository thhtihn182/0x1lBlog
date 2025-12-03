
import { createApp } from 'vue'
import App from './App.vue'
import router from 'router/index.js'
import { createPinia } from "pinia";
import '@/util/dateTimeFormatUtils.js'

import PrimeVue from 'primevue/config'
import Image from 'primevue/image'
import Divider from 'primevue/divider'
import Menubar from 'primevue/menubar'
import InputText from 'primevue/inputtext'
import Button from 'primevue/button'
import Badge from "primevue/badge";
import Aura from '@primevue/themes/aura'

import 'primeicons/primeicons.css'
import 'primevue'
import 'primeflex/primeflex.css'

import ElementPlus, {ElMessage} from 'element-plus'
import 'element-plus/dist/index.css'

import '@/assets/css/base.css'
import '@/assets/css/badge.css'

const app = createApp(App)
const pinia = createPinia()

app.use(router)
app.use(pinia)
app.use(ElementPlus)
app.use(PrimeVue, {
    // theme: {
    //     preset: Aura,
    //     options: {
    //         darkModeSelector: false,
    //     }
    // }
})
const PrimeVueComponents = {
    install(app) {
        app.component('Image', Image)
        app.component('Divider', Divider)
        app.component('Menubar', Menubar)
        app.component('InputText', InputText)
        app.component('Button', Button)
        app.component('Badge', Badge)
    }
}
app.use(PrimeVueComponents)
app.mount('#app')
