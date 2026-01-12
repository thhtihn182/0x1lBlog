<template>
  <div>
    <!-- Breadcrumb navigation -->
    <Breadcrumb parent-title="Quản lý hệ thống" />

    <el-card>
      <el-row :gutter="20">
        <!-- Cài đặt cơ bản -->
        <el-col :span="12">
          <el-card>
            <template #header>
              <span>Cài đặt cơ bản</span>
            </template>
            <el-form label-position="right" label-width="100px">
              <el-form-item
                  :label="item.nameVn"
                  v-for="item in typeMap.type1"
                  :key="item.id"
              >
                <el-input v-model="item.value" />
              </el-form-item>
            </el-form>
          </el-card>
        </el-col>

        <!-- Thông tin thẻ cá nhân -->
        <el-col :span="12">
          <el-card>
            <template #header>
              <span>Thông tin thẻ cá nhân</span>
            </template>
            <el-form label-position="right" label-width="100px">
              <el-form-item
                  :label="item.nameVn"
                  v-for="item in typeMap.type3"
                  :key="item.id"
              >
                <el-input v-model="item.value" />
              </el-form-item>
            </el-form>
          </el-card>
        </el-col>
      </el-row>

      <!-- Phần huy hiệu chân trang -->
      <el-row style="margin-top: 20px">
        <el-card>
          <template #header>
            <span>Huy hiệu chân trang</span>
          </template>

          <!-- Danh sách huy hiệu -->
          <div v-for="badge in typeMap.type2" :key="badge.id" class="badge-item">
            <el-form :inline="true" :model="badge">
              <el-form-item label="Tiêu đề">
                <el-input v-model="badge.value.title" placeholder="Nhập tiêu đề" />
              </el-form-item>
              <el-form-item label="URL">
                <el-input v-model="badge.value.url" placeholder="Nhập URL" />
              </el-form-item>
              <el-form-item label="Chủ đề">
                <el-input v-model="badge.value.subject" placeholder="Nhập chủ đề" />
              </el-form-item>
              <el-form-item label="Giá trị">
                <el-input v-model="badge.value.value" placeholder="Nhập giá trị" />
              </el-form-item>
              <el-form-item label="Màu sắc">
                <el-color-picker v-model="badge.value.color" show-alpha />
                <el-input
                    v-model="badge.value.color"
                    placeholder="Nhập mã màu"
                    style="margin-left: 10px; width: 120px"
                />
              </el-form-item>
              <el-form-item>
                <el-button type="danger" :icon="Delete" @click="removeBadge(badge.id)">
                  Xóa
                </el-button>
              </el-form-item>
            </el-form>
          </div>

          <!-- Nút thêm huy hiệu mới -->
          <el-button type="primary" :icon="Plus" @click="addBadge">
            Thêm huy hiệu
          </el-button>
        </el-card>
      </el-row>

      <!-- Nút lưu -->
      <div class="save-button-container">
        <el-button type="primary" :icon="Check" >
          Lưu cài đặt
        </el-button>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { Delete, Plus, Check } from '@element-plus/icons-vue'
import Breadcrumb from "@/components/Breadcrumb.vue"
import { getSiteSettingList,  } from "@/network/siteSetting"

// Dữ liệu
const typeMap = ref({
  type1: [], // Cài đặt cơ bản
  type2: [], // Huy hiệu chân trang
  type3: []  // Thông tin thẻ cá nhân
})

// Hàm xử lý
const getList = async () => {
  try {
    const res = await getSiteSettingList()
    if (res.code === 200) {
      res.data.type2.forEach(item => {
        try {
          if (typeof item.value === 'string')
            item.value = JSON.parse(item.value)
        } catch (error) {
          item.value = {}
        }
      })
      typeMap.value = res.data
      ElMessage.success(res.msg || 'Tải dữ liệu thành công')
    } else {
      ElMessage.error(res.msg || 'Tải dữ liệu thất bại')
    }
  } catch (error) {
    console.error('Lỗi khi tải dữ liệu:', error)
    ElMessage.error('Yêu cầu thất bại')
  }
}

const addBadge = () => {
  if (!typeMap.value.type2) {
    typeMap.value.type2 = []
  }

  const newBadge = {
    id: Date.now(), // ID tạm thời
    value: {
      title: '',
      url: '',
      subject: '',
      value: '',
      color: '#409EFF'
    }
  }

  typeMap.value.type2.push(newBadge)
  ElMessage.info('Đã thêm huy hiệu mới')
}

const removeBadge = (id) => {
  const index = typeMap.value.type2.findIndex(item => item.id === id)
  if (index !== -1) {
    typeMap.value.type2.splice(index, 1)
    ElMessage.success('Đã xóa huy hiệu')
  }
}

// const saveSettings = async () => {
//   try {
//     // Chuẩn bị dữ liệu để gửi
//     const dataToSend = {
//       ...typeMap.value,
//       type2: typeMap.value.type2?.map(item => ({
//         ...item,
//         value: JSON.stringify(item.value)
//       })) || []
//     }
//
//     const res = await updateSiteSettings(dataToSend)
//
//     if (res.code === 200) {
//       ElMessage.success(res.msg || 'Lưu cài đặt thành công')
//       // Refresh dữ liệu sau khi lưu
//       await getList()
//     } else {
//       ElMessage.error(res.msg || 'Lưu cài đặt thất bại')
//     }
//   } catch (error) {
//     console.error('Lỗi khi lưu:', error)
//     ElMessage.error('Lưu thất bại')
//   }
// }

