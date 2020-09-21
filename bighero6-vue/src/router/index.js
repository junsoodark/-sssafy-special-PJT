import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import MyPage from '../views/MyPage.vue'
import Login from '../views/Login.vue'
import Playlist from '../views/Playlist.vue'
import Signup from '../views/SignupPage.vue'
<<<<<<< HEAD
=======

>>>>>>> ccb4aa10b3497f1130337d7331bc36d56b21b2c4


Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: MyPage,
    },
    {

      path: '/login',
      name: 'login',
      component: Login,
    },
    {
      path: '/signup',
      name: 'signup',
      component: Signup,
    },
    {
      path: '/playlist',
      name: 'playlist',
      component: Playlist,

    },
  ],
})
