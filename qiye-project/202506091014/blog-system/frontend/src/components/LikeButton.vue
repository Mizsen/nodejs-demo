<template>
  <el-button
    :type="liked ? 'primary' : 'default'"
    :icon="liked ? 'el-icon-thumb' : 'el-icon-thumb'"
    @click="toggleLike"
    circle
    size="small"
  >
    <span style="margin-left: 4px">{{ count }}</span>
  </el-button>
</template>

<script setup>
import { ref, watch, computed } from 'vue'
import { likePost, unlikePost } from '../api/like'
const props = defineProps({
  postId: [String, Number],
  liked: Boolean,
  count: Number
})
const emit = defineEmits(['update:liked', 'update:count'])
const isLiked = ref(props.liked)
const likeCount = ref(props.count)

watch(() => props.liked, v => (isLiked.value = v))
watch(() => props.count, v => (likeCount.value = v))

const toggleLike = async () => {
  if (!isLiked.value) {
    await likePost('', props.postId)
    likeCount.value++
    isLiked.value = true
  } else {
    await unlikePost('', props.postId)
    likeCount.value--
    isLiked.value = false
  }
  emit('update:liked', isLiked.value)
  emit('update:count', likeCount.value)
}
</script>
