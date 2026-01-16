<template>
  <div>
    <div class="top-section surface-ground border-round-top border-1 border-300 p-4 text-center">
      <h2 class="font-semibold text-xl m-0">Lưu trữ bài viết</h2>
      <p class="text-color-secondary mt-2 mb-0">Tốt! Hiện tại có tổng cộng {{ count }} bài viết. Tiếp tục cố gắng.</p>
    </div>

    <div class="content-section surface-card border-round-bottom border-1 border-300 border-top-none p-4">
      <div class="timeline">

        <div :class="colorObj[index%5]" v-for="(value,key,index) in blogMap" :key="index">
          <div class="tl-header">
            <span class="p-tag p-tag-lg font-medium">{{ key }}</span>
          </div>
          <div class="tl-item" v-for="blog in value" :key="blog.id">
            <div class="tl-wrap">
              <span class="tl-date">{{ blog.day }}</span>
              <a href="#" @click.prevent="toBlog(blog)" class="timeline-link">
                <span class="timeline-label tl-title">{{ blog.title }}</span>
              </a>
            </div>
          </div>
        </div>


        <div class="tl-header">
          <span class="p-tag p-tag-lg font-medium bg-gray-900"><span style="color: white">Hello</span> </span>
        </div>
        <div class="tl-dark">
          <div class="tl-item">
            <div class="tl-wrap">
              <span class="tl-date">15d</span>
              <a href="#" @click.prevent="toBlog({id: 0, title: 'Bài viết đầu tiên'})" class="timeline-link">
                <span class="timeline-label tl-title">Bài viết đầu tiên trên blog của tôi</span>
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const blogMap = ref({
  "12/2023": [
    { id: 1, day: "31d", title: "Tổng kết hành trình học tập năm 2023" },
    { id: 2, day: "25d", title: "Giáng sinh vui vẻ! Kế hoạch nghỉ lễ của tôi" },
    { id: 3, day: "20d", title: "Thực hành Vue 3 Composition API" },
    { id: 4, day: "15d", title: "Kinh nghiệm sử dụng thư viện PrimeVue" }
  ],
  "11/2023": [
    { id: 5, day: "30d", title: "Mẹo sử dụng TypeScript nâng cao" },
    { id: 6, day: "25d", title: "Tối ưu hiệu suất Frontend thực tế" },
    { id: 7, day: "20d", title: "Nhập môn phát triển Backend với Node.js" },
    { id: 8, day: "10d", title: "Thiết kế và tối ưu cơ sở dữ liệu" }
  ],
  "10/2023": [
    { id: 9, day: "28d", title: "Thực hành tốt nhất cho thiết kế Responsive" },
    { id: 10, day: "20d", title: "Phân tích sâu về CSS Grid Layout" },
    { id: 11, day: "15d", title: "Tính năng mới trong JavaScript ES2023" },
    { id: 12, day: "05d", title: "Hướng dẫn cấu hình Frontend Engineering" }
  ],
  "09/2023": [
    { id: 13, day: "30d", title: "Cấu hình chi tiết Webpack 5" },
    { id: 14, day: "25d", title: "So sánh hiệu suất Vite vs Webpack" },
    { id: 15, day: "18d", title: "Unit Testing và Integration Testing" },
    { id: 16, day: "10d", title: "Thiết lập pipeline CI/CD" }
  ],
  "08/2023": [
    { id: 17, day: "28d", title: "Thực hành kiến trúc Micro Frontend" },
    { id: 18, day: "20d", title: "Phân tích so sánh các thư viện quản lý state" },
    { id: 19, day: "15d", title: "Giải pháp thích ứng cho phát triển di động" },
    { id: 20, day: "05d", title: "Hướng dẫn phát triển ứng dụng PWA" }
  ]
})

const count = ref(20)
const colorObj = ref({
  0: 'tl-blue',
  1: 'tl-dark',
  2: 'tl-green',
  3: 'tl-purple',
  4: 'tl-red',
})


const toBlog = (blog) => {
  console.log('Điều hướng đến bài viết:', blog)

}
</script>

<style scoped>
.top-section {
  border-radius: 0.28571429rem 0.28571429rem 0 0;
}

.content-section {
  border-radius: 0 0 0.28571429rem 0.28571429rem;
}

.timeline {
  margin: 20px 0;
}


.tl-header {
  text-align: left;
  margin:  0;
  padding-left: calc(4.5em - 7.5em + 6em + 4px);
  position: relative;
}




.tl-header .p-tag {
  display: inline-block;
  font-weight: 500;
  letter-spacing: 0.5px;
  padding: 0.5em 1em;
  position: relative;
  z-index: 10;
}




.tl-date {
  position: relative;
  top: 10px;
  display: block;
  float: left;
  width: 4.5em;
  margin-left: -7.5em;
  text-align: right;
}

.tl-wrap {
  padding: 15px 0 15px 20px;
  margin-left: 6em;
  border-style: solid;
  border-width: 0 0 0 4px;
  position: relative;
}


.timeline-label {
  position: relative;
  margin-left: 8px !important;
  letter-spacing: 0.3px !important;
  font-size: 14px !important;
  font-weight: 500 !important;
  padding: 12px 15px !important;
  border-radius: 0.28571429rem;
  display: inline-block;
  cursor: pointer;
  transition: all 0.2s ease;
}


