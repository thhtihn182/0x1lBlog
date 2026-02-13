import {request, type ApiResponse} from '@/plugins/axios2'

export interface Tag{
    id: number,
    name: string,
    color: string
}

export const getTags = (): Promise<ApiResponse<Tag[]>> =>
    request({
        url: 'tags',
        method: 'get'
    })
