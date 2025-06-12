<template>
  <el-menu mode="horizontal">
    <el-menu-item index="1" @click="$router.push('/')">首页</el-menu-item>
    <el-menu-item index="2" @click="$router.push('/edit')">写博客</el-menu-item>
    <el-menu-item index="3" v-if="!isLogin" @click="$router.push('/login')">登录</el-menu-item>
    <el-menu-item index="4" v-if="!isLogin" @click="$router.push('/register')">注册</el-menu-item>
    <el-sub-menu v-if="isLogin" index="5">
      <template #title>
        <el-avatar :src="user.avatar" />
        {{ user.nickname || user.username }}
      </template>
      <el-menu-item @click="$router.push(`/user/${user.id}`)">个人主页</el-menu-item>
      <el-menu-item @click="logout">退出</el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<script setup>
import { computed } from 'vue'
import { useUserStore } from '../store/user'
const userStore = useUserStore()
const isLogin = computed(() => !!userStore.token)
const user = computed(() => userStore.user)
const logout = () => {
  userStore.logout()
}
</script> 