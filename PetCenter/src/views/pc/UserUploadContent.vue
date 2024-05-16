<template>
  <div style="height:500px;">
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="userId" label="用户ID" width="100">
      </el-table-column>
      <el-table-column prop="username" label="用户名称" width="200">
      </el-table-column>
      <el-table-column prop="category" label="上传类别" width="200">
      </el-table-column>
      <el-table-column prop="images" label="上传图片" align="center" width="200">
        <template slot-scope="scope">
          <img :src="scope.row.images" width="100" height="100" />
        </template>
      </el-table-column>
      <el-table-column prop="introduce" label="上传信息" width="200">
      </el-table-column>
      <el-table-column prop="datetime" label="上传时间" width="200">
      </el-table-column>
      <el-table-column prop="varieties" label="上传动物类别" width="200">
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button style="background-color:blue;color:white;width: 50px" @click="getUpload(scope.row) " type="text" size="small">查看</el-button>
          <el-dialog title="详细信息" :visible.sync="dialogFormVisible" :append-to-body="true">
            <el-form :model="form" ref="form">
              <el-form-item label="用户ID" :label-width="formLabelWidth">
                <el-input v-model="form.userId" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="用户名称" :label-width="formLabelWidth">
                <el-input v-model="form.username" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="上传类别" :label-width="formLabelWidth">
                <el-input v-model="form.category" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="上传动物类别" :label-width="formLabelWidth">
                <el-input v-model="form.varieties" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="上传图片" :label-width="formLabelWidth">
                <el-image style="width: 100px; height: 100px" :src="form.images" ></el-image>
              </el-form-item>
              <el-form-item label="上传信息" :label-width="formLabelWidth">
                <el-input
                  type="textarea"
                  :autosize="{ minRows: 6, maxRows: 10}"
                  placeholder="请输入内容"
                  v-model="form.introduce">
                </el-input>
              </el-form-item>
              <el-form-item label="上传时间" :label-width="formLabelWidth">
                <el-input v-model="form.datetime" autocomplete="off" ></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
            </div>
          </el-dialog>
          <el-button style="background-color: lightskyblue ;color: white;width: 50px;margin-left: 10px" @click="update(scope.row)" type="text" size="small">通过</el-button>
          <el-button style="background-color: red ;color: white;width: 50px;margin-left: 10px" @click="deleteBlog(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div>
      <el-pagination
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
          userId: '',
          username: '',
          category: '',
          images: '',
          introduce: '',
          datetime: '',
          varieties: ''
        }
      ],
      formLabelWidth: '100px',
      dialogFormVisible: false,
      currentPage: 1,
      total: null,
      form: {
        userId: '',
        username: '',
        category: '',
        images: '',
        introduce: '',
        datetime: '',
        varieties: ''
      }
    }
  },
  methods: {
    handlerPageChange (currentPage) {
      this.getData(currentPage)
    },
    getData (currentPage) {
      const _this = this
      this.$axios.get('http://localhost:8081/findAll1/' + currentPage + '/4').then(function (resp) {
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
      this.$axios.get('http://localhost:8081/findUserUpload/' + row.id).then(function (resp) {
        _this.form = resp.data
      })
    },
    update (row) {
      const _this = this
      window.alert(row.id)
      this.$axios.get('http://localhost:8081/update/' + row.id).then(function (resp) {
        if (resp.data === 'success') {
          _this.$alert('审核通过！', {
            confirmButtonText: '确定',
            callback: action => {
              _this.$message({
                showClose: true,
                type: 'success',
                message: `审核通过`
              })
            }
          })
          _this.delete(row)
          _this.$router.go(0)
        }
        ;
      })
    },
    deleteBlog (row) {
      const _this = this
      this.$axios.get('http://localhost:8081/userUpload/delete/' + row.id).then(function (resp) {
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
    },
    delete (row) {
      this.$axios.get('http://localhost:8081/userUpload/delete/' + row.id).then(function (resp) {
      })
    }
  },
  created () {
    this.currentTime = setInterval(() => {
      this.getData(this.currentPage)
    }, 100)
    this.userid = this.$route.query.id
    this.username = this.$route.query.username
  }
}
</script>

<style scoped>

</style>
