<template>
  <div style="height:500px;">
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="name" label="用户名称" width="360">
      </el-table-column>
      <el-table-column prop="email" label="用户邮箱" width="360">
      </el-table-column>
      <el-table-column prop="message" label="反映问题" width="400">
      </el-table-column>
      <el-table-column prop="datetime" label="反映时间" width="380">
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button style="background-color:blue;color:white;width: 50px" @click="getMessage(scope.row) " type="text" size="small">回复</el-button>
          <el-dialog title="" :visible.sync="dialogFormVisible" :append-to-body="true">
            <el-form :model="form" ref="form">
              <el-form-item label="用户名称" :label-width="formLabelWidth">
                <el-input v-model="form.name" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="用户邮箱" :label-width="formLabelWidth">
                <el-input v-model="form.email" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="用户问题" :label-width="formLabelWidth">
                <el-input v-model="form.message" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="回复" :label-width="formLabelWidth">
                <el-input
                  type="textarea"
                  :autosize="{ minRows: 6, maxRows: 10}"
                  placeholder="请输入内容"
                  v-model="form.feedbackmessage">
                </el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button @click="sendMessage('form')" type="primary">确 定</el-button>
            </div>
          </el-dialog>
          <el-button style="background-color: red ;color: white;width: 50px;margin-left: 20px" @click="deleteMessage(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div>
      <el-pagination style="height: 40px; width: 100%; float: left; margin-top: 20px;text-align: center"
        background
        layout="prev, pager, next"
        :page-size=4
        :total="total"
        :current-page="currentPage"
        @current-change="handlerPageChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BlogManage',
  data () {
    return {
      userid: '',
      username: '',
      tableData: [
        {
          id: '',
          name: '',
          email: '',
          message: '',
          datetime: '',
          time: '',
          userId: ''
        }
      ],
      formLabelWidth: '100px',
      dialogFormVisible: false,
      currentPage: 1,
      total: null,
      form: {
        id: '',
        name: '',
        email: '',
        message: '',
        datetime: '',
        time: '',
        feedbackmessage: '',
        userId: ''
      }
    }
  },
  methods: {
    handlerPageChange (currentPage) {
      const _this = this
      _this.currentPage = currentPage
      _this.getData(currentPage)
    },
    getData (currentPage) {
      const _this = this
      this.$axios.get('http://119.29.250.12:8081/findAllMessage/' + currentPage + '/4').then(function (resp) {
        console.log(resp)
        _this.tableData = resp.data.records
        _this.total = resp.data.total
      }).catch(function (error) {
        console.log(error)
      })
    },
    getMessage (row) {
      this.dialogFormVisible = true
      const _this = this
      this.$axios.get('http://119.29.250.12:8081/findMessage/' + row.id).then(function (resp) {
        _this.form = resp.data
      })
    },
    sendMessage (formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          _this.$message({
            showClose: true,
            type: 'success',
            message: `正在发送中，请稍后...`
          })
          _this.$router.go(-1)
          _this.$router.push({
            path: '/admin',
            query: {
              userid: _this.userid
            }
          })
          this.$axios.post('http://119.29.250.12:8081/sendMessage', _this.form).then(function (resp) {
            if (resp.data === 'success') {
              _this.$alert('发送成功', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$message({
                    showClose: true,
                    type: 'success',
                    message: `发送成功`
                  })
                }
              })
            } else {
              _this.$message({
                showClose: true,
                type: 'error',
                message: `发送失败`
              })
            }
          })
        }
      })
    },
    deleteMessage (row) {
      const _this = this
      this.$axios.get('http://119.29.250.12:8081/deleteMessage/' + row.id).then(function (resp) {
        if (resp.data === 'success') {
          _this.$alert('删除成功', {
            confirmButtonText: '确定',
            callback: action => {
              _this.$message({
                showClose: true,
                type: 'success',
                message: `删除成功`
              })
              _this.$router.go(0)
            }
          })
        } ;
      })
    }
  },
  created () {
    // this.currentTime = setInterval(() => {
    this.getData(this.currentPage)
    // }, 100)
    this.userid = this.$route.query.id
    this.username = this.$route.query.username
  }
}
</script>

<style scoped>

</style>
