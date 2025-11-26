<template>
  <footer class="m-padded-tb-large m-opacity" style="background: #1b1c1d; color: white">
    <div style="text-align: center; max-width: 1200px; margin: 0 auto">
      <div style="background: transparent">
        <div>
          <div style="text-align: left">
            <h4 style="color: white; font-weight: 300">Xem trên điện thoại</h4>
            <div>
              <Image
                :src="footer.mobileQRSrc"
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
              v-for="(item,index) in footer.newBlogList"
              :href="item.href"
              :key="index"
              class="m-padded-tb-small"
              target="_blank"
              style="color: rgba(255,255,255,0.8); display: block; padding: 0.5rem 0;">
              {{item.title}}
            </a>
          </div>
        </div>

        <div>
          <p class="m-opacity-mini">{{ footer.hitokoto.hitokoto}}</p>
          <p
            class="m-opacity-mini"
            style="float: right"
            v-if="footer.hitokoto.from"
          >
            ——《{{ footer.hitokoto.from }}》
          </p>
        </div>
      </div>

      <Divider/>

      <p class="m-opacity-tiny">
        <span style="margin-right: 10px">{{ footer.copyright.time }}</span>
        <a href="/" target="_blank" style="color:#ffe500">{{ footer.copyright.title }}</a>
        <span style="margin: 0 15px">|</span>
        <img src="@/assets/img/beian.png" alt="Biểu tượng bản quyền" class="beian-icon">
        <a rel="external nofollow noopener" href="http://www.beian.miit.gov.cn/" target="_blank" style="color:#ffe500">
          {{ footer.copyright.beianId }}
        </a>
      </p>

      <div class="github-badges">
        <a
            v-for="(item, index) in footer.badges"
            :key="index"
            rel="external nofollow"
            :href="item.href"
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
import { defineProps } from 'vue'
const props = defineProps({
  footer: {
    type: Object,
    required: false
  }
})
</script>

<style scoped>
.github-badge a {
  color: #fff;
}

.beian-icon {
  width: 17px;
  height: 17px;
  margin-bottom: -4px;
  margin-right: 5px;
}
</style>