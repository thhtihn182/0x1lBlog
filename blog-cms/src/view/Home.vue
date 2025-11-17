<template>
  <el-container class="home-container">
    <!-- Header -->
    <el-header>
      <div class="header-left">
        <span><img src="@/assets/img/logo.png" alt="Logo" height="60">
           Hệ thống quản lý Blog</span>
      </div>
      <el-button type="info" @click="logout">Đăng xuất</el-button>
    </el-header>

    <!-- Main Content -->
    <el-container>
      <!-- Sidebar -->
      <el-aside :width="isCollapse ? '64px' : '200px'">
        <div class="toggle-button" @click="toggleCollapse">
          <i :class="isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold'"></i>
        </div>


        <!-- Menu -->
        <el-menu
          background-color="#333744"
          text-color="#fff"
          active-text-color="#409eff"
          :default-openeds="defaultOpeneds"
          :unique-opened="false"
          :collapse="isCollapse"
          :collapse-transition="false"
          :router="true"
          :default-active="store.activePath"
        >
          <!-- First Level Menu -->
          <el-sub-menu
            v-for="item in menuList"
            :key="item.id"
            :index="String(item.id)"
          >
            <template #title>
<!--              <el-button class="iconfont" size="small"  style="max-width: 8px" :icon="iconsObj[item.id]"></el-button>-->
              <span>  {{ item.title }}</span>
            </template>

            <!-- Second Level Menu -->
            <el-menu-item
              v-for="subItem in item.children"
              :key="subItem.id"
              :index="subItem.path"
              @click="store.saveNavState(subItem.path)"
            >
              <template #title>
<!--                <el-button style="max-width: 14px"  size="small"  :icon="iconsObj[subItem.id]"></el-button>-->
                <span>{{ subItem.title }}</span>
              </template>

            </el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-aside>

      <!-- Right Content Area -->
      <el-main>
        <router-view :key="route.fullPath"/>
      </el-main>

    </el-container>
  </el-container>
</template>

<script setup>
import { ref, onMounted} from "vue";
import {useRouter ,useRoute } from "vue-router";
import { getCurrentInstance } from "vue";
import { useAppStore } from '@/store/index.js'

const store = useAppStore()

const { proxy } = getCurrentInstance()

//Router instance
const route = useRoute()
const router = useRouter()

//Reactive data
const isCollapse = ref(false)
const defaultOpeneds = ref(['1','2','3','4'])

const menuList = [
  {
    id: 1,
    title: 'Quản lý Blog',
    children: [
      {
        id: 11,
        title: 'Viết Blog',
        children: [],
        path: '/blogs/write'
      },
      {
        id: 12,
        title: 'Danh sách Blog',
        children: [],
        path: '/blogs'
      },
      {
        id: 13,
        title: 'Danh sách danh mục',
        children: [],
        path: '/categories'
      },
      {
        id: 14,
        title: 'Danh sách thẻ',
        children: [],
        path: '/tags'
      }
    ]
  },
  {
    id: 2,
    title: 'Quản lý bình luận',
    children: [
      {
        id: 21,
        title: 'Danh sách bình luận',
        children: [],
        path: '/comments'
      },
      {
        id: 22,
        title: 'Thùng rác',
        children: [],
        path: '/comments/trashes'
      }
    ]
  },
  {
    id: 3,
    title: 'Thống kê dữ liệu',
    children: [
      {
        id: 31,
        title: 'Lượt truy cập',
        children: [],
        path: '/pv'
      },
      {
        id: 32,
        title: 'Truy cập trực tuyến',
        children: [],
        path: '/latest'
      }
    ]
  },
  {
    id: 4,
    title: 'Giám sát hệ thống',
    children: [
      {
        id: 41,
        title: 'Nhật ký hệ thống',
        children: [],
        path: '/log'
      }
    ]
  }
]

import {
  Menu,
  Tickets,
  DataAnalysis,
  Tools,
  Edit,
  Opportunity,
  Discount,
  DocumentCopy,
  Delete,
  TrendCharts,
  View,
  Document
} from '@element-plus/icons-vue'

const iconsObj = {
  '1': Menu,
  '2': Tickets,
  '3': DataAnalysis,
  '4': Tools,
  '11': Edit,
  '12': Tickets,
  '13': Opportunity,
  '14': Discount,
  '21': DocumentCopy,
  '22': Delete,
  '31': TrendCharts,
  '32': View,
  '41': Document
}

// Lifecycle
onMounted(() => {
})

// Các phương thức
const logout = () => {
  window.sessionStorage.clear()
  router.push('/login')
  proxy.$msgSuccess('Đăng xuất thành công')
}

const toggleCollapse = () => {
  isCollapse.value = !isCollapse.value
}


</script>


<style scoped>
.home-container{
  height: 100%;
}

.el-header{
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-left: 10px;
  color: #ffffff;
  font-size: 22px;
}

.header-left span{
  display: flex ;
  align-items: center;
}

.el-aside{
  background-color: #333744;
}

.el-aside .el-menu{
  border-right: none ;
}

.el-main{
  background-color: #eaedf1;
}

.iconfont{
  margin-right: 5px;
  font-size: 20px;
}

.toggle-button {
  background-color: #4a5064;
  font-size: 20px;
  line-height: 40px;
  color: #ffffff;
  text-align: center;
  cursor: pointer;
  transition: background-color 0.3s;
}

.toggle-button:hover {
  background-color: #5a6074;
}
</style>