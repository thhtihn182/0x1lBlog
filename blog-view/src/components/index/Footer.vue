<template>
  <footer class="m-padded-tb-large m-opacity" style="background: #1b1c1d; color: white">
    <div style="text-align: center; max-width: 1200px; margin: 0 auto">
      <div style="background: transparent">
        <div>
          <div style="text-align: left">
            <h4 style="color: white; font-weight: 300">{{ siteInfo.footerImgTitle }}</h4>
            <div>
              <Image

                  atl="QR Code điện thoại"
                  image-style="width: 100px"
                  preview
              />
            </div>
          </div>
        </div>

        <div>
          <h4 style="color: white; font-weight: 300">Bài viết mới nhất</h4>
          <div>
            <a
                v-for="(item,index) in newBlogList"
                :href="item.id"
                :key="item.id"
                class="m-padded-tb-small"
                target="_blank"
                style="color: rgba(255,255,255,0.8); display: block; padding: 0.5rem 0;">
              {{item.title}}
            </a>
          </div>
        </div>

        <div>
          <p class="m-opacity-mini">{{ hitokoto.hitokoto}}</p>
          <p
              class="m-opacity-mini"
              style="float: right"
              v-if="hitokoto.from"
          >
            ——《{{ hitokoto.from }}》
          </p>
        </div>
      </div>

      <Divider/>

      <p class="m-opacity-tiny">
        <span style="margin-right: 10px" v-if="siteInfo.copyright">{{ siteInfo.copyright.title }}</span>
        <a href="/" style="color:#ffe500" v-if="siteInfo.copyright">{{ siteInfo.copyright.siteName }}</a>
      </p>

      <div class="github-badges">
        <a
            v-for="(item, index) in badges"
            :key="index"
            rel="external nofollow"
            :href="item.url"
            target="_blank"
            :title="item.title"
            class="github-badge"
        >
          <span class="badge-subject">{{ item.subject }}</span>
          <span class="badge-value" :class="'bg-'+item.color">{{ item.value }}</span>
        </a>
      </div>
    </div>
  </footer>
</template>


<script setup>
import {defineProps, getCurrentInstance, onMounted} from 'vue'


const { proxy } = getCurrentInstance()
const props = defineProps({
  siteInfo: {
    type: Object,
    required: true
  },
  badges: {
    type: Array,
    required: true
  },
  newBlogList: {
    type: Array,
    required: true
  },
  hitokoto: {
    type: Object,
    required: true
  }
})
onMounted(() => {
})
</script>

<style scoped>
.github-badge a {
  color: #fff;
}

</style>