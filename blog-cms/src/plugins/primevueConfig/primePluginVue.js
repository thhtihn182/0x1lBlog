import PrimeVue from 'primevue/config'
import ToastService from 'primevue/toastservice'
import ConfirmationService from 'primevue/confirmationservice'
import {inject} from "vue";
import 'primeicons/primeicons.css'
import Aura from '@primevue/themes/aura'
import Toast from 'primevue/toast'
import ConfirmDialog from 'primevue/confirmdialog'

const PrimePluginVue = {
    install(app){
        app.use(PrimeVue,{

            theme: {
                preset: Aura,
                options: {
                    prefix: 'p',
                    darkModeSelector: false,
                    cssLayer: false
                },
                ripple: true
            }
        })
        app.use(ToastService)
        app.use(ConfirmationService)

        const components = {
            'Toast': Toast,
            'ConfirmDialog': ConfirmDialog
        }
        Object.entries(components).forEach(([name, component])  => {
            app.component(name, component)
        })

        const toastService = {
            success: (msg, options = {}) =>{
                app.config.globalProperties.$toast.add({
                    severity: 'success',
                    summary: options.summary || 'Thành công',
                    detail: msg,
                    life: options.life || 3000,
                    ...options
                })
            },
            error: (msg, options = {}) =>{
                app.config.globalProperties.$toast.add({
                    severity: 'error',
                    summary: options.summary || 'Lỗi',
                    detail: msg,
                    life: options.life || 5000,
                    ...options
                })
            },
            info: (msg, options = {}) => {
                app.config.globalProperties.$toast.add({
                    severity: 'info',
                    summary: options.summary || 'Thông tin',
                    detail: msg,
                    life: options.life || 3000,
                    ...options
                })
            },
            warn: (msg, options= {}) => {
                app.config.globalProperties.$toast.add({
                    severity: 'warn',
                    summary: options.summary || 'Cảnh báo',
                    detail: msg,
                    life: options.life || 4000,
                    ...options
                })
            },
            showLoading: (msg = 'Đang xử lý...') => {
                app.config.globalProperties.$toast.add({
                    severity: 'info',
                    summary: 'Đang xử lý',
                    detail: msg,
                    life: 100000
                })
            },
            hideLoading: () => {
                app.config.globalProperties.$toast.removeAllGroups()
            },
            confirm: (options = {}) => {
                return new Promise((resolove) => {
                    app.config.globalProperties.$confirm.require({
                        message: options.message || 'Bạn có chắc chắn không?',
                        header: options.header || 'Xác nhận',
                        icon: options.icon || 'pi pi-exclamation-triangle',
                        acceptLabel: options.acceptLabel || 'Đồng ý',
                        rejectLabel: options.rejectLabel || 'Hủy',
                        acceptClass: options.acceptClass || 'p-button-danger',
                        rejectClass: options.rejectClass || 'p-button-secondary p-button-text',
                        accept: () => resolve(true),
                        reject: () => resolve(false)
                    })
                })
            }
        }

        app.provide('toast', toastService)
    }
}
export function useToast(){
    const toast = inject('toast')

    if(!toast){
        throw new Error('useToast() must be used within a component that has PrimeVue plugin installed')
    }
    return toast
}

export default PrimePluginVue