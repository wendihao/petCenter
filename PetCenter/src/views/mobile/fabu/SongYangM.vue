<template>
  <div class="" style="width: 100%;margin-top: 0.2rem">
    <div style="background-color: white;padding: 0.1rem;width: 100%">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item style="margin-top: 5%;padding-bottom: 3%"><a @click="back1()" style="font-size: 15px; ">{{this.return}}返回</a></el-breadcrumb-item>
      </el-breadcrumb>
      <h1 style="margin-left: 40%;margin-top: -5%;font-size: 15px;font-weight: bolder">发布送养</h1>
      <hr style="margin-bottom: -1%;width: 100%">
    </div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="2rem" class="demo-ruleForm" style="margin-top: 5%">
      <el-form-item label="宠物品种" prop="varieties">
        <el-radio-group v-model="ruleForm.varieties" style="margin-left: 5%">
          <el-radio-button label="1">猫</el-radio-button>
          <el-radio-button label="0">狗</el-radio-button>
          <el-radio-button label="2">其他</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="宠物性别" prop="petsex">
        <el-radio-group v-model="ruleForm.petsex" style="margin-left: 5%">
          <el-radio-button label="1">公</el-radio-button>
          <el-radio-button label="0">母</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="是否免费" prop="free">
        <el-radio-group v-model="ruleForm.free" style="margin-left: 5%">
          <el-radio-button label="1">是</el-radio-button>
          <el-radio-button label="0">否</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="宠物相册" style="margin-left: 5%">
        <div>
          <el-upload
            ref="upload"
            multiple
            list-type="picture"
            :auto-upload="false"
            :http-request="uploadFile"
            action="http://119.29.250.12:8081/file/uploads">
            <el-button slot="trigger" size="small" type="primary" style="">选取文件</el-button>
          </el-upload>
        </div>
        <div style="margin-top: 10px; margin-left: -10px;">
          <el-button size="small" type="success" @click="submitUpload" style="margin-left: 5%">上传到服务器</el-button>
          <div slot="tip" class="el-upload__tip" style="">只能上传jpg/png文件，且不超过500kb</div>
        </div>
      </el-form-item>
      <el-form-item label="宠物年龄" prop="petage">
        <el-input v-model.number="ruleForm.petage"
                  style="width:2rem;margin-left: 5%;"
                  maxlength="2"
                  show-word-limit
                  placeholder="10"
        ></el-input>
      </el-form-item>
      <el-form-item label="宠物名字" prop="petname">
        <el-input v-model="ruleForm.petname"
                  style="width:2rem;margin-left: 5%;"
                  maxlength="5"
                  show-word-limit
                  placeholder="小白"
        ></el-input>
      </el-form-item>
      <el-form-item label="领养地址" prop="address">
        <el-input v-model="ruleForm.address"
                  style="margin-left: 5%;width: 6rem"
                  placeholder="xx省xx市xx区"
        ></el-input>
      </el-form-item>
      <el-form-item label="领养标题" prop="title">
        <el-input v-model="ruleForm.title"
                  style="margin-left: 5%;width: 6rem"
                  placeholder="领养标题"
        ></el-input>
      </el-form-item>
      <el-form-item label="宠物简介" prop="introduce">
        <el-input
          type="textarea"
          :autosize="{ minRows: 5, maxRows: 10}"
          v-model="ruleForm.introduce"
          style="margin-left: 5%;width:6rem">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="info" round @click="back" style="margin-left: 15%">重置</el-button>
        <!--        <el-button type="success" round>保存</el-button>-->
        <el-button type="primary" style="margin-left: 50%;margin-top: -1rem" @click="onSubmit('ruleForm')" round>发布</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data () {
    return {
      return: '<',
      input: '',
      id: '',
      username: '',
      fileList: [],
      formData: '',
      ruleForm: {
        title: '',
        address: '',
        introduce: '',
        images: '',
        petname: '',
        petsex: '1',
        petage: '',
        varieties: '1',
        free: '1',
        userId: ''
      },
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' },
          { min: 3, message: '标题不少于3个字', trigger: 'blur' }
        ],
        petsex: [
          { required: true, message: '请选择性别', trigger: 'change' }
        ],
        petage: [
          { required: true, message: '请输入年龄', trigger: 'blur' },
          { type: 'number', message: '年龄必须为数字值' }
        ],
        petname: [
          { required: true, message: '请输入名字', trigger: 'blur' }
        ],
        varieties: [
          { required: true, message: '请输入品种', trigger: 'blur' }
        ],
        free: [
          { required: true, message: '请选择', trigger: 'change' }
        ],
        introduce: [
          {required: true, message: '请输入内容', trigger: 'blur'}
        ],
        address: [
          {required: true, message: '请输入领养地址', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    back1 () {
      this.$router.go(-1)
    },
    onSubmit (formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post('http://119.29.250.12:8081/save', _this.ruleForm).then(function (resp) {
            if (resp.data === 'success') {
              _this.$alert('《' + _this.ruleForm.title + '》', '添加成功', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$message({
                    showClose: true,
                    type: 'success',
                    message: `添加成功`
                  })
                }
              })
            }
            this.$router.go(0)
          })
        } else {
          return false
        }
      })
    },
    back () {
      this.ruleForm = {}
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
  /*display: grid;*/
  /*justify-items: start;*/
  /*background-color: white;*/
  /*width: 100%;*/
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
