import { createRouter, createWebHistory } from 'vue-router';
import PrescriptionEdit from '../views/PrescriptionEdit.vue';
import PrescriptionDetail from '../views/PrescriptionDetail.vue';
import DrugEdit from '../views/DrugEdit.vue';
import DrugDetail from '../views/DrugDetail.vue';
import Login from '../views/Login.vue';
import Register from '../views/Register.vue';

const routes = [
  { path: '/login', name: 'Login', component: Login },
  { path: '/register', name: 'Register', component: Register },
  {
    path: '/prescription/edit',
    name: 'PrescriptionEdit',
    component: PrescriptionEdit,
  },
  {
    path: '/prescription/:id',
    name: 'PrescriptionDetail',
    component: PrescriptionDetail,
  },
  {
    path: '/drug/edit',
    name: 'DrugEdit',
    component: DrugEdit,
  },
  {
    path: '/drug/:id',
    name: 'DrugDetail',
    component: DrugDetail,
  },
  {
    path: '/user/list',
    name: 'UserList',
    component: () => import('../views/UserList.vue'),
  },
  // 系统管理页（后续实现）
  { path: '/admin', name: 'Admin', component: () => import('../views/Admin.vue') },
  { path: '/', redirect: '/login' },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

// 路由守卫：未登录强制跳转登录页
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token');
  if (to.path !== '/login' && to.path !== '/register' && !token) {
    next('/login');
  } else {
    next();
  }
});

export default router;