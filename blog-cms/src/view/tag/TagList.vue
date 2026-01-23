<template>
  <div>
    <Breadcrumb parent-title="Quản lý Blog"/>

    <el-card>
      <el-row :gutter="10">   <!-- :gutter = khoảng cách giữa các cột-->
        <el-col :span="6">   <!-- :span = độ rộng của cột -->
          <el-button type="primary" @click="addDialogVisible=true" :icon="Plus">
            Thêm Tag
          </el-button>
        </el-col>
      </el-row>

      <el-table :data="tagList" border stripe>
        <el-table-column label="STT" type="index" width="70"/>
        <el-table-column label="Tên" prop="tagName"/> <!--  prop="tagName" của tagList map với field của API -->
        <el-table-column label="Màu sắc" prop="tagColor">
          <template #default="scope">
            <div class="color-display-cell">
              <div
                  class="color-swatch"
                  :style="{ backgroundColor: scope.row.tagColor }"
              ></div>
              <el-text  >
                {{ scope.row.tagColor }}
              </el-text>
            </div>
          </template>
        </el-table-column>
<!--        <el-table-column label="Màu sắc " prop="tagColor">-->
<!--          <template v-slot="scope">-->
<!--            <span style="float:left;width: 100px;">{{ scope.row.tagColor }}</span>-->
<!--            <span style="float:left;width: 100px; height: 23px" class="me-base" :class="'me-'+scope.row.tagColor "></span>-->
<!--          </template>-->
<!--        </el-table-column>-->
        <el-table-column label="Thao tác" >
          <template #default="{ row }">

            <el-button type="primary" :icon="Edit" size="small" @click="showEditDialog(row)" >Chỉnh sửa</el-button>
            <el-popconfirm title="Bạn có chắc chắn muốn xóa không?" :icon="Delete" icon-color="red"
                           @confirm="deleteTagByIdVue(row.tagId)"
                           confirm-button-text="Xóa" confirm-button-type="danger" cancel-button-text="Hủy">
              <template #reference>
                <el-button type="danger" size="small"  :icon="Delete">Xóa</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="queryInfo.pageNum"
          :page-size="queryInfo.pageSize"
          :page-sizes="[5,10,15,20]"
          :total="total"
          layout="total, sizes, prev, paper, next, jumper"
          background/>
    </el-card>


<!--    addTag-->
    <el-dialog
        title="Thêm tag"
        width="50%"
        v-model="addDialogVisible"
        :close-on-click-moda="false"
        @close="addDialogClose">
      <el-form ref="addFormRef" :model="addForm" :rules="formRules" label-width="90">
        <el-form-item label="Tên Tag" prop="tagName">
          <el-input v-model="addForm.tagName" placeholder="Nhập tên Tag"/>
        </el-form-item>
        <el-form-item label="Màu sắc" prop="tagColor">
         <el-select
           v-model="addForm.tagColor"
           placeholder="Chọn màu cho tag"
           :clearable="true"
           style="width: 100%"
           filterable>
           <el-option
               v-for="item in colors"
               :key="item.value"
               :label="item.label"
               :value="item.value"
           >
             <div class="color-display-cell">
               <el-text style="float: left; width: 60px">
                 {{ item.value }}
               </el-text>
               <div class="color-swatch"
                    style="float: left;"
                    :style="{backgroundColor: item.value}">
               </div>
               <el-text>{{ item.label }}</el-text>
             </div>
           </el-option>
         </el-select>
        </el-form-item>
      </el-form>
      <template #footer >
        <span>
          <el-button @click="addDialogClose">Hủy</el-button>
          <el-button @click="addTag" type="primary">Tạo mới</el-button>
        </span>
      </template>
    </el-dialog>
<!--    editTag-->
    <el-dialog
      title="Chỉnh sửa Tag"
      width="50%"
      v-model="editDialogVisible"
      :close-on-click-modal="false"
      @close="editDialogClose">
      <el-form ref="editFormRef" :model="editForm" :rules="formRules" label-width="90">
        <el-form-item label="Id">
          <el-input v-model="editForm.tagId" readonly/>
        </el-form-item>
        <el-form-item label="Tên Tag" prop="tagName">
          <el-input v-model="editForm.tagName"/>
        </el-form-item>
        <el-form-item label="Màu sắc" prop="tagColor">
          <el-select
            v-model="editForm.tagColor"
            placeholder="Chọn màu cho tag"
            :clearable="true"
            style="width: 100%"
            filterable>
            <el-option
              v-for="item in colors"
              :key="item.value"
              :value="item.value"
              :label="item.label">
              <div class="color-display-cell">
                <el-text style="float:left; width: 60px">
                  {{ item.value }}
                </el-text>
                <div style="float:left"
                    class="color-swatch"
                    :style="{ backgroundColor: item.value }"
                ></div>
                <el-text  >
                  {{ item.label }}
                </el-text>
              </div>
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <span>
          <el-button @click="editDialogClose">Hủy</el-button>
          <el-button @click="editTag" type="primary">Chỉnh sửa</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import Breadcrumb from "@/components/Breadcrumb.vue";
