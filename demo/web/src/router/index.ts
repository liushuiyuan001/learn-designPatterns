import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Ebook from '../views/admin/Ebook.vue'
import Category from '../views/admin/Category.vue'
import Doc from '../views/admin/Doc.vue'
import User from '../views/admin/User.vue'
import DocView from '../views/DocView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/ebook',
      name: 'ebook',
      component: Ebook
    },
    {
      path: '/category',
      name: 'category',
      component: Category
    },
    {
      path: '/doc',
      name: 'doc',
      component: Doc
    },
    {
      path: '/docView',
      name: 'docView',
      component: DocView
    },
    {
      path: '/user',
      name: 'user',
      component: User
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    }
  ]
})

export default router
