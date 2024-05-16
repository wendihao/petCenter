<template >
  <div style="margin-bottom: 1.5rem ;background-color: whitesmoke" >
    <!-- 轮播图 -->
    <div class="swipe">
<!--      <mt-swipe :auto="2000">-->
<!--        <mt-swipe-item v-for="(img,index) in bannerData" :key="index">-->
<!--          <a :href="img.url">-->
            <img :src="bannerData" style="background-color: greenyellow" alt="">
<!--          </a>-->
<!--        </mt-swipe-item>-->
<!--      </mt-swipe>-->
    </div>
    <!-- 九宫格 -->
    <div>
      <ul class="mui-table-view mui-grid-view mui-grid-9">
        <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3" @click="change('寻物启事')">
          <!-- vue-router跳转 -->
            <span class="mui-icon mui-icon-home"></span>
            <div class="mui-media-body">寻物启事</div>
        </li>
        <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3" @click="change('宠物招领')">
            <span class="mui-icon mui-icon-email"></span>
            <div class="mui-media-body">宠物招领</div>
        </li>
        <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3" @click="change('宠物领养')">
            <span class="mui-icon mui-icon-chatbubble"></span>
            <div class="mui-media-body">宠物领养</div>
        </li>
        <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3" @click="jubao">
            <span class="mui-icon mui-icon-location"></span>
            <div class="mui-media-body">举报用户</div>
        </li>
        <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3" @click="liaotian">
            <span class="mui-icon mui-icon-search"></span>
            <div class="mui-media-body">聊天</div>
        </li>
        <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3" @click="contactadmin">
            <span class="mui-icon mui-icon-phone"></span>
            <div class="mui-media-body">联系管理员</div>
        </li>
      </ul>
    </div>
    <div style="text-align: center;" >
      <div style="text-align: center ; padding-top: 0.5rem">
        <h3 style="font-size: 20px;color: lightskyblue; font-weight: bolder;">{{this.type}}</h3>
      </div>
      <el-divider></el-divider>
      <!--eslint-disable-next-line-->
      <input v-model="value" placeholder="  搜索城市"  indicator-position="outside" style="float:left;margin-left:20%;width: 60%;height: 1rem;border-radius: 0.4rem;border: solid 1px white">
        <el-button slot="append" icon="el-icon-search" STYLE="margin-left: -3.5rem;height:1rem;border: solid 0;border-radius: 0.4rem" @click="search"></el-button>
      <div v-for="item in list" :key="item.uuid" style="width: 30%;margin-left: 15%;margin-top: 1rem;float: left;height: 5.0rem;">
          <div v-if="type==='寻物启事'">
            <router-link :to="{name:'XiangQing0M',query:{id:item.id,userId:item.userId,myId:myId}}">
              <img :src="item.images[0]" style="width: 3rem;height: 3rem;" alt="">
            </router-link>
          </div>
          <div v-if="type==='宠物招领'">
            <router-link :to="{name:'XiangQing1M',query:{id:item.id,userId:item.userId,renlingId:myId,status:item.status}}">
              <img :src="item.images[0]" style="width: 3rem;height: 3rem;" alt="">
            </router-link>
          </div>
          <div v-if="type==='宠物领养'">
            <router-link :to="{name:'XiangQingM',query:{id:item.id,userId:item.userId,adopterId:myId,status:item.status}}">
              <img :src="item.images[0]" style="width: 3rem;height: 3rem;" alt="">
            </router-link>
          </div>
        <div>
          <div style="display: none;width: 40%;height: 0.54rem"></div>
          <div style="text-align: left;color: slategray;" @click="blogdetails(item.id,item.userId)" class="XunWu-title"></div>
          <div style="display:flex;width: 100%;">
            <p style="color: #999;width: 100%;">
              时间<i class="el-icon-date" ></i>{{item.datetime}}
            </p>
          </div>
            <div style="display:flex;width: 100%;">
            <p style="color: #999;width: 100%;">
              地点<i class="el-icon-location-outline" ></i>{{item.address}}
            </p>
          </div>
          <div style="text-align: initial;"><p style="font-size: small;color: #999;font-size: medium;" class="p-overflow">{{item.introduce}}</p></div>
        </div>
      </div>
      </div>
    </div>
</template>
<script>
// import {L2Dwidget} from 'live2d-widget'

