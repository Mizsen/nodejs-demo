<template>
  <div>
    <el-card>
      <h2>用户列表</h2>
      <el-table :data="users" style="width: 100%">
        <el-table-column prop="username" label="用户名" />
        <el-table-column prop="realName" label="真实姓名" />
        <el-table-column prop="role" label="角色" />
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from '@/api/index';

const users = ref([]);

onMounted(async () => {
  // 正确的后端接口路径
  const { data } = await axios.get('/auth/users');
  users.value = data.list || [];
});
</script>
