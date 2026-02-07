import axios from '@/plugins/axios'

export function getCommentListByQuery(query) {
    return axios({
        url: 'comment-tree',
        method: 'GET',
        params: {
            ...query
        }
    })
}