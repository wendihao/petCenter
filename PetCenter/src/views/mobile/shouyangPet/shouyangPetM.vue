<template>
  <div class="tmpl">
    <ul class="mui-table-view">
      <li class="mui-table-view-cell mui-media" v-for="(item,index) in newsList" :key="index">
        <router-link :to="{name:'XiangQingM',query:{id:item.id,userId:item.userId,adopterId:adopterId,status:item.status}}">
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
      adopterId: '',
      newsList: [
        {
          id: '',
          address: '',
          datetime: '',
          images: [],
          title: '',
          status: '',
          userId: ''
        }
      ] // 新闻列表数据
    }
  },
  created () {
    const _this = this
    this.$axios.get('http://119.29.250.12:8081/index/select/' + '宠物收养').then(function (resp) {
      for (let i = 0; i < resp.data.length; i++) {
        _this.newsList[i] = resp.data[i]
        _this.newsList[i].images = resp.data[i].images.split(';')
        console.log(_this.newsList[i])
      }
    })
    _this.adopterId = _this.$route.query.id
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
