<template>
  <div class="contact-admin">
    <h1>Contact Administrator</h1>
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
          :autosize="{ minRows: 6, maxRows: 10}"
          placeholder="请输入你遇到的问题"
          v-model="ruleForm.message">
        </el-input>
      </el-form-item>
      <el-form-item style="margin-top: -20px;">
        <el-button type="primary" @click="submitForm('ruleForm')" round="">提交</el-button>
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
      window.alert(fromName)
      const _this = this
      this.$refs[fromName].validate((valid) => {
        window.alert(valid)
        if (valid) {
          this.$axios.post('http://localhost:8081/insert/message', _this.ruleForm).then(function (resp) {
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
  border-radius: 4px;
}
</style>
