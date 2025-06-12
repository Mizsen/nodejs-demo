<template>
  <el-card class="comment-card" shadow="never">
    <div class="comment-header">
      <CircleAvatar :src="comment.userAvatar" :size="32" />
      <span class="nickname">{{ comment.userNickname }}</span>
      <span class="date">{{ comment.createdAt }}</span>
    </div>
    <div class="comment-content">{{ comment.content }}</div>
    <div class="comment-actions">
      <el-button size="small" text @click="$emit('reply', comment)">回复</el-button>
      <el-button size="small" text type="danger" v-if="canDelete" @click="$emit('delete', comment)">删除</el-button>
    </div>
  </el-card>
</template>

<script setup>
import CircleAvatar from './CircleAvatar.vue'
const props = defineProps({
  comment: Object,
  canDelete: Boolean
})
</script>

<style scoped>
.comment-card {
  margin-bottom: 16px;
  background: #23232b;
  color: #fff;
  border-radius: 8px;
  border: none;
}
.comment-header {
  display: flex;
  align-items: center;
  gap: 12px;
  font-size: 1rem;
  margin-bottom: 4px;
}
.nickname {
  font-weight: bold;
}
.date {
  color: #aaa;
  font-size: 0.9em;
  margin-left: 8px;
}
.comment-content {
  margin: 8px 0 4px 0;
  word-break: break-all;
}
.comment-actions {
  text-align: right;
}
</style>
