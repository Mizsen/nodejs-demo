<template>
  <div class="user-profile-page">
    <Navbar />
    <el-card class="profile-card">
      <div class="user-info">
        <CircleAvatar :src="user.avatar" :size="64" />
        <div class="user-meta">
          <h2>{{ user.nickname }}</h2>
          <div>邮箱：{{ user.email }}</div>
          <div>注册时间：{{ user.registeredAt }}</div>
          <div>最后登录：{{ user.lastLogin }}</div>
        </div>
      </div>
    </el-card>
    <el-card class="blog-list-card">
      <h3>TA的博客</h3>
      <PostCard v-for="post in posts" :key="post.id" :post="post" />
      <el-pagination
        background
        layout="prev, pager, next"
        :total="total"
        :page-size="pageSize"
        :current-page="page"
        @current-change="handlePageChange"
        style="margin: 32px auto; text-align: center;"
      />
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import Navbar from '../components/Navbar.vue'
import CircleAvatar from '../components/CircleAvatar.vue'
import PostCard from '../components/PostCard.vue'
import { getUserProfile } from '../api/user'
import { getPosts } from '../api/post'

const route = useRoute()
const user = ref({})
const posts = ref([])
const total = ref(0)
const page = ref(1)
const pageSize = 10

const fetchUser = async () => {
  const { data } = await getUserProfile(route.params.id)
  user.value = data
}
const fetchPosts = async () => {
  const { data } = await getPosts(page.value, pageSize, route.params.id)
  posts.value = data.records || data.data || []
  total.value = data.total || 0
}

const handlePageChange = (newPage) => {
  page.value = newPage
  fetchPosts()
}

onMounted(() => {
  fetchUser()
  fetchPosts()
})
</script>

<style scoped>
.user-profile-page {
  background: #18181c;
  min-height: 100vh;
  color: #fff;
  padding-bottom: 40px;
}
.profile-card {
  margin: 32px auto 16px auto;
  max-width: 600px;
}
.user-info {
  display: flex;
  align-items: center;
  gap: 24px;
}
.user-meta {
  flex: 1;
}
.blog-list-card {
  margin: 0 auto;
  max-width: 800px;
}
</style>
