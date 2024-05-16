<template>
  <div class="contact-admin" style="margin-left: 25%">
    <h1 style="display: block;
  font-size: 2em;
  margin-block-start: 0.67em;
  margin-block-end: 0.67em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  font-weight: bold;margin-left:15%">Contact Administrator</h1>
<!--    <form class="contact-form" @submit="submitForm">-->
<!--      <div class="form-group">-->
<!--        <label for="name">Name</label>-->
<!--        <input type="text" id="name" v-model="name" required>-->
<!--      </div>-->
<!--      <div class="form-group">-->
<!--        <label for="email">Email</label>-->
<!--        <input type="email" id="email" v-model="email" required>-->
<!--      </div>-->
<!--      <div class="form-group">-->
<!--        <label for="message">Message</label>-->
<!--        <textarea id="message" v-model="message" required></textarea>-->
<!--      </div>-->
<!--      <button type="submit">Submit</button>-->
<!--    </form>-->
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="姓 名" prop="name">
        <el-input v-model="ruleForm.name" autocomplete="off" clearable></el-input>
      </el-form-item>
      <el-form-item label="邮 箱" prop="email">
        <el-input v-model="ruleForm.email" autocomplete="off" clearable></el-input>
      </el-form-item>
      <el-form-item label="问 题" prop="condit">
        <el-input
          type="textarea"
          :autosize="{ minRows: 10, maxRows: 20}"
          placeholder="请输入你遇到的问题"
          v-model="ruleForm.message">
        </el-input>
      </el-form-item>
      <el-form-item style="margin-top: -20px;">
        <el-button style="font-size: 40px;height:100px; width:100px; border-radius: 50%;background-color: #99a9bf;margin-top: 50px" @click="submitForm('ruleForm')" round="">→</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  data () {
    return {
      ruleForm: {
        name: '',
        email: '',
        message: ''
      },
      rules: {
        name: [
          {required: true, message: '请输入名字', trigger: 'blur'}
        ],
        email: [
          {required: true, message: '请输入邮箱地址', trigger: 'blur'},
          {type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change']}
        ]
      }
    }
  },
  methods: {
    submitForm (fromName) {
      // 在此处编写提交表单的逻辑，例如通过 Ajax 请求发送消息给管理员
      // 可以使用 this.name, this.email 和 this.message 来获取表单字段的值
      const _this = this
      this.$refs[fromName].validate((valid) => {
        if (valid) {
          this.$axios.post('http://119.29.250.12:8081/insert/message', _this.ruleForm).then(function (resp) {
            if (resp.data === 'success') {
              _this.$alert('提交成功,稍后管理员将会将信息发送到你的邮箱上，请注意查收', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$message({
                    showClose: true,
                    type: 'success',
                    message: `提交成功`
                  })
                  _this.$router.go(0)
                }
              })
            }
            ;
          })
        } else {
          return false
        }
      })
      // 提交后请将表单字段重置为初始值
      _this.name = ''
      _this.email = ''
      _this.message = ''
    }
  }
}
</script>

<style scoped>
h1 {
  display: block;
  font-size: 2em;
  margin-block-start: 0.67em;
  margin-block-end: 0.67em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  font-weight: bold;
}
.contact-admin {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}

.demo-ruleForm {
  display: flex;
  flex-direction: column;
}

.form-group {
  margin-bottom: 10px;
}

label {
  font-weight: bold;
}

input, textarea {
  width: 100%;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 50px;
}
</style>
