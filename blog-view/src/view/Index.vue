<template>
  <div class="site">
    <Nav :blogName="blogName"/>

    <div class="main">
      <div class="py-6">
        <div class="container mx-auto" style="max-width: 1400px;">
          <div class="flex flex-row flex-nowrap gap-3">

            <div class="hidden md:block flex-none sticky-sidebar-wrapper" style="width: 280px;">
              <div class="sticky-sidebar">
                <Introduction/>
              </div>
            </div>

            <div class="flex-1 min-w-0">
              <router-view/>
            </div>

            <div class="flex-none" style="width: 280px;">
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
import {onMounted, ref, watch} from "vue";
import Footer from "@/components/index/Footer.vue";
import {getHitokoto, getSite, translateUrl} from "@/network/index.js";
import {useAppStore} from "@/store/index.js";
import {useRoute} from "vue-router";
import Introduction from "@/components/sidebar/Introduction.vue";

const route = useRoute()
const store = useAppStore()

const siteInfo = ref({})
const badges = ref([])
const newBlogList = ref([])
const hitokoto = ref({})
const blogName = ref('Think\'s Blog')

// Functions
const getHitokotoData = async () => {
  try {
    const res = await getHitokoto()
    hitokoto.value = res
    const trans1 = await translateUrl(hitokoto.value.hitokoto)
    const trans2 = await translateUrl(hitokoto.value.from)
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

const site = async () => {
  try {
    const res = await getSite();
    if(res.code === 200){
      badges.value = res.data.badges
      siteInfo.value = res.data.siteInfo
      newBlogList.value = res.data.newBlogList
      blogName.value = res.data.siteInfo.blogName || 'Thinh0x1l\''
      store.saveIntroduction(res.data.introduction)
      store.saveWebTitleSuffix(res.data.siteInfo.webTitleSuffix)
      watch(
          [() => route.meta?.title , () => store.webTitleSuffix],
          ([title, suffix]) => {
            document.title = `${title || ''} | ${suffix}`;
          },
          { immediate: true }
      )
    }
  } catch (e) {
    console.error(e)
  }
}

onMounted(() => {
  site()
  getHitokotoData()
})
</script>

<style scoped>
/* Reset các thuộc tính có thể ảnh hưởng đến sticky */
.site, .main, .container, .flex {
  position: static !important;
  overflow: visible !important;
}

.site {
  display: flex;
  min-height: 100vh;
  flex-direction: column;
}

.main {
  margin-top: 40px;
  flex: 1;
  position: relative;
}

.container {
  position: relative;
}

/* Sticky sidebar wrapper */
.sticky-sidebar-wrapper {
  align-self: flex-start;
  position: sticky;
  top: 68px;
  height: 100%;
}

/*.sticky-sidebar {
  position: sticky;n dev
  top: 10px;
  height: fit-content;
  overflow-y: auto;
}*/

/* Responsive */
@media (max-width: 1399px) and (min-width: 1200px) {
  .container {
    max-width: 1140px;
  }
  .sticky-sidebar-wrapper,
  .flex-none[style*="width: 280px"] {
    width: 250px !important;
  }
}

@media (max-width: 1199px) and (min-width: 992px) {
  .container {
    max-width: 960px;
  }
  .sticky-sidebar-wrapper,
  .flex-none[style*="width: 280px"] {
    width: 220px !important;
  }
}

@media (max-width: 991px) {
  .flex-nowrap {
    flex-wrap: wrap !important;
  }

  .sticky-sidebar-wrapper,
  .flex-1,
  .flex-none[style*="width: 280px"]:not(.hidden) {
    width: 100% !important;
    margin-bottom: 1.5rem;
  }

  .flex-none[style*="width: 280px"]:not(.hidden) {
    order: 2;
  }

  .flex-1 {
    order: 1;
  }

  .sticky-sidebar-wrapper {
    position: static !important;
    top: auto !important;
  }

  .sticky-sidebar {
    position: static !important;
    top: auto !important;
    max-height: none;
  }
}

</style>