import { defineStore } from "pinia";
import { ref } from "vue";

export const useAppStore = defineStore('app', () => {

    const webTitleSuffix = ref(getStorage('webTitleSuffix', ''));
    const introduction = ref(getStorage('introduction', getDefaultIntro()));


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
    const saveWebTitleSuffix = (suffix) => {
        webTitleSuffix.value = suffix;
        setStorage('webTitleSuffix', suffix);

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
        webTitleSuffix,
        introduction,
        saveWebTitleSuffix,
        saveIntroduction,
        resetIntroduction,
    };
});