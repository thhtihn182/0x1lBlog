<template>
  <div>
    <!--Thanh điều hướng breadcrumb-->
    <Breadcrumb parent-title="Quản lý Blog"></Breadcrumb>

    <el-card>
      <el-form :model="form" :rules="formRules" ref="formRef" label-position="top">
        <el-form-item prop="title">
          <el-input v-model="form.title" placeholder="Vui lòng nhập tiêu đề"
                    style="min-width: 500px">
            <template #prepend>
              <el-select
                  v-model="form.flag"
                  placeholder="Vui lòng chọn thể loại"
                  :allow-create="true"
                  :filterable="true"
                  style="width: 160px"
              >
                <el-option
                    :label="item"
                    :value="item"
                    v-for="(item,index) in flagList"
                    :key="index"
                />
              </el-select>
            </template>
          </el-input>
        </el-form-item>

        <!-- Editor cho mô tả -->
        <el-form-item label="Mô tả Blog" prop="description">
          <div style="border: 1px solid #DCDFE6; border-radius: 4px; background: white;">
            <!-- Thanh công cụ cho mô tả -->
            <div v-if="descriptionEditor" style="padding: 8px; border-bottom: 1px solid #DCDFE6; background: #F5F7FA; display: flex; gap: 4px;">
              <el-button
                  size="small"
                  :type="descriptionEditor.isActive('bold') ? 'primary' : ''"
                  @click="descriptionEditor.chain().focus().toggleBold().run()"
              >
                B
              </el-button>
              <el-button
                  size="small"
                  :type="descriptionEditor.isActive('italic') ? 'primary' : ''"
                  @click="descriptionEditor.chain().focus().toggleItalic().run()"
              >
                I
              </el-button>
            </div>
            <editor-content
                :editor="descriptionEditor"
                style="padding: 16px; min-height: 120px; outline: none; min-width: 500px"
            />
          </div>
        </el-form-item>

        <!-- Editor cho nội dung chính -->
        <el-form-item label="Nội dung Blog" prop="content">
          <div style="border: 1px solid #DCDFE6; border-radius: 4px; background: white;">
            <!-- Thanh công cụ đơn giản -->
            <div v-if="editor" style="padding: 8px; border-bottom: 1px solid #DCDFE6; background: #F5F7FA; display: flex; gap: 4px; flex-wrap: wrap;">
              <el-button
                  size="small"
                  :type="editor.isActive('bold') ? 'primary' : ''"
                  @click="editor.chain().focus().toggleBold().run()"
              >
                B
              </el-button>
              <el-button
                  size="small"
                  :type="editor.isActive('italic') ? 'primary' : ''"
                  @click="editor.chain().focus().toggleItalic().run()"
              >
                I
              </el-button>
              <el-button
                  size="small"
                  :type="editor.isActive('heading', { level: 2 }) ? 'primary' : ''"
                  @click="editor.chain().focus().toggleHeading({ level: 2 }).run()"
              >
                H2
              </el-button>
              <el-button
                  size="small"
                  @click="addImage"
              >
                Hình ảnh
              </el-button>
            </div>

            <!-- Nội dung Editor -->
            <editor-content :editor="editor" style="padding: 16px; min-height: 400px; outline: none; min-width: 500px" />
          </div>
        </el-form-item>

        <!-- Các mục form khác giữ nguyên -->
        <el-form-item label="Thể loại (có thể thêm thể loại mới)" prop="cate" >
          <el-select
              v-model="form.cate"
              placeholder="Vui lòng chọn thể loại"
              :allow-create="true"
              :filterable="true"
              style="width: 50%;"
          >
            <el-option
                :label="item.name"
                :value="item.id"
                v-for="item in categoryList"
                :key="item.id"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="Tag (có thể thêm tag mới)" prop="tagList">
          <el-select
              v-model="form.tagList"
              placeholder="Vui lòng chọn Tag"
              :allow-create="true"
              :filterable="true"
              :multiple="true"
              style="width: 50%;"
          >
            <el-option
                :label="item.name"
                :value="item.id"
                v-for="item in tagList"
                :key="item.id"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="Số từ" prop="words">
          <el-input
              v-model="form.words"
              placeholder="Vui lòng nhập số từ của Blog"
              type="number"
              style="width: 50%;"
          />
        </el-form-item>

        <el-form-item label="Thời gian đọc (tùy chọn)" >
          <el-input
              v-model="form.readTime"
              placeholder="Vui lòng nhập thời gian đọc. Mặc định Math.round(words/200)"
              type="number"
              style="width: 50%"
          />
        </el-form-item>

        <el-form-item label="Lượt xem (tùy chọn)" prop="views">
          <el-input
              v-model="form.views"
              placeholder="Vui lòng nhập số lượt xem. Mặc đinh là 0"
              type="number"
              style="width: 50%"
          />
        </el-form-item>

        <el-form-item>
          <el-switch v-model="form.shareStatement" active-text="Bản quyền " />
          <el-switch v-model="form.appreciation" active-text="Ủng hộ " />
          <el-switch v-model="form.recommend" active-text="Đề xuất " />
          <el-switch v-model="form.commentEnabled" active-text="Bình luận " />
        </el-form-item>

        <el-form-item style="text-align: right;">
          <el-button type="info" @click="submit(false)">Lưu bản nháp</el-button>
          <el-button type="primary" @click="submit(true)">Đăng bài</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { useEditor, EditorContent } from '@tiptap/vue-3'
