<template>
  <el-container>
    <el-header class="el-header" style="background-color: #393D49;width: 100%">
      <div class="header">
        <div class="header-left" style="">
          <div style="color: white; font-size: 20px">宠物招领与领养系统</div>
          <!--          <div>-->
          <!--            <el-button icon="el-icon-s-home" style="margin: 0 0px;" size="small" @click="removeIndex"  round>主页</el-button>-->
          <!--            <el-button icon="el-icon-s-home" style="margin: 0 0px" @click="removeXunWuQiShi" size="small" round>寻物启事</el-button>-->
          <!--            <el-button icon="el-icon-s-home" style="margin: 0 0px" @click="removeHomelessPet" size="small" round>流浪宠物招领</el-button>-->
          <!--            <el-button icon="el-icon-s-home" style="margin: 0 0px" @click="removeShouYang" size="small" round>领养</el-button>-->
          <!--            <el-button icon="el-icon-s-home" style="margin: 0 0px" @click="removePetCircle" size="small" round>宠物圈</el-button>-->
          <!--            <el-button icon="el-icon-s-home" style="margin: 0 0px" @click="removeallchat" size="small" round>聊天</el-button>-->
          <!--            <el-button icon="el-icon-s-home" style="margin: 0 0px" @click="removexiaoxi" size="small" round>消息通知</el-button>-->
          <!--          </div>-->
        </div>
        <div class="header-right" style="margin-right: 120px">
<!--          <el-button icon="el-icon-s-home" style="margin:15px 5px 25px 5px" @click="removeMy" size="small" round>我的</el-button>-->
<!--          <div>-->
<!--            <el-input v-model="input" placeholder="请输入内容" suffix-icon="el-icon-search" indicator-position="outside" ></el-input>-->
<!--          </div>-->
          <div style="margin-left: 20px;display: flex;">
            <div v-if="this.$store.state.token===''||this.user.portrait===null">
              <img :src="this.circleUrl" alt="touxiang" style="width: 50px;height: 50px;margin-top: 5px;border-radius: 50%" @click="userdetails()" class="blog-title">
            </div>
            <div v-if="this.$store.state.token!==''&&this.user.portrait!=null">
              <img :src="this.user.portrait" alt="touxiang" style="width: 50px;height: 50px;margin-top: 5px;border-radius: 50%" @click="userdetails()" class="blog-title">
            </div>
            <el-dropdown>
              <div style="display: flex;margin-left: 10px">
                <div style="color:white;">{{this.$store.state.token===''?'用户名':user .name}}<i class="el-icon-arrow-down el-icon--right"></i></div>
              </div>
              <el-dropdown-menu slot="dropdown">
                <router-link :to="{path: '/'}"><el-dropdown-item v-if="this.$store.state.token===''"><el-button style="border-style: none">登录</el-button></el-dropdown-item></router-link>
                <el-dropdown-item v-if="this.$store.state.token!==''"><el-button style="border-style: none" @click="removeXunWu" >发布寻物启事</el-button></el-dropdown-item>
                <el-dropdown-item v-if="this.$store.state.token!==''"><el-button style="border-style: none"  @click="removeZhaoLing" >发布招领</el-button></el-dropdown-item>
                <el-dropdown-item v-if="this.$store.state.token!==''"><el-button style="border-style: none"  @click="removeSongYang" >发布送养</el-button></el-dropdown-item>
                <el-dropdown-item v-if="this.$store.state.token!==''"><el-button style="border-style: none"  @click="removeFenXiang">发布分享</el-button></el-dropdown-item>
                <el-dropdown-item v-if="this.$store.state.token!==''"><el-button style="border-style: none" @click="userdetails()">修改信息</el-button></el-dropdown-item>
                <el-dropdown-item v-if="this.$store.state.token!==''"><el-button style="border-style: none" @click="removeUser">注销用户</el-button></el-dropdown-item>
                <el-dropdown-item v-if="this.$store.state.token!==''"><el-button style="border-style: none" @click="removeContactAdmin">联系管理员</el-button></el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
            <div v-if="this.$store.state.token!==''">
              <el-button style="border-style: none;margin-top: 10px"  type="text" @click="removeUser" round small>注销</el-button>
            </div>
          </div>
        </div>
      </div>
    </el-header>
    <div style="height: 100%;width: 100%;">
      <div style="height: 100%;width: 250px;float: left;">
        <el-aside width="250px" >
          <el-menu background-color="#545c64" text-color="#ffffff"
                   active-text-color="#ffd04b" class="el-menu-vertical-demo"
                   router>
            <el-submenu index="1" >
              <template slot="title"><i class="el-icon-office-building"></i><span>动态</span> </template>
              <el-menu-item index="1-1" @click="removeXunWuQiShi"><i class="el-icon-search"></i>寻物启事</el-menu-item>
              <el-menu-item index="1-2" @click="removeHomelessPet"><i class="el-icon-view"></i>流浪宠物招领</el-menu-item>
              <el-menu-item index="1-3" @click="removeShouYang"><i class="el-icon-apple"></i>领养</el-menu-item>
              <el-menu-item index="1-4" @click="removePetCircle"><i class="el-icon-camera"></i>宠物圈</el-menu-item>
            </el-submenu>
            <el-submenu index="2"  v-if="this.$store.state.token!==''">
              <template slot="title"><i class="el-icon-s-promotion"></i><span>发布</span> </template>
              <el-menu-item index="2-1" @click="removeXunWu">发布寻物启事</el-menu-item>
              <el-menu-item index="2-2" @click="removeZhaoLing">发布招领</el-menu-item>
              <el-menu-item index="2-3" @click="removeSongYang">发布送养</el-menu-item>
              <el-menu-item index="2-4" @click="removeFenXiang">发布分享</el-menu-item>
            </el-submenu>
            <el-submenu index="3" v-if="this.$store.state.token!==''">
              <template slot="title"><i class="el-icon-user"></i><span>我的</span> </template>
              <el-menu-item index="3-1" @click="myadoption">我的收养</el-menu-item>
              <el-menu-item index="3-2" @click="mysongyang">我的送养</el-menu-item>
              <el-menu-item index="3-3" @click="myzhaoling">我的招领</el-menu-item>
              <el-menu-item index="3-4" @click="myzhaohui">我的找回</el-menu-item>
            </el-submenu>
            <el-submenu index="4" v-if="this.$store.state.token!==''">
              <template slot="title"><i class="el-icon-setting"></i><span>管理</span> </template>
              <el-menu-item index="4-1" @click="userdetails(user.username)">个人信息管理</el-menu-item>
              <el-menu-item index="4-2" @click="myfenxiangmenage(user.username)">宠物圈管理</el-menu-item>
              <el-menu-item index="4-3" @click="messagedetails(user.username)">领养管理</el-menu-item>
              <el-menu-item index="4-4" @click="messagedetails1(user.username)">招领管理</el-menu-item>
              <el-menu-item index="4-5" @click="mymessagemenage(user.username)">上传信息管理</el-menu-item>
            </el-submenu>
            <el-menu-item index="5" v-if="this.$store.state.token!==''" @click="removeallchat"><i class="el-icon-s-comment"></i>聊天</el-menu-item>
            <el-menu-item index="6" v-if="this.$store.state.token!==''" @click="removexiaoxi"> <i class="el-icon-bell"></i>消息通知
              <span v-if="num!==0" style="float:right;width: 40px;height: 20px;background-color: red;border-radius: 20px;margin-top:15px;padding-top:5px;display: inline-block;text-align: center;line-height: 50%">{{num}}</span>
              <span v-if="num>99" style="float:right;width: 40px;height: 20px;background-color: red;border-radius: 20px;margin-top:15px;padding-top:5px;display: inline-block;text-align: center;line-height: 50%">99+</span>
            </el-menu-item>
            <el-menu-item index="7" v-if="this.$store.state.token!==''" @click="removeContactAdmin"><i class="el-icon-phone"></i>联系管理员</el-menu-item>
            <el-menu-item index="8" v-if="this.$store.state.token!==''" @click="removeJuBao"><i class="el-icon-search"></i>举报</el-menu-item>

          </el-menu>
        </el-aside>

      </div>
      <el-container style="float: top;margin-left: 200px;">
        <el-main style="background-color: #f1f2f4;" class="el-main" id="Top" >
          <router-view></router-view>
        </el-main>
      </el-container>
    </div>

  </el-container>