.timeline-label::before {
  content: '';
  position: absolute;
  right: 100%;
  top: 50%;
  transform: translateY(-50%);
  width: 0;
  height: 0;
  border-style: solid;
  border-width: 6px 8px 6px 0;
  border-color: transparent currentColor transparent transparent; /* Đổi border-color */
  z-index: 1;
}

.timeline-label:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
}

.tl-wrap:before {
  position: relative;
  top: 15px;
  float: left;
  width: 10px;
  height: 10px;
  margin-left: -27px;
  background: #fff;
  border-color: inherit;
  border-style: solid;
  border-width: 3px;
  border-radius: 50%;
  content: "";
  box-shadow: 0 0 0 4px #fff;
  z-index: 10;
}

.tl-wrap:hover:before {
  background: 0 0;
  border-color: #fff;
}

.timeline-link {
  text-decoration: none;
  display: inline-block;
}


.tl-blue .tl-header .p-tag,
.tl-blue .timeline-label {
  background: #23b7e5 !important;
  color: #fff !important;
}

.tl-blue .tl-item .tl-wrap {
  border-color: #23b7e5;
}

.tl-blue .timeline-label::before {
  border-color: transparent #23b7e5 transparent transparent;
}

.tl-blue .tl-header::before {
  background-color: #23b7e5;
}

.tl-blue .tl-header .p-tag::before {
  border-color: transparent #23b7e5 transparent transparent;
}

.tl-dark .tl-header .p-tag,
.tl-dark .timeline-label {
  background: #3a3f51 !important;
  color: #fff !important;
}

.tl-dark .tl-item .tl-wrap {
  border-color: #3a3f51;
}

.tl-dark .timeline-label::before {
  border-color: transparent #3a3f51 transparent transparent;
}

.tl-dark .tl-header::before {
  background-color: #3a3f51;
}

.tl-dark .tl-header .p-tag::before {
  border-color: transparent #3a3f51 transparent transparent;
}

.tl-green .tl-header .p-tag,
.tl-green .timeline-label {
  background: #27c24c !important;
  color: #fff !important;
}

.tl-green .tl-item .tl-wrap {
  border-color: #27c24c;
}


.tl-green .timeline-label::before {
  border-color: transparent #27c24c transparent transparent;
}

.tl-green .tl-header::before {
  background-color: #27c24c;
}

.tl-green .tl-header .p-tag::before {
  border-color: transparent #27c24c transparent transparent;
}

.tl-purple .tl-header .p-tag,
.tl-purple .timeline-label {
  background: #7266ba !important;
  color: #fff !important;
}

.tl-purple .tl-item .tl-wrap {
  border-color: #7266ba;
}

.tl-purple .timeline-label::before {
  border-color: transparent #7266ba transparent transparent;
}

.tl-purple .tl-header::before {
  background-color: #7266ba;
}

.tl-purple .tl-header .p-tag::before {
  border-color: transparent #7266ba transparent transparent;
}

.tl-red .tl-header .p-tag,
.tl-red .timeline-label {
  background: #f05050 !important;
  color: #fff !important;
}

.tl-red .tl-item .tl-wrap {
  border-color: #f05050;
}

.tl-red .timeline-label::before {
  border-color: transparent #f05050 transparent transparent;
}

.tl-red .tl-header::before {
  background-color: #f05050;
}

.tl-red .tl-header .p-tag::before {
  border-color: transparent #f05050 transparent transparent;
}

/* Responsive cho mobile */
@media (max-width: 768px) {
  .tl-header {
    width: auto;
    padding-left: calc(3.5em - 6em + 5em + 4px);
  }

  .tl-header::before {
    left: calc(3.5em - 6em + 5em);
  }

  .tl-header .p-tag {
    font-size: 0.9rem;
    padding: 0.4em 0.8em;
  }

  .tl-header .p-tag::before {
    border-width: 5px 6px 5px 0;
  }

  .tl-date {
    width: 3.5em;
    margin-left: -6em;
  }

  .tl-wrap {
    margin-left: 5em;
  }

  .timeline-label {
    font-size: 13px !important;
    padding: 10px 12px !important;
    margin-left: 6px !important;
  }

  .timeline-label::before {
    border-width: 5px 6px 5px 0;
  }
}

@media (max-width: 480px) {
  .tl-header {
    padding-left: calc(3em - 5em + 4em + 4px);
  }

  .tl-header::before {
    left: calc(3em - 5em + 4em);
    width: 3px;
  }

  .tl-header .p-tag {
    font-size: 0.8rem;
    padding: 0.3em 0.6em;
  }

  .tl-header .p-tag::before {
    border-width: 4px 5px 4px 0;
  }

  .tl-date {
    width: 3em;
    margin-left: -5em;
    font-size: 0.9rem;
  }

  .tl-wrap {
    margin-left: 4em;
    padding: 12px 0 12px 15px;
  }

  .tl-wrap:before {
    top: 12px;
    margin-left: -23px;
  }

  .timeline-label {
    font-size: 12px !important;
    padding: 8px 10px !important;
    margin-left: 4px !important;
  }

  .timeline-label::before {
    border-width: 4px 5px 4px 0;
  }
}
</style>