<template>
  <div>
    <!-- 头部内容 -->
    <router-view></router-view>
    <!-- 底部 -->
  </div>
</template>

<script>
export default {
  name: 'App',
  mounted () {
    // 关闭浏览器窗口的时候清空浏览器缓存在localStorage的数据
    window.addEventListener('load', () => {
      var storage = window.localStorage
      storage.clear()
    })
    // 区别浏览器关闭和刷新的代码
    let beforeUnloadtime = 0
    let gapTime = 0
    window.onunload = function () {
      gapTime = new Date().getTime() - beforeUnloadtime
      if (gapTime <= 5) {
        var storage = window.localStorage
        storage.clear()
      } else {

      }
    }
    window.onbeforeunload = function () {
      beforeUnloadtime = new Date().getTime()
    }
  }
}
</script>

<style scoped>
::-webkit-scrollbar{
  width: 0 !important
}
::-webkit-scrollbar{
  width: 0 !important; height: 0;
}
</style>
