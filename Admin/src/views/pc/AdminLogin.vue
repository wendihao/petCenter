<template>
  <div class="loginbody">
    <div class="logindata">
      <div class="logintext" style="margin-left: 84px;"><h2>管理员登录</h2></div>
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="账 户" prop="username">
          <el-input v-model="ruleForm.username" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item label="密 码" prop="password">
          <el-input type="password" v-model="ruleForm.password" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item style="margin-top: -20px;margin-left: 60px;">
          <el-button @click="resetForm('ruleForm')" round="">重置</el-button>
          <el-button type="primary" @click="onSubmit('ruleForm')" round="">提交</el-button>
        </el-form-item>
      </el-form>
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
          this.$axios.get('http://119.29.250.12:8081/adminlogin/' + _this.ruleForm.username + '/' + _this.ruleForm.password).then(function (resp) {
            console.log(resp)
            if (resp.data === 200) {
              var userInfo = resp.data
              _this.$store.commit('set_Token', userInfo)
              console.log('token:' + _this.$store.state.token)
              _this.$message({
                message: '管理员登录成功',
                type: 'success'
              })
              _this.$router.push({
                path: '/admin',
                query: {
                  id: _this.ruleForm.id,
                  username: _this.ruleForm.username
                }
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
    min-width: 1000px;
    background-color: #00000033;
    background-size: 100% 100%;
    background-position: center center;
    overflow: auto;
    background-repeat: no-repeat;
    position: fixed;
    line-height: 100%;
    padding-top: 150px;
  }

  .logintext {
    margin-bottom: 20px;
    line-height: 50px;
    text-align: center;
    font-size: 30px;
    font-weight: bolder;
    color: white;
    text-shadow: 2px 2px 4px #000000;
  }

  .logindata {
    width: 400px;
    height: 300px;
    transform: translate(-50%);
    margin-left: 47%;
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
