<template>
  <div style="width: 100%">
    <!--左边-->
    <div class="blog-left">
      <!--社区分享-->
      <div style="display: flex;justify-content: space-between;margin-bottom: -0.2rem;">
        <!--社区分享-->
        <div>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item style="margin-top: 5%;padding-bottom: 3%"><a @click="back()" style="font-size: 15px; ">{{this.return}}返回</a></el-breadcrumb-item>
          </el-breadcrumb>
        </div>
<!--        <div>-->
<!--          <el-link icon="el-icon-s-promotion" :underline="false">分享</el-link>-->
<!--        </div>-->
      </div>
      <el-divider></el-divider>
      <!--标题-->
<!--      <div style="text-align: initial;margin-top: -0.2rem">-->
<!--        <h2>{{petBlog.title}}</h2>-->
<!--      </div>-->
      <!--博主信息-->
      <div class="blog-user">
        <!--头像和用户信息-->
        <div class="block"  style="display:flex;">
          <img :src="user1.portrait" style="width: 1rem;height: 1rem;float: left" alt="" >
<!--          <el-avatar  :src="user1.portrait" style="width: 1rem;height:1rem"></el-avatar>-->
          <div style="text-align: initial;margin-left: 0.2rem;">
            <div  class="blog-user-name">
              <span>{{user1.name}}</span>
            </div>
            <div class="blog-user-time" style="width: 100%">时间:{{petBlog.datetime}}  <i class="el-icon-time"/></div>
          </div>
        </div>
        <!--浏览信息-->
        <div class="blog-user-icon">
            <div><i class="el-icon-view" style="margin-left: 0.1rem;"></i><span style="margin-left: 0.1rem;">{{petBlog.pageview}}</span></div>
            <div style="margin-left: 0.1rem;"><i class="el-icon-s-comment" style="margin-left: 0.1rem;"></i><span style="margin-left: 0.1rem;">{{num}}</span></div>
          </div>
      </div>
      <!--宠物内容-->
      <div>
        <div>
          <el-divider content-position="left"><h3>宠物相册</h3></el-divider>
          <el-carousel height="5rem">
            <el-carousel-item v-for="(image,index) in petBlog.images" :key="index">
              <img  :src="image" style="width: 80%;height: 100%;margin-left: 10%"/>
            </el-carousel-item>
          </el-carousel>
        </div>
        <div style="margin-top: 0.5rem">
          <el-divider content-position="left"><h3>说说内容</h3></el-divider>
          <div style="text-align: start;margin-left: 0.4rem;">
            <p>{{petBlog.introduce}}</p>
          </div>
        </div>
          <el-divider  content-position="left"><h3>评论区</h3></el-divider>
        <div id="div-nav" style="margin-top: 0.5rem;height: 5rem;overflow: auto">
          <div style="float: left;width: 100%" class="listItem" v-for="item in commList" :key="item">
            <div>
              <div  style="float: left;margin-top:0.2rem" class="userInfo">
                <img :src="item.portrait" style="width: 1rem;height: 1rem;float: left" alt="">
                <p style="font-size: 15px;float: left;padding-top:0.5rem">{{item.username}}:{{item.comment}}</p>
<!--                <div class="userComm">{{item.comment}}</div>-->
              </div>
            </div>
          </div>
        </div>
        <el-input
          type="textarea"
          style="width: 85%;float: left"
          :rows="1"
          placeholder="请输入内容"
          :autosize="{ minRows: 1, maxRows: 6}"
          v-model="comment.comment">
        </el-input>
        <el-button style="width: 15%;background: #409EFF" @click="sendcomment() " type="button" size="small">发送</el-button>
      </div>
    </div>
    <!--右边-->
  </div>
</template>
<script>
export default {
  name: 'PetXiangQing',
  data () {
    return {
      return: '<',
      currentTime: new Date(),
      username: '',
      textarea: '',
      num: '',
      circleUrl: 'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',
      comment: {
        id: '',
        petblogId: '',
        comment: '',
        username: '',
        portrait: ''
      },
      commList: [{
        id: '',
        petblogId: '',
        comment: '',
        username: '',
        portrait: ''
      }],
      petBlog: {
        id: '',
        title: '',
        introduce: '',
        images: [],
        datetime: '',
        pageview: '',
        comment: '',
        userId: ''
      },
      blog: {
        introduce: '',
        images: [],
        title: '',
        bloguser: {
          name: ''
        }
      },
      user: {
        username: '',
        portrait: ''
      },
      user1: {
        username: '',
        portrait: ''
      }
    }
  },
  methods: {
    back () {
      this.$router.go(-1)
    },
    sendcomment () {
      const _this = this
      this.$axios.post('http://119.29.250.12:8081/insert/', _this.comment).then(function (resp) {
        if (resp.data === 'success') {
          _this.$message({
            showClose: true,
            type: 'success',
            message: `提交成功`
          })
          _this.$router.go(0)
        }
      })
    },
    toPetXiangQing (id) {
      this.$router.push({
        path: '/petxiangqing2',
        query: {
          id: id,
          username: this.username
        }
      })
    },
    removeIndex () {
      this.$router.push({
        path: 'indexM',
        query: {
          username: this.username
        }
      })
    },
    removePetCircle () {
      this.$router.push({
        path: '/shouyangpet',
        query: {
          username: this.username
        }
      })
    }
  },
  created () {
    // this.currentTime = setInterval(() => {
    const _this = this
    this.$axios.get('http://119.29.250.12:8081/comment/querryByPetBlogId/' + _this.$route.query.id).then(res => {
      if (res.data !== this.commList) {
        this.commList = res.data
      }
    })
    this.$axios.get('http://119.29.250.12:8081/comment/querryNumberById/' + _this.$route.query.id).then(res => {
      if (res.data !== this.num) {
        this.num = res.data
      }
    })
    // }, 100)
    this.username = this.$route.query.username
    this.$axios.get('http://119.29.250.12:8081/petBlog/querryById/' + _this.$route.query.id).then(function (resp) {
      _this.petBlog = resp.data
      _this.comment.petblogId = resp.data.id
      _this.petBlog.userId = resp.data.userId
      _this.petBlog.images = resp.data.images.split(';')
      console.log(_this.blog.images)
    })
    _this.comment.username = _this.$route.query.username
    this.$axios.get('http://119.29.250.12:8081/user/select/' + _this.$route.query.username).then(function (resp) {
      _this.user = resp.data
      _this.comment.portrait = resp.data.portrait
    })
    this.$axios.get('http://119.29.250.12:8081/user/selectbyid/' + _this.$route.query.userId).then(function (resp) {
      _this.user1 = resp.data
    })
  }
}
</script>

<style scoped>
#div-nav::-webkit-scrollbar{
  display: none;
}
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  .p-overflow{
    font-size: x-small;
    overflow:hidden;
    text-overflow:ellipsis;
    display:-webkit-box;
    -webkit-box-orient:vertical;
    -webkit-line-clamp: 2;
  }
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
  .blog-title:hover{
    cursor: pointer;
  }
  .blog-left{
    width: 100%;
    background-color: white;
    padding: 10px;
  }
  .blog-user-name{
    font-size: 18px;
    line-height: 23px;
    color: #333;
  }
  .blog-user-time{
    color: #999;
    font-size: 14px;
    margin-top: 5px;
  }
  .blog-user-icon{
    display: flex;
    font-size: 15px;
    color: rgb(151, 159, 168);
    margin-top: 5px;
  }
  .blog-user{
    display: flex;
    margin-left: 20px;
    justify-content: space-between;
    margin-bottom: 35px;
  }
</style>
