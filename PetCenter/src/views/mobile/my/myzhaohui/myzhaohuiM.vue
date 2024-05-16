<template>
  <div class="tmpl" style="width: 100% ;height:120%">
    <div style="background-color: white;padding: 0.1rem;width: 100%">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item style="margin-top: 5%;padding-bottom: 3%"><a @click="back()" style="font-size: 15px; ">{{this.return}}返回</a></el-breadcrumb-item>
      </el-breadcrumb>
      <h1 style="margin-left: 40%;margin-top: -5%;font-size: 15px;font-weight: bolder">我的找回</h1>
      <hr style="margin-bottom: -1%;width: 100%">
    </div>
    <ul class="mui-table-view">
      <li class="mui-table-view-cell mui-media" v-for="(item,index) in homelesspets" :key="index">
        <router-link :to="{name:'XiangQing5M',query:{id:item.id,userId:item.userId,renlingId:item.renlingId,status:item.petrenling}}">
          <img class="mui-media-object mui-pull-left" :src="item.images">
          <div class="mui-media-body">
            <span class="title">{{item.title}}</span>
            <div class="news-desc clearfix">
              <p>地点:{{item.address}}</p>
              <p>时间:{{item.datetime}}</p>
            </div>
          </div>
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default{
  name: 'newsList',
  data () {
    return {
      return: '<',
      adopterId: '',
      homelesspets: [
        {
          id: '',
          address: '',
          datetime: '',
          title: '',
          images: [],
          introduce: '',
          petrenling: '',
          renlingId: '',
          userId: ''
        }
      ]
    }
  },
  methods: {
    back () {
      this.$router.go(-1)
    }
  },
  created () {
    const _this = this
    this.$axios.get('http://119.29.250.12:8081/findmyzhaohui/' + _this.$route.query.id).then(function (resp) {
      _this.homelesspets = []
      for (let i = 0; i < resp.data.length; i++) {
        _this.homelesspets[i] = resp.data[i]
        _this.homelesspets[i].images = resp.data[i].images.split(';')
      }
    })
  }
}
</script>

<style scoped>
/*scoped作用是css只作用于当前组件，不会影响其他组件样式*/
.mui-media-body p {
  color: #0bb0f5;
}
.mui-table-view {
  margin-bottom: 70px;
}
.news-desc p:nth-child(1) {
  float: left;
}

.news-desc p:nth-child(2) {
  float: right;
}
.title {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 17em;
  display: block;
}
</style>
