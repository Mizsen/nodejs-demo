<template>
  <el-input
    v-model="input"
    placeholder="搜索博客"
    clearable
    @keyup.enter="onSearch"
    style="width: 320px"
  >
    <template #append>
      <el-button icon="el-icon-search" @click="onSearch" />
    </template>
  </el-input>
</template>

<script setup>
import { ref, watch } from 'vue'
const props = defineProps({ modelValue: String })
const emit = defineEmits(['update:modelValue', 'search'])
const input = ref(props.modelValue || '')

watch(input, val => emit('update:modelValue', val))
const onSearch = () => emit('search', input.value)
</script>