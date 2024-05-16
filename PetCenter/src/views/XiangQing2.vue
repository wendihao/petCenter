<template>
  <div  style="display: flex;justify-content: space-around;">
    <div style="width: 1120px;padding: 10px;">
      <!--标签页-->
      <div style="background-color: white;padding: 10px;margin-bottom: 10px;">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item ><a @click=" removeIndex()" class="blog-title">首页</a></el-breadcrumb-item>
          <el-breadcrumb-item ><a @click="removePetCenter()">认领中心</a></el-breadcrumb-item>
          <el-breadcrumb-item :to="{ path: '/' }">认领页面</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <!--标题-->
      <div style="background-color: white;padding: 10px;display:flex;justify-content: space-between;">
        <div style="text-align: initial">
          <h2 style="display: contents;">{{blog.title}}</h2>
        </div>
        <div>
          <el-tag type="success" v-if="this.$route.query.status == 0" style="font-size: initial;">等待对方同意中...</el-tag>
          <el-tag type="danger" v-if="this.$route.query.status == 1" style="font-size: initial;">已收养</el-tag>
        </div>
      </div>
      <!--领养内容-->
       <div style="background-color: white;padding: 10px;">
        <div style="display: flex;justify-content: space-around;">
          <div style="width: 55%;">
            <el-carousel height="300px;">
              <el-carousel-item v-for="(image,index) in blog.images" :key="index">
                <img  :src="image" style="width: 100%;height: 100%;"/>
              </el-carousel-item>
            </el-carousel>
          </div>
          <div class="blog-message">
            <div>
              <p><i class="el-icon-time blog-icon"></i>发布时间: {{blog.datetime}}</p>
            </div>
            <div>
              <p><i class="el-icon-time blog-icon"></i>收养时间: {{adoption.datetime}}</p>
            </div>
            <div>
              <p><i class="el-icon-location-outline blog-icon"></i>地点: {{blog.address}}</p>
            </div>
            <div>
              <p><i class="el-icon-user blog-icon"></i>联系人： {{blog_user.name}}</p>
            </div>
            <div>
              <p><i class="el-icon-phone blog-icon"></i>联系电话: {{blog_user.phoneid}}</p>
            </div>
          </div>
        </div>
        <div style="margin-top: 40px">
          <el-divider content-position="left"><h3>详细描述</h3></el-divider>
          <div style="text-align: start;">
            <p>{{adoption.condit}}</p>
          </div>
      </div>
        <div>
          <el-divider content-position="left"><h3>评论区</h3></el-divider>
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入内容"
            :autosize="{ minRows: 3, maxRows: 6}"
            v-model="textarea">
          </el-input>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'XiangQing2',
  data () {
    return {
      textarea: '',
      username: '',
      dialogFormVisible: false,
      userid: '',
      adoption: {
        datetime: '',
        condit: ''
      },
      blog: {
        id: '',
        datetime: '',
        address: '',
        introduce: '',
        title: '',
        images: [],
        varieties: '',
        userId: ''
      },
      blog_user: {
        id: '',
        address: '',
        email: '',
        name: '',
        phoneid: ''
      },
      user: {
        id: '',
        name: '',
        phoneid: '',
        email: ''
      }
    }
  },
  methods: {
    removeIndex () {
      this.$router.push({
        path: '/',
        query: {
          id: this.blog.userId,
          username: this.user.username
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
    }
  },
  created () {
    // this.status = this.$route.query.status this.username = this.$route.query.username
    window.alert(this.$route.query.status)
    this.blog_user.id = this.$route.query.userId
    this.blog.id = this.$route.query.id
    const _this = this
    this.$axios.get('http://localhost:8081/findBlog/' + _this.$route.query.id).then(function (resp) {
      _this.blog = resp.data
      _this.blog.images = resp.data.images.split(';')
    })
    this.$axios.get('http://localhost:8081/querry/findadoption/' + _this.$route.query.id + '/' + _this.$route.query.adopterId).then(function (resp) {
      _this.adoption = resp.data
    })
    window.alert(_this.$route.query.userId)
    this.$axios.get('http://localhost:8081/user/selectbyid/' + _this.$route.query.userId).then(function (resp) {
      _this.blog_user = resp.data
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
