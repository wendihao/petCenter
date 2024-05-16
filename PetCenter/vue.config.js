const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      "/api": {
        target: 'http://119.29.250.12:8081',
        //允许跨域请求
        changeOrigin: true
      }
    }
  }
})
  // 跨域 --> node代理
  // devServer: {
  //   port: 8080,
  //   proxy: {
  //     '/api': {
  //       target: 'http://192.168.0.101:3000',
  //       changeOrigin: true,
  //       ws: true,  // 这一行很关键  表示是否开启  websocket
  //       secure: true,
  //       pathRewrite: {
  //         '^/api': '/'
  //       },
  //       open: true
  //     }
  //   }
  // }

