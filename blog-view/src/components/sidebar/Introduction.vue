<template>
  <div style="margin-right: 10px">
    <div class="m-box">
      <div class="card">
        <div >
          <img
              :src="introduction.avatar"
              style="width: 100%; height: auto; aspect-ratio: 1/1; object-fit: cover;"
              alt="Avatar"
          />
        </div>
        <div class="content" align="center">
          <div class="header">{{ introduction.name }}</div>
          <!-- Dãi màu văn bản-->
          <div id="rollText" class="m-margin-top" style="font-size: 15px;"></div>
        </div>
        <div class="extra content" align="center">
          <a :href="introduction.facebook" v-if="introduction.facebook" target="_blank" class="icon-button">
            <svg style="width: 1em!important;height: 1em!important;" viewBox="0 0 16 16" xmlns="http://www.w3.org/2000/svg" fill="none"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"><path fill="#1877F2" d="M15 8a7 7 0 00-7-7 7 7 0 00-1.094 13.915v-4.892H5.13V8h1.777V6.458c0-1.754 1.045-2.724 2.644-2.724.766 0 1.567.137 1.567.137v1.723h-.883c-.87 0-1.14.54-1.14 1.093V8h1.941l-.31 2.023H9.094v4.892A7.001 7.001 0 0015 8z"></path><path fill="#ffffff" d="M10.725 10.023L11.035 8H9.094V6.687c0-.553.27-1.093 1.14-1.093h.883V3.87s-.801-.137-1.567-.137c-1.6 0-2.644.97-2.644 2.724V8H5.13v2.023h1.777v4.892a7.037 7.037 0 002.188 0v-4.892h1.63z"></path></g></svg>
          </a>

          <!-- Instagram Icon -->
          <a :href="introduction.instagram" v-if="introduction.instagram" target="_blank" class="icon-button">
            <svg style="width: 1em!important;height: 1em!important;" viewBox="0 0 32 32" fill="none" xmlns="http://www.w3.org/2000/svg">
              <rect x="2" y="2" width="28" height="28" rx="6" fill="url(#paint0_radial_87_7153)"/>
              <rect x="2" y="2" width="28" height="28" rx="6" fill="url(#paint1_radial_87_7153)"/>
              <rect x="2" y="2" width="28" height="28" rx="6" fill="url(#paint2_radial_87_7153)"/>
              <path d="M23 10.5C23 11.3284 22.3284 12 21.5 12C20.6716 12 20 11.3284 20 10.5C20 9.67157 20.6716 9 21.5 9C22.3284 9 23 9.67157 23 10.5Z" fill="white"/>
              <path fill-rule="evenodd" clip-rule="evenodd" d="M16 21C18.7614 21 21 18.7614 21 16C21 13.2386 18.7614 11 16 11C13.2386 11 11 13.2386 11 16C11 18.7614 13.2386 21 16 21ZM16 19C17.6569 19 19 17.6569 19 16C19 14.3431 17.6569 13 16 13C14.3431 13 13 14.3431 13 16C13 17.6569 14.3431 19 16 19Z" fill="white"/>
              <path fill-rule="evenodd" clip-rule="evenodd" d="M6 15.6C6 12.2397 6 10.5595 6.65396 9.27606C7.2292 8.14708 8.14708 7.2292 9.27606 6.65396C10.5595 6 12.2397 6 15.6 6H16.4C19.7603 6 21.4405 6 22.7239 6.65396C23.8529 7.2292 24.7708 8.14708 25.346 9.27606C26 10.5595 26 12.2397 26 15.6V16.4C26 19.7603 26 21.4405 25.346 22.7239C24.7708 23.8529 23.8529 24.7708 22.7239 25.346C21.4405 26 19.7603 26 16.4 26H15.6C12.2397 26 10.5595 26 9.27606 25.346C8.14708 24.7708 7.2292 23.8529 6.65396 22.7239C6 21.4405 6 19.7603 6 16.4V15.6ZM15.6 8H16.4C18.1132 8 19.2777 8.00156 20.1779 8.0751C21.0548 8.14674 21.5032 8.27659 21.816 8.43597C22.5686 8.81947 23.1805 9.43139 23.564 10.184C23.7234 10.4968 23.8533 10.9452 23.9249 11.8221C23.9984 12.7223 24 13.8868 24 15.6V16.4C24 18.1132 23.9984 19.2777 23.9249 20.1779C23.8533 21.0548 23.7234 21.5032 23.564 21.816C23.1805 22.5686 22.5686 23.1805 21.816 23.564C21.5032 23.7234 21.0548 23.8533 20.1779 23.9249C19.2777 23.9984 18.1132 24 16.4 24H15.6C13.8868 24 12.7223 23.9984 11.8221 23.9249C10.9452 23.8533 10.4968 23.7234 10.184 23.564C9.43139 23.1805 8.81947 22.5686 8.43597 21.816C8.27659 21.5032 8.14674 21.0548 8.0751 20.1779C8.00156 19.2777 8 18.1132 8 16.4V15.6C8 13.8868 8.00156 12.7223 8.0751 11.8221C8.14674 10.9452 8.27659 10.4968 8.43597 10.184C8.81947 9.43139 9.43139 8.81947 10.184 8.43597C10.4968 8.27659 10.9452 8.14674 11.8221 8.0751C12.7223 8.00156 13.8868 8 15.6 8Z" fill="white"/>
              <defs>
                <radialGradient id="paint0_radial_87_7153" cx="0" cy="0" r="1" gradientUnits="userSpaceOnUse" gradientTransform="translate(12 23) rotate(-55.3758) scale(25.5196)">
                  <stop stop-color="#B13589"  offset=""/>
                  <stop offset="0.79309" stop-color="#C62F94"/>
                  <stop offset="1" stop-color="#8A3AC8"/>
                </radialGradient>
                <radialGradient id="paint1_radial_87_7153" cx="0" cy="0" r="1" gradientUnits="userSpaceOnUse" gradientTransform="translate(11 31) rotate(-65.1363) scale(22.5942)">
                  <stop stop-color="#E0E8B7"  offset=""/>
                  <stop offset="0.444662" stop-color="#FB8A2E"/>
                  <stop offset="0.71474" stop-color="#E2425C"/>
                  <stop offset="1" stop-color="#E2425C" stop-opacity="0"/>
                </radialGradient>
                <radialGradient id="paint2_radial_87_7153" cx="0" cy="0" r="1" gradientUnits="userSpaceOnUse" gradientTransform="translate(0.500002 3) rotate(-8.1301) scale(38.8909 8.31836)">
                  <stop offset="0.156701" stop-color="#406ADC"/>
                  <stop offset="0.467799" stop-color="#6A45BE"/>
                  <stop offset="1" stop-color="#6A45BE" stop-opacity="0"/>
                </radialGradient>
              </defs>
            </svg>
          </a>

          <a :href="introduction.github" v-if="introduction.github" target="_blank" class="icon-button">
            <svg style="width: 1em!important;height: 1em!important;" fill="#000000" viewBox="0 0 32 32" version="1.1" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <title>github</title> <path d="M16 1.375c-8.282 0-14.996 6.714-14.996 14.996 0 6.585 4.245 12.18 10.148 14.195l0.106 0.031c0.75 0.141 1.025-0.322 1.025-0.721 0-0.356-0.012-1.3-0.019-2.549-4.171 0.905-5.051-2.012-5.051-2.012-0.288-0.925-0.878-1.685-1.653-2.184l-0.016-0.009c-1.358-0.93 0.105-0.911 0.105-0.911 0.987 0.139 1.814 0.718 2.289 1.53l0.008 0.015c0.554 0.995 1.6 1.657 2.801 1.657 0.576 0 1.116-0.152 1.582-0.419l-0.016 0.008c0.072-0.791 0.421-1.489 0.949-2.005l0.001-0.001c-3.33-0.375-6.831-1.665-6.831-7.41-0-0.027-0.001-0.058-0.001-0.089 0-1.521 0.587-2.905 1.547-3.938l-0.003 0.004c-0.203-0.542-0.321-1.168-0.321-1.821 0-0.777 0.166-1.516 0.465-2.182l-0.014 0.034s1.256-0.402 4.124 1.537c1.124-0.321 2.415-0.506 3.749-0.506s2.625 0.185 3.849 0.53l-0.1-0.024c2.849-1.939 4.105-1.537 4.105-1.537 0.285 0.642 0.451 1.39 0.451 2.177 0 0.642-0.11 1.258-0.313 1.83l0.012-0.038c0.953 1.032 1.538 2.416 1.538 3.937 0 0.031-0 0.061-0.001 0.091l0-0.005c0 5.761-3.505 7.029-6.842 7.398 0.632 0.647 1.022 1.532 1.022 2.509 0 0.093-0.004 0.186-0.011 0.278l0.001-0.012c0 2.007-0.019 3.619-0.019 4.106 0 0.394 0.262 0.862 1.031 0.712 6.028-2.029 10.292-7.629 10.292-14.226 0-8.272-6.706-14.977-14.977-14.977-0.006 0-0.013 0-0.019 0h0.001z"></path> </g></svg>
          </a>

          <a :href="introduction.email" v-if="introduction.email" class="icon-button">
            <svg style="width: 1em!important;height: 1em!important;" fill="#000000" viewBox="0 0 18 20" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"><path d="M1.574 5.286l7.5 4.029c.252.135.578.199.906.199.328 0 .654-.064.906-.199l7.5-4.029c.489-.263.951-1.286.054-1.286H1.521c-.897 0-.435 1.023.053 1.286zm17.039 2.203l-7.727 4.027c-.34.178-.578.199-.906.199s-.566-.021-.906-.199-7.133-3.739-7.688-4.028C.996 7.284 1 7.523 1 7.707V15c0 .42.566 1 1 1h16c.434 0 1-.58 1-1V7.708c0-.184.004-.423-.387-.219z"></path></g></svg>
          </a>
        </div>
        <div>
          <el-collapse accordion>
            <el-collapse-item :title="item.title" :name="index" v-for="(item,index) in introduction.favorite" :key="index">
              <div>{{ item.content }}</div>
            </el-collapse-item>
          </el-collapse>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { ElCollapse, ElCollapseItem } from 'element-plus';

