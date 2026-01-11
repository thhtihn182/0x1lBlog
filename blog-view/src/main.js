
import { createApp } from 'vue'
import { createPinia } from "pinia";
import App from './App.vue'
import router from 'router/index.js'

import '@/util/dateTimeFormatUtils.js'


import PrimeVue from 'primevue/config'

import 'primeflex/themes/primeone-light.css'
import 'primeicons/primeicons.css'
import 'primeflex/primeflex.css'

import Image from 'primevue/image'
import Divider from 'primevue/divider'
import Menubar from 'primevue/menubar'
import InputText from 'primevue/inputtext'
import Button from 'primevue/button'
import Badge from "primevue/badge";
import Card from 'primevue/card'
import Accordion from 'primevue/accordion'
import AccordionPanel from 'primevue/accordionpanel'
import Aura  from '@primevue/themes/aura'
import {definePreset} from "@primevue/themes";

import '@/assets/css/base.css'
import '@/assets/css/badge.css'



const app = createApp(App)
const pinia = createPinia()

app.use(router)
app.use(pinia)

const MyPreset = definePreset(Aura,{
        semantic: {
            // primary: {
            //     50: "{red.50}",
            //     100: "{red.100}",
            //     200: "{red.200}",
            //     300: "{red.300}",
            //     400: "{red.400}",
            //     500: "{red.500}",
            //     600: "{red.600}",
            //     700: "{red.700}",
            //     800: "{red.800}",
            //     900: "{red.900}",
            //     950: "{red.950}",
            // }
            // ,
            colorScheme: {
                light: {
                    button: {
                        background: "{primary.color}",
                    },
                    accordion:{

                    }
                },
                dark: {
                    //...
                }
            }
        }
    }
)

app.use(PrimeVue, {
    theme: {
        preset: MyPreset,
        options: {
            // prefix: 'p',
            darkModeSelector: false,
            cssLayer: false
        }
    }
});


const PrimeVueComponents = {
    install(app) {
        app.component('Image', Image)
        app.component('Divider', Divider)
        app.component('Menubar', Menubar)
        app.component('InputText', InputText)
        app.component('Button', Button)
        app.component('Badge', Badge)
        app.component('Card', Card)
        app.component('AccordionPanel', AccordionPanel)
        app.component('Accordion', Accordion)
    }
}
app.use(PrimeVueComponents)
app.mount('#app')
