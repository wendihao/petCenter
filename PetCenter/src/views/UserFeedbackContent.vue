<template>
  <div style="height:500px;">
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column fixed prop="name" label="用户名称" width="360">
      </el-table-column>
      <el-table-column prop="email" label="用户邮箱" width="360">
      </el-table-column>
      <el-table-column prop="message" label="反映问题" width="360">
      </el-table-column>
      <el-table-column prop="datetime" label="反映时间" width="360">
      </el-table-column>
      <el-table-column label="操作" width="100">
        <template slot-scope="scope">
          <el-button @click="getMessage(scope.row) " type="text" size="small">回复</el-button>
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
          <el-button @click="deleteMessage(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div>
      <el-pagination
        background
        layout="prev, pager, next"
        :page-size=5
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
    handlerPageChange (currentPage, userid) {
      userid = this.userid
      this.getData(currentPage, userid)
    },
    getData (currentPage, userid) {
      const _this = this
      this.$axios.get('http://localhost:8081/findAllMessage/' + currentPage + '/5').then(function (resp) {
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
      this.$axios.get('http://localhost:8081/findMessage/' + row.id).then(function (resp) {
        _this.form = resp.data
      })
    },
    sendMessage (formName) {
      window.alert(formName)
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
            path: '/',
            query: {
              userid: _this.userid
            }
          })
          this.$axios.post('http://localhost:8081/sendMessage', _this.form).then(function (resp) {
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
      window.alert(row.id)
      const _this = this
      this.$axios.get('http://localhost:8081/deleteMessage/' + row.id).then(function (resp) {
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
    this.getData(this.currentPage)
    this.userid = this.$route.query.id
    this.username = this.$route.query.username
  }
}
</script>

<style scoped>

</style>
