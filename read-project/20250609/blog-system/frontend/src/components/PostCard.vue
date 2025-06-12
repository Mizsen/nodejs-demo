<template>
  <el-card>
    <h2 @click="$router.push(`/post/${post.id}`)">{{ post.title }}</h2>
    <div>{{ post.summary }}</div>
    <div>
      <el-avatar :src="post.authorAvatar" />
      {{ post.authorNickname }}
      <el-icon><i class="el-icon-thumb"></i></el-icon> {{ post.likesCount }}
      <el-icon><i class="el-icon-chat-dot-round"></i></el-icon> {{ post.commentCount }}
    </div>
  </el-card>
</template>

<script setup>
defineProps({ post: Object })
</script> 