// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
// import Vue from 'vue'
// import App from './App'
// import router from './router'
// import axios from 'axios'
// import ElementUI from 'element-ui'
// import 'element-ui/lib/theme-chalk/index.css'
// import mavonEditor from 'mavon-editor'
// import 'mavon-editor/dist/css/index.css'
// import store from './store'
// // import MyVant from 'vant'
// import 'lib-flexible/flexible' // 移动端适配
// import 'vant/lib/index.css'
// // import {Tab, Tabs} from 'vant'
// import './util/vant'
// import 'vant/es/dialog/style'
// import 'vant/es/toast/style'
// import 'vant/es/notify/style'
// // import Vant from 'vant'
// import './assets/style/reset.css'
// import './assets/style/custom.less'
// import MintUi from 'mint-ui'
// import 'mint-ui/lib/style.css'
// // 引入mui的css
// import '../static/vendor/mui/dist/css/mui.css'
// // 引入自己的css
// import '../static/css/style.css'
if ((navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i))) {
  Promise.all([import('./mainMobile')])
} else {
  Promise.all([import('./mainPc')])
}
// 添加请求拦截器
// axios.interceptors.request.use(function (config) {
//   // 在发送请求之前做些什么
//   MintUi.Indicator.open({ // 加载loading
//     text: '加载中...',
//     spinnerType: 'fading-circle'
//   })
//   return config
// }, function (error) {
//   // 对请求错误做些什么
//   return Promise.reject(error)
// })
// // 添加响应拦截器
// axios.interceptors.response.use(function (response) {
//   MintUi.Indicator.close() // 去掉loading
//   // 对响应数据做点什么
//   return response
// }, function (error) {
//   // 对响应错误做点什么
//   return Promise.reject(error)
// })
// Vue.prototype.$ajax = axios // 可以使用this.$ajax
//
// // 引入自己的全局组件
//
// Vue.use(mavonEditor)
// Vue.config.productionTip = false
// Vue.prototype.$axios = axios
// Vue.use(router)
// // Vue.use(Vant)
// Vue.use(ElementUI)
// // Vue.use(Vant)
// // Vue.use(MyVant)
// // Vue.use(Tab).use(Tabs)
// /* eslint-disable no-new */
//
// router.beforeEach((to, from, next) => {
//   if (to.matched.some(m => m.meta.isAuth)) {
//     if (window.localStorage.token) {
//       next()
//     } else if (to.path !== '/login') {
//       let token = window.localStorage.token
//       if (token === 'null' || token === '' || token === undefined) {
//         next({path: '/login'})
//         ElementUI.Message.warning('检测到您还未登录,请登录后操作！')
//         console.log('检测到您还未登录,请登录后操作！')
//       }
//     }
//   } else {
//     next(true)
//   }
// })
// new Vue({
//   el: '#app',
//   router,
//   axios,
//   store: store,
//   render: h => h(App),
//   components: { App },
//   template: '<App/>'
// })