import StarterKit from '@tiptap/starter-kit'
import Image from '@tiptap/extension-image'
import { ref, reactive, onMounted, onUnmounted, watch } from 'vue'
import { useRouter , useRoute} from 'vue-router'
import { getCategoryAndTag, saveBlog , getBlogById , updateBlog} from '@/network/blog'
import { getCurrentInstance} from "vue";
import Breadcrumb from "@/components/Breadcrumb.vue";

const { proxy } = getCurrentInstance()
const route = useRoute()
const router = useRouter()
const formRef = ref()

// Dữ liệu
const flagList = ['Nguyên tác', 'Chuyển tải', 'Dịch thuật']
const categoryList = ref([])
const tagList = ref([])

const form = reactive({
  title: '',
  flag: null,
  content: '',
  description: '',
  cate: null,
  tagList: [],
  readTime: null,
  views: 0,
  words: null,
  shareStatement: false,
  appreciation: false,
  recommend: false,
  commentEnabled: false,
  published: false
})

// Watch cho cả content và description
watch(() => form.content, (newContent) => {
  if (editor.value && newContent !== editor.value.getHTML()) {
    console.log('🔄 Updating content editor with new content')
    editor.value.commands.setContent(newContent, false)
  }
})

watch(() => form.description, (newDescription) => {
  if (descriptionEditor.value && newDescription !== descriptionEditor.value.getHTML()) {
    console.log('🔄 Updating description editor with new content')
    descriptionEditor.value.commands.setContent(newDescription, false)
  }
})

const formRules = {
  title: [{ required: true, message: 'Vui lòng nhập tiêu đề', trigger: 'change' }],
  cate: [{ required: true, message: 'Vui lòng chọn danh mục', trigger: 'change' }],
  tagList: [{ required: true, message: 'Vui lòng chọn thẻ', trigger: 'change' }],
  words: [{ required: true, message: 'Vui lòng nhập số từ bài viết', trigger: 'change' }],
  description: [{ required: true, message: 'Vui lòng nhập mô tả bài viết', trigger: 'change' }],
}

// TipTap Editor cho nội dung chính
const editor = useEditor({
  content: form.content,
  extensions: [
    StarterKit,
    Image,
  ],
  onUpdate: ({editor}) => {
    form.content = editor.getHTML()
    const wordLength = editor.getText().trim().length
    form.words = wordLength
    form.readTime = Math.round(wordLength / 200.0) + 5
  },
})

// TipTap Editor cho mô tả
const descriptionEditor = useEditor({
  content: form.description,
  extensions: [
    StarterKit.configure({
      // Giới hạn các tính năng cho mô tả
      heading: false,
      codeBlock: false,
      blockquote: false,
      bulletList: false,
      orderedList: false,
    }),
  ],
  onUpdate: ({editor}) => {
    form.description = editor.getHTML()
  },
})

const addImage = () => {
  const url = window.prompt('Vui lòng nhập URL hình ảnh: ')
  if (url) {
    editor.value.chain().focus().setImage({src: url}).run()
  }
}

const getBlog = async (id) => {
  try {
    const res = await getBlogById(id);
    if (res.code === 200) {
      computeCategoryAndTag(res.data)
      Object.assign(form, res.data)
    }
  } catch (e) {
    console.error('Lỗi khi lấy blog:', e)
  }
}

const computeCategoryAndTag = (blog) => {
  blog.cate = blog.category.id;
  blog.tagList = []
  blog.tags.forEach(tag => blog.tagList.push(tag.id))
}

const getData = async () => {
  try {
    const res = await getCategoryAndTag()
    if (res.code === 200) {
      console.log(res.msg)
      categoryList.value = res.data.categories
      tagList.value = res.data.tags
    } else {
      proxy.$msgError(res.msg)
    }
  } catch (error) {
    proxy.$msgError('Yêu cầu thất bại')
  }
}

const submit = async (published) => {
  try {
    await formRef.value.validate()

    form.published = published

    console.log('Dữ liệu gửi:', form)
    let res
    if (route.params.id) {
      res = await updateBlog(form);
    } else {
      res = await saveBlog(form)
    }
    if (res.code === 200) {
      await router.push('/blogs')
      console.log(res.data)
      proxy.$msgSuccess(res.msg)
    } else
      proxy.$msgError(res.msg)

  } catch (error) {
    if (error?.errors) {
      proxy.$msgError('Vui lòng điền các mục biểu mẫu bắt buộc')
    } else {
      proxy.$msgError('Yêu cầu thất bại')
    }
  }
}

onMounted(() => {
  getData()
  if (route.params.id)
    getBlog(route.params.id)
})

onUnmounted(() => {
  if (editor.value) {
    editor.value.destroy()
  }
  if (descriptionEditor.value) {
    descriptionEditor.value.destroy()
  }
})
</script>

<style>
.ProseMirror {
  outline: none;
  min-height: 400px;
  padding: 16px;
  line-height: 1.6;
}

.ProseMirror:first-child {
  min-height: 120px;
}

.ProseMirror h1 {
  font-size: 2em;
  margin: 0.67em 0;
}

.ProseMirror h2 {
  font-size: 1.5em;
  margin: 0.83em 0;
}

.ProseMirror h3 {
  font-size: 1.17em;
  margin: 1em 0;
}

.ProseMirror p {
  margin: 1em 0;
}

.ProseMirror img {
  max-width: 100%;
}
</style>