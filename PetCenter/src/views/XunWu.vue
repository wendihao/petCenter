<template>
  <div class="blog-form">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="丢失宠动品种" prop="varieties">
        <el-radio-group v-model="ruleForm.varieties" >
          <el-radio-button label="1">猫</el-radio-button>
          <el-radio-button label="0">狗</el-radio-button>
          <el-radio-button label="2">其他</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="性别" prop="petsex">
        <el-radio-group v-model="ruleForm.petsex" style="margin-left: -70px;">
          <el-radio-button label="1">公</el-radio-button>
          <el-radio-button label="0">母</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="照片" style="width: 500px;">
        <div>
          <el-upload
            ref="upload"
            multiple
            list-type="picture"
            :auto-upload="false"
            :http-request="uploadFile"
            action="http://localhost:8081/file/uploads">
            <el-button slot="trigger" size="small" type="primary" style="margin-left: -100px;">选取文件</el-button>
          </el-upload>
        </div>
        <div style="margin-top: 10px; margin-left: -10px;">
          <el-button size="small" type="success" @click="submitUpload" style="margin-left: -70px;">上传到服务器</el-button>
          <div slot="tip" class="el-upload__tip" style="margin-left: -80px;">只能上传jpg/png文件，且不超过500kb</div>
        </div>
      </el-form-item>
      <el-form-item label="年龄" prop="petage">
        <el-input v-model.number="ruleForm.petage"
                  style="width: 100px;margin-left: -100px;"
                  maxlength="2"
                  show-word-limit
                  placeholder="10"
        ></el-input>
      </el-form-item>
      <el-form-item label="丢失地址" prop="address">
        <el-input v-model="ruleForm.address"
                  style="margin-left: 100px"
                  placeholder="xx省xx市xx区"
        ></el-input>
      </el-form-item>
      <el-form-item label="丢失时间" prop="time" class="is-required">
        <el-date-picker v-model="ruleForm.time" style="margin-left: 5%" type="date" placeholder="选择丢失时间" value-format="yyyy-MM-dd">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="详细说明" prop="introduce">
        <el-input
          type="textarea"
          :autosize="{ minRows: 6, maxRows: 10}"
          v-model="ruleForm.introduce"
          style="margin-left: 100px">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="info" round>返回</el-button>
        <el-button type="success" round>保存</el-button>
        <el-button type="primary" @click="onSubmit('ruleForm')" round>发布</el-button>
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
        address: '',
        introduce: '',
        images: '',
        petsex: '',
        petage: '',
        varieties: '',
        schedule: 0,
        success: 0,
        time: '',
        userId: ''
      },
      rules: {
        petsex: [
          { required: true, message: '请选择性别', trigger: 'change' }
        ],
        petage: [
          { required: true, message: '请输入年龄', trigger: 'blur' },
          { type: 'number', message: '年龄必须为数字值' }
        ],
        varieties: [
          { required: true, message: '请输入品种', trigger: 'blur' }
        ],
        introduce: [
          {required: true, message: '请输入内容', trigger: 'blur'}
        ],
        address: [
          {required: true, message: '请输入丢失地址', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    onSubmit (formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post('http://localhost:8081/save2', _this.ruleForm).then(function (resp) {
            window.alert(resp)
            if (resp.data === 'success') {
              _this.$alert('发布成功', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$message({
                    showClose: true,
                    type: 'success',
                    message: `发布成功，正在全力搜查中...`
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
      axios.post('http://localhost:8081/file/uploads', this.formData, config).then(res => {
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
    this.username = this.$route.query.username
    this.ruleForm.userId = this.$route.query.id
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
