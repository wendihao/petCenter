<template>
  <div>
    <div style="width: 400px;margin-left: 35%;">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
        <el-form-item label="头像" prop="portrait">
          <el-upload
            action="http://119.29.250.12:8081/file/upload"
            list-type="picture"
            :show-file-list="false"
            :on-remove="handlePreview"
            :on-change="uploadSectionFile"
            :on-success="success"
            :limit="1"
            ref="businessLicense">
            <i class="el-icon-plus"></i>
          </el-upload>
          <el-image style="width: 100px; height: 100px" :src="ruleForm.portrait" ></el-image>
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
          <el-button type="primary" @click="submitForm('ruleForm')">立即修改</el-button>
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
      changepassword: '',
      photoList: [{ url: '' }],
      dialogImageUrl: '',
      dialogVisible: false,
      id: '',
      ruleForm: {
        id: '',
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
    success (response, file) {
      console.log(response)
      if (response.success === true) {
        this.ruleForm.portrait = response.message
        sessionStorage.setItem('imgUrl', this.ruleForm.portrait)
      }
    },
    uploadSectionFile (file) {
      this.ruleForm.portrait = file.url
      var testmsg = file.name.substring(file.name.lastIndexOf('.') + 1)
      const extension =
        testmsg === 'jpg' ||
        testmsg === 'JPG' ||
        testmsg === 'png' ||
        testmsg === 'PNG'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!extension) {
        this.$message.error('上传头像图片只能是 JPG/PNG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return extension && isLt2M
    },
    handlePreview (file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    submitForm (formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (sessionStorage.getItem('imgUrl') !== null) {
            _this.ruleForm.portrait = sessionStorage.getItem('imgUrl')
          }
          this.$axios.post('http://119.29.250.12:8081/user/update', _this.ruleForm).then(function (resp) {
            if (resp.data === 'success') {
              if (_this.ruleForm.username !== _this.$route.query.username || (_this.ruleForm.password !== _this.changepassword)) {
                _this.$router.push('/')
                _this.$message({
                  showClose: true,
                  type: 'warning',
                  message: `你已修改账号密码，请重新登录`
                })
              } else {
                _this.$alert('修改成功', {
                  confirmButtonText: '确定',
                  callback: action => {
                    _this.$message({
                      showClose: true,
                      type: 'success',
                      message: `修改成功`
                    })
                    _this.$router.go(0)
                  }
                })
              }
            } else {
              return false
            }
          })
        }
      })
    }
  },
  created () {
    this.id = this.$route.query.id
    // const query = JSON.parse((JSON.stringify((this.$route.query))))
    // query.username = this.ruleForm.username
    const _this = this
    this.$axios.get('http://119.29.250.12:8081/user/selectbyid/' + _this.$route.query.id).then(function (resp) {
      _this.ruleForm = resp.data
      _this.changepassword = resp.data.password
    })
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
