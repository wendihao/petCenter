import Vue from 'vue'
import VueRouter from 'vue-router'
// import Index from '../views/pc/index'
import PetCenter from '../views/pc/PetCenter'
import ShouYangPet from '../views/pc/ShouYangPet'
import SongYang from '../views/pc/SongYang'
import Login from '../views/pc/Login'
import XiangQing from '../views/pc/XiangQing'
import Register from '../views/pc/Register'
import MyIndex from '../views/pc/MyIndex'
import BlogManage from '../views/pc/BlogManage'
import MyInformation from '../views/pc/MyInformation'
import Test from '../views/pc/test'
import PetCircle from '../views/pc/PetCircle'
import FenXiang from '../views/pc/FenXiang'
import PetXiangQing from '../views/pc/PetXiangQing'
import PetXiangQing2 from '../views/pc/PetXiangQing2'
import MessageManage from '../views/pc/MessageManage'
import MessageManage1 from '../views/pc/MessageManage1'
import MessageManage2 from '../views/pc/MessageManage2'
import MessageManage3 from '../views/pc/MessageManage3'
// import My from '../views/My'
import MyAdoption from '../views/pc/MyAdoption'
import MySongYang from '../views/pc/MySongYang'
import MyZhaoLing from '../views/pc/MyZhaoLing'
import MyZhaoHui from '../views/pc/MyZhaoHui'
import HomelessPet from '../views/pc/HomelessPet'
import ZhaoLing from '../views/pc/ZhaoLing'
import ContactAdmin from '../views/pc/ContactAdmin'
// import UserUploadContent from '../views/UserUploadContent'
import XiangQing0 from '../views/pc/XiangQing0'
import XiangQing1 from '../views/pc/XiangQing1'
import XiangQing2 from '../views/pc/XiangQing2'
import XiangQing3 from '../views/pc/XiangQing3'
import XiangQing4 from '../views/pc/XiangQing4'
import XiangQing5 from '../views/pc/XiangQing5'
import XunWu from '../views/pc/XunWu'
import XunWuQiShi from '../views/pc/XunWuQiShi'
import Im from '../views/pc/Im'
import AllChat from '../views/pc/AllChat'
import XiaoXi from '../views/pc/XiaoXi'
import Login2 from '../views/pc/Login2'
import MyMessageManage from '../views/pc/MyMessageManage'
import MyFenXiangManage from '../views/pc/MyFenXiangManage'
import JuBao from '../views/pc/JuBao'
import Index1 from '../views/pc/Index1'
// import Index from '../views/pc/index'
import Index2 from '../views/pc/index2'

Vue.use(VueRouter)

