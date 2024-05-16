import Vue from 'vue'
import VueRouter from 'vue-router'
import Admin from '../views/pc/Admin'
import UserFeedbackContent from '../views/pc/UserFeedbackContent'
import UserMannagement from '../views/pc/UserMannagement'
import PetCircleManagement from '../views/pc/PetCircleManagement'
import XunWuQiShiManagement from '../views/pc/XunWuQiShiManagement'
import SongYangManagement from '../views/pc/SongYangManagement'
import ZhaoLingManagement from '../views/pc/ZhaoLingManagement'
import CommentManagement from '../views/pc/CommentManagement'
import JuBaoManagement from '../views/pc/JuBaoManagement'
import AdminManagement from '../views/pc/AdminManagement'
import AdminLogin from '../views/pc/AdminLogin'
Vue.use(VueRouter)

const routesP = [
  {
    path: '/admin',
    name: 'Admin',
    component: Admin,
    redirect: '/usermannagement',
    children: [
      {
        path: '/xunwuqishimanagement',
        name: '用户寻物启事管理',
        meta: {isAuth: false},
        component: XunWuQiShiManagement
      },
      {
        path: '/songyangmanagement',
        name: '用户送养管理',
        meta: {isAuth: false},
        component: SongYangManagement
      },
      {
        path: '/zhaolingmanagement',
        name: '用户招领管理',
        meta: {isAuth: false},
        component: ZhaoLingManagement
      },
      // {
      //   path: '/useruploadcontent',
      //   name: '用户上传信息管理',
      //   meta: {isAuth: false},
      //   component: UserUploadContent
      // },
      {
        path: '/userfeedbackcontent',
        name: '用户反馈信息管理',
        meta: {isAuth: false},
        component: UserFeedbackContent
      },
      {
        path: '/usermannagement',
        name: '用户管理',
        meta: {isAuth: false},
        component: UserMannagement
      },
      {
        path: '/petcirclemanagement',
        name: '用户宠物圈管理',
        meta: {isAuth: false},
        component: PetCircleManagement
      },
      {
        path: '/commentmanagement',
        name: '用户评论管理',
        meta: {isAuth: false},
        component: CommentManagement
      },
      {
        path: '/adminmanagement',
        name: '新增管理员',
        meta: {isAuth: false},
        component: AdminManagement
      },
      {
        path: '/jubaomanagement',
        name: '举报管理',
        meta: {isAuth: false},
        component: JuBaoManagement
      }
    ]
  },
  {
    path: '/adminlogin',
    name: 'AdminLogin',
    meta: {isAuth: false},
    component: AdminLogin
  }
]
var routes = routesP
const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (to) {
  return VueRouterPush.call(this, to).catch(err => err)
}
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