const introduction = ref({
  avatar: '/src/assets/img/avatar.jpg',
  name: 'thjnk',
  rollText: ['Câu châm ngôn 1', 'Câu châm ngôn 2', 'Câu châm ngôn 3'],
  github: 'https://github.com/',
  facebook: 'https://www.facebook.com/thjnk.profile/',
  instagram: 'https://www.instagram.com/_thhtihn.18/',
  email: 'hoangthinhh18.deva@gmail.com',
  favorite: [
    {
      title: 'Anime yêu thích',
      content: 'Mô tả về anime yêu thích'
    },
    {
      title: 'Nhạc yêu thich',
      content: 'Son'
    },
    {
      title: 'Trò chơi yêu thích',
      content: 'Mô tả về trò chơi yêu thích'
    },
  ]
})

onMounted(() => {
  rollText()
})

const rollText = () => {
  // r = DOM element hiển thị text
  let r = document.getElementById('rollText')

  // l = prefix string (có thể rỗng)
  let l = ""

  // o = array sentences (chuyển tất cả thành string)
  let o = introduction.value.rollText.map(function (r) {
    return r + ""
  })

  // a = số frame pause khi đổi chiều
  let a = 2

  // g = step size (tốc độ typing)
  let g = 1

  // s = số ký tự ngẫu nhiên hiển thị
  let s = 5

  // d = delay giữa các frame (ms)
  let d = 75

  // b = mảng màu gradient
  let b = ["rgb(110,64,170)", "rgb(150,61,179)", "rgb(191,60,175)",
    "rgb(228,65,157)", "rgb(254,75,131)", "rgb(255,94,99)",
    "rgb(255,120,71)", "rgb(251,150,51)", "rgb(226,183,47)",
    "rgb(198,214,60)", "rgb(175,240,91)", "rgb(127,246,88)",
    "rgb(82,246,103)", "rgb(48,239,130)", "rgb(29,223,163)",
    "rgb(26,199,194)", "rgb(35,171,216)", "rgb(54,140,225)",
    "rgb(76,110,219)", "rgb(96,84,200)"]

  // c = typing state object
  let c = {
    text: "",          // text hiện tại đang hiển thị
    prefixP: -s,       // prefix position (bắt đầu từ -5)
    skillI: 0,         // sentence index (câu hiện tại)
    skillP: 0,         // character position trong câu
    direction: "forward", // "forward" = typing, "backward" = deleting
    delay: a,          // delay counter
    step: g            // step counter
  }

  // t = get random color function
  function t() {
    return b[Math.floor(Math.random() * b.length)]
  }

  // e = get random ASCII character function
  function e() {
    return String.fromCharCode(94 * Math.random() + 33)
  }

  // n = create random chars fragment function
  function n(r) {
    let n = document.createDocumentFragment();
    let i = 0;
    for (; r > i; i++) {
      let l = document.createElement("span")
      l.textContent = e()
      l.style.color = t()
      n.appendChild(l)
    }
    return n
  }

  // i = main animation loop function
  function i() {
    let t = o[c.skillI]  // Lấy câu hiện tại

    // // Logic điều khiển animation
    // c.step ? c.step-- : (c.step = g, // Giảm step hoặc reset
    //
    //         c.prefixP < l.length ?  // Nếu đang hiển thị prefix
    //             (c.prefixP >= 0 && (c.text += l[c.prefixP]), c.prefixP++) : // Thêm ký tự prefix
    //
    //             "forward" === c.direction ?  // Nếu đang gõ forward
    //                 c.skillP < t.length ?  // Nếu chưa gõ hết câu
    //                     (c.text += t[c.skillP], c.skillP++) : // Thêm ký tự
    //                     c.delay ? c.delay-- : (c.direction = "backward", c.delay = a) : // Hết câu → đổi hướng
    //
    //                 c.skillP > 0 ?  // Nếu đang xóa backward
    //                     (c.text = c.text.slice(0, -1), c.skillP--) : // Xóa ký tự cuối
    //                     (c.skillI = (c.skillI + 1) % o.length, c.direction = "forward") // Chuyển câu tiếp theo
    // )
// Logic điều khiển animation
    if (c.step) {
      // Nếu step > 0, chỉ giảm step, không làm gì khác
      c.step--
    }else {
      // Reset step về giá trị gốc
      c.step = g

      // Kiểm tra xem đang ở giai đoạn nào
      if (c.prefixP < l.length) {
        // Đang hiển thị prefix
        // if (c.prefixP >= 0) { // Chưa có ý nghĩa
        //   c.text += l[c.prefixP]  // Thêm ký tự prefix (l rỗng nên không thêm gì)
        // }
        c.prefixP++  // Tăng vị trí prefix
      } else if (c.direction === "forward") {
        // Đang gõ forward (từ trái sang phải)
        if (c.skillP < t.length) {
          // Chưa gõ hết câu - thêm ký tự tiếp theo
          c.text += t[c.skillP]
          c.skillP++
        } else {
          // Đã gõ hết câu - kiểm tra delay
          if (c.delay) {
            c.delay--  // Giảm delay counter
          } else {
            // Hết delay - chuyển sang xóa (backward)
            c.direction = "backward"
            c.delay = a  // Reset delay
          }
        }
      }else {
        // Đang xóa backward (từ phải sang trái)
        if (c.skillP > 0) {
          // Còn ký tự để xóa
          c.text = c.text.slice(0, -1)  // Xóa ký tự cuối
          c.skillP--  // Giảm vị trí
        } else {
          // Đã xóa hết - chuyển sang câu tiếp theo
          c.skillI = (c.skillI + 1) % o.length  // Chuyển câu (vòng lặp)
          c.direction = "forward"  // Reset về chế độ gõ
          // Lưu ý: KHÔNG reset prefixP về -s ở đây (có thể là bug)
        }
      }
    }
    // Hiển thị
    r.textContent = c.text // Hiển thị text chính
    r.appendChild(        // Thêm ký tự ngẫu nhiên ở cuối
        n(c.prefixP < l.length ?
            Math.min(s, s + c.prefixP ) :  // Số ký tự prefix còn lại
            Math.min(s, t.length - c.skillP)  // Số ký tự còn lại trong câu
        )
    )
    setTimeout(i, d) // Lặp lại sau delay
  }


  i()
}

</script>

<style>
.el-collapse-item {
  padding-left: 15px;
  padding-right: 15px;
}

.el-collapse-item .el-collapse-item__content {
  padding-bottom: 10px;
}

.card {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);

}

</style>