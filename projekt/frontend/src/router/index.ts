import DoenerListeView from '@/views/DoenerListeView.vue'
import LoginView from '@/views/LoginView.vue'
import { createRouter, createWebHistory } from 'vue-router'
//import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', redirect: '/doener'},
    {path: '/login', name: 'LoginView', component: LoginView},
    {path: '/doener', name: 'DonerListeView', component: DoenerListeView}
  ],
})

export default router
