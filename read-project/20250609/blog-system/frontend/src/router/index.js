import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import PostDetail from '../views/PostDetail.vue'
import PostEdit from '../views/PostEdit.vue'
import UserProfile from '../views/UserProfile.vue'
import Search from '../views/Search.vue'

const routes = [
  { path: '/', component: Home },
  { path: '/login', component: Login },
  { path: '/register', component: Register },
  { path: '/post/:id', component: PostDetail },
  { path: '/edit/:id?', component: PostEdit },
  { path: '/user/:id', component: UserProfile },
  { path: '/search', component: Search }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router 