import { createRouter, createWebHashHistory } from 'vue-router'

import uploadmusic from '../components/music/uploadmusic.vue'
import allmusic from '../components/music/allmusic.vue'
import onemusic from '@/components/music/onemusic.vue'
import heartMusic from '@/components/music/heartMusic.vue'
import artist from '@/components/music/artist.vue'

import listmusic from '@/components/music/listmusic.vue'
import home from '@/views/HomeView.vue'
import login from '@/views/login.vue'

const routes = [
  {
    path: '/login',
    name: 'login',
    component: login,
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/',
    name: 'home',
    component: home,
    meta: {
      requireAuth: false
    },
    children:[
      {
        path: '/', 
        name: 'allmusic', 
        component: allmusic
      },
      {
        path: '/upload',
        name: 'uploadmusic',
        component: uploadmusic
      },
      {
        path: '/one',
        name: 'onemusic',
        component: onemusic
      },
      {
        path: '/heart',
        name: 'heartMusic',
        component: heartMusic
      },
      {
        path: '/artist',
        name: 'artist',
        component: artist
      },
      {
        path: '/list',
        name: 'listmusic',
        component: listmusic
      },
    ]
  },
  {
    path: '/register',
    name: 'register',
    component: import('@/views/register.vue'),
    meta: {
      requireAuth: true
    }
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
