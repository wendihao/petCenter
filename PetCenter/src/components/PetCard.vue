<template>
  <div class="card ripple" @click="toDetails" ref="rippleBox">
      <div class="item_img">
          <van-image width="5rem" height="4rem" fit="cover" :src="petItem.petimg" show-loading/>
      </div>
      <div class="item_content">
          <div class="wrap_left">
              <p class="name">{{petItem.petname}}</p>
              <p>{{petItem.petbreed}}（{{petItem.petage}}岁）</p>
          </div>
          <div class="wrap_right">
            <div>
                <i class="iconfont icon-sex" :class="petItem.petsex == '1' ? 'icon-nan' : 'icon-nv'"></i>
            </div>
            <div v-if="petCollect" @click.stop="cardColl(petItem.petid)" class="floatBox" :class="collect ? 'fill' : ''">
                <i class="iconfont icon-xin2 iconColl2" v-if="collect" ></i>
                <i class="iconfont icon-xin2 iconColl1" v-else></i>
            </div>
            <div class="exitCollect" v-else @click.stop="isSheet(petItem.petid)">
                <i class="iconfont icon-diandian" color="#95909c"></i>
            </div>
          </div>
      </div>
  </div>
</template>

<script>
export default {
  props: {
    petItem: {
      type: Object,
      default: {}
    },
    petCollect: {
      type: Boolean,
      default: true
    }
  },
  setup (props, ctx) {
    const uStore = userStore()
    const router = useRouter()
    const state = reactive({
      collect: false // 收藏状态
    })

    uStore.getCollectByPetId(props.petItem.petid).then(res => {
      state.collect = res
    })
    const toDetails = () => { // 跳转宠物详情页面
      const {petid, hostid} = props.petItem
      toRelatedPage('petSpace', router, 500, {petid, hostid})
    }

    const cardColl = id => { // 点击心形收藏改变样式
      uStore.setFondCollectSync(id).then(res => {
        state.collect = res
      })
    }

    const isSheet = id => {
      ctx.emit('open', { type: true, petid: id })
    }

    return {
      ...toRefs(state),
      cardColl,
      toDetails,
      isSheet
    }
  }
}
</script>

<style lang="less" scoped>
.card{
    width: 100%;
    background-color: #fdfdfd;
    border-radius: .2rem;
    box-shadow: rgba(0, 0, 0, 0.08) 0px 4px 12px;
    // position: relative;
    //.button-ripple();

    .item_img{
        height: 4rem;
        display: flex;
        justify-content: center;
        align-items: center;
        overflow: hidden;
        border-radius: .2rem .2rem .6rem .2rem;
        background-color: #fdfdfd;
        .petimg{
            height: 120%;
        }
    }
    .item_content{
        width: 100%;
        padding: .1rem .28rem .3rem;
        box-sizing: border-box;
        display: flex;
        justify-content: space-evenly;
        .wrap_left{
            flex: 1;
            max-width: 70%;
            font-size: .3rem;
            color: #909090;
            line-height: .38rem;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;

            .name{
                font-size: .4rem;
                line-height: .75rem;
                color: #242424;
                font-weight: bold;
            }
        }
        .wrap_right{
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            font-size: .5rem;
            text-align: center;
            .exitCollect{
                transform: rotate(90deg);
                &>i{
                    font-size: .45rem;
                    color: #86909c;
                }
            }
            .icon-sex{
                padding: .1rem;
                border-radius: 50%;
                position: absolute;
                right: 0.2rem;
                top: 0.2rem;
            }
            .icon-nan{
                color: #8db0e5;
                background-color: #e8effa;
            }
            .icon-nv{
                color: #f672e1;
                background-color: #fde3f9;
            }

            /* 放大 */
            @keyframes banuce {
                0% {
                    transform: scale(0.8);
                }
                100% {
                    transform: scale(1.5);
                }
            }
            /* 缩放加透明度变化 */
            @keyframes circle {
                0% {
                    transform: scale(0.2);
                    opacity: 0.8;
                }
                100% {
                    transform: scale(1.5);
                    opacity: 1;
                }
            }
            /* 出现。用opacity来控制元素隐藏显示 */
            @keyframes show {
                0% {
                    opacity: 1;
                }
            }
            .fill{
                position: relative;
                display: inline-block;
                animation: banuce 0.2s ease 0s 2 alternate;
            }
            .fill::before{
                content: "";
                width: .7rem;
                height: .7rem;
                position: absolute;
                left: -.07rem;
                top: -.12rem;
                border-radius: 50%;
                background: rgba(254, 1, 1, 0.1);
                opacity: 0;
                animation: circle 0.3s ease 0.02s 1 alternate;
            }
            .fill::after{
                content: "";
                width: 10%;
                height: 10%;
                position: absolute;
                left: 50%;
                top: 50%;
                transform: translate(-50%, 0%);
                border-radius: 50%;
                background: transparent;
                box-shadow: 15px -15px 0 #ff735e, 15px 15px 0 #ff735e,
                    -15px -15px 0 #ff735e, -15px 15px 0 #ff735e;
                opacity: 0;
                animation: show 0.2s steps(1,end) 0s 1;
            }
            .iconColl1{
                font-size: .5rem;
                color: #d8d8d8;
            }
            .iconColl2{
                font-size: .5rem;
                color: #ff6969;
            }
        }
    }
}
</style>
