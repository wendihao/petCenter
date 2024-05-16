<template>
  <div class="loginbody">
    <div class="logindata">
      <div class="main_wrap">
        <div class="logintext" style=""><h2>Welcome</h2></div>
      <img src="../../../assets/icons/login.svg" class="login_icon"/>
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="2rem" class="demo-ruleForm">
        <el-form-item label="账 户" prop="username">
          <el-input v-model="ruleForm.username" autocomplete="off" clearable style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="密 码" prop="password">
          <el-input type="password" v-model="ruleForm.password" autocomplete="off" clearable style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item style="">
          <router-link :to="{path: '/login2M'}"><el-link type="warning" :underline="false">忘记密码</el-link></router-link>
          <router-link :to="{path: '/registerM'}"><el-link style="margin-left: 45%" type="primary" :underline="false">用户注册</el-link></router-link>
        </el-form-item>
        <el-form-item style="">
<!--          <el-button @click="resetForm('ruleForm')" round="">重置</el-button>-->
          <el-button type="primary" style="margin-left: 25%" @click="onSubmit('ruleForm')" round="">登录</el-button>
        </el-form-item>
      </el-form>
      </div>
    </div>
  </div>
</template>

<script>

export default {
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
      this.$refs[formName].validate((valid) => {
        if (valid) {
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
                name: 'IndexM',
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
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>
<style scoped>
.loginbody {
  width: 100%;
  height: 100%;
  background-color: white;
  background-size: 100% 100%;
  background-position: center center;
  overflow: auto;
  background-repeat: no-repeat;
  position: fixed;
  line-height: 100%;
}

.logintext {
  margin-bottom: 0.5rem;
  line-height: 1rem;
  text-align: center;
  font-size: 0.5rem;
  font-weight: bolder;
  color: white;
  text-shadow: 2px 2px 4px #000000;
}

.logindata{
  margin-top: -45%;
  width: 100%;
  height: 50%;
  transform: translate(-50%);
  margin-left: 50%;
}

.tool {
  display: flex;
  justify-content: space-between;
  color: #606266;
}

.butt {
  margin-top: 10px;
  text-align: center;
}

.shou {
  cursor: pointer;
  color: #606266;
}
.main_wrap {
  width: 100%;
  height: calc(50%);
  position: absolute;
  bottom: 0;
  background-color: lightgreen;
  border-radius: 47px 47px 0 0;
  border-top: 4px solid #683931;
}

/*ui*/
/* /deep/ .el-form-item__label {
  font-weight: bolder;
  font-size: 15px;
  text-align: left;
}

/deep/ .el-button {
  width: 100%;
  margin-bottom: 10px;

} */
</style>
