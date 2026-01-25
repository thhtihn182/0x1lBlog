import { createApp} from "vue";
import App from "@/App.vue";
import router from "@/router/index.js";
import '@/assets/css/base.css'
import { createPinia } from "pinia";

const pinia = createPinia()

// Element Plus (thay cho Element UI)
import ElementPlus, {ElMessage} from 'element-plus'
import 'element-plus/dist/index.css'
import editor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import './util/dateTimeFormatUtils.js'
import PrimePluginVue from "@/plugins/primevueConfig/primePluginVue.js";

const app = createApp(App)

// sử dụng plugins
app .use(router)
    .use(pinia)
    .use(ElementPlus)
    .use(PrimePluginVue)
    .use(editor)

const showMessage = (type,msg) =>{
    try{
        ElMessage[type](msg)
    }catch (error){
        console.warn('Thông báo không khả dụng', error)
    }
}

// Global properties
app.config.globalProperties.$msgSuccess = (msg) => showMessage('success',msg)
app.config.globalProperties.$msgError = (msg) => showMessage('error',msg)
app.config.globalProperties.$msgInfo = (msg) => showMessage('info',msg)

app.mount('#app')