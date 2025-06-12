<template>
  <el-container>
    <el-header>
      <SearchBar @search="onSearch" />
    </el-header>
    <el-main>
      <PostCard v-for="post in posts" :key="post.id" :post="post" />
      <el-pagination
        v-model:current-page="page"
        :page-size="size"
        :total="total"
        @current-change="fetchPosts"
      />
    </el-main>
  </el-container>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import PostCard from '../components/PostCard.vue'
import SearchBar from '../components/SearchBar.vue'
import { getPosts } from '../api/post'

const posts = ref([])
const page = ref(1)
const size = ref(10)
const total = ref(0)

const fetchPosts = async () => {
  const res = await getPosts(page.value - 1, size.value)
  posts.value = res.data
  // total.value = res.total // 需后端返回总数
}

const onSearch = (keyword) => {
  // 跳转到 /search?keyword=xxx
}

onMounted(fetchPosts)
</script> 