import axios from "@/plugins/axios.js";

export function getSiteSettingData(){
    return axios({
        url: 'site-settings',
        method: "GET"
    })
}

export function update(settings, deleteIds){
    return axios({
        url: 'site-settings',
        method: "PUT",
        data:{
            settings,
            deleteIds
        }
    })
}