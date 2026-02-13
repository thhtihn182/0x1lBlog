import {request, type ApiResponse, type PageInfo} from '@/plugins/axios2'

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


export interface Category {
    id: number;
    name: string;
}

export interface BlogInfo {
    id: number;
    title: string;
    description: string;
    createTime: string;
    views: number;
    words: number;
    readTime: number;
    top: boolean;
    category: Category;
    tags: Tag[];
}

export interface TagIdGetBlogsResponse {
    queryTag: Tag,
    blogInfos: PageInfo<BlogInfo>;
}

export const getBlogListByTagId =
    (tagId: number, pageNum: number, pageSize: number): Promise<ApiResponse<TagIdGetBlogsResponse>> =>
    request({
        url: 'tag',
        method: 'GET',
        params: {
            tagId,
            pageNum,
            pageSize
        }
    })


