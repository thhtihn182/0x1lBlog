
import { createApp } from 'vue'
import { createPinia } from "pinia";
import App from './App.vue'
import router from 'router/index.js'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { library } from '@fortawesome/fontawesome-svg-core'
import {
    faHome,
    faTags,
    faArchive,
    faTag,
    faInfoCircle,
    faCalendarAlt,
    faEye,
    faPencilAlt,
    faClock,
    faBars,
    faSearch,
    faCopy,
    faFolderOpen,
    faLightbulb
} from '@fortawesome/free-solid-svg-icons'
import '@/util/dateTimeFormatUtils.js'
import '@/assets/css/base.css'
import '@/assets/css/badge.css'
import '@/assets/css/typo.css'
import PrimeVuePlugin from "@/plugins/primevueConfig/primePluginVue.js";


library.add(
    faHome,
    faTags,
    faArchive,
    faTag,
    faInfoCircle,
    faCalendarAlt,
    faEye,
    faPencilAlt,
    faClock,
    faBars,
    faSearch,
    faCopy,
    faLightbulb,
    faFolderOpen
)

const app = createApp(App)
const pinia = createPinia()
app.use(router)
app.component('font-awesome-icon', FontAwesomeIcon)
app.use(pinia)
app.use(PrimeVuePlugin)
app.mount('#app')
