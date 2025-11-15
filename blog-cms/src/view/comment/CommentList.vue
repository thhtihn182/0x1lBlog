<template>
  <div>
    <!--面包屑导航-->
    <Breadcrumb parentTitle="评论管理"/>

    <el-card>
      <!--搜索-->
      <el-row>
        <el-col :span="6">
          <el-select v-model="pageId" placeholder="请选择页面" :filterable="true" :clearable="true" @change="search">
            <el-option :label="item.title" :value="item.id" v-for="item in blogList" :key="item.id"></el-option>
          </el-select>
        </el-col>
      </el-row>

      <el-table :data="commentList" row-key="id" :tree-props="{children: 'replyComments'}" border stripe>
        <el-table-column label="序号" type="index" width="50"></el-table-column>
        <el-table-column label="昵称" prop="nickname">
          <template #default="scope">
            {{ scope.row.nickname }}
            <el-tag v-if="scope.row.adminComment" size="mini" effect="dark" style="margin-left: 5px">我</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="头像" width="80">
          <template #default="scope">
<!--            <el-avatar shape="square" :size="60" fit="contain" :src="getAvatarUrl(scope.row.avatar)"></el-avatar>-->
            <el-avatar shape="square" :size="60" fit="contain" :src="getAvatarUrl(scope.row.avatar)"></el-avatar>
          </template>
        </el-table-column>
        <el-table-column label="邮箱" prop="email"></el-table-column>
        <el-table-column label="ip" prop="ip" width="130"></el-table-column>
        <el-table-column label="评论内容" prop="content"></el-table-column>
        <el-table-column label="所在页面">
          <template #default="scope">
            <el-link type="success" href="" target="_blank" v-if="scope.row.page==0">{{ scope.row.blog.title }}</el-link>
            <el-link type="success" href="" target="_blank" v-else-if="scope.row.page==1">关于我</el-link>
          </template>
        </el-table-column>
        <el-table-column label="发表时间" width="170">
          <template #default="scope">{{ formatDate(scope.row.createTime) }}</template>
        </el-table-column>
        <el-table-column label="是否公开" width="80">
          <template #default="scope">
            <el-switch v-model="scope.row.published" @change="commentPublishedChanged(scope.row)"></el-switch>
          </template>
        </el-table-column>
        <el-table-column label="邮件提醒" width="80">
          <template #default="scope">
            <el-switch v-model="scope.row.notice" @change="commentNoticeChanged(scope.row)"></el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="190">
          <template #default="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="">编辑</el-button>
            <el-popconfirm title="确定删除吗？" icon="el-icon-delete" iconColor="red" @confirm="deleteCommentById(scope.row.id)">
              <el-button size="mini" type="danger" icon="el-icon-delete" slot="reference">删除</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>

      <!--分页-->
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.pageNum"
                     :page-sizes="[5, 10, 15, 20]" :page-size="queryInfo.pageSize" :total="total"
                     layout="total, sizes, prev, pager, next, jumper" background>
      </el-pagination>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import Breadcrumb from "@/components/Breadcrumb.vue"
import { getCommentListByQuery, getBlogList} from '@/network/comment'
import { getCurrentInstance } from "vue";

const { proxy } = getCurrentInstance()
// 响应式数据
const pageId = ref(null)
const queryInfo = reactive({
  page: 0,
  blogId: null,
  pageNum: 1,
  pageSize: 10
})
const total = ref(0)
const commentList = ref([])
const blogList = ref([])

// 方法
const getCommentList = async () => {
  try {
    const res = await getCommentListByQuery(queryInfo)
    console.log(res)
    if (res.code === 200) {
      proxy.$msgSuccess(res.msg)
      commentList.value = res.data.list
      total.value = res.data.total
    } else {
      proxy.$msgError(res.msg)
    }
  } catch {
    proxy.$msgError("请求失败")
  }
}

const getBlogListData = async () => {
  try {
    const res = await getBlogList()
    console.log(res)
    if (res.code === 200) {
      blogList.value = res.data
      blogList.value.unshift({id: -1, title: '关于我'})
    } else {
      proxy.$msgError(res.msg)
    }
  } catch {
    proxy.$msgError("请求失败")
  }
}

const search = () => {
  if (pageId.value === -1) {
    queryInfo.page = 1
    queryInfo.blogId = null
  } else {
    queryInfo.page = 0
    queryInfo.blogId = pageId.value
  }
  queryInfo.pageNum = 1
  queryInfo.pageSize = 10
  getCommentList()
}

const handleSizeChange = (newSize) => {
  queryInfo.pageSize = newSize
  getCommentList()
}

const handleCurrentChange = (newPage) => {
  queryInfo.pageNum = newPage
  getCommentList()
}

// const commentPublishedChanged = async (row) => {
//   try {
//     const res = await updatePublished(row.id, row.published)
//     if (res.code === 200) {
//       proxy.$msgSuccess(res.msg)
//     } else {
//       proxy.$msgError(res.msg)
//     }
//   } catch {
//     proxy.$msgError("请求失败")
//   }
// }
//
// const commentNoticeChanged = async (row) => {
//   try {
//     const res = await updateNotice(row.id, row.notice)
//     if (res.code === 200) {
//       proxy.$msgSuccess(res.msg)
//     } else {
//       proxy.$msgError(res.msg)
//     }
//   } catch {
//     msgError("请求失败")
//   }
// }
//
// const deleteCommentById = async (id) => {
//   try {
//     const res = await deleteCommentById(id)
//     console.log(res)
//     if (res.code === 200) {
//       msgSuccess(res.msg)
//       getCommentList()
//     } else {
//       msgError(res.msg)
//     }
//   } catch {
//     msgError("请求失败")
//   }
// }

// 工具函数
// const getAvatarUrl = (avatar) => {
//   return require(`@/assets/img/comment-avatar/${avatar}`)
// }
const getAvatarUrl = (avatar) => {
  console.log(avatar)
  const images = import.meta.glob('@/assets/img/comment-avatar/*', {
    eager: true,
    import: 'default'
  })

  const path = `/src/assets/img/comment-avatar/${avatar}`
  console.log(path)
  return images[path] || '/src/assets/img/comment-avatar/default-avatar.png'
}
const formatDate = (dateString) => {

  return new Date(dateString).toLocaleString('zh-CN')
}

// 生命周期
onMounted(() => {
  getCommentList()
  getBlogListData()
})
</script>

<style scoped>
.el-button {
  margin-right: 10px;
}
</style>