<template>
    <div class="registerbody">
      <div style="">
        <div class="registertext" style="margin-left: 3rem">
          <h2>号码邮箱登录</h2>
        </div>
        <div style="width: 100%">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="2rem" style="margin-top: 5%">
            <el-form-item label="电话" prop="phoneid"  style="width: 80%">
              <el-input v-model="ruleForm.phoneid"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email"  style="width: 80%" >
              <el-input v-model="ruleForm.email"></el-input>
            </el-form-item>
            <el-form-item label="账户" prop="username"  style="width: 80%">
              <el-input v-model="ruleForm.username"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="info" @click="back">返回</el-button>
              <el-button type="primary" @click="onSubmit('ruleForm')">登录</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
</template>

<script>
export default {
  data () {
    return {
      ruleForm: {
        id: '',
        name: '',
        phoneid: '',
        email: '',
        username: ''
      },
      rules: {
        phoneid: [
          { required: true, message: '请输入电话', trigger: 'blur' },
          { min: 11, max: 11, message: '电话格式不正确', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ],
        username: [
          { required: true, message: '请输入账户', trigger: 'blur' },
          { min: 5, message: '请输入不少于5个的账户', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    back () {
      this.$router.push('/loginM')
    },
    onSubmit (formName) {
      const _this = this
      this.$axios.get('http://119.29.250.12:8081/user/select/' + this.ruleForm.username).then(function (resp) {
        _this.ruleForm.id = resp.data.id
      })
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.get('http://119.29.250.12:8081/login1/' + _this.ruleForm.phoneid + '/' + _this.ruleForm.email + '/' + _this.ruleForm.username).then(function (resp) {
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
                  username: _this.ruleForm.username}
              })
            }
            if (resp.data === 405) {
              _this.$message(
                {showClose: true,
                  message: '手机或邮箱错误',
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
  .registerbody {
    width: 100%;
    height: 100%;
    background-color: #00000033;
    background-size: 100% 100%;
    background-position: center center;
    overflow: auto;
    background-repeat: no-repeat;
    position: fixed;
    line-height: 100%;
    padding-top: 150px;
  }
  .registertext {
    font-size: 30px;
    font-weight: bolder;
    color: white;
    text-shadow: 2px 2px 4px #000000;
  }
</style>
