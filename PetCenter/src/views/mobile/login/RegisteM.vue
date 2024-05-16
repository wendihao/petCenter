<template>
    <div class="registerbody">
      <div style="">
        <div class="registertext" style="margin-left: 3rem">
          <h2>账户注册</h2>
        </div>
        <div style="width: 100%">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="2rem" style="margin-top: 5%">
            <el-form-item label="用户名" prop="name" style="width: 80%">
              <el-input v-model="ruleForm.name" ></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="sex" style="width: 80%">
              <el-radio-group v-model="ruleForm.sex">
                <el-radio label="1">男</el-radio>
                <el-radio label="0">女</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="电话" prop="phoneid" style="width: 80%">
              <el-input v-model="ruleForm.phoneid"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email" style="width: 80%">
              <el-input v-model="ruleForm.email"></el-input>
            </el-form-item>
            <el-form-item label="账户" prop="username" style="width: 80%">
              <el-input v-model="ruleForm.username"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password" style="width: 80%">
              <el-input v-model="ruleForm.password"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="info" @click="back">返回</el-button>
              <el-button type="primary" @click="onSubmit('ruleForm')">立即创建</el-button>
              <el-button @click="resetForm('ruleForm')">重置</el-button>
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
        name: '',
        sex: '',
        phoneid: '',
        email: '',
        username: '',
        password: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入用户名称', trigger: 'blur' },
          { min: 1, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        sex: [
          { required: true, message: '请选择性别', trigger: 'change' }
        ],
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
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, message: '请输入不少于6个的密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    back () {
      this.$router.push('/')
    },
    onSubmit (formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        console.log(_this.ruleForm)
        if (valid) {
          this.$axios.post('http://119.29.250.12:8081/user/save', _this.ruleForm).then(function (resp) {
            console.log(resp)
            if (resp.data === 'success') {
              _this.$alert(_this.ruleForm.name + '账户,添加成功', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$message({
                    showClose: true,
                    type: 'success',
                    message: `添加成功`
                  })
                }
              })
              _this.$router.push('/')
            } else if (resp.data === 'error') {
              _this.$message({
                message: '账户名已存在，请更换账户名',
                type: 'warning'
              })
            } else {
              _this.$message({
                message: '添加失败',
                type: 'warning'
              })
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
    padding-top: 2rem;
  }
  .registertext {
    font-size: 30px;
    font-weight: bolder;
    color: white;
    text-shadow: 2px 2px 4px #000000;
  }
</style>