import { ref, reactive, onMounted } from "vue";
import {createTag, deleteTagById, getData, updateTag} from "@/network/tag.js";
import { getCurrentInstance } from "vue";
import {Delete, Edit, Plus} from "@element-plus/icons-vue";

const { proxy } = getCurrentInstance()


const queryInfo = reactive({
  pageNum: 1,
  pageSize: 10,
})

const tagList = ref([])
const total = ref(0)
const addDialogVisible = ref(false)
const editDialogVisible = ref(false)
const addFormRef = ref(null)
const editFormRef = ref(null)
const addForm = ref({
  tagName: '',
  tagColor: '',
})
const editForm = reactive({})
const formRules = {
  tagName: [{required: true, message: 'Vui lòng nhập tên tag', trigger:'blur'},],
  tagColor: [{required: true, message: 'Vui lòng nhập màu tag', trigger:'blur'},],
}
const colors = ref([
  { label: 'Đỏ', value: '#FF4D4F' },
  { label: 'Cam', value: '#FA8C16' },
  { label: 'Vàng', value: '#FADB14' },
  { label: 'Xanh lá', value: '#52C41A' },
  { label: 'Xanh dương', value: '#1890FF' },
  { label: 'Tím', value: '#722ED1' },
  { label: 'Hồng', value: '#EB2F96' },
  { label: 'Xám', value: '#8C8C8C' },
  { label: 'Đen', value: '#262626' },
  { label: 'Xanh ngọc', value: '#13C2C2' },
  { label: 'Xanh biển', value: '#2F54EB' },
  { label: 'Nâu', value: '#A52A2A' }
])
const fetchData = async () => {
  try{
    const res = await getData(queryInfo)
    if(res.code === 200){
      console.log(res.data)
      total.value = res.data.tags.total
      tagList.value = res.data.tags.list
    }else
      proxy.$msgError(res.msg)
  }catch (error){

  }
}

const handleSizeChange = (newSize) =>{
  queryInfo.pageSize = newSize
  fetchData()
}
const handleCurrentChange = (newPage) => {
  queryInfo.pageNum = newPage
  fetchData()
}
const addDialogClose = () => {
  addDialogVisible.value = false
  addFormRef.value.resetFields()
}
const editDialogClose = () => {
  editDialogVisible.value = false
  editFormRef.value.resetFields()
}
const showEditDialog = (row) => {
  console.log(row)
  Object.assign(editForm ,row)
  editDialogVisible.value = true
}

const addTag = async () => {
  try{
    console.log(addForm.value)
    const res = await createTag(addForm.value)

    if(res.code === 200){
      proxy.$msgSuccess(res.msg)
    }else
      proxy.$msgError(res.msg)
  }catch (error){
    proxy.$msgError(error.response.data.msg)
  }
  addDialogClose()
  await fetchData()
}

const editTag = async () => {
  try {
    const res = await updateTag(editForm)
    if(res.code === 200){
      proxy.$msgSuccess(res.msg)
    }else
      proxy.$msgError(res.msg)
  }catch (error){
    proxy.$msgError(error.response.data.msg)
  }
  editDialogClose()
  await fetchData()
}

const deleteTagByIdVue = async (id) => {
  try {
    const res = await deleteTagById(id)
    if(res.code === 200){
      proxy.$msgSuccess(res.msg)
    }else
      proxy.$msgError(res.msg)
  }catch (error){
    proxy.$msgError(error.response.data.msg)
  }
  await fetchData()
}

onMounted(() =>{
  fetchData()
})

</script>

<style scoped>
.color-display-cell {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 4px 0;
}

.color-swatch {
  width: 100px;
  height: 20px;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
  flex-shrink: 0;
}

.el-button{
  margin-left: 10px;
}

</style>