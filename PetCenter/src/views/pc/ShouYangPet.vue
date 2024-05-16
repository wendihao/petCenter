<template>
  <div>
    <!--  <div class="sear_header">-->
    <!--&lt;!&ndash;    <img @click="$router.back()" src="../assets/img/1.jpg" alt="">&ndash;&gt;-->
    <!--    <div class="search">-->
    <!--      <van-search v-model="value" show-action placeholder="搜索" @search="search">-->
    <!--        <template #action>-->
    <!--          <div @click="search">搜索</div>-->
    <!--        </template>-->
    <!--      </van-search>-->
    <!--    </div>-->
    <!--  </div>-->
    <div style="">
      <!--左边-->
      <div>
        <!--城市-->
        <div style="text-align: center;font-size: large">输入城市名称:</div>
        <div style="width: 100%;height: 42px;">
          <input v-model="value" placeholder="     搜索城市"  indicator-position="outside" style="float:left;margin-left:35%;width: 32%;height: 100%;border-radius: 1rem;border: solid 1px black">
          <el-button slot="append" icon="el-icon-search" STYLE="margin-left: -37%;margin-top:2px;height:90%;border: solid 0;border-radius: 0.4rem" @click="search"></el-button>
        </div>
        <div class="sear_histor">
          <div class="history">
            <div class="title">
              <p>最近搜索</p >
              <!--        <img @click="del" src="../assets/img/1.jpg" alt="">-->
            </div>
            <div class="content">
              <span v-if="!historyList.length">暂无</span>
              <!-- 采用本地存储 -->
              <el-button  v-else @click="value=item;search()" v-for="(item,index) in historyList"
                          :key="index" style="border: 1px solid lightskyblue;margin-left: 5px;border-radius: 20px">
                {{item}}
              </el-button>
            </div>
          </div>
        </div>
        <div >
          <div style="margin: 50px 0px;"></div>
          <el-checkbox-group :max="1" v-model="checkedCities" style="margin-left: 35%;margin-right:35%" @change="handleCheckedCitiesChange" class="checkbox">
            <el-checkbox-button  v-for="city in cities" :label="city" :key="city" style="border: 1px solid lightskyblue;margin-left: 1px;margin-top: 1px">{{city}}</el-checkbox-button>
          </el-checkbox-group></div>
        <!--领养博客-->
        <div style="text-align: center">
          <div style="text-align: center;font-size: 20px">
            <h3>领养</h3>
          </div>
          <el-divider></el-divider>
          <!--eslint-disable-next-line-->
          <div v-for="blog in blogs" style="width: 600px;float: left;margin-left: 135px;margin-top:10px">
            <div style="display:flex;background-color: white;padding: 10px;margin-bottom: 10px;">
              <div style="margin-right: 10px;">
                <router-link :to="{path: '/xiangqing',query:{id:blog.id,userId: blog.userId,status:blog.petadopt,adopterId:adopterId}}">
                  <img :src="blog.images[0]" style="width: 100px;height: 110px;" alt="">
                </router-link>
              </div>
              <div>
                <div style="display: none;"></div>
                <div style="text-align: left;color: slategray;margin-top:-10px;font-size: 18px" @click="blogdetails(blog.id,blog.userId)" class="XunWu-title"><h4>{{blog.title}}</h4></div>
                <div style="display:flex;margin-top:-20px">
                  <p style="color: #999;font-size: 15px">
                    <i class="el-icon-date"></i>{{blog.datetime}}
                    <i class="el-icon-location-outline"></i>{{blog.address}}
                  </p>
                </div>
                <div style="text-align: initial;margin-top:-10px;font-size: 15px"><p style="color: #999;font-size: medium;" class="p-overflow">{{blog.introduce}}</p></div>
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
    </div>
  </div>
</template>
<script>
const cityOptions = ['上海', '北京', '广州', '深圳', '长春', '成都', '重庆', '长沙', '常州', '东莞', '大连', '佛山', '福州', '南通', '揭阳', '惠州', '澳门', '香港']
export default {
  name: 'ShouYangPet',
  data () {
    return {
      pastHistory: true,
      newArr: [],
      list: [],
      historyList: [],
      adopterId: '',
      checkAll: false,
      checkedCities: [],
      cities: cityOptions,
      isIndeterminate: true,
      currentPage: 1,
      total: null,
      value: '',
      blogs: [
        {
          id: '',
          address: '',
          datetime: '',
          title: '',
          images: [],
          introduce: '',
          petadopt: '',
          userId: ''
        }
      ],
      petBlog: [
        {
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
    search () {
      if (!this.value) { // 判断输入的值是否为空
        return
      }
      if (this.historyList.indexOf(this.value) === -1) {
        // 添加到头部
        this.historyList.unshift(this.value)
        // 最多显示10个
        this.historyList = this.historyList.slice(0, 8)
        localStorage.setItem('history', JSON.stringify(this.historyList))
      }
      // this.getProductList() // 最后调用接口
      this.handleCheckedCitiesChange(this.value)
      this.value = ''
    },
    handleCheckAllChange (val) {
      this.checkedCities = val ? cityOptions : []
      this.isIndeterminate = false
    },
    handleCheckedCitiesChange (value) {
      const _this = this
      _this.value = value
      let checkedCount = value.length
      this.checkAll = checkedCount === this.cities.length
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.cities.length
      this.$axios.get('http://119.29.250.12:8081/findByAddress1/' + _this.currentPage + '/4/' + value).then(function (resp) {
        _this.blogs = resp.data.records
        for (let i = 0; i < resp.data.records.length; i++) {
          _this.blogs[i].images = resp.data.records[i].images.split(';')
        }
        _this.total = resp.data.total
        console.log(resp)
      })
    },
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
      _this.handleCheckedCitiesChange(_this.value)
    },
    getData (currentPage) {
      const _this = this
      this.$axios.get('http://119.29.250.12:8081/findAll/' + currentPage + '/4').then(function (resp) {
        _this.blogs = resp.data.records
        for (let i = 0; i < resp.data.records.length; i++) {
          _this.blogs[i].images = resp.data.records[i].images.split(';')
        }
        _this.total = resp.data.total
        console.log(resp)
      })
    },
    del () {
      localStorage.removeItem('history')
      this.historyList = []
    }
  },
  mounted () {
    if (JSON.parse(localStorage.getItem('history'))) {
      this.historyList = JSON.parse(localStorage.getItem('history'))
    }
    console.log(this.historyList)
  },
  created () {
    this.adopterId = this.$route.query.id
    this.getData(this.currentPage)
  }
}
</script>
<style scoped>
h4 {
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
  .checkbox{
       display: table;
       width: 500px;
       white-space: pre-wrap;
       word-wrap: break-word;
     }
  .blog-title:hover{
    cursor: pointer;
  }
  .p-overflow{
    font-size: x-small;
    overflow:hidden;
    text-overflow:ellipsis;
    display:-webkit-box;
    -webkit-box-orient:vertical;
    -webkit-line-clamp: 2;
  }
  .border-blog{
    background-color: white;
    padding: 10px;
    border-style: groove;
    border-color: #ddd;
    border: 10px solid #00000000;
  }
</style>
