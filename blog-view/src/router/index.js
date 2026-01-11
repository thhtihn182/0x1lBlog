import {createRouter, createWebHistory} from "vue-router";
import Index from "@/view/Index.vue";
import Home from "@/view/home/Home.vue";
import GridL from "@/view/GridL.vue";
import {useAppStore} from "@/store/index.js";

const routes = [
    {
        path: '/',
        redirect:'/index'
    },
    {
        path: '/grid',
        component: GridL,
        meta:{
            title: 'GridL'
        }
    },
    {
        path: '/index',
        component: Index,
        redirect: '/home',
        children: [
            {
                path: '/home',
                component: Home,
                meta:{
                    title: 'Trang chủ'
                }
            },

        ]
    },
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})

router.beforeEach((to, from, next) => {
    const store =  useAppStore()
    if(to.meta.title){
        if(store.webTitleSuffix !== ''){
            document.title = to.meta.title + ' | ' + store.webTitleSuffix
        }
        else {
            document.title = to.meta.title
        }
    }
    next()
})

export default router