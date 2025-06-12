<template>
  <el-form @submit.prevent="onRegister" :model="form">
    <el-form-item label="用户名">
      <el-input v-model="form.username" />
    </el-form-item>
    <el-form-item label="邮箱">
      <el-input v-model="form.email" />
    </el-form-item>
    <el-form-item label="密码">
      <el-input v-model="form.password" type="password" />
    </el-form-item>
    <el-form-item label="昵称">
      <el-input v-model="form.nickname" />
    </el-form-item>
    <el-form-item label="头像">
      <el-input v-model="form.avatar" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" native-type="submit">注册</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import { ref } from 'vue'
import { register } from '../api/user'
import { useRouter } from 'vue-router'

const router = useRouter()
const form = ref({ username: '', email: '', password: '', nickname: '', avatar: '' })

const onRegister = async () => {
  const res = await register(form.value)
  if (res.data) {
    router.push('/login')
  }
}
</script> 