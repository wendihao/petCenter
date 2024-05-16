<template>
  <section class="profile">
    <!-- 顶部标题-开始 -->
    <!-- 顶部标题-结束 -->
    <section class="profile-number" @click="goTo('/loginM')">
      <a href="javascript:" class="profile-link">
        <div style="width: 2rem;height: 2rem;margin-bottom: 0.2rem" v-if="this.$store.state.token===''||this.user.portrait===null||this.$store.state.token=== undefined">
          <img :src="this.circleUrl" alt="touxiang" >
        </div>
        <div class="profile_image" style="width: 2rem;height: 2rem;margin-bottom: 0.2rem" @click="removemyiformation"  v-if="this.$store.state.token!==''&&this.$store.state.token!== undefined&&this.user.portrait!==null">
          <i class="iconfont icon-person" ></i>
          <img :src="user.portrait"
            alt=""
            class="user-pic"
          />
        </div>
        <div v-if="this.$store.state.token!==''" class="user-info">
          <p class="user-info-top" style="font-size: 0.5rem;color: black ;margin-top: 25%">{{user.username}}</p>
        </div>
        <div v-if="this.$store.state.token===''||this.$store.state.token==='undefined'" class="user-info">
          <p class="user-info-top" @click="removelogin" >登录/注册</p>
          <p class="user-info-top" >{{'暂无昵称' }}</p>
