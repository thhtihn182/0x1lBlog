<template>
  <div class="site">
    <Nav :blogName="blogName"/>

    <div class="main">
      <div class="m-padded-tb-big">
        <div class="p-container">
          <div class="flex flex-row flex-wrap">
            <!-- Nội dung chính - 12 cột -->
            <div class="col-12 md:col-9 lg:col-8 xl:col-9">
              <router-view/>
            </div>

            <!-- Sidebar - 4 cột -->
            <div class="col-12 md:col-3 lg:col-4 xl:col-3">
              <Introduction/>
            </div>
          </div>
        </div>
      </div>
    </div>

    <Footer :siteInfo="siteInfo" :badges="badges" :newBlogList="newBlogList" :hitokoto="hitokoto"/>

  </div>


</template>

<script setup>
import Nav from "@/components/index/Nav.vue";
import { onMounted, ref, watch} from "vue";
import Footer from "@/components/index/Footer.vue";
import {getHitokoto, getSite, translateUrl} from "@/network/index.js";
import {useAppStore} from "@/store/index.js";
import {useRoute} from "vue-router";
import {useToast} from "@/plugins/primevueConfig/primePluginVue.js";
import Introduction from "@/components/sidebar/Introduction.vue";

const route = useRoute()

const siteInfo = ref({})
const badges = ref([])
const newBlogList= ref([])
const hitokoto= ref({})

const blogName = ref('Think\'s Blog')
const siteT = ref({})
const footer = ref({
  mobileQRSrc: new URL('/src/assets/img/qr.png', import.meta.url).href,
  newBlogList: [
    {
      href: '/',
      title: 'Blog mới nhất 1',
    },
    {
      href: '/',
      title: 'Blog mới nhất 2',
    },
    {
      href: '/',
      title: 'Blog mới nhất 3',
    }
  ],
  hitokoto:{},
  copyright:{
    time: 'Bản quyền © 2025 - 2026',
    title:'BLOG CỦA THINK',
    beianId: 'Mã test', //Mã ICP 20005222
  },
  badges:[
    {
      href: 'https://spring.io/projects/spring-boot/',
      title: 'Được cung cấp bởi Spring Boot',
      subject: 'Backend',
      value: 'Spring Boot ',
      color: 'blue',
    },
    {
      href: 'https://cn.vuejs.org/',
      title: 'Render phía client bằng Vue.js',
      subject: 'SPA',
      value: 'Vue.js',
      color: 'brightgreen',
    },
    {
      href: 'https://primevue.org/',
      title: 'Framework UI PrimeVue-UI',
      subject: 'Frontend',
      value: 'PrimeVue-UI',
      color: 'primevue-ui',
    },
    {
      href: 'https://www.aliyun.com/',
      title: 'Dịch vụ máy chủ và tên miền được cung cấp bởi Aliyun',
      subject: 'Máy chủ & DNS',
      value: 'Aliyun',
      color: 'blueviolet',
    },
    {
      href: 'https://www.jsdelivr.com/',
      title: 'Dịch vụ CDN được cung cấp bởi jsDelivr',
      subject: 'CDN',
      value: 'jsDelivr',
      color: 'orange',
    },
    {
      href: 'https://github.com/',
      title: 'Lưu trữ hình ảnh bởi GitHub',
      subject: 'Lưu trữ',
      value: 'GitHub',
      color: 'github',
    },
    // {
    //   href: 'https://creativecommons.org/licenses/by/4.0/',
    //   title: 'Trang web này được cấp phép theo CC BY 4.0 International',
    //   subject: 'Giấy phép',
    //   value: 'BY 4.0',
    //   color: 'lightgray',
    // }
  ]
})

const getHitokotoData = async () => {
  try {
    const res = await getHitokoto()
    console.log(res)
    hitokoto.value = res
    console.log(hitokoto.value)
    const trans1 = await translateUrl(hitokoto.value.hitokoto)
    const trans2 =    await translateUrl(hitokoto.value.from)
    hitokoto.value.hitokoto = trans1[0][0][0]
    hitokoto.value.from = trans2[0][0][0]

  } catch (error) {
    console.error('Lấy hitokoto thất bại:', error)
    hitokoto.value = {
      hitokoto: 'Hãy viết code bằng cả trái tim',
      from: 'Lập trình viên'
    }
  }

}
const store = useAppStore()


const site = async () => {
  try{
    const res = await getSite();
    if(res.code === 200){
      badges.value = res.data.badges
      siteInfo.value = res.data.siteInfo
      newBlogList.value = res.data.newBlogList
      blogName.value = res.data.siteInfo.blogName ||  'Thinh0x1l\''
      store.saveIntroduction(res.data.introduction)
      store.saveWebTitleSuffix(res.data.siteInfo.webTitleSuffix)
      watch(
          [()=>route.meta?.title , () => store.webTitleSuffix],
          ([title,suffix]) => {
            document.title = `${title ||''} | ${suffix}`;
          },
          { immediate : true}
      )
    }
    console.log(res.data)
  }catch (e){
    console.error(e)
  }
}
const toast = useToast()

// Lifecycle hook
onMounted(() => {
  site()
  getHitokotoData()

})
</script>

<style scoped>
.site {
  display: flex;
  min-height: 100vh;
  flex-direction: column;
}

.main {
  opacity: 0.9;
  flex: 1;
}
.p-container {
  width: 100%;
  margin-left: auto;
  margin-right: auto;
  padding-left: 1rem;
  padding-right: 1rem;
}

/* Responsive breakpoints */
@media (min-width: 576px) {
  .p-container {
    max-width: 540px;
  }
}

@media (min-width: 768px) {
  .p-container {
    max-width: 720px;
  }
}

@media (min-width: 992px) {
  .p-container {
    max-width: 960px;
  }
}

@media (min-width: 1200px) {
  .p-container {
    max-width: 1140px;
  }
}

@media (min-width: 1400px) {
  .p-container {
    max-width: 1320px;
  }
}
</style>