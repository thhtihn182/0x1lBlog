import { defineStore } from "pinia";
import { ref} from "vue";

export const useAppStore = defineStore('app', () => {
    const activePath = ref(window.sessionStorage.getItem('activePath')||'')

    const saveNavState = (path) => {
        activePath.value = path
        window.sessionStorage.setItem('activePath',path)
    }
    return { activePath, saveNavState }
})