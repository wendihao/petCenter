// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import axios from 'axios'
import 'element-ui/lib/theme-chalk/index.css'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import store from './store'
// import MyVant from 'vant'
// import 'lib-flexible/flexible' // 移动端适配
// import 'vant/lib/index.css'
// import {Tab, Tabs} from 'vant'
// import './util/vant'
// import 'vant/es/dialog/style'
// import 'vant/es/toast/style'
// import 'vant/es/notify/style'
// import Vant from 'vant'
// import './assets/style/reset.css'
// import './assets/style/custom.less'
// 引入mui的css
// import '../static/vendor/mui/dist/css/mui.css'
// 引入自己的css
// import '../static/css/style.css'
// 添加请求拦截器
// 引入自己的全局组件
Vue.use(router)
Vue.use(ElementUI)
Vue.use(mavonEditor)
Vue.config.productionTip = false
Vue.prototype.$axios = axios
// Vue.use(Vant)
// Vue.use(Vant)
// Vue.use(MyVant)
// Vue.use(Tab).use(Tabs)
/* eslint-disable no-new */

router.beforeEach((to, from, next) => {
  if (to.matched.some(m => m.meta.isAuth)) {
    if (window.sessionStorage.token) {
      next()
    } else if (to.path !== '/') {
      let token = window.sessionStorage.token
      if (token === 'null' || token === '' || token === undefined) {
        next({path: '/'})
        ElementUI.Message.warning('检测到您还未登录,请登录后操作！')
        console.log('检测到您还未登录,请登录后操作！')
      }
    }
  } else {
    next(true)
  }
})
new Vue({
  el: '#app',
  router,
  axios,
  store: store,
  render: h => h(App),
  components: { App },
  template: '<App/>'
})
