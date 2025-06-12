<template>
  <el-form @submit.prevent="onSubmit" :model="form">
    <el-form-item label="标题">
      <el-input v-model="form.title" />
    </el-form-item>
    <el-form-item label="内容">
      <mavon-editor
        ref="md"
        v-model="form.content"
        :toolbars="toolbars"
        @imgAdd="onImageAdd"
      />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" native-type="submit">发布</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import { ref } from 'vue'
import { createPost, updatePost, getPost } from '../api/post'
import { uploadFile } from '../api/upload'
import { useRoute, useRouter } from 'vue-router'
import MavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

const route = useRoute()
const router = useRouter()
const form = ref({ title: '', content: '' })
const toolbars = { bold: true, italic: true, image: true, code: true, link: true, video: true }

if (route.params.id) {
  getPost(route.params.id).then(res => {
    form.value = res.data
  })
}

// 图片上传钩子
const onImageAdd = async (pos, file) => {
  const res = await uploadFile(file, 'image', 1) // 1为当前用户id
  // 插入图片到编辑器
  const md = document.querySelector('.v-note-edit')
  if (md && res.data.url) {
    // mavon-editor 3.x 需用 $refs.md.$img2Url
    // 这里假设你有 ref="md"
    // this.$refs.md.$img2Url(pos, res.data.url)
    // setup语法糖下用如下方式
    const mdRef = document.querySelector('.v-note-edit')
    if (mdRef) {
      // 直接插入markdown图片语法
      form.value.content += `\n![](${res.data.url})\n`
    }
  }
}

// 视频上传（可自定义按钮，弹窗输入视频链接或上传视频文件）
const onVideoAdd = async (file) => {
  const res = await uploadFile(file, 'video', 1)
  // 插入视频Markdown语法
  form.value.content += `\n![](${res.data.url})\n`
}

const onSubmit = async () => {
  if (route.params.id) {
    await updatePost(route.params.id, form.value)
  } else {
    await createPost(form.value)
  }
  router.push('/')
}
</script> 