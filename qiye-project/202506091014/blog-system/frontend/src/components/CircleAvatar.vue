<template>
  <el-avatar :src="src" :size="sizeMap[size]" :style="avatarStyle" />
</template>

<script setup>
const props = defineProps({
  src: String,
  size: {
    type: String,
    default: 'medium',
    validator: v => ['small', 'medium', 'large', '32', '48', '64'].includes(v)
  }
})
const sizeMap = {
  small: 32,
  medium: 48,
  large: 64,
  32: 32,
  48: 48,
  64: 64
}
const avatarStyle = {
  border: '2px solid #444',
  background: '#222'
}
</script>

<style scoped>
.el-avatar {
  transition: box-shadow 0.2s;
}
.el-avatar:hover {
  box-shadow: 0 0 8px #409eff;
  cursor: pointer;
}
</style>
