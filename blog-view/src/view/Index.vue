<template>
  <div class="site">
    <Nav :blogName="blogName"/>

    <div class="main">
      <router-view/>
    </div>

    <Footer :siteInfo="siteInfo" :badges="badges" :newBlogList="newBlogList" :hitokoto="hitokoto"/>

  </div>


</template>

<script setup>
import Nav from "@/components/index/Nav.vue";
import {getCurrentInstance, onMounted, ref, watch} from "vue";
import Footer from "@/components/index/Footer.vue";
import {getHitokoto, getSite, translateUrl} from "@/network/index.js";
import {useAppStore} from "@/store/index.js";
import {useRoute} from "vue-router";


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
    footer.value.hitokoto = res
    hitokoto.value = res
    console.log(hitokoto.value)
    const trans1 = await translateUrl(hitokoto.value.hitokoto)
    const trans2 =    await translateUrl(hitokoto.value.from)
    hitokoto.value.hitokoto = trans1[0][0][0]
    hitokoto.value.from = trans2[0][0][0]

  } catch (error) {
    console.error('Lấy hitokoto thất bại:', error)
    // Cung cấp hitokoto mặc định nếu request thất bại
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

  }catch (e){
    console.error(e)
  }
}

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
</style>