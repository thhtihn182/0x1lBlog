<template>
  <div class="site">
    <Nav :blogName="blogName"/>

    <div class="main">
      <router-view/>
    </div>

    <Footer :footer="footer"/>

  </div>


</template>

<script setup>
import Nav from "@/components/index/Nav.vue";
import {onMounted, ref} from "vue";
import Footer from "@/components/index/Footer.vue";
import {getHitokoto, translateUrl} from "@/network/index.js";


const blogName = ref('Blog\'s thinhh0x1l')
const footer = ref({
  mobileQRSrc: new URL('/src/assets/img/qrSimple.png', import.meta.url).href,
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
      value: 'Spring Boot',
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
    const trans1 = await translateUrl(footer.value.hitokoto.hitokoto)
    const trans2 =    await translateUrl(footer.value.hitokoto.from)
    footer.value.hitokoto.hitokoto = trans1[0][0][0]
    footer.value.hitokoto.from = trans2[0][0][0]

  } catch (error) {
    console.error('Lấy hitokoto thất bại:', error)
    // Cung cấp hitokoto mặc định nếu request thất bại
    footer.value.hitokoto = {
      hitokoto: 'Hãy viết code bằng cả trái tim',
      from: 'Lập trình viên'
    }
  }

}

// Lifecycle hook
onMounted(() => {
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