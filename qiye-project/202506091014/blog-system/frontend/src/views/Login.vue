<template>
  <div class="login-page">
    <Navbar />
    <el-card class="login-card">
      <h2>登录</h2>
      <el-form :model="form" @submit.prevent="onLogin">
        <el-form-item label="用户名">
          <el-input v-model="form.username" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" type="password" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" native-type="submit">登录</el-button>
        </el-form-item>
      </el-form>
      <div class="register-link">
        还没有账号？<router-link to="/register">注册</router-link>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import Navbar from '../components/Navbar.vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '../store/user'
import { login } from '../api/user'

const router = useRouter()
const userStore = useUserStore()
const form = ref({ username: '', password: '' })

const onLogin = async () => {
  if (!form.value.username || !form.value.password) {
    window.$message ? window.$message.error('用户名和密码不能为空') : alert('用户名和密码不能为空')
    return
  }
  const res = await login(form.value)
  if (res.data && res.data.token) {
    userStore.setToken(res.data.token)
    await userStore.fetchUserInfo() // 调用获取用户信息的方法
    router.push('/')
  } else {
    window.$message ? window.$message.error('登录失败') : alert('登录失败')
  }
}
</script>

<style scoped>
.login-page {
  background: #18181c;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.login-card {
  width: 350px;
  margin-top: 80px;
}
.register-link {
  margin-top: 16px;
  text-align: center;
}
</style>
