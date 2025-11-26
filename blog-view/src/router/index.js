import {createRouter, createWebHistory} from "vue-router";
import Index from "@/view/Index.vue";

const routes = [
    {
        path: '/',
        redirect:'/index'
    },
    {
        path: '/index',
        component: Index,
    },
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})

router.beforeEach((to, from, next) => {
    if(to.meta.title){
        document.title = to.meta.title + ' | thinhh0x1\'s Blog'
    }
    next()
})

export default router