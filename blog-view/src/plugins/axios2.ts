
import axios from 'axios'
import type { AxiosInstance, InternalAxiosRequestConfig, AxiosResponse, AxiosError } from 'axios'
// @ts-ignore
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

export interface ApiResponse<T = any>{
    code: number,
    msg: string,
    data: T,
}

export const request: AxiosInstance = axios.create({
    baseURL: 'http://localhost:8090/',
    timeout: 10000,
    withCredentials: true
})

request.interceptors.request.use(
    (config: InternalAxiosRequestConfig): InternalAxiosRequestConfig => {
        NProgress.start()
        return config
    }
)

request.interceptors.response.use(
    (response: AxiosResponse) => {
        NProgress.done()
        return response.data
    }
)
