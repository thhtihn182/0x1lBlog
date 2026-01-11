import axios from "@/plugins/axios.js";

export function getSiteSettingList(){
    return axios({
        url: 'site-settings',
        method: "GET"
    })
}