<template>
  <div class="post-detail-page">
    <Navbar />
    <div class="post-header">
      <el-button @click="$router.back()" icon="el-icon-arrow-left">返回</el-button>
      <el-button icon="el-icon-share">分享</el-button>
    </div>
    <h1 class="post-title">{{ post.title }}</h1>
    <div class="post-meta">
      <CircleAvatar :src="post.authorAvatar" :size="32" />
      <span class="author">{{ post.authorName }}</span>
      <span class="date">{{ post.createdAt }}</span>
      <el-tag v-for="tag in post.tags" :key="tag">{{ tag }}</el-tag>
    </div>
    <MarkdownRenderer :content="post.content" />
    <div class="post-actions">
      <LikeButton :post-id="post.id" :liked="post.liked" :count="post.likeCount" />
      <el-button icon="el-icon-chat-dot-round">评论</el-button>
    </div>
    <div class="comment-section">
      <CommentCard v-for="comment in comments" :key="comment.id" :comment="comment" />
      <el-input
        v-model="newComment"
        type="textarea"
        placeholder="发表评论..."
        rows="3"
        style="margin: 16px 0;"
      />
      <el-button type="primary" @click="submitComment">发表评论</el-button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import Navbar from '../components/Navbar.vue'
import MarkdownRenderer from '../components/MarkdownRenderer.vue'
import CommentCard from '../components/CommentCard.vue'
import CircleAvatar from '../components/CircleAvatar.vue'
import LikeButton from '../components/LikeButton.vue'
import { getPost } from '../api/post'
import { getComments, addComment } from '../api/comment'

const route = useRoute()
const post = ref({})
const comments = ref([])
const newComment = ref('')

const fetchPost = async () => {
  const { data } = await getPost(route.params.id)
  post.value = data
}
const fetchComments = async () => {
  const { data } = await getComments(route.params.id)
  comments.value = data
}
const submitComment = async () => {
  if (newComment.value.length < 5) return
  await addComment({ postId: route.params.id, content: newComment.value })
  newComment.value = ''
  fetchComments()
}
onMounted(() => {
  fetchPost()
  fetchComments()
})
</script>

<style scoped>
.post-detail-page {
  background: #18181c;
  min-height: 100vh;
  color: #fff;
  padding-bottom: 40px;
}
.post-header {
  display: flex;
  gap: 12px;
  margin: 16px 0;
}
.post-title {
  font-size: 2.2rem;
  margin: 16px 0 8px 0;
}
.post-meta {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 24px;
}
.post-actions {
  display: flex;
  gap: 16px;
  margin: 24px 0;
}
.comment-section {
  margin-top: 32px;
}
</style>
