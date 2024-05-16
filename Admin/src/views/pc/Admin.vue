<template>
  <div>
  <el-header style="background-color: #393D49;">
    <div class="header">
      <div class="header-left" style="">
        <div style="font-size: 20px;color: white;float: left">宠物招领与领养系统后台</div>
      </div>
      <div class="header-right" style="margin-right: 120px">
        <div style="margin-left: 20px;display: flex;">
          <div v-if="this.$store.state.token!=''">
            <img :src="require('../../assets/img/admin.jpg')" alt="touxiang" style="width: 50px;height: 50px;margin-top: 5px;border-radius: 50%" @click="userdetails()" class="blog-title">
          </div>
          <el-dropdown>
            <div style="display: flex;margin-left: 10px">
              <div style="color:white;">{{this.$store.state.token===''?'用户名':'管理员'}}</div>
            </div>
          <el-dropdown-menu slot="dropdown">
            <router-link :to="{path: '/adminlogin'}"><el-dropdown-item v-if="this.$store.state.token===''"><el-button style="border-style: none">登录</el-button></el-dropdown-item></router-link>
          </el-dropdown-menu>
          </el-dropdown>
          <div v-if="this.$store.state.token!=''">
            <el-button style="border-style: none;margin-top: 10px"  type="text"  @click="removeUser" round small>注销</el-button>
          </div>
        </div>
      </div>
    </div>
  </el-header>
  <div style="display: flex">
    <div>
      <el-menu
        style="height: 100%;"
        default-active="1"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose">
<!--        <el-menu-item index="1"  @click="useruploadcontent(ruleForm.username)">-->
<!--          <i class="el-icon-menu"></i>-->
<!--          <span slot="title">用户上传信息管理</span>-->
<!--        </el-menu-item>-->
        <el-menu-item index="1"  @click="usermannagement(ruleForm.username)">
          <i class="el-icon-user"></i>
          <span slot="title">用户管理</span>
        </el-menu-item>
        <el-menu-item index="2"  @click="userfeedbackcontent(ruleForm.username)">
          <i class="el-icon-message"></i>
          <span slot="title">用户反馈信息管理</span>
        </el-menu-item>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-files"></i><span>用户上传信息管理</span> </template>
          <el-menu-item index="3-1" @click="xunwuqishi">寻物启事</el-menu-item>
          <el-menu-item index="3-2" @click="songyang">送养</el-menu-item>
          <el-menu-item index="3-3" @click="zhaoling">招领</el-menu-item>
        </el-submenu>
        <el-menu-item index="4"  @click="petcirclemannagement(ruleForm.username)">
          <i class="el-icon-camera"></i>
          <span slot="title">用户宠物圈管理</span>
        </el-menu-item>
        <el-menu-item index="5"  @click="commentmannagement(ruleForm.username)">
          <i class="el-icon-s-comment"></i>
          <span slot="title">用户评论与聊天管理</span>
        </el-menu-item>
        <el-menu-item index="6"  @click="jubaomannagement(ruleForm.username)">
          <i class="el-icon-upload"></i>
          <span slot="title">用户举报管理</span>
        </el-menu-item>
        <el-menu-item index="7"  @click="adminmannagement(ruleForm.username)">
          <i class="el-icon-user-solid"></i>
          <span slot="title">新增管理员</span>
        </el-menu-item>
      </el-menu>
    </div>
    <div>
      <router-view></router-view>
    </div>
  </div>
  </div>
</template>
<script>
export default {
  name: 'Admin',
  data () {
    return {
      imageUrl: '',
      ruleForm: {
        id: '',
        username: ''
      },
      admin: {
        id: '',
        username: '',
        name: ''
      }
    }
  },
  methods: {
    handleAvatarSuccess (res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
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
    removeUser () {
      this.$store.commit('set_Token', '')
    },
    useruploadcontent () {
      this.$router.push({
        path: '/useruploadcontent',
        query: {
          id: this.ruleForm.id,
          username: this.ruleForm.username
        }
      })
    },
    userfeedbackcontent (username) {
      this.$router.push({
        path: '/userfeedbackcontent',
        query: {
          username: username,
          id: this.ruleForm.id
        }
      })
    },
    usermannagement () {
      this.$router.push({
        path: '/usermannagement',
        query: {
          id: this.ruleForm.id,
          username: this.ruleForm.username
        }
      })
    },
    adminmannagement () {
      this.$router.push({
        path: '/adminmanagement',
        query: {
          id: this.ruleForm.id,
          username: this.ruleForm.username
        }
      })
    },
    petcirclemannagement () {
      this.$router.push({
        path: '/petcirclemanagement',
        query: {
          id: this.ruleForm.id,
          username: this.ruleForm.username
        }
      })
    },
    commentmannagement () {
      this.$router.push({
        path: '/commentmanagement',
        query: {
          id: this.ruleForm.id,
          username: this.ruleForm.username
        }
      })
    },
    jubaomannagement () {
      this.$router.push({
        path: '/jubaomanagement',
        query: {
          id: this.ruleForm.id,
          username: this.ruleForm.username
        }
      })
    },
    xunwuqishi () {
      this.$router.push({
        path: '/xunwuqishimanagement',
        query: {
          id: this.ruleForm.id,
          username: this.ruleForm.username
        }
      })
    },
    songyang () {
      this.$router.push({
        path: '/songyangmanagement',
        query: {
          id: this.ruleForm.id,
          username: this.ruleForm.username
        }
      })
    },
    zhaoling () {
      this.$router.push({
        path: '/zhaolingmanagement',
        query: {
          id: this.ruleForm.id,
          username: this.ruleForm.username
        }
      })
    }
  },
  created () {
    const _this = this
    this.$axios.get('http://119.29.250.12:8081/admin/select/' + _this.$route.query.username).then(function (resp) {
      console.log(resp)
      _this.admin = resp.data
    })
    this.ruleForm.username = this.$route.query.username
    this.ruleForm.id = this.$route.query.id
  }
}
</script>

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
  text-align: center;
  line-height: 800px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
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
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;

}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
  background-color: #00000033;
  margin-left: -150px;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
