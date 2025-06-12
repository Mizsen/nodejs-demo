import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import PersonalHome from '../views/PersonalHome.vue';
import NotFound from '../views/NotFound.vue';
import BlogDetail from '../components/BlogDetail.vue';
import BlogEditor from '../components/BlogEditor.vue';
import Login from '../components/Login.vue';
import Register from '../components/Register.vue';
import UserProfile from '../components/UserProfile.vue';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/personal-home',
    name: 'PersonalHome',
    component: PersonalHome,
  },
  {
    path: '/blog/:id',
    name: 'BlogDetail',
    component: BlogDetail,
    props: true,
  },
  {
    path: '/edit-blog/:id',
    name: 'BlogEditor',
    component: BlogEditor,
    props: true,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
  },
  {
    path: '/user-profile',
    name: 'UserProfile',
    component: UserProfile,
  },
  {
    path: '/:catchAll(.*)',
    name: 'NotFound',
    component: NotFound,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;