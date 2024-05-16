<template>
  <div  style="display: flex;justify-content: space-around;">
    <div style="width: 1120px;padding: 10px;">
      <!--标签页-->
      <div style="background-color: white;padding: 10px;margin-bottom: 10px;">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item ><a @click=" removeIndex()" class="blog-title">首页</a></el-breadcrumb-item>
          <el-breadcrumb-item ><a @click="back()">{{this.return}}返回</a></el-breadcrumb-item>
<!--          <el-breadcrumb-item :to="{ path: '/' }">认领页面</el-breadcrumb-item>-->
        </el-breadcrumb>
      </div>
      <!--标题-->
      <div style="background-color: white;padding: 10px;display:flex;justify-content: space-between;">
        <div style="text-align: initial">
          <h2 style="display: contents;">寻物启事</h2>
        </div>
      </div>
      <!--领养内容-->
       <div style="background-color: white;padding: 10px;">
        <div style="display: flex;justify-content: space-around;">
          <div style="width: 55%;height: 400px">
            <el-carousel style=" height: 400px">
              <el-carousel-item v-for="(image,index) in xunwu.images" :key="index" style=" height: 400px">
                <img  :src="image" style="width: 100%;height: 400px;"/>
              </el-carousel-item>
            </el-carousel>
          </div>
          <div class="HomeLessPet-message">
            <div >
              <p style="font-size: 20px"><i class="el-icon-time blog-icon"></i>丢失时间: {{xunwu.datetime}}</p>
            </div>
            <div>
              <p style="font-size: 20px"><i class="el-icon-location-outline blog-icon"></i>丢失地点: {{xunwu.address}}</p>
            </div>
            <div>
              <p style="font-size: 20px"><i class="el-icon-user blog-icon"></i>联系人： {{xunwu_user.name}}</p>
            </div>
            <div >
              <p style="font-size: 20px"><i class="el-icon-phone blog-icon"></i>联系电话: {{xunwu_user.phoneid}}</p>
            </div>
          </div>
        </div>
        <div style="margin-top: 40px">
          <el-divider content-position="left"><h3>详细描述</h3></el-divider>
          <div style="text-align: start;">
            <p>{{xunwu.introduce}}</p>
          </div>
        <div>
          <el-button v-if="xunwu_user.id!=user.id" type="success"  @click="removeim" round>联系</el-button>
        </div>
      </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'XiangQing0',
  data () {
    return {
      return: '<',
      flag: '',
      flag1: '',
      textarea: '',
      username: '',
      dialogFormVisible: false,
      userid: '',
      xunwu: {
        id: '',
        datetime: '',
        address: '',
        introduce: '',
        images: [],
        varieties: '',
        userId: ''
      },
      xunwu_user: {
        id: '',
        name: '',
        username: '',
        phoneid: '',
        email: ''
      },
      user: {
        id: '',
        name: '',
        username: '',
        phoneid: '',
        email: ''
      }
    }
  },
  methods: {
    toPetXiangQing (id) {
      this.$router.push({
        path: '/petxiangqing',
        query: {
          id: id,
          username: this.username
        }
      })
    },
    removeIndex () {
      this.$router.push({
        path: '/index2',
        query: {
          // id: this.blog.userId,
          username: this.user.username
        }
      })
    },
    removeim () {
      this.$router.push({
        path: '/im',
        query: {
          id: this.user.id,
          username: this.user.username,
          username1: this.xunwu_user.username
        }
      })
    },
    removePetCenter () {
      this.$router.push({
        path: '/shouyangpet',
        query: {
          id: this.userid,
          username: this.user.username
        }
      })
    },
    back () {
      this.$router.go(-1)
    },
    onSubmit (formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        _this.ruleForm.homelesspettitle = _this.HomeLessPet.title
        _this.ruleForm.homelesspetId = _this.HomeLessPet.id
        if (valid) {
          this.$axios.post('http://119.29.250.12:8081/insert/renling', _this.ruleForm).then(function (resp) {
            if (resp.data === 'success') {
              _this.$alert('提交成功', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$message({
                    showClose: true,
                    type: 'success',
                    message: `提交成功`
                  })
                }
              })
            } ;
            _this.$router.go(-1)
          })
        } else {
          return false
        }
      })
    }
  },
  created () {
    // this.status = this.$route.query.status this.username = this.$route.query.username
    this.xunwu.userId = this.$route.query.userId
    this.xunwu.id = this.$route.query.id
    const _this = this
    this.$axios.get('http://119.29.250.12:8081/findXunwu/' + _this.$route.query.id).then(function (resp) {
      _this.xunwu = resp.data
      _this.xunwu.images = resp.data.images.split(';')
    })
    this.$axios.get('http://119.29.250.12:8081/user/selectbyid/' + _this.$route.query.userId).then(function (resp) {
      _this.xunwu_user = resp.data
    })
    this.$axios.get('http://119.29.250.12:8081/user/selectbyid/' + _this.$route.query.myId).then(function (resp) {
      _this.user = resp.data
    })
  }
}
</script>

<style scoped>
.world{
  font-family: "PingFang SC", "Helvetica Neue", Helvetica, "Hiragino Sans GB", Arial, "Microsoft YaHei", 微软雅黑, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol";
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}
.p-overflow{
  font-size: x-small;
  overflow:hidden;
  text-overflow:ellipsis;
  display:-webkit-box;
  -webkit-box-orient:vertical;
  -webkit-line-clamp: 2;
}
.blog-title:hover{
  cursor: pointer;
}
.blog-message{
  font-size: medium;
  margin-top: 20px;
  width: 50%;
  text-align: initial;
  padding-left: 20px;
}
.blog-icon{
  margin-right: 5px;
  color: #ff6363;
}
.blog-title:hover{
  cursor: pointer;
}
</style>
