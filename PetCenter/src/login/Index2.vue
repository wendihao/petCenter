<template>
  <div class="page">

    <div class="header">
      <p>Hello!</p>
      <p>Good to see you here</p>
    </div>

    <div class="main_wrap">
      <img src="@/assets/icons/login.svg" class="login_icon"/>
      <!-- login -->
      <van-form @submit="onSubmit('ruleForm') " class="form"  v-if="type === 'login'">
        <van-cell-group inset>
          <van-field
            v-model="ruleForm.username"
            name="username"
            label="用户名"
            placeholder="用户名"
            :rules="[{ required: true, message: '请填写用户名' }]"
          />
        </van-cell-group>
        <van-cell-group inset>
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
          <p class="change_btn">没有账号，前往注册</p>
        </div>
      </van-form>

      <!-- register -->
      <van-form @submit="onSubmit"  v-else class="form">
        <van-cell inset>
          <van-field
            v-model="ruleForm.username"
            name="username"
            label="用户名"
            placeholder="用户名"
          />
          <van-field
            v-model="ruleForm.password"
            type="password"
            name="password"
            label="密码"
            placeholder="密码"
          />
<!--          <van-field-->
<!--            v-model="verify"-->
<!--            name="verify"-->
<!--            label="验证码"-->
<!--            placeholder="验证码"-->
<!--            :rules="[{ required: true, message: '请填写验证码' }]"-->
<!--          >-->
<!--            <template #button>-->
<!--&lt;!&ndash;              <VueImageVerifyVue ref="verifyRef" @sendImgCode="getImgCode"/>&ndash;&gt;-->
<!--            </template>-->
<!--          </van-field>-->
        </van-cell>
        <div style="margin: 16px;">
          <van-button round block type="primary" native-type="submit" class="login_btn">注册</van-button>
          <p class="change_btn">已有账号，点击注册</p>
        </div>
      </van-form>
    </div>
  </div>
</template>
<script>
// import { Toast } from 'vant'
// import VueImageVerifyVue from './components/VueImageVerify.vue'
// import { reactive, toRefs, ref } from 'vue'
// import { useRouter } from 'vue-router'
// import VueImageVerifyVue from './components/VueImageVerify.vue'
// import { login, register } from '@/axios/index.js';
// import { Toast } from 'vant'
// import { userStore } from '@/store/user.js';
export default {
  name: 'Index2',
  data () {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass')
        }
        callback()
      }
    }
    return {
      type: 'login',
      ruleForm: {
        id: '',
        username: '',
        password: ''
      },
      rules: {
        password: [
          {validator: validatePass, trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    onSubmit (formName) {
      const _this = this
      this.$axios.get('http://119.29.250.12:8081/user/select/' + this.ruleForm.username).then(function (resp) {
        _this.ruleForm.id = resp.data.id
      })
      // this.$refs[formName].validate((valid) => {
      //   window.alert('1111')
      //   if (valid) {
      //     window.alert(_this.ruleForm.username)
      //     window.alert(_this.ruleForm.password)
      this.$axios.get('http://119.29.250.12:8081/login/' + _this.ruleForm.username + '/' + _this.ruleForm.password).then(function (resp) {
        console.log(resp)
        if (resp.data === 200) {
          var userInfo = resp.data
          _this.$store.commit('set_Token', userInfo)
          console.log('token:' + _this.$store.state.token)
          _this.$message({
            message: '登录成功',
            type: 'success'
          })
          _this.$router.push({
            path: '/',
            query: {
              id: _this.ruleForm.id,
              username: _this.ruleForm.username
            }
          })
        }
        ;
        if (resp.data === 401) {
          _this.$message({
            showClose: true,
            message: '你已被封号',
            type: 'warning'
          })
        }
        ;
        if (resp.data === 405) {
          _this.$message({
            showClose: true,
            message: '密码错误',
            type: 'warning'
          })
        }
        ;
        if (resp.data === 404) {
          _this.$message({
            showClose: true,
            message: '账户错误',
            type: 'warning'
          })
        } else {
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  }
  // components: {
  //   // Header,
  //   VueImageVerifyVue
  // },
  // setup () {
  //   const router = useRouter() // 得到路由实例
  //   const verifyRef = ref(null)
  //   const uStore = userStore() // 仓库的上下文对象
  //   const state = reactive({
  //     username: '',
  //     password: '',
  //     verify: '',
  //     imgCode: '',
  //     type: 'login'
  //   })
  //
  //   // 登录或注册信息提交
  //   const onSubmit = () => {
  //     if (state.type === 'login') { // 登录按钮
  //       uStore.loginByUserNameSync(state.username, state.password).then(res => {
  //         Toast.success(res)
  //         router.push('/home')
  //       })
  //     } else { // 注册按钮
  //       if (state.verify !== state.imgCode) {
  //         Toast.fail('验证码错误')
  //         return
  //       }
  //       register({
  //         username: state.username,
  //         password: state.password,
  //         userimg: 'user_001',
  //         usersign: '今日份营业汪 ! ! !'
  //       }).then(res => {
  //         if (res.code) {
  //           Toast.success(res.mess)
  //           state.type = 'login'
  //         }
  //       })
  //     }
  //   }
  //
  //   // 切换登录注册
  //   const changType = (type) => {
  //     state.type = type
  //   }
  //
  //   // 获取子组件传递过来的imgCode
  //   const getImgCode = (val) => {
  //     state.imgCode = val
  //     console.log(state.imgCode)
  //   }
  //
  //   return {
  //     ...toRefs(state),
  //     onSubmit,
  //     changType,
  //     verifyRef,
  //     getImgCode
  //   }
  // }
}
</script>
<style lang="less" scoped>
.page{
  width: 100%;
  height: 100%;
  background-color: #8d9f5e;
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
        background-color: #8d9f5e;
        border: none;
      }
      .change_btn{
        text-align: center;
        margin: 1rem 0;
        color: #8d9f5e;
        font-size: 14px;
      }
    }
  }
}
</style>
