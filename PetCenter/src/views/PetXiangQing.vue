<template>
  <div style="display: flex;justify-content: space-around;">
    <!--左边-->
    <div class="blog-left">
      <!--社区分享-->
      <div style="display: flex;justify-content: space-between;margin-bottom: -10px;">
        <!--社区分享-->
        <div>
          <el-link icon="el-icon-s-home" :underline="false" @click="removeIndex()">社区主页</el-link>
          <el-link icon="el-icon-s-grid" :underline="false" @click="removePetCircle()">领养中心</el-link>
        </div>
        <div>
          <el-link icon="el-icon-s-promotion" :underline="false">分享</el-link>
        </div>
      </div>
      <el-divider></el-divider>
      <!--标题-->
      <div style="text-align: initial;margin-top: -15px">
        <h2>{{petBlog.title}}</h2>
      </div>
      <!--博主信息-->
      <div class="blog-user">
        <!--头像和用户信息-->
        <div class="block"  style="display:flex;">
          <el-avatar :size="50" shape="square" :src="user1.portrait"></el-avatar>
          <div style="text-align: initial;margin-left: 10px;">
            <div  class="blog-user-name">
              <span>{{user1.name}}</span>
            </div>
            <div class="blog-user-time">时间:{{petBlog.datetime}}  <i class="el-icon-time"/></div>
          </div>
        </div>
        <!--浏览信息-->
        <div class="blog-user-icon">
            <div><i class="el-icon-view" style="margin-left: 5px;"></i><span style="margin-left: 5px;">{{petBlog.pageview}}</span></div>
            <div style="margin-left: 10px;"><i class="el-icon-s-comment" style="margin-left: 5px;"></i><span style="margin-left: 5px;">{{num}}</span></div>
          </div>
      </div>
      <!--宠物内容-->
      <div>
        <div>
          <el-divider content-position="left"><h3>宠物相册</h3></el-divider>
          <el-carousel height="500px">
            <el-carousel-item v-for="(image,index) in petBlog.images" :key="index">
              <img  :src="image" style="width: 100%;height: 100%;"/>
            </el-carousel-item>
          </el-carousel>
        </div>
        <div style="margin-top: 30px">
          <el-divider content-position="left"><h3>说说内容</h3></el-divider>
          <div style="text-align: start;margin-left: 20px;">
            <p>{{petBlog.introduce}}</p>
          </div>
        </div>
        <div id="div-nav" style="margin-top: 30px;height: 300px;overflow: auto">
          <el-divider  content-position="left"><h3>评论区</h3></el-divider>
          <div style="float: left;width: 100%" class="listItem" v-for="item in commList" :key="item">
            <div>
              <div  style="float: left" class="userInfo">
                <img :src="item.portrait" style="width: 30px;height: 30px" alt="">
                <b style="font-size: 6px">{{item.username}}:{{item.comment}}</b>
<!--                <div class="userComm">{{item.comment}}</div>-->
              </div>
            </div>
          </div>
        </div>
        <el-input
          type="textarea"
          style="width: 90%;float: left"
          :rows="1"
          placeholder="请输入内容"
          :autosize="{ minRows: 1, maxRows: 6}"
          v-model="comment.comment">
        </el-input>
        <el-button style="float: left;background: #409EFF" @click="sendcomment() " type="button" size="small">发送</el-button>
      </div>
    </div>
    <!--右边-->
    <div style="display: flex;justify-content: space-around;;">
      <div style="width: 300px;">
        <div style="text-align: left"><h3>最新文章</h3></div>
        <el-divider></el-divider>
        <div  v-for="(pet,index) in petBlog" :key="index"  v-if="index<5">
          <div @click="toPetXiangQing(pet.id)" class="blog-title">
            <div style="display: flex">
              <div v-if="pet.user"><img :src="pet.user.portrait" style="width: 48px;height: 48px" alt=""></div>
              <div v-if="pet.user" style="margin-left: 10px;text-align: left">
                <a  style="text-decoration: none;color: #029789;">{{pet.user.name}}</a>
                <a  style="text-decoration: none;margin-top: 10px"><div style="font-size: xx-small;color: #888">{{pet.title}}</div></a>
              </div>
            </div>
            <div style="font-size: x-small;text-overflow: ellipsis;text-align: left" class="p-overflow"><p>{{pet.introduce}}</p></div>
          </div>
          <div style="margin-top: -20px">
            <el-divider></el-divider>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PetXiangQing',
  data () {
    return {
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
    sendcomment () {
      const _this = this
      this.$axios.post('http://localhost:8081/insert/', _this.comment).then(function (resp) {
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
        path: '/',
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
    this.currentTime = setInterval(() => {
      this.$axios.get('http://localhost:8081/comment/querryByPetBlogId/' + _this.$route.query.id).then(res => {
        if (res.data !== this.commList) {
          this.commList = res.data
        }
      })
      this.$axios.get('http://localhost:8081/comment/querryNumberById/' + _this.$route.query.id).then(res => {
        if (res.data !== this.num) {
          this.num = res.data
        }
      })
    }, 2000)
    this.username = this.$route.query.username
    const _this = this
    this.$axios.get('http://localhost:8081/petBlog/querryById/' + _this.$route.query.id).then(function (resp) {
      _this.petBlog = resp.data
      _this.comment.petblogId = resp.data.id
      _this.petBlog.userId = resp.data.userId
      _this.petBlog.images = resp.data.images.split(';')
      console.log(_this.blog.images)
    })
    _this.comment.username = _this.$route.query.username
    this.$axios.get('http://localhost:8081/user/select/' + _this.$route.query.username).then(function (resp) {
      _this.user = resp.data
      _this.comment.portrait = resp.data.portrait
    })
    this.$axios.get('http://localhost:8081/user/selectbyid/' + _this.$route.query.userId).then(function (resp) {
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
    width: 55%;
    background-color: white;
    padding: 10px;
    margin-left: 60px;
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
