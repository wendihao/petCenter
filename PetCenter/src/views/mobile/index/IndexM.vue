<template>
  <div>
    <!-- 头部内容 -->
    <el-container id="Top" style="width: 100%">
        <router-view></router-view>
    </el-container>
    <!-- 底部 -->
    <nav  class="mui-bar mui-bar-tab">
      <div v-if="home" style="width: 25%;height: 1.5rem" @click="removehome" class="mui-tab-item mui-active" >
        <span class="mui-icon mui-icon-bars"></span>
        <span class="mui-tab-label">首页</span>
      </div>
      <div v-if="!home" style="width: 25%;height: 1.5rem" @click="removehome" class="mui-tab-item " >
        <span class="mui-icon mui-icon-bars"></span>
        <span class="mui-tab-label">首页</span>
      </div>
      <div v-if="xiaoxi" style="width: 25%;height: 1.5rem" @click="removexiaoxi" class="mui-tab-item mui-active" >
        <span class="mui-icon mui-icon-chat"><span v-if="num!==0" class="mui-badge">{{num}}</span></span>
        <span class="mui-tab-label">消息</span>
      </div>
      <div v-if="!xiaoxi" style="width: 25%;height: 1.5rem" @click="removexiaoxi" class="mui-tab-item" >
        <span class="mui-icon mui-icon-chat"><span v-if="num!==0" class="mui-badge">{{num}}</span></span>
        <span class="mui-tab-label">消息</span>
      </div>
      <div v-if="petcircle" style="width: 25%;height: 1.5rem" @click="removepetcircle" class="mui-tab-item mui-active" >
        <span class="mui-icon mui-icon-pengyouquan"></span>
        <span class="mui-tab-label">宠物圈</span>
      </div>
      <div v-if="!petcircle" style="width: 25%;height: 1.5rem" @click="removepetcircle" class="mui-tab-item" >
        <span class="mui-icon mui-icon-pengyouquan"></span>
        <span class="mui-tab-label">宠物圈</span>
      </div>
      <div v-if="mine" style="width: 25%;height: 1.5rem" @click="removemine" class="mui-tab-item mui-active" >
        <span class="mui-icon mui-icon-person"></span>
        <span class="mui-tab-label">我的</span>
      </div>
      <div v-if="!mine" style="width: 25%;height: 1.5rem" @click="removemine" class="mui-tab-item" >
        <span class="mui-icon mui-icon-person"></span>
        <span class="mui-tab-label">我的</span>
      </div>
    </nav>
  </div>
</template>

<script>

export default {
  data () {
    return {
      home: true,
      xiaoxi: false,
      petcircle: false,
      mine: false,
      bannerData: [], // 轮播图数据
      num: '',
      user: {
        id: '',
        username: '',
        sex: '',
        name: '',
        portrait: ''
      }
    }
  },
  methods: {
    change () {
      setInterval(() => {
        this.opacity -= 0.01
        if (this.opacity <= 0) this.opacity = 1
      }, 16)
    },
    getHeight () {
      document.getElementById('Top').style.minHeight = (document.documentElement.clientHeight - 62.09) + 'px'
    },
    removehome () {
      this.home = true
      this.xiaoxi = false
      this.petcircle = false
      this.mine = false
      this.$router.push({
        name: 'HomeM',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removexiaoxi () {
      this.home = false
      this.xiaoxi = true
      this.petcircle = false
      this.mine = false
      this.$router.push({
        name: 'XiaoXiM',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removepetcircle () {
      this.home = false
      this.xiaoxi = false
      this.petcircle = true
      this.mine = false
      this.$router.push({
        name: 'PetCircleM',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removemine () {
      this.home = false
      this.xiaoxi = false
      this.petcircle = false
      this.mine = true
      this.$router.push({
        name: 'MineM',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removeUser () {
      this.$store.commit('set_Token', '')
    },
    removelogin () {
      this.$router.push({
        name: 'loginM',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    }
  },
  mounted () {
    this.change()
    this.$nextTick(() => {
      this.getHeight()
    })
  },
  created () { // 组件初始化时期请求数据
    const _this = this
    this.$axios.get('http://119.29.250.12:8081/findAContactByUsername1/' + this.$route.query.username).then(function (resp) {
      _this.num = resp.data
    })
    this.$axios.get('http://119.29.250.12:8081/findStatusByUsername/' + this.$route.query.username).then(function (resp) {
      _this.flag = resp.data
      if (!_this.flag) {
        _this.removelogin()
      }
    })
    this.$axios.get('http://119.29.250.12:8081/user/select/' + _this.$route.query.username).then(function (resp) {
      console.log(resp)
      _this.user = {}
      _this.user = resp.data
    })
  }
}
</script>

<style scoped>
/*scoped作用是css只作用于当前组件，不会影响其他组件样式*/
.swipe {
  height: 190px;
}
.mui-table-view.mui-grid-view.mui-grid-9 {
  border: none;
  background: #fff;
}
.mui-table-view.mui-grid-view.mui-grid-9:after {
  height: 0;
}
.mui-table-view-cell.mui-media.mui-col-xs-4.mui-col-sm-3 {
  border: none;
  background-color: #fff;
}
.mui-icon {
  width: 50px;
  height: 50px;
}
.mui-icon-home:before,
.mui-icon-email:before,
.mui-icon-chatbubble:before,
.mui-icon-location:before,
.mui-icon-search:before,
.mui-icon-phone:before {
  content: '';
}
.mui-icon-home {
  background: url('../../../../static/imgs/home/news.png') no-repeat center;
  background-size: 100%;
}
.mui-icon-email {
  background: url('../../../../static/imgs/home/picShare.png') no-repeat center;
  background-size: 100%;
}
.mui-icon-chatbubble {
  background: url('../../../../static/imgs/home/goodShow.png') no-repeat center;
  background-size: 100%;
}
.mui-icon-location {
  background: url('../../../../static/imgs/home/feedback.png') no-repeat center;
  background-size: 100%;
}
.mui-icon-search {
  background: url('../../../../static/imgs/home/search.png') no-repeat center;
  background-size: 100%;
}
.mui-icon-phone {
  background: url('../../../../static/imgs/home/callme.png') no-repeat center;
  background-size: 100%;
}

</style>
