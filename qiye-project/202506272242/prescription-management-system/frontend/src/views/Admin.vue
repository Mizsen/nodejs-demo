<template>
  <div class="admin-layout">
    <el-container style="height: 100vh">
      <el-aside width="220px">
        <el-menu
          :default-active="activeMenu"
          :default-openeds="defaultOpeneds"
          class="el-menu-vertical-demo"
          :unique-opened="true"
          router
        >
          <el-sub-menu v-for="(group, idx) in menuGroups" :key="idx" :index="String(idx)">
            <template #title>{{ group.title }}</template>
            <el-menu-item v-for="item in group.children" :key="item.path" :index="item.path" @click="go(item.path)">
              {{ item.label }}
            </el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header style="background:#f5f5f5;display:flex;justify-content:space-between;align-items:center;">
          <span>欢迎，{{ user?.username }}（{{ user?.role }}）</span>
          <!-- <el-button type="danger" @click="logout" size="small">退出系统</el-button> -->
        </el-header>
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import { computed, ref, onMounted } from 'vue';
import { useStore } from 'vuex';
import { useRouter, useRoute } from 'vue-router';

const store = useStore();
const router = useRouter();
const route = useRoute();
const user = computed(() => store.getters.user);
const menu = computed(() => store.getters.menu);
const activeMenu = computed(() => route.path);

// 菜单映射：根据后端返回的菜单名生成二级菜单
const menuMap = {
  '药品管理': [
    { label: '新增药品', path: '/admin/drug/edit' },
    { label: '药品列表', path: '/admin/drug/list' }
  ],
  '药方管理': [
    { label: '新增药方', path: '/admin/prescription/edit' },
    { label: '药方列表', path: '/admin/prescription/list' }
  ],
  '用户管理': [
    { label: '用户列表', path: '/admin/user/list' }
  ]
};

const menuGroups = computed(() => {
  return menu.value.map(m => ({
    title: m,
    children: menuMap[m] || []
  }));
});

const defaultOpeneds = ref([]);
onMounted(() => {
  // 默认展开所有分组
  defaultOpeneds.value = menuGroups.value.map((_, idx) => String(idx));
});

function go(path) {
  router.push(path);
}

function logout() {
  store.dispatch('logout');
  router.push('/login');
}
</script>

<style scoped>
.admin-layout { height: 100vh; }
</style>
