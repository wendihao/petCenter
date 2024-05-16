<template>
  <div style="display: flex;justify-content: space-around;">
    <!--左边-->
    <div>
      <!--城市-->
      <!--领养博客-->
      <div>
        <div style="text-align: left">
          <h3>我的收养</h3>
        </div>
        <el-divider></el-divider>
        <!--eslint-disable-next-line-->
        <div v-for="blog in blogs" style="width: 600px;">
          <div style="display:flex;background-color: white;padding: 10px;margin-bottom: 10px;">
            <div style="margin-right: 10px;">
              <router-link :to="{path: '/xiangqing2' ,query: {id:blog.id,adopterId:blog.adopterId,userId:blog.userId,status:blog.petadopt}}">
                <img :src="blog.images[0]" style="width: 100px;height: 110px;" alt="">
              </router-link>
            </div>
            <div>
              <div style="display: none;">{{blog.id}}</div>
              <div style="text-align: left;;margin-top: -15px;color: slategray;" @click="blogdetails(blog.id,blog.userId)" class="blog-title"><h4>{{blog.title}}</h4></div>
              <div style="display:flex;margin-top: -30px;">
                <p style="color: #999;">
                  <i class="el-icon-date"></i>{{blog.datetime}}
                  <i class="el-icon-location-outline"></i>{{blog.address}}
                </p>
              </div>
              <div style="text-align: initial;margin-top: -25px;"><p style="font-size: small;color: #999;font-size: medium;" class="p-overflow">{{blog.introduce}}</p></div>
            </div>
          </div>
        </div>
      </div>
      <!--分页-->
      <div>
        <el-pagination
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
  name: 'MyAdoption',
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
          introduce: '',
          petadopt: '',
          adopterId: '',
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
      this.$axios.get('http://localhost:8081/querry/alladoption1/' + currentPage + '/4/' + _this.$route.query.id).then(function (resp) {
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
    this.getData(this.currentPage)
    const _this = this
    this.$axios.get('http://localhost:8081/petBlog/querryAll').then(function (resp) {
      _this.petBlog = resp.data
    })
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
