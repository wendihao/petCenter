<template>
  <div style="height:500px;">
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="jubaoId" label="举报人ID" width="200">
      </el-table-column>
      <el-table-column prop="jubaousername" label="举报人账号" width="200">
      </el-table-column>
      <el-table-column prop="username" label="被举报人账号" width="200">
      </el-table-column>
      <el-table-column prop="reason" label="举报原因" width="400">
      </el-table-column>
      <el-table-column prop="images" label="相关截图" align="center" width="300">
        <template slot-scope="scope">
          <img :src="scope.row.images" style="height: 100px;width: 100px" />
        </template>
      </el-table-column>
      <el-table-column prop="datetime" label="举报时间" width="200">
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button style="background-color:blue;color:white;width: 50px" @click="getUpload(scope.row) " type="text" size="small">回复</el-button>
          <el-dialog title="" :visible.sync="dialogFormVisible" :append-to-body="true">
            <el-form :model="form" ref="form">
              <el-form-item label="举报人ID" :label-width="formLabelWidth">
                <el-input v-model="form.jubaoId" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="举报人" :label-width="formLabelWidth">
                <el-input v-model="form.jubaousername" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="举报问题" :label-width="formLabelWidth">
                <el-input v-model="form.reason" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="被举报人" :label-width="formLabelWidth">
                <el-input v-model="form.username" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="举报截图" :label-width="formLabelWidth">
                <el-image style="width: 100px; height: 100px" :src="form.images" ></el-image>
              </el-form-item>
              <el-form-item label="举报时间" :label-width="formLabelWidth">
                <el-input v-model="form.datetime" autocomplete="off"></el-input>
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
          <el-button style="background-color: red ;color: white;width: 50px;margin-left: 10px" @click="deleteJuBao(scope.row)" type="text" size="small">删除</el-button>
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
          username: '',
          reason: '',
          images: '',
          datetime: '',
          jubaoId: '',
          jubaousername: ''
        }
      ],
      formLabelWidth: '100px',
      dialogFormVisible: false,
      currentPage: 1,
      total: null,
      form: {
        jubaoId: '',
        jubaousername: '',
        reason: '',
        username: '',
        images: '',
        datetime: '',
        feedbackmessage: ''
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
      this.$axios.get('http://119.29.250.12:8081/findAllJuBao/' + currentPage + '/4').then(function (resp) {
        console.log(resp)
        _this.tableData = resp.data.records
        _this.total = resp.data.total
      }).catch(function (error) {
        console.log(error)
      })
    },
    getUpload (row) {
      this.dialogFormVisible = true
      const _this = this
      this.$axios.get('http://119.29.250.12:8081/findJuBaoById/' + row.id).then(function (resp) {
        _this.form = resp.data
      })
    },
    deleteJuBao (row) {
      const _this = this
      this.$axios.get('http://119.29.250.12:8081/deleteJuBao/' + row.id).then(function (resp) {
        if (resp.data === 'success') {
          _this.$alert('删除成功', {
            confirmButtonText: '确定',
            callback: action => {
              _this.$message({
                showClose: true,
                type: 'success',
                message: `删除成功`
              })
            }
          })
          _this.$router.go(0)
        } ;
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
          this.$axios.post('http://119.29.250.12:8081/sendMessage1', _this.form).then(function (resp) {
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
    delete (row) {
      this.$axios.get('http://119.29.250.12:8081/userUpload/delete/' + row.id).then(function (resp) {
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
