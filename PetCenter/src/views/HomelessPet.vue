<template>
  <div style="display: flex;justify-content: space-around;">
    <!--左边-->
    <div>
      <!--城市-->
      <div style="text-align: left;font-size: large">输入城市名称:</div>
      <div style="float: left">
        <el-input v-model="value" placeholder="请输入想要查找的城市"  indicator-position="outside" >
          <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
        </el-input>
      </div>
      <div >
        <div style="margin: 15px 0;"></div>
        <el-checkbox-group :max="1" v-model="checkedCities" @change="handleCheckedCitiesChange" class="checkbox">
          <el-checkbox-button v-for="city in cities" :label="city" :key="city">{{city}}</el-checkbox-button>
        </el-checkbox-group></div>
      <!--领养博客-->
      <div>
        <div style="text-align: left">
          <h3>流浪宠物招领</h3>
        </div>
        <el-divider></el-divider>
        <!--eslint-disable-next-line-->
        <div v-for="homeLessPet in homeLessPets" style="width: 600px;">
          <div style="display:flex;background-color: white;padding: 10px;margin-bottom: 10px;">
            <div style="margin-right: 10px;">
              <router-link :to="{path: '/xiangqing1',query:{id:homeLessPet.id,userId: homeLessPet.userId,status:homeLessPet.petrenling,renlingId:renlingId}}">
                <img :src="homeLessPet.images[0]" style="width: 100px;height: 110px;" alt="">
              </router-link>
            </div>
            <div>
              <div style="display: none;">{{homeLessPet.title}}</div>
              <div style="text-align: left;;margin-top: -15px;color: slategray;" @click="blogdetails(homeLessPet.id,homeLessPet.userId)" class="homeLessPet-title"><h4>{{homeLessPet.title}}</h4></div>
              <div style="display:flex;margin-top: -30px;">
                <p style="color: #999;">
                  <i class="el-icon-date"></i>{{homeLessPet.datetime}}
                  <i class="el-icon-location-outline"></i>{{homeLessPet.address}}
                </p>
              </div>
              <div style="text-align: initial;margin-top: -25px;"><p style="font-size: small;color: #999;font-size: medium;" class="p-overflow">{{homeLessPet.introduce}}</p></div>
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
    <div>
      <!--最新文章-->
      <div style="width: 300px;">
        <div style="text-align: left"><h3>最新文章</h3></div>
        <el-divider></el-divider>
        <div class="border-blog">
          <div  v-for="(pet,index) in petBlog" :key="index" v-if="index<5" style="margin-top:-10px">
            <div @click="toPetXiangQing(pet.id)" class="blog-title">
              <div style="display: flex">
                <div  v-if="pet.user"><img :src="pet.user.portrait" style="width: 48px;height: 48px" alt=""></div>
                <div  v-if="pet.user" style="margin-left: 10px;text-align: left">
                  <a style="text-decoration: none;color: #029789;">{{pet.user.name}}</a>
                  <a style="text-decoration: none;margin-top: 10px"><div style="font-size: xx-small;color: #888">{{pet.title}}</div></a>
                </div>
              </div>
              <div style="font-size: x-small;text-overflow: ellipsis;text-align: left;margin-top: -10px;" class="p-overflow"><p>{{pet.introduce}}</p></div>
            </div>
            <div style="margin-top: -20px">
              <el-divider></el-divider>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
const cityOptions = ['上海', '北京', '广州', '深圳', '保定', '长春', '成都', '重庆', '长沙', '常州', '东莞', '大连', '佛山', '福州', '南通', '揭阳', '普宁', '惠来', '惠州', '澳门', '香港']
export default {
  name: 'HomelessPet',
  data () {
    return {
      renlingId: '',
      checkAll: false,
      checkedCities: [],
      cities: cityOptions,
      isIndeterminate: true,
      currentPage: 1,
      total: null,
      value: '',
      homeLessPets: [
        {
          id: '',
          title: '',
          address: '',
          datetime: '',
          images: [],
          introduce: '',
          petrenling: '',
          renlingId: '',
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
      window.alert(this.value)
      this.handleCheckedCitiesChange(this.value)
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
      this.$axios.get('http://localhost:8081/findByAddress/' + _this.currentPage + '/4/' + value).then(function (resp) {
        _this.homeLessPets = resp.data.records
        for (let i = 0; i < resp.data.records.length; i++) {
          _this.homeLessPets[i].images = resp.data.records[i].images.split(';')
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
      this.$axios.get('http://localhost:8081/findAllHomelessPet/' + currentPage + '/4').then(function (resp) {
        _this.homeLessPets = resp.data.records
        for (let i = 0; i < resp.data.records.length; i++) {
          _this.homeLessPets[i].images = resp.data.records[i].images.split(';')
        }
        _this.total = resp.data.total
        console.log(resp)
      })
    }
  },
  created () {
    this.renlingId = this.$route.query.id
    window.alert(this.$route.query.id)
    this.getData(this.currentPage)
    const _this = this
    this.$axios.get('http://localhost:8081/petBlog/querryAll').then(function (resp) {
      _this.petBlog = resp.data
    })
  }
}
</script>
<style scoped>
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
