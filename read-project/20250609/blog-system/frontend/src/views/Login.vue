<template>
  <el-form @submit.prevent="onLogin" :model="form">
    <el-form-item label="用户名/邮箱">
      <el-input v-model="form.usernameOrEmail" />
    </el-form-item>
    <el-form-item label="密码">
      <el-input v-model="form.password" type="password" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" native-type="submit">登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import { ref } from 'vue'
import { login } from '../api/user'
import { useRouter } from 'vue-router'
import { useUserStore } from '../store/user'

const router = useRouter()
const userStore = useUserStore()
const form = ref({ usernameOrEmail: '', password: '' })

const onLogin = async () => {
  const res = await login(form.value)
  if (res.data) {
    userStore.setToken(res.data)
    router.push('/')
  }
}
</script> 