<template>
  <div  style="display: flex;justify-content: space-around;">
    <div style="width: 100%">
      <!--标签页-->
      <div style="background-color: white;padding: 0.2rem;margin-bottom: 0.2rem;">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <!--          <el-breadcrumb-item ><a @click=" removeIndex()" class="blog-title">首页</a></el-breadcrumb-item>-->
          <el-breadcrumb-item style="margin-top: 5%"><a @click="back()" style="font-size: 15px;">{{this.return}}返回</a></el-breadcrumb-item>
          <!--          <el-breadcrumb-item :to="{ path: '/' }">认领页面</el-breadcrumb-item>-->
        </el-breadcrumb>
      </div>
      <!--标题-->
      <div style="background-color: white;padding: 0.2rem;display:flex;justify-content: space-between;">
        <div style="text-align: initial">
          <h2 style="display: contents;">{{homelesspet.title}}</h2>
        </div>
        <div>
          <el-tag type="success" v-if="this.$route.query.status == 0" style="font-size: initial;">等待对方同意中...</el-tag>
          <el-tag type="danger" v-if="this.$route.query.status == 1" style="font-size: initial;">已收养</el-tag>
        </div>
      </div>
      <!--领养内容-->
      <div style="background-color: beige;padding: 0.2rem;">
        <div style="display: flex;justify-content: space-around;">
          <div style="width: 55%;height: 100%">
            <el-carousel style="height: 100%" >
              <el-carousel-item v-for="(image,index) in homelesspet.images" :key="index" style="height: 100%" >
                <img  :src="image" style="width: 80%;height: 80%;margin-left: 10%"/>
              </el-carousel-item>
            </el-carousel>
          </div>
          <div class="homelesspet-message">
            <div>
              <p style="font-size: 14px"><i class="el-icon-time blog-icon"></i>发布时间: {{homelesspet.datetime}}</p>
            </div>
            <div>
              <p style="font-size: 14px"><i class="el-icon-time blog-icon"></i>找回时间: {{renling.datetime}}</p>
            </div>
            <div>
              <p style="font-size: 14px"><i class="el-icon-location-outline blog-icon"></i>地点: {{homelesspet.address}}</p>
            </div>
            <div>
              <p style="font-size: 14px"><i class="el-icon-user blog-icon"></i>联系人： {{homelesspet_user.name}}</p>
            </div>
            <div>
              <p style="font-size: 14px"><i class="el-icon-phone blog-icon"></i>联系电话: {{homelesspet_user.phoneid}}</p>
            </div>
          </div>
        </div>
        <div style="margin-top: 1rem">
          <el-divider content-position="left"><h3>详细描述</h3></el-divider>
          <div style="text-align: start;">
            <p>{{renling.condit}}</p>
          </div>
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
      return: '<',
      textarea: '',
      username: '',
      dialogFormVisible: false,
      userid: '',
      homelesspet: {
        id: '',
        datetime: '',
        address: '',
        introduce: '',
        title: '',
        images: [],
        varieties: '',
        userId: ''
      },
      renling: {
        datetime: '',
        condit: ''
      },
      homelesspet_user: {
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
    back () {
      this.$router.go(-1)
    },
    removeIndex () {
      this.$router.push({
        path: '/indexM',
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
    this.homelesspet_user.id = this.$route.query.userId
    this.homelesspet.id = this.$route.query.id
    const _this = this
    this.$axios.get('http://119.29.250.12:8081/findHomeLessPet/' + _this.$route.query.id).then(function (resp) {
      _this.homelesspet = resp.data
      _this.homelesspet.images = resp.data.images.split(';')
    })
    this.$axios.get('http://119.29.250.12:8081/querry/findrenlinger/' + _this.$route.query.id + '/' + _this.$route.query.renlingId).then(function (resp) {
      _this.renling = resp.data
    })
    this.$axios.get('http://119.29.250.12:8081/user/selectbyid/' + _this.$route.query.userId).then(function (resp) {
      _this.homelesspet_user = resp.data
    })
  }
}
</script>

<style scoped>
>>>.el-carousel__container{
  height: 4rem;
}
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
