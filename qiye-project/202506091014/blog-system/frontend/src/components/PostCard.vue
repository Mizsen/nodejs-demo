<template>
  <el-card class="post-card" shadow="hover">
    <div class="post-header">
      <span class="post-title">{{ post.title }}</span>
    </div>
    <div class="post-summary">{{ post.summary || post.content?.slice(0, 80) + '...' }}</div>
    <div class="post-meta">
      <CircleAvatar :src="post.authorAvatar" :size="32" />
      <span class="author">{{ post.authorName }}</span>
      <span class="meta-item"><el-icon><i class="el-icon-thumb"></i></el-icon> {{ post.likeCount || 0 }}</span>
      <span class="meta-item"><el-icon><i class="el-icon-chat-dot-round"></i></el-icon> {{ post.commentCount || 0 }}</span>
    </div>
    <div class="post-footer">
      <router-link :to="`/posts/${post.id}`">
        <el-button type="primary" size="small">查看详情</el-button>
      </router-link>
    </div>
  </el-card>
</template>

<script setup>
import CircleAvatar from './CircleAvatar.vue'
const props = defineProps({ post: Object })
</script>

<style scoped>
.post-card {
  width: 340px;
  margin: 0 8px 16px 8px;
  background: #23232b;
  color: #fff;
  border-radius: 12px;
  border: none;
  transition: box-shadow 0.2s;
}
.post-header {
  font-size: 1.2rem;
  font-weight: bold;
  margin-bottom: 8px;
}
.post-summary {
  color: #bbb;
  margin-bottom: 12px;
  min-height: 40px;
}
.post-meta {
  display: flex;
  align-items: center;
  gap: 12px;
  font-size: 0.95rem;
  margin-bottom: 8px;
}
.meta-item {
  display: flex;
  align-items: center;
  gap: 4px;
}
.post-footer {
  text-align: right;
}
</style>