const routesP = [
  {
    path: '/index2',
    name: 'Index2',
    component: Index2,
    redirect: '/homelesspet',
    children: [
      {
        path: '/petcenter',
        name: 'PetCenter',
        meta: {isAuth: false},
        component: PetCenter
      },
      {
        path: '/shouyangpet',
        name: 'ShouYangPet',
        meta: {isAuth: false},
        component: ShouYangPet
      },
      {
        path: '/petcircle',
        name: 'PetCirCle',
        meta: {isAuth: false},
        component: PetCircle
      },
      {
        path: '/songyang',
        name: 'SongYang',
        meta: {isAuth: true},
        component: SongYang
      },
      {
        path: '/xunwu',
        name: 'XunWu',
        meta: {isAuth: true},
        component: XunWu
      },
      {
        path: '/im',
        name: 'Im',
        component: Im
      },
      {
        path: '/zhaoling',
        name: 'ZhaoLing',
        meta: {isAuth: true},
        component: ZhaoLing
      },
      {
        path: '/allchat',
        name: 'AllChat',
        meta: {isAuth: true},
        component: AllChat
      },
      {
        path: '/fenxiang',
        name: 'FenXiang',
        meta: {isAuth: true},
        component: FenXiang
      },
      {
        path: '/xiangqing',
        name: 'XiangQing',
        meta: {isAuth: false},
        component: XiangQing
      },
      {
        path: '/xiangqing0',
        name: 'XiangQing0',
        meta: {isAuth: false},
        component: XiangQing0
      },
      {
        path: '/xiangqing1',
        name: 'XiangQing1',
        meta: {isAuth: false},
        component: XiangQing1
      },
      {
        path: '/xiangqing2',
        name: 'XiangQing2',
        meta: {isAuth: false},
        component: XiangQing2
      },
      {
        path: '/xiangqing3',
        name: 'XiangQing3',
        meta: {isAuth: false},
        component: XiangQing3
      },
      {
        path: '/xiangqing4',
        name: 'XiangQing4',
        meta: {isAuth: false},
        component: XiangQing4
      },
      {
        path: '/xiangqing5',
        name: 'XiangQing5',
        meta: {isAuth: false},
        component: XiangQing5
      },
      {
        path: '/homelesspet',
        name: 'HomelessPet',
        meta: {isAuth: false},
        component: HomelessPet
      },
      {
        path: '/xunwuqishi',
        name: 'XunWuQiShi',
        meta: {isAuth: false},
        component: XunWuQiShi
      },
      {
        path: '/xiaoxi',
        name: 'XiaoXi',
        meta: {isAuth: false},
        component: XiaoXi
      },
      {
        path: '/jubao',
        name: 'JuBao',
        meta: {isAuth: false},
        component: JuBao
      },
      {
        // path: '/my',
        // name: 'My',
        // component: My,
        // redirect: '/myadoption',
        // children: [
        //   {
        path: '/myadoption',
        name: '我的收养',
        meta: {isAuth: false},
        component: MyAdoption
      },
      {
        path: '/mysongyang',
        name: '我的送养',
        meta: {isAuth: false},
        component: MySongYang
      },
      {
        path: '/myzhaoling',
        name: '我的招领',
        meta: {isAuth: false},
        component: MyZhaoLing
      },
      {
        path: '/myzhaohui',
        name: '我的找回',
        meta: {isAuth: false},
        component: MyZhaoHui
        //   }
        // ]
      },
      {
        path: '/contactadmin',
        name: 'ContactAdmin',
        meta: {isAuth: false},
        component: ContactAdmin
      },
      {
        path: '/petxiangqing',
        name: 'PetXiangQing',
        meta: {isAuth: false},
        component: PetXiangQing
      },
      {
        path: '/petxiangqing2',
        name: 'PetXiangQing2',
        meta: {isAuth: false},
        component: PetXiangQing2
      },
      {
        path: '/myinformation',
        name: '用户个人信息管理',
        meta: {isAuth: true},
        component: MyInformation
      },
      {
        path: '/myfenxiangmanage',
        name: '用户分享管理',
        meta: {isAuth: true},
        component: MyFenXiangManage
      },
      {
        path: '/blogmanage',
        name: '博客管理',
        meta: {isAuth: true},
        component: BlogManage
      },
      {
        path: '/messagemanage',
        name: '领养管理',
        meta: {isAuth: true},
        component: MessageManage
      },
      {
        path: '/messagemanage1',
        name: '招领管理',
        meta: {isAuth: true},
        component: MessageManage1
      },
      {
        path: '/messagemanage2',
        name: '我的领养',
        meta: {isAuth: true},
        component: MessageManage2
      },
      {
        path: '/mymessagemanage',
        name: '上传信息管理',
        meta: {isAuth: true},
        component: MyMessageManage
      },
      {
        path: '/myindex',
        name: 'MyIndex',
        component: MyIndex,
        redirect: '/myinformation',
        children: [
          {
            path: '/myinformation',
            name: '用户个人信息管理',
            meta: {isAuth: true},
            component: MyInformation
          },
          {
            path: '/blogmanage',
            name: '博客管理',
            meta: {isAuth: true},
            component: BlogManage
          },
          {
            path: '/messagemanage',
            name: '领养管理',
            meta: {isAuth: true},
            component: MessageManage
          },
          {
            path: '/messagemanage1',
            name: '招领管理',
            meta: {isAuth: true},
            component: MessageManage1
          },
          {
            path: '/messagemanage3',
            name: '我的找回',
            meta: {isAuth: true},
            component: MessageManage3
          }
        ]
      }
    ]
  },
  {
    path: '/',
    name: 'Login',
    meta: {isAuth: false},
    component: Login
  },
  {
    path: '/login2',
    name: 'Login2',
    meta: {isAuth: false},
    component: Login2
  },
  {
    path: '/register',
    name: 'Register',
    meta: {isAuth: false},
    component: Register
  },
  {
    path: '/test',
    name: 'test',
    component: Test
  },
  {
    path: '/index1',
    name: 'index1',
    component: Index1
  }
]
const routesM = [{
  path: '/indexM',
  name: 'IndexM',
  component: () => import('../views/mobile/index/IndexM'),
  redirect: '/home',
  children: [
    {
      path: '/home',
      name: 'HomeM',
      component: () => import('../views/mobile/home/Home'),
      meta: {
        type: 'view',
        keepAlive: true
      }
    },
    {
      path: '/xiaoxiM',
      name: 'XiaoXiM',
      component: () => import('../views/mobile/xiaoxi/XiaoXiM'),
      meta: {
        type: 'view',
        keepAlive: true
      }
    },
    {
      path: '/petcircleM',
      name: 'PetCircleM',
      component: () => import('../views/mobile/petcircle/PetCircleM'),
      meta: {
        type: 'view',
        keepAlive: true
      }
    },
    {
      path: '/mineM',
      name: 'MineM',
      component: () => import('../views/mobile/mine/mineM'),
      meta: {
        type: 'view',
        keepAlive: true
      }
    },
    {
      path: '/myadoptionM',
      name: 'MyAdoptionM',
      component: () => import('../views/mobile/my/myadoption/myadoptionM'),
      meta: {
        type: 'view',
        keepAlive: true
      }
    },
    {
      path: '/mysongyangM',
      name: 'MySongYangM',
      component: () => import('../views/mobile/my/mysongyang/mysongyangM'),
      meta: {
        type: 'view',
        keepAlive: true
      }
    },
    {
      path: '/myzhaohuiM',
      name: 'MyZhaoHuiM',
      component: () => import('../views/mobile/my/myzhaohui/myzhaohuiM'),
      meta: {
        type: 'view',
        keepAlive: true
      }
    },
    {
      path: '/myzhaolingM',
      name: 'MyZhaoLingM',
      component: () => import('../views/mobile/my/myzhaoling/myzhaolingM'),
      meta: {
        type: 'view',
        keepAlive: true
      }
    }
  ]
},
{
  path: '/allchatm',
  name: 'AllChatM',
  component: () => import('../views/mobile/liaotian/AllChatM'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: '/xiangqing4M',
  name: 'XiangQing4M',
  component: () => import('../views/mobile/my/myzhaoling/XiangQing4M'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: '/xiangqing2M',
  name: 'XiangQing2M',
  component: () => import('../views/mobile/my/myadoption/XiangQing2M'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: '/xiangqing3M',
  name: 'XiangQing3M',
  component: () => import('../views/mobile/my/mysongyang/XiangQing3M'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: '/xiangqing5M',
  name: 'XiangQing5M',
  component: () => import('../views/mobile/my/myzhaohui/XiangQing5M'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},

{
  path: '/imm',
  name: 'ImM',
  component: () => import('../views/mobile/liaotian/ImM'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: 'xunwuqishiM',
  name: 'XunWuQiShiM',
  component: () => import('../views/mobile/XunWuQiShi/XunWuQiShiM'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: 'xiangqing0M',
  name: 'XiangQing0M',
  component: () => import('../views/mobile/XunWuQiShi/XiangQing0M'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: '/homelesspetM',
  name: 'homelessPetM',
  component: () => import('../views/mobile/HomeLessPet/HomeLessPetM'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: '/xiangqing1M',
  name: 'XiangQing1M',
  component: () => import('../views/mobile/HomeLessPet/XiangQing1M'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: '/shouyangpetM',
  name: 'shouyangPetM',
  component: () => import('../views/mobile/shouyangPet/shouyangPetM'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: '/xiangqingM',
  name: 'XiangQingM',
  component: () => import('../views/mobile/shouyangPet/XiangQingM'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: '/jubaoM',
  name: 'JuBaoM',
  component: () => import('../views/mobile/jubao/JuBaoM'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: '/xunwuM',
  name: 'XunWuM',
  component: () => import('../views/mobile/fabu/XunWuM'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: '/zhaolingM',
  name: 'ZhaoLingM',
  component: () => import('../views/mobile/fabu/ZhaoLingM'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: '/songyangM',
  name: 'SongYangM',
  component: () => import('../views/mobile/fabu/SongYangM'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: '/fenxiangM',
  name: 'FenXiangM',
  component: () => import('../views/mobile/fabu/FenXiangM'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: '/contactadminM',
  name: 'ContactAdminM',
  component: () => import('../views/mobile/contactAdmin/ContactAdminM'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: '/myinformationM',
  name: 'MyInformationM',
  component: () => import('../views/mobile/myinformation/MyInformationM'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: '/messagemanageM',
  name: 'MessageManageM',
  component: () => import('../views/mobile/management/MessageManageM'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: '/messagemanage1M',
  name: 'MessageManage1M',
  component: () => import('../views/mobile/management/MessageManage1M'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: '/myfenxiangmanageM',
  name: 'MyFenXiangManageM',
  component: () => import('../views/mobile/management/MyFenXiangManageM'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: '/mymessagemanageM',
  name: 'MyMessageManageM',
  component: () => import('../views/mobile/management/MyMessageManageM'),
  meta: {
    type: 'view',
    keepAlive: true
  }
},
{
  path: '/petxiangqingM',
  name: 'PetXiangQingM',
  component: () => import('../views/mobile/petcircle/PetXiangQingM'),
  meta: {
    type: 'view',
    keepAlive: false
  }
},
{
  path: '/',
  name: 'loginM',
  component: () => import('../views/mobile/login/LoginM'),
  meta: {
    type: 'view',
    keepAlive: false
  }
},
{
  path: '/registerM',
  name: 'RegisterM',
  component: () => import('../views/mobile/login/RegisteM'),
  meta: {
    type: 'view',
    keepAlive: false
  }
},
{
  path: '/login2M',
  name: 'login2M',
  component: () => import('../views/mobile/login/Login2M'),
  meta: {
    type: 'view',
    keepAlive: false
  }
}
// {
//   path: '/fond',
//   name: 'fond',
//   component: () => import('../views/fond'),
//   meta: {
//     type: 'view',
//     keepAlive: false
//   }
// },
// {
//   path: '/comment',
//   name: 'comment',
//   component: () => import('@/views/comment'),
//   meta: {
//     type: 'view',
//     keepAlive: false
//   }
// },
// {
//   path: '/user',
//   name: 'user',
//   component: () => import('@/views/user'),
//   meta: {
//     type: 'view',
//     keepAlive: false
//   }
// },
// {
//   path: '/add',
//   name: 'add',
//   component: () => import('@/pages/addPet'),
//   meta: {
//     type: 'page',
//     keepAlive: false
//   }
// },
// {
//   path: '/mypet',
//   name: 'mypet',
//   component: () => import('@/pages/myPet'),
//   meta: {
//     type: 'page',
//     keepAlive: false
//   }
// },
// {
//   path: '/updatauser',
//   name: 'updatauser',
//   component: () => import('@/pages/updataUser'),
//   meta: {
//     type: 'page',
//     keepAlive: false
//   }
// },
// {
//   path: '/userSpace',
//   name: 'userSpace',
//   component: () => import('@/pages/userSpace'),
//   meta: {
//     type: 'userSpace',
//     keepAlive: false
//   }
// },
// {
//   path: '/petSpace',
//   name: 'petSpace',
//   component: () => import('@/pages/petSpace'),
//   meta: {
//     type: 'petSpace',
//     keepAlive: false
//   }
// },
// {
//   path: '/chatPage',
//   name: 'chatPage',
//   component: () => import('@/pages/chatPage'),
//   meta: {
//     type: 'page',
//     keepAlive: false
//   }
// }
]
var routes = []
if ((navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i))) {
  routes = routesM
} else {
  routes = routesP
}
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
