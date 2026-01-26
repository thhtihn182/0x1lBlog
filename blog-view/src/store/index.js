import { defineStore } from "pinia";
import {computed, ref} from "vue";

export const useAppStore = defineStore('app', () => {

    const siteInfo = ref(getStorage('siteInfo', {}));
    const introduction = ref(getStorage('introduction', getDefaultIntro()));

    const webTitleSuffix = computed(() => siteInfo.value?.webTitleSuffix || '')
    const author = computed(() => siteInfo.value?.author || '')

    function getStorage  (key, defaultValue) {
        try {
            const stored = sessionStorage.getItem(key);
            return stored ? JSON.parse(stored) : defaultValue;
        } catch {
            return defaultValue;
        }
    }

    function setStorage  (key, value) {
        sessionStorage.setItem(key, JSON.stringify(value));
    }

    function getDefaultIntro() {
        return {
        avatar: '', name: '', leetCode: '', facebook: '',
        instagram: '', github: '', email: '', favorites: [], rollText: [],
    }}

    // Actions
    const saveSiteInfo = (data) => {
        siteInfo.value = data
        setStorage('siteInfo', data);
    };


    const saveIntroduction = (data) => {
        introduction.value = { ...introduction.value, ...data };
        setStorage('introduction', introduction.value);
    };

    const resetIntroduction = () => {
        introduction.value = getDefaultIntro();
        setStorage('introduction', introduction.value);
    };

    return {
        siteInfo,
        author,
        introduction,
        webTitleSuffix,
        saveSiteInfo,
        saveIntroduction,
        resetIntroduction,
    };
});