// Lifecycle hook
onMounted(() => {
  getList()
})
</script>

<style scoped>
/* Giữ nguyên CSS gốc */

/* Thêm style mới cho giao diện hiện đại */
.badge-item {
  background: #f8f9fa;
  border-radius: 8px;
  padding: 20px;
  margin-bottom: 16px;
  border: 1px solid #e9ecef;
  transition: all 0.3s ease;
}

.badge-item:hover {
  background: #f1f3f5;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

.badge-item + .badge-item {
  margin-top: 16px;
}

.save-button-container {
  text-align: right;
  margin-top: 30px;
  padding: 20px 0;
  border-top: 1px solid #e9ecef;
}

/* Responsive */
@media (max-width: 768px) {
  .el-col {
    width: 100%;
    margin-bottom: 20px;
  }

  .badge-item .el-form--inline {
    display: flex;
    flex-direction: column;
  }

  .badge-item .el-form-item {
    margin-right: 0;
    margin-bottom: 12px;
  }
}

/* Animation cho các hành động */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>

<!--&lt;!&ndash; GỢI Ý TỐI ƯU (để ngoài component) &ndash;&gt;-->

<!--&lt;!&ndash;-->
<!--1. SỬ DỤNG COMPOSABLES ĐỂ TÁI SỬ DỤNG LOGIC:-->
<!--// composables/useSiteSettings.js-->
<!--import { ref } from 'vue'-->
<!--import { getSiteSettingList, updateSiteSettings } from '@/network/siteSetting'-->

<!--export function useSiteSettings() {-->
<!--  const typeMap = ref({ type1: [], type2: [], type3: [] })-->
<!--  -->
<!--  const loadSettings = async () => {-->
<!--    // ... logic tải dữ liệu-->
<!--  }-->
<!--  -->
<!--  const saveSettings = async () => {-->
<!--    // ... logic lưu dữ liệu-->
<!--  }-->
<!--  -->
<!--  return { typeMap, loadSettings, saveSettings }-->
<!--}-->

<!--2. SỬ DỤNG COMPONENTS NHỎ HƠN ĐỂ TÁI SỬ DỤNG:-->
<!-- - Tạo BadgeForm.vue component riêng cho mỗi huy hiệu-->
<!-- - Tạo SettingSection.vue component cho mỗi phần cài đặt-->

<!--3. THÊM VALIDATION CHO FORM:-->
<!--import { reactive } from 'vue'-->
<!--const formRules = reactive({-->
<!--  title: [{ required: true, message: 'Vui lòng nhập tiêu đề', trigger: 'blur' }],-->
<!--  url: [{ type: 'url', message: 'Vui lòng nhập URL hợp lệ', trigger: 'blur' }]-->
<!--})-->

<!--4. THÊM DEBOUNCE CHO AUTO-SAVE:-->
<!--import { debounce } from 'lodash-es'-->
<!--const saveSettings = debounce(async () => {-->
<!--  // ... logic lưu-->
<!--}, 1000)-->

<!--5. SỬ DỤNG PINIA CHO STATE MANAGEMENT:-->
<!--// stores/siteSettings.js-->
<!--import { defineStore } from 'pinia'-->
<!--export const useSiteSettingsStore = defineStore('siteSettings', {-->
<!--  state: () => ({ typeMap: {} }),-->
<!--  actions: {-->
<!--    async loadSettings() { /* ... */ },-->
<!--    async saveSettings() { /* ... */ }-->
<!--  }-->
<!--})-->

<!--6. THÊM LOADING STATES:-->
<!--const loading = ref(false)-->
<!--const getList = async () => {-->
<!--  loading.value = true-->
<!--  try {-->
<!--    // ... logic-->
<!--  } finally {-->
<!--    loading.value = false-->
<!--  }-->
<!--}-->

<!--7. TẠO CUSTOM DIRECTIVE CHO AUTO-FOCUS:-->
<!--// directives/autoFocus.js-->
<!--export const autoFocus = {-->
<!--  mounted(el) {-->
<!--    el.focus()-->
<!--  }-->
<!--}-->

<!--8. SỬ DỤNG TELEPORT CHO MODAL/DIALOG:-->
<!--<teleport to="body">-->
<!--  <el-dialog v-model="dialogVisible">-->
<!--    &lt;!&ndash; Nội dung &ndash;&gt;-->
<!--</el-dialog>-->
<!--</teleport>-->

<!--9. THÊM INTERNATIONALIZATION (I18N) NẾU CẦN ĐA NGÔN NGỮ:-->
<!--import { useI18n } from 'vue-i18n'-->
<!--const { t } = useI18n()-->
<!--// Sử dụng: {{ t('settings.basic') }}-->

<!--10. THÊM DARK MODE SUPPORT:-->
<!--:class="{ 'dark-mode': isDark }"-->
<!--// CSS:-->
<!--.dark-mode .badge-item {-->
<!--background: #2d3748;-->
<!--border-color: #4a5568;-->
<!--}-->
<!--&ndash;&gt;-->