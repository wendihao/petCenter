<template>
  <div style="display: flex;justify-content: space-around;">
    <!--左边-->
    <div>
      <!--城市-->
      <!--领养博客-->
      <div style="text-align: center">
        <div style="text-align: center;font-size: 20px">
          <h3>我的送养</h3>
        </div>
        <el-divider></el-divider>
        <!--eslint-disable-next-line-->
        <div  v-for="blog in blogs" style="width: 600px;float: left;margin-left: 120px;margin-top:10px;border: 1px solid black">
          <div style="display:flex;background-color: white;padding: 10px;margin-bottom: 10px;">
            <div style="margin-right: 10px;">
              <router-link :to="{path: '/xiangqing3' ,query: {id:blog.id,adopterId:blog.adopterId,userId:blog.userId,status:blog.petadopt,schedule:blog.schedule}}">
                <img :src="blog.images[0]" style="width: 100px;height: 110px;" alt="">
              </router-link>
            </div>
            <div>
              <div style="display: none;"></div>
              <div style="text-align: left;color: slategray;font-size: 18px" @click="blogdetails(blog.id,blog.userId)" class="XunWu-title"><h4>{{blog.title}}</h4></div>
              <div style="display:flex;font-size: 15px">
                <p style="color: #999;">
                  <i class="el-icon-date"></i>{{blog.datetime}}
                  <i class="el-icon-location-outline"></i>{{blog.address}}
                </p>
              </div>
              <div style="text-align: initial;font-size: 15px"><p style="color: #999;font-size: medium;" class="p-overflow">{{blog.introduce}}</p></div>
            </div>
          </div>
        </div>
      </div>
      <!--分页-->
      <div>
        <el-pagination style="height:40px;width: 100%;float: left;margin-top: 20px"
                       background
                       layout="prev, pager, next"
                       :page-size=4
                       :total="total"
                       :current-page="currentPage"
                       @current-change="handlerPageChange">
        </el-pagination>
      </div>
    </div>
    <!--右边-->
  </div>
</template>

<script>
export default {
  name: 'MySongYang',
  data () {
    return {
      currentPage: 1,
      total: null,
      user: {
        username: '',
        name: '',
        portrait: '',
        phoneid: '',
        email: ''
      },
      imgs: [],
      blogs: [
        {
          id: '',
          address: '',
          datetime: '',
          title: '',
          images: [],
          petadopt: '',
          introduce: '',
          adopterId: '',
          schedule: '',
          userId: ''
        }
      ],
      petBlog: [
        {
          id: '',
          title: '',
          introduce: '',
          images: '',
          petuser: {
            name: ''
          }
        }
      ]
    }
  },
  methods: {
    blogdetails (id, userId) {
      this.$router.push({
        path: '/xiangqing',
        query: {
          id: id,
          userId: userId,
          username: this.$route.query.username
        }
      })
    },
    toPetXiangQing (id) {
      this.$router.push({
        path: '/petxiangqing2',
        query: {
          id: id,
          username: this.$route.query.username
        }
      })
    },
    handlerPageChange (currentPage) {
      const _this = this
      _this.currentPage = currentPage
      _this.getData(currentPage)
    },
    getData (currentPage) {
      const _this = this
      this.$axios.get('http://119.29.250.12:8081/findAll/' + currentPage + '/4/' + _this.$route.query.id).then(function (resp) {
        _this.blogs = resp.data.records
        for (let i = 0; i < resp.data.records.length; i++) {
          _this.blogs[i].images = resp.data.records[i].images.split(';')
        }
        _this.total = resp.data.total
        console.log(resp)
      })
    }
  },
  created () {
    this.user.username = this.$route.query.username
    // this.currentTime = setInterval(() => {
    this.getData(this.currentPage)
    // }, 100)
  }
}
</script>
<style scoped>
{
  display: block;
  margin-block-start: 1.33em;
  margin-block-end: 1.33em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  font-weight: bold;
}
h3 {
  display: block;
  font-size: 1.17em;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  font-weight: bold;
}
p {
  display: block;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
}
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
.blog-title:hover{
  cursor: pointer;
}
.border-blog{
  background-color: white;
  padding: 10px;
  border-style: groove;
  border-color: #ddd;
  border: 10px solid #00000000;
}
</style>
