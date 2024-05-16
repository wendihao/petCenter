<template>
  <div class="page">

    <div class="header">
      <p>Hello!</p>
      <p>Good to see you here</p>
    </div>

    <div class="main_wrap">
      <img src="src/assets/icons/login.svg" class="login_icon"/>

      <!-- login -->
      <van-form @submit="onSubmit" v-if="type === 'login'" class="form">
        <van-cell-group inset>
          <van-field
            v-model="ruleForm.username"
            name="username"
            label="用户名"
            placeholder="用户名"
            :rules="[{ required: true, message: '请填写用户名' }]"
          />
          <van-field
            v-model="ruleForm.password"
            type="password"
            name="password"
            label="密码"
            placeholder="密码"
            :rules="[{ required: true, message: '请填写密码' }]"
          />
        </van-cell-group>
        <div style="margin: 16px;">
          <van-button round block type="primary" native-type="submit" class="login_btn">登录</van-button>
          <p class="change_btn" @click="changType('register')">没有账号，前往注册</p>
        </div>
      </van-form>

      <!-- register -->
      <van-form @submit="onSubmit" v-else class="form">
        <van-cell-group inset>
          <van-field
            v-model="ruleForm.username"
            name="username"
            label="用户名"
            placeholder="用户名"
            :rules="[{ required: true, message: '请填写用户名' }]"
          />
          <van-field
            v-model="ruleForm.password"
            type="password"
            name="password"
            label="密码"
            placeholder="密码"
            :rules="[{ required: true, message: '请填写密码' }]"
          />
          <!--                <van-field-->
          <!--                    v-model="verify"-->
          <!--                    name="verify"-->
          <!--                    label="验证码"-->
          <!--                    placeholder="验证码"-->
          <!--                    :rules="[{ required: true, message: '请填写验证码' }]"-->
          <!--                > -->
          <!--                    <template #button>-->
          <!--                        <VueImageVerifyVue ref="verifyRef" @sendImgCode="getImgCode"/>-->
          <!--                    </template>-->
          <!--                </van-field>-->
        </van-cell-group>
        <div style="margin: 16px;">
          <van-button round block type="primary" native-type="submit" class="login_btn">注册</van-button>
          <p class="change_btn" @click="changType('login')">已有账号，点击注册</p>
        </div>
      </van-form>
    </div>

  </div>
</template>
<script>
import { reactive, toRefs, ref } from 'vue'
import { useRouter } from 'vue-router'
import VueImageVerifyVue from './components/VueImageVerify.vue'
import { Toast } from 'vant'
export default {
  components: {
    // Header,
    VueImageVerifyVue
  },
  setup () {
    const router = useRouter()
    const verifyRef = ref(null)
    const uStore = userStore() // 仓库的上下文对象
    const state = reactive({
      username: '',
      password: '',
      verify: '',
      imgCode: '',
      type: 'login'
    })
    // 登录或注册信息提交
    const onSubmit = () => {
      if (state.type === 'login') { // 登录按钮
        uStore.loginByUserNameSync(state.username, state.password).then(res => {
          Toast.success(res)
          router.push('/home')
        })
      } else { // 注册按钮
        if (state.verify !== state.imgCode) {
          Toast.fail('验证码错误')
          return
        }
        register({
          username: state.username,
          password: state.password,
          userimg: 'user_001',
          usersign: '今日份营业汪 ! ! !'
        }).then(res => {
          if (res.code) {
            Toast.success(res.mess)
            state.type = 'login'
          }
        })
      }
    }
    // 切换登录注册
    const changType = (type) => {
      state.type = type
    }

    // 获取子组件传递过来的imgCode
    const getImgCode = (val) => {
      state.imgCode = val
      console.log(state.imgCode)
    }

    return {
      ...toRefs(state),
      onSubmit,
      changType,
      verifyRef,
      getImgCode
    }
  }
}
</script>
<style lang="less" scoped>
.page{
  width: 100%;
  height: 100%;
  background-color: black;
  .header{
    width: 100%;
    padding: 20px;
    box-sizing: border-box;
    font-size: 35px;
    color: #fff;
    p:last-child{
      font-size: 18px;
      margin-top: 10px;
    }
  }
  .main_wrap{
    width: 100%;
    height: calc(100% - 200px);
    position: absolute;
    bottom: 0;
    background-color: #fff;
    border-radius: 47px 47px 0 0;
    border-top: 4px solid #683931;
    .login_icon{
      position: absolute;
      bottom: 100%;
      right: 0;
      transform: translate(-12%, 25%);
    }
    .form{
      margin-top: 1rem;
      .login_btn{
        margin-top: 1rem;
        background-color: black;
        border: none;
      }
      .change_btn{
        text-align: center;
        margin: 1rem 0;
        color: black;
        font-size: 14px;
      }
    }
  }
}
</style>
