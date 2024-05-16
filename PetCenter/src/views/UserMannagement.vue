<template>
  <div style="height:500px;">
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column fixed prop="id" label="用户ID" width="200">
      </el-table-column>
      <el-table-column prop="username" label="用户账号" width="200">
      </el-table-column>
      <el-table-column prop="name" label="用户姓名" width="200">
      </el-table-column>
      <el-table-column  prop="sex1" label="用户性别" width="200">
      </el-table-column>
      <el-table-column prop="email" label="用户邮箱" width="200">
      </el-table-column>
      <el-table-column prop="phoneid" label="用户号码" width="200">
      </el-table-column>
      <el-table-column prop="portrait" label="用户头像" width="200">
        <template slot-scope="scope">
          <img :src="scope.row.portrait" width="100" height="100" />
        </template>
      </el-table-column>
      <el-table-column label="操作" width="100">
        <template slot-scope="scope">
          <el-button @click="getUser(scope.row) " type="text" size="small">查看</el-button>
          <el-dialog title="用户信息" :visible.sync="dialogFormVisible" :append-to-body="true">
            <el-form :model="form" ref="form">
              <el-form-item label="用户ID" :label-width="formLabelWidth">
                <el-input v-model="form.id" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="用户账号" :label-width="formLabelWidth">
                <el-input v-model="form.username" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="用户姓名" :label-width="formLabelWidth">
                <el-input v-model="form.name" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="用户性别" prop="sex" :label-width="formLabelWidth">
                <el-radio-group v-model="form.sex">
                  <el-radio :label="1">男</el-radio>
                  <el-radio :label="0">女</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="用户邮箱" :label-width="formLabelWidth">
                <el-input v-model="form.email" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="用户号码" :label-width="formLabelWidth">
                <el-input v-model="form.phoneid" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="用户头像" :label-width="formLabelWidth">
                <el-image style="width: 100px; height: 100px" :src="form.portrait" ></el-image>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
            </div>
          </el-dialog>
          <el-button @click="deleteUser(scope.row)" type="text" size="small">删除</el-button>
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
  name: 'UserManagement',
  data () {
    return {
      userid: '',
      username: '',
      tableData: [
        {
          id: '',
          name: '',
          username: '',
          sex: '',
          sex1: '',
          email: '',
          phoneid: '',
          portrait: ''
        }
      ],
      formLabelWidth: '100px',
      dialogFormVisible: false,
      currentPage: 1,
      total: null,
      form: {
        id: '',
        name: '',
        username: '',
        sex: '',
        sex1: '',
        email: '',
        phoneid: '',
        portrait: ''
      }
    }
  },
  methods: {
    handlerPageChange (currentPage, userid) {
      userid = this.userid
      this.getData(currentPage, userid)
    },
    getData (currentPage) {
      const _this = this
      this.$axios.get('http://localhost:8081/findAllUser/' + currentPage + '/5').then(function (resp) {
        console.log(resp)
        _this.tableData = resp.data.records
        _this.total = resp.data.total
      }).catch(function (error) {
        console.log(error)
      })
    },
    getUser (row) {
      this.dialogFormVisible = true
      const _this = this
      this.$axios.get('http://localhost:8081/user/selectbyid/' + row.id).then(function (resp) {
        _this.form = resp.data
      })
    },
    deleteUser (row) {
      const _this = this
      this.$axios.get('http://localhost:8081/user/delete/' + row.id).then(function (resp) {
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
