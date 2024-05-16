<template>
  <div style="display: flex">
    <div>
      <el-menu
        style="height: 100%;"
        default-active="1"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose">
        <el-menu-item index="1"  @click="myadoption(ruleForm.username)">
          <i class="el-icon-menu"></i>
          <span slot="title">我的收养</span>
        </el-menu-item>
        <el-menu-item index="2"  @click="mysongyang(ruleForm.username)">
          <i class="el-icon-menu"></i>
          <span slot="title">我的送养</span>
        </el-menu-item>
        <el-menu-item index="3"  @click="myzhaoling(ruleForm.username)">
          <i class="el-icon-menu"></i>
          <span slot="title">我的招领</span>
        </el-menu-item>
        <el-menu-item index="4"  @click="myzhaohui(ruleForm.username)">
          <i class="el-icon-menu"></i>
          <span slot="title">我的找回</span>
        </el-menu-item>
      </el-menu>
    </div>
    <div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: 'My',
  data () {
    return {
      imageUrl: '',
      ruleForm: {
        id: '',
        username: ''
      },
      rules: {
        name: [
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        sex: [
          { message: '请选择性别', trigger: 'change' }
        ],
        address: [
          { message: '请输入住址', trigger: 'blur' }
        ],
        phoneId: [
          { message: '请输入电话', trigger: 'blur' },
          { min: 11, max: 11, message: '电话格式不正确', trigger: 'blur' }
        ],
        email: [
          { message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ],
        username: [
          { message: '请输入账户', trigger: 'blur' },
          { min: 5, message: '请输入不少于5个的账户', trigger: 'blur' }
        ],
        password: [
          { message: '请输入密码', trigger: 'blur' },
          { min: 6, message: '请输入不少于6个的密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    handleAvatarSuccess (res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    },
    myadoption () {
      this.$router.push({
        path: '/myadoption',
        query: {
          id: this.ruleForm.id,
          username: this.ruleForm.username
        }
      })
    },
    mysongyang (username) {
      this.$router.push({
        path: '/mysongyang',
        query: {
          username: username,
          id: this.ruleForm.id
        }
      })
    },
    myzhaoling () {
      this.$router.push({
        path: '/myzhaoling',
        query: {
          id: this.ruleForm.id,
          username: this.ruleForm.username
        }
      })
    },
    myzhaohui () {
      this.$router.push({
        path: '/myzhaohui',
        query: {
          id: this.ruleForm.id,
          username: this.ruleForm.username
        }
      })
    }
  },
  created () {
    this.ruleForm.username = this.$route.query.username
    this.ruleForm.id = this.$route.query.id
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