</template>
<!--<script src="../../static/js/L2Dwidget.min.js"></script>-->
<script>
import {L2Dwidget} from 'live2d-widget'
// import {L2Dwidget} from '../../static/js/L2Dwidget.min.js'
export default {
  data () {
    return {
      circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      input: '',
      flag: true,
      num: 0,
      user: {
        id: '',
        username: '',
        sex: '',
        name: '',
        portrait: ''
      },
      imgList: [
        {id: 0, idView: require('../../assets/img/1.jpg')},
        {id: 1, idView: require('../../assets/img/2.jpg')}
      ]
    }
  },
  methods: {
    change () {
      setInterval(() => {
        this.opacity -= 0.01
        if (this.opacity <= 0) this.opacity = 1
      }, 16)
    },
    removeXunWu () {
      this.$router.push({
        path: '/xunwu',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removexiaoxi () {
      this.$router.push({
        path: '/xiaoxi',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removeallchat () {
      this.$router.push({
        path: '/allchat',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removeMy () {
      this.$router.push({
        path: '/my',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removeZhaoLing () {
      this.$router.push({
        path: '/zhaoling',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removeFenXiang () {
      this.$router.push({
        path: '/petcircle',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removePetCircle () {
      this.$router.push({
        path: '/petcircle',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removeXunWuQiShi () {
      this.$router.push({
        path: '/xunwuqishi',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removeHomelessPet () {
      this.$router.push({
        path: '/homelesspet',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removeMySongYang () {
      this.$router.push({
        path: '/mysongyang',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removeMyAdoption () {
      this.$router.push({
        path: '/myadoption',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removeJuBao () {
      this.$router.push({
        path: '/jubao',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    myadoption () {
      this.$router.push({
        path: '/myadoption',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    mysongyang (username) {
      this.$router.push({
        path: '/mysongyang',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    myzhaoling () {
      this.$router.push({
        path: '/myzhaoling',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    myzhaohui () {
      this.$router.push({
        path: '/myzhaohui',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removeContactAdmin () {
      this.$router.push({
        path: '/contactadmin',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    userdetails () {
      this.$router.push({
        path: '/myindex',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removeSongYang () {
      this.$router.push({
        path: '/songyang',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removeShouYang () {
      this.$router.push({
        path: '/shouyangpet',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removeIndex () {
      this.$router.push({
        path: '/index2',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    messagedetails () {
      this.$router.push({
        path: '/messagemanage',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    messagedetails1 () {
      this.$router.push({
        path: '/messagemanage1',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    messagedetails2 () {
      this.$router.push({
        path: '/messagemanage2',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    mymessagemenage () {
      this.$router.push({
        path: '/mymessagemanage',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    myfenxiangmenage () {
      this.$router.push({
        path: '/myfenxiangmanage',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    messagedetails3 () {
      this.$router.push({
        path: '/messagemanage3',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    blogdetails () {
      this.$router.push({
        path: '/blogmanage',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removelogin () {
      this.$router.push({
        path: '/',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removeUser () {
      this.$store.commit('set_Token', '')
    },
    getHeight () {
      document.getElementById('Top').style.minHeight = document.documentElement.clientHeight + 'px'
    }
  },
  mounted () {
    this.change()
    this.$nextTick(() => {
      this.getHeight()
    })
  },
  created () {
    const _this = this
    this.currentTime = setInterval(() => {
      this.$axios.get('http://119.29.250.12:8081/findAContactByUsername1/' + _this.$route.query.username).then(function (resp) {
        _this.num = resp.data
      })
      this.$axios.get('http://119.29.250.12:8081/findStatusByUsername/' + _this.$route.query.username).then(function (resp) {
        _this.flag = resp.data
        if (!_this.flag) {
          _this.removelogin()
        }
      })
    }, 100)
    this.$axios.get('http://119.29.250.12:8081/user/select/' + _this.$route.query.username).then(function (resp) {
      console.log(resp)
      _this.user = resp.data
      _this.user.sex = resp.data.sex
      if (_this.user.sex === 1) {
        L2Dwidget.init({
          'model': {
            jsonPath: 'https://unpkg.com/live2d-widget-model-koharu@1.0.5/assets/koharu.model.json',
            'scale': 1
          },
          'display': {
            'position': 'right',
            'width': 200,
            'height': 300,
            'hOffset': -20,
            'vOffset': -20
          },
          'mobile': {
            'show': true,
            'scale': 0.5
          },
          'react': {
            'opacityDefault': 0.8,
            'opacityOnHover': 0.1
          }
        })
      }
      if (_this.user.sex === 0) {
        L2Dwidget.init({
          'model': {
            jsonPath: 'https://unpkg.com/live2d-widget-model-haruto@1.0.5/assets/haruto.model.json',
            'scale': 1
          },
          'display': {'position': 'right',
            'width': 200,
            'height': 300,
            'hOffset': -20,
            'vOffset': -20
          },
          'mobile': {
            'show': true,
            'scale': 0.5
          },
          'react': {
            'opacityDefault': 0.8,
            'opacityOnHover': 0.1
          }
        })
      }
    })
  }
}</script>
<style scoped>
.header{
  display: flex;
  justify-content: space-between;
  justify-items: center;
}
.header-left{
  display: flex;
}
.header-right{
  display: flex;
}
.footer{
  display: flex;
  justify-content: space-around;
}
.footer-footer{
  display: flex;
  justify-content: center;
}
.p-overflow{
  font-size: x-small;
  overflow:hidden;
  text-overflow:ellipsis;
  display:-webkit-box;
  -webkit-box-orient:vertical;
  -webkit-line-clamp: 2;
}
.footer-right{
  display: flex;
}
.footer-left{
  display: flex;
  text-align: center;
}
.el-divider--vertical {
  display: inline-block;
  width: 1px;
  height: 10em;
  margin: 0 8px;
  vertical-align: middle;
  position: relative;
}
/*<!--container-->*/
.el-header {
  background-color: #ffffff;
  color: #333;
  line-height: 60px;
  text-align: center;
}
.el-footer{
  background: #363b384f;
  text-align: center;
}
.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: left;
  line-height: 800px;
}

.el-main {
  /*background-color: #E9EEF3;*/
  background-color: #409EFF;
  color: #333;
  text-align: center;
 /* height: 850px;*/
 /* height: calc(100vh-100px);*/
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
/*Layout*/
/*走马灯*/
/*下拉菜单*/
.el-dropdown-link {
  color: #409EFF;
}
.el-icon-arrow-down {
}
.blog-title:hover{
  cursor: pointer;
}
.el-menu-vertical-demo{
  height: 100vh;
}
</style>
