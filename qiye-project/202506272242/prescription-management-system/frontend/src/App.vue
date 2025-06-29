<template>
  <div id="app">
    <nav v-if="!isLoggedIn">
      <router-link to="/login">登录</router-link> |
      <router-link to="/register">注册</router-link>
    </nav>
    <div v-else class="topbar">
      <div class="topbar-title">药方数据管理系统</div>
      <button class="logout-btn" @click="logout">退出系统</button>
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
  computed: {
    isLoggedIn() {
      return !!localStorage.getItem('token');
    }
  },
  methods: {
    showMenu(role) {
      const userRole = localStorage.getItem('role');
      if (role === 'admin') return userRole === 'admin';
      if (role === 'doctor') return userRole === 'doctor';
      if (role === 'pharmacist') return userRole === 'pharmacist';
      return false;
    },
    logout() {
      localStorage.removeItem('token');
      localStorage.removeItem('role');
      localStorage.removeItem('menu');
      localStorage.removeItem('username');
      this.$router.push('/login');
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
nav {
  margin-bottom: 30px;
}
nav a {
  margin: 0 10px;
  text-decoration: none;
  color: #007bff;
}
nav a.router-link-exact-active {
  font-weight: bold;
  color: #42b983;
}
.topbar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);
  padding: 0 32px;
  height: 56px;
  margin-bottom: 12px;
}
.topbar-title {
  font-size: 20px;
  font-weight: bold;
  color: #409EFF;
  letter-spacing: 2px;
}
.logout-btn {
  background: #f56c6c;
  color: #fff;
  border: none;
  border-radius: 4px;
  padding: 6px 18px;
  font-size: 15px;
  cursor: pointer;
  transition: background 0.2s;
}
.logout-btn:hover {
  background: #d93026;
}
</style>