<!--          <p>-->
<!--                  <span class="user-icon">-->
<!--                     <i class="iconfont icon-shouji icon-mobile"></i>-->
<!--                  </span>-->
<!--            <span class="icon-mobile-number" >请先登录</span>-->
<!--            <span class="icon-mobile-number" >{{'待完善'}}</span>-->
<!--          </p>-->
        </div>
        <span class="arrow">
               <i class="iconfont icon-jiantou1"></i>
            </span>
      </a>
    </section>
    <section class="profile_info_data border-1px" style="border: solid 1px white;border-radius: 0.5rem 0.5rem 0 0">
      <ul class="info_data_list" style="margin-bottom: 0" v-if="this.$store.state.token!==''">
        <router-link :to="{name:'MyAdoptionM',query :{id:user.id,username:user.username}}" class="info_data_link">
          <span class="info_data_top"><span>{{num1}}</span>个</span>
          <span class="info_data_bottom">我的收养</span>
        </router-link>
        <router-link :to="{name:'MySongYangM',query :{id:user.id,username:user.username}}" class="info_data_link">
          <span class="info_data_top"><span>{{num2}}</span>个</span>
          <span class="info_data_bottom">我的送养</span>
        </router-link>
          <router-link :to="{name:'MyZhaoLingM',query :{id:user.id,username:user.username}}" class="info_data_link">
          <span class="info_data_top"><span>{{num3}}</span>个</span>
          <span class="info_data_bottom">我的招领</span>
          </router-link>
            <router-link :to="{name:'MyZhaoHuiM',query :{id:user.id,username:user.username}}" class="info_data_link">
                    <span class="info_data_top"><span>{{num4}}</span>个</span>
          <span class="info_data_bottom">我的找回</span>
            </router-link>
      </ul>
    </section>
    <section class="profile_my_order border-1px" style="height: 100%">
      <!-- 我的订单 -->
      <router-link :to="{name:'XunWuM',query :{id:user.id,username:user.username}}" class="my_order"  v-if="this.$store.state.token!==''">
        <div class="my_order_div">
          <span>发布寻物启事</span>
          <span class="my_order_icon">
                  <i class="iconfont icon-jiantou1"></i>
               </span>
        </div>
        <p style="font-size: 0.5rem; margin-right: 5%"> ></p>
      </router-link>
      <!-- 积分商城 -->
            <span>
               <i class="iconfont icon-jifen"></i>
            </span>
      <router-link :to="{name:'ZhaoLingM',query :{id:user.id,username:user.username}}" class="my_order"  v-if="this.$store.state.token!==''">
        <div class="my_order_div">
          <span>发布招领</span>
          <span class="my_order_icon">
                  <i class="iconfont icon-jiantou1"></i>
               </span>
        </div>
        <p style="font-size: 0.5rem; margin-right: 5%"> ></p>
      </router-link>
            <span>
               <i class="iconfont icon-vip"></i>
            </span>
      <router-link :to="{name:'SongYangM',query :{id:user.id,username:user.username}}" class="my_order"  v-if="this.$store.state.token!==''">
        <div class="my_order_div">
          <span>发布送养</span>
          <span class="my_order_icon">
                  <i class="iconfont icon-jiantou1"></i>
               </span>
        </div>
        <p style="font-size: 0.5rem; margin-right: 5%"> ></p>
      </router-link>
            <span>
               <i class="iconfont icon-order-s"></i>
            </span>
      <router-link :to="{name:'FenXiangM',query :{id:user.id,username:user.username}}" class="my_order"  v-if="this.$store.state.token!==''">
        <div class="my_order_div">
          <span>发布分享</span>
          <span class="my_order_icon">
                  <i class="iconfont icon-jiantou1"></i>
               </span>
        </div>
        <p style="font-size: 0.5rem; margin-right: 5%"> ></p>
      </router-link>
      <!-- 积分商城 -->
            <span>
               <i class="iconfont icon-jifen"></i>
            </span>
      <router-link :to="{name:'MyInformationM',query :{id:user.id,username:user.username}}" class="my_order"  v-if="this.$store.state.token!==''">
        <div class="my_order_div">
          <span>个人信息管理</span>
          <span class="my_order_icon">
                  <i class="iconfont icon-jiantou1"></i>
               </span>
        </div>
        <p style="font-size: 0.5rem; margin-right: 5%"> ></p>
      </router-link>
      <!-- 起点外卖会员卡 -->
            <span>
               <i class="iconfont icon-vip"></i>
            </span>
      <router-link :to="{name:'MyFenXiangManageM',query :{id:user.id,username:user.username}}" class="my_order"  v-if="this.$store.state.token!==''">
        <div class="my_order_div">
          <span>宠物圈管理</span>
          <span class="my_order_icon">
                  <i class="iconfont icon-jiantou1"></i>
               </span>
        </div>
        <p style="font-size: 0.5rem; margin-right: 5%"> ></p>
      </router-link>
            <span>
               <i class="iconfont icon-vip"></i>
            </span>
      <router-link :to="{name:'MessageManageM',query :{id:user.id,username:user.username}}" class="my_order"  v-if="this.$store.state.token!==''">
        <div class="my_order_div">
          <span>领养管理</span>
          <span class="my_order_icon">
                  <i class="iconfont icon-jiantou1"></i>
               </span>
        </div>
        <p style="font-size: 0.5rem; margin-right: 5%"> ></p>
      </router-link>
            <span>
               <i class="iconfont icon-vip"></i>
            </span>
      <router-link :to="{name:'MessageManage1M',query :{id:user.id,username:user.username}}" class="my_order"  v-if="this.$store.state.token!==''">
        <div class="my_order_div">
          <span>招领管理</span>
          <span class="my_order_icon">
                  <i class="iconfont icon-jiantou1"></i>
               </span>
        </div>
        <p style="font-size: 0.5rem; margin-right: 5%"> ></p>
      </router-link>
            <span>
               <i class="iconfont icon-vip"></i>
            </span>
      <router-link :to="{name:'MyMessageManageM',query :{id:user.id,username:user.username}}" class="my_order"  v-if="this.$store.state.token!==''">
        <div class="my_order_div">
          <span>上传信息管理</span>
          <span class="my_order_icon">
                  <i class="iconfont icon-jiantou1"></i>
               </span>
        </div>
        <p style="font-size: 0.5rem; margin-right: 5%"> ></p>
      </router-link>
            <span>
               <i class="iconfont icon-vip"></i>
            </span>
      <router-link :to="{name:'loginM'}" class="my_order">
        <div class="my_order_div">
          <span>退出</span>
          <span class="my_order_icon">
                  <i class="iconfont icon-jiantou1"></i>
               </span>
        </div>
        <p style="font-size: 0.5rem; margin-right: 5%"> ></p>
      </router-link>
    </section>
<!--    <section class="profile_my_order border-1px">-->
<!--      &lt;!&ndash; 服务中心 &ndash;&gt;-->
<!--      <a href="javascript:" class="my_order">-->
<!--            <span>-->
<!--               <i class="iconfont icon-fuwu"></i>-->
<!--            </span>-->
<!--        <div class="my_order_div">-->
<!--          <span>服务中心</span>-->
<!--          <span class="my_order_icon">-->
<!--                  <i class="iconfont icon-jiantou1"></i>-->
<!--               </span>-->
<!--        </div>-->
<!--      </a>-->
<!--    </section>-->
    <!-- 退出登陆 -->
  </section>
</template>
<script>
import '../../../../static/stylus/mixins.styl'
export default {
  name: 'Profile',
  data () {
    return {
      circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      num1: 0,
      num2: 0,
      num3: 0,
      num4: 0,
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
    removemyiformation () {
      this.$router.push({
        name: 'MyInformationM',
        query: {
          id: this.user.id,
          username: this.user.username
        }
      })
    },
    removelogin () {
      this.$router.push({
        name: 'loginM'
      })
    },
    /* 去登录 */
    goTo () {
      // 未登录处理的罗辑
      if (!this.userInfo.username) return this.$router.replace('/loginM')
      // 已登录处理的罗辑
      this.$router.push('/personal')
    }
  },
  created () {
    const _this = this
    this.$axios.get('http://119.29.250.12:8081/user/select/' + _this.$route.query.username).then(function (resp) {
      _this.user = resp.data
    })
    this.$axios.get('http://119.29.250.12:8081/getadoptionnum/' + _this.$route.query.id).then(function (resp) {
      _this.num1 = resp.data
    })
    this.$axios.get('http://119.29.250.12:8081/getsongyangnum/' + _this.$route.query.id).then(function (resp) {
      _this.num2 = resp.data
    })
    this.$axios.get('http://119.29.250.12:8081/getzhaolingnum/' + _this.$route.query.id).then(function (resp) {
      _this.num3 = resp.data
    })
    this.$axios.get('http://119.29.250.12:8081/getzhaohuinum/' + _this.$route.query.id).then(function (resp) {
      _this.num4 = resp.data
    })
  }
}
</script>
<style scoped lang="stylus">
.profile {
  width: 100vw;
  height: 100vh;
  box-sizing: border-box;
  background-color greenyellow
}
.user-pic {
  width: 100%;
  height: 100%;
}

&.profile { // 我的
  width: 100%;

  .profile-number {
    .profile-link {
      clearFix();
      position: relative;
      display: block;
      background: var(--theme) var(--bgimg);
      background-image: cover;
      padding: 19px 10px 20px;
      margin-top: 1px;

      .profile_image {
        float: left;
        width: 60px;
        height: 60px;
        border-radius: 50%;
        overflow: hidden;
        vertical-align: top;

        .icon-person {
          background: #e4e4e4;
          font-size: 62px;
        }
      }

      .user-info {
        float: left;
        margin-top: 8px;
        margin-left: 15px;

        p {
          font-weight: 700;
          font-size: 18px;
          color: #fff;
          position relative;

          &.user-info-top {
            padding-bottom: 8px;
          }

          .user-icon {
            display: inline-block;
            margin-left: -15px;
            margin-right: 5px;
            width: 20px;
            height: 20px;

            .icon-mobile {
              font-size: 30px;
              vertical-align: text-top;
            }
          }

          .icon-mobile-number {
            font-size: 14px;
            color: #fff;
            position absolute;
            transform: translate(5%, 50%);
            margin-left: 2px;
          }
        }
      }

      .arrow {
        width: 12px;
        height: 12px;
        position: absolute;
        right: 15px;
        top: 40%;

        .icon-jiantou1 {
          color: #fff;
          font-size: 5px;
        }
      }
    }
  }

  .profile_info_data {
    bottom-border-1px(#e4e4e4);
    width: 100%;
    background: #fff;
    overflow: hidden;

    .info_data_list {
      clearFix();

      .info_data_link {
        float: left;
        width: 25%;
        text-align: center;
        border-right: 1px solid #f1f1f1;

        .info_data_top {
          display: block;
          width: 100%;
          font-size: 14px;
          color: #333;
          padding: 15px 5px 10px;

          span {
            display: inline-block;
            font-size: 30px;
            color: #f90;
            font-weight: 700;
            line-height: 30px;
          }
        }

        .info_data_bottom {
          display: inline-block;
          font-size: 14px;
          color: #666;
          font-weight: 400;
          padding-bottom: 10px;
        }
      }

      .info_data_link:nth-of-type(2) {
        .info_data_top {
          span {
            color: #ff5f3e;
          }
        }
      }

      .info_data_link:nth-of-type(3) {
        border: 0;

        .info_data_top {
          span {
            color: #6ac20b;
          }
        }
      }
    }
  }

  .profile_my_order {
    top-border-1px(#e4e4e4);
    margin-top: 0;
    background: #fff;

    .my_order {
      display: flex;
      align-items: center;
      padding-left: 15px;

      >span {
        display: flex;
        align-items: center;
        width: 20px;
        height: 20px;

        >.iconfont {
          margin-left: -10px;
          font-size: 30px;
        }

        .icon-order-s {
          color: #02a774;
        }

        .icon-jifen {
          color: #ff5f3e;
        }

        .icon-vip {
          color: #f90;
        }

        .icon-fuwu {
          color: #02a774;
        }
      }

      .my_order_div {
        width: 100%;
        border-bottom: 1px solid #f1f1f1;
        padding: 18px 10px 18px 0;
        font-size: 16px;
        color: #333;
        display: flex;
        justify-content: space-between;

        span {
          display: block;
        }

        .my_order_icon {
          width: 10px;
          height: 10px;

          .icon-jiantou1 {
            color: #bbb;
            font-size: 10px;
          }
        }
      }
    }
  }
}
</style>
