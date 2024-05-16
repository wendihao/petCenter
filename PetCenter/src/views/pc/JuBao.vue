<template>
  <div class="blog-form">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="举报用户名" prop="username">
        <el-input v-model="ruleForm.username"
                  style="margin-left: 100px"
        ></el-input>
      </el-form-item>
      <el-form-item label="理由" prop="reason">
        <select v-model="ruleForm.reason" style="width: 200px;height: 50px;border: solid 1px lightskyblue;border-radius: 5px">
<!--          <a-select-option :value="1">侮辱谩骂</a-select-option>-->
<!--          <a-select-option :value="2">违法犯罪</a-select-option>-->
<!--          <a-select-option :value="3">不实信息</a-select-option>-->
<!--          <a-select-option :value="4">涉嫌欺诈</a-select-option>-->
<!--          <a-select-option :value="5">其他</a-select-option>-->
          <option value="0">请选择</option>
          <option value="1">侮辱谩骂</option>
          <option value="2">违法犯罪</option>
          <option value="3">不实信息</option>
          <option value="4">涉嫌欺诈</option>
          <option value="5">其他</option>
        </select>
      </el-form-item>
      <el-form-item label="照片" style="width: 500px;">
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
<!--        <img :src="ruleForm.images" alt="请上传图片" style="width: 400px; height: 400px">-->
        <el-image style="width: 200px; height: 200px" :src="ruleForm.images" ></el-image>
      </el-form-item>
      <el-form-item>
        <el-button type="info" round @click="back">重置</el-button>
        <!--        <el-button type="success" round>保存</el-button>-->
        <el-button type="primary" @click="onSubmit('ruleForm')" round>举报</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import axios from 'axios'
import datepicker from 'vuejs-datepicker'
export default {
  components: {
    datepicker
  },
  data () {
    return {
      input: '',
      id: '',
      username: '',
      fileList: [],
      formData: '',
      isVisible: false,
      defaultValue: '',
      ruleForm: {
        username: '',
        reason: '0',
        images: '',
        jubaoId: '',
        jubaousername: ''
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'change' }
        ],
        reason: [
          { required: true, message: '请选择举报理由', trigger: 'blur' }
        ],
        images: [
          { required: true, message: '请上传截图', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    success (response, file) {
      console.log(response)
      if (response.success === true) {
        this.ruleForm.images = response.message
        sessionStorage.setItem('imgUrl', this.ruleForm.images)
      }
    },
    uploadSectionFile (file) {
      this.ruleForm.images = file.url
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
    onSubmit (formName) {
      const _this = this
      _this.ruleForm.images = sessionStorage.getItem('imgUrl')
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post('http://119.29.250.12:8081/jubao/insert', _this.ruleForm).then(function (resp) {
            if (resp.data === 'success') {
              _this.$alert('举报成功', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$message({
                    showClose: true,
                    type: 'success',
                    message: `举报成功，稍后会给您答复，请注意邮箱查收`
                  })
                }
              })
            }
          })
        } else {
          return false
        }
      })
    },
    back () {
      this.ruleForm = {}
    },
    baocun () {
      sessionStorage.setItem('ruleForm', JSON.stringify(this.ruleForm))
      console.log(sessionStorage.getItem('ruleForm'))
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    uploadFile (file) {
      this.formData.append('files', file.file)
    },
    submitUpload () {
      this.formData = new FormData()
      this.$refs.upload.submit()
      this.formData.append('WS_CODE', '12133')
      let config = {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }
      axios.post('http://119.29.250.12:8081/file/uploads', this.formData, config).then(res => {
        this.ruleForm.images = res.data.message
        if (res.data.success === true) {
          this.$message({
            message: '上传成功',
            type: 'success'
          })
        }
      }).catch(reason => {
        console.log(reason)
      })
    }
  },
  created () {
    this.id = this.$route.query.id
    this.ruleForm.jubaoId = this.$route.query.id
    this.ruleForm.jubaousername = this.$route.query.username
    console.log(this.ruleForm.userId)
  }
}
</script>

<style  lang="css">
.input{
  -webkit-appearance: none;
  background-color: #FFF;
  border-radius: 4px;
  border: 1px solid #DCDFE6;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  color: #606266;
  display: inline-block;
  font-size: inherit;
  height: 40px;
  line-height: 40px;
  outline: 0;
  padding: 0 15px;
  -webkit-transition: border-color .2s cubic-bezier(.645,.045,.355,1);
  transition: border-color .2s cubic-bezier(.645,.045,.355,1);
  width: 100%;
}
.blog-form{
  display: grid;
  justify-items: start;
  background-color: white;
  padding: 20px;
  width: 1100px;
  margin-left: 10%;
}
.el-form-item__error {
  color: #F56C6C;
  font-size: 12px;
  line-height: 1;
  padding-top: 4px;
  position: absolute;
  top: 100%;
  margin-left: 100px;
  left: 0;
}
</style>