export default {
  data () {
    return {
      bannerData: require('../../../assets/icons/home.svg'), // 轮播图数据
      myId: '',
      num: '',
      type: '寻物启事',
      value: '',
      list: [{
        id: '',
        images: '',
        address: '',
        datetime: '',
        time: '',
        title: '',
        userId: '',
        status: '',
        uuid: ''
      }],
      user: {
        id: '',
        username: '',
        sex: '',
        name: '',
        portrait: ''
      }
    }
  },
  methods: {
    search () {
      if (!this.value) { // 判断输入的值是否为空
        return
      }
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
      this.$axios.get('http://119.29.250.12:8081/findByAddress/' + _this.type + '/' + value).then(function (resp) {
        _this.list = []
        for (let i = 0; i < resp.data.length; i++) {
          _this.list[i] = resp.data[i]
          _this.list[i].images = resp.data[i].images.split(';')
        }
      })
    },
    jubao () {
      this.$router.push({
        name: 'JuBaoM',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    liaotian () {
      this.$router.push({
        name: 'AllChatM',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    contactadmin () {
      this.$router.push({
        name: 'ContactAdminM',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removeUser () {
      this.$store.commit('set_Token', '')
    },
    removelogin () {
      this.$router.push({
        name: 'loginM',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    change (type) {
      const _this = this
      _this.type = type
      _this.getDate(_this.type)
    },
    getDate (type) {
      const _this = this
      this.$axios.get('http://119.29.250.12:8081/index/select/' + type).then(function (resp) {
        _this.list = []
        for (let i = 0; i < resp.data.length; i++) {
          _this.list[i] = resp.data[i]
          _this.list[i].images = resp.data[i].images.split(';')
        }
        console.log(_this.list)
      })
    }
  },
  created () { // 组件初始化时期请求数据
    this.myId = this.$route.query.id
    this.getDate(this.type)
    //  const _this = this
    //   window.alert(_this.$route.query.username + '3')
    //   this.$ajax.get('/lunbo').then((res) => {
    //     _this.bannerData = res.data.bannerSrc // 拿到数据
    //   })
    // }
    //   this.$axios.get('http://119.29.250.12:8081/findAContactByUsername1/' + this.$route.query.username).then(function (resp) {
    //     _this.num = resp.data
    //   })
    //   this.$axios.get('http://119.29.250.12:8081/findStatusByUsername/' + this.$route.query.username).then(function (resp) {
    //     _this.flag = resp.data
    //     if (!_this.flag) {
    //       _this.removelogin()
    //     }
    //   })
    const _this = this
    this.$axios.get('http://119.29.250.12:8081/user/select/' + _this.$route.query.username).then(function (resp) {
      console.log(resp)
      _this.user = resp.data
    })
    //     _this.user.sex = resp.data.sex
    //     if (_this.user.sex === 1) {
    //       L2Dwidget.init({
    //         'model': {
    //           jsonPath: 'https://unpkg.com/live2d-widget-model-koharu@1.0.5/assets/koharu.model.json',
    //           'scale': 1
    //         },
    //         'display': {
    //           'position': 'right',
    //           'width': 300,
    //           'height': 360,
    //           'hOffset': 0,
    //           'vOffset': -20
    //         },
    //         'mobile': {
    //           'show': true,
    //           'scale': 0.5
    //         },
    //         'react': {
    //           'opacityDefault': 0.8,
    //           'opacityOnHover': 0.1
    //         }
    //       })
    //     }
    //     if (_this.user.sex === 0) {
    //       L2Dwidget.init({
    //         'model': {
    //           jsonPath: 'https://unpkg.com/live2d-widget-model-haruto@1.0.5/assets/haruto.model.json',
    //           'scale': 1
    //         },
    //         'display': {'position': 'right',
    //           'width': 300,
    //           'height': 360,
    //           'hOffset': 0,
    //           'vOffset': -20
    //         },
    //         'mobile': {
    //           'show': true,
    //           'scale': 0.5
    //         },
    //         'react': {
    //           'opacityDefault': 0.8,
    //           'opacityOnHover': 0.1
    //         }
    //       })
    //     }
    //   })
    // }
  }
}
</script>

<style scoped>
/*scoped作用是css只作用于当前组件，不会影响其他组件样式*/
.swipe {
  height: 190px;
}
.mui-table-view.mui-grid-view.mui-grid-9 {
  border: none;
  background: #fff;
}
.mui-table-view.mui-grid-view.mui-grid-9:after {
  height: 0;
}
.mui-table-view-cell.mui-media.mui-col-xs-4.mui-col-sm-3 {
  border: none;
  background-color: #fff;
}
.mui-icon {
  width: 50px;
  height: 50px;
}
.mui-icon-home:before,
.mui-icon-email:before,
.mui-icon-chatbubble:before,
.mui-icon-location:before,
.mui-icon-search:before,
.mui-icon-phone:before {
  content: '';
}
.mui-icon-home {
  background: url('../../../../static/imgs/1.png') no-repeat center;
  background-size: 100%;
  border-radius: 50%;
}
.mui-icon-email {
  background: url('../../../../static/imgs/2.png') no-repeat center;
  background-size: 100%;
  border-radius: 50%;
}
.mui-icon-chatbubble {
  background: url('../../../../static/imgs/3.png') no-repeat center;
  background-size: 100%;
  border-radius: 50%;
}
.mui-icon-location {
  background: url('../../../../static/imgs/4.png') no-repeat center;
  background-size: 100%;
  border-radius: 50%;
}
.mui-icon-search {
  background: url('../../../../static/imgs/5.png') no-repeat center;
  background-size: 100%;
  border-radius: 50%;
}
.mui-icon-phone {
  background: url('../../../../static/imgs/6.png') no-repeat center;
  background-size: 100%;
  border-radius: 50%;
}

</style>
