<template>
  <div>
    <div style="width: 400px;margin-left: 150%;">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
        <el-form-item label="头像">
          <el-image style="width: 100px; height: 100px;border-radius: 50%;margin-left: 25%" :src="require('../../assets/img/admin.jpg')"  ></el-image>
        </el-form-item>
        <el-form-item label="用户名" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="ruleForm.sex">
            <el-radio :label="1">男</el-radio>
            <el-radio :label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="电话" prop="phoneid">
          <el-input v-model="ruleForm.phoneid"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email" >
          <el-input v-model="ruleForm.email"></el-input>
        </el-form-item>
        <el-form-item label="账户" prop="username">
          <el-input v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="ruleForm.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')" style="margin-left: 40%">添加</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MyInformation',
  data () {
    return {
      photoList: [{ url: '' }],
      dialogImageUrl: '',
      dialogVisible: false,
      id: '',
      ruleForm: {
        name: '',
        sex: '',
        phoneid: '',
        email: '',
        username: '',
        password: '',
        portrait: ''
      },
      rules: {
        name: [
          { required: true, min: 1, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
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
    submitForm (formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post('http://119.29.250.12:8081/admin/insert', _this.ruleForm).then(function (resp) {
            console.log(_this.ruleForm)
            if (resp.data === 'success') {
              _this.$alert('添加成功', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$message({
                    showClose: true,
                    type: 'success',
                    message: `添加成功`
                  })
                  _this.$router.go(0)
                }
              })
            } ;
          })
        } else {
          return false
        }
      })
    }
  },
  created () {
    this.id = this.$route.query.id
  }
}
</script>

<style scoped>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;

  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 120px;
    height: 120px;
    line-height: 120px;
    text-align: center;
    background-color: #00000033;
    margin-left: -150px;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
