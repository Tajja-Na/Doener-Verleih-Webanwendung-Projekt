import DoenerListeView from '@/views/DoenerListeView.vue'
import LoginView from '@/views/LoginView.vue'
import DoenerEntleihungView from '@/views/DoenerEntleihungView.vue'
import { createRouter, createWebHistory } from 'vue-router'
//import HomeView from '../views/HomeView.vue'
import { useLoginStore } from '@/stores/loginstore'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {path: '/', redirect: '/doener'},
    {path: '/login', name: 'LoginView', component: LoginView},
    {path: '/doener', name: 'DonerListeView', component: DoenerListeView},
    {path: '/entleihung', name: 'DoenerEntleihungView', component: DoenerEntleihungView}
  ],
})

router.beforeEach( async (to, from) => {
  const loginstore = useLoginStore()

  if(!loginstore.loggedIn && to.name !== 'LoginView'){
    return {name : 'LoginView'}
  }
})

export default router
