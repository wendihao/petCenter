<template>
  <div style="height:500px;">
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="username" label="用户名" width="200">
      </el-table-column>
      <el-table-column prop="petblogId" label="评论宠物圈ID" width="200">
      </el-table-column>
      <el-table-column prop="comment" label="评论内容" width="200">
      </el-table-column>
      <el-table-column prop="datetime" label="评论时间" width="200">
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button style="background-color: red ;color: white;width: 50px;margin-left: 10px" @click="deleteComment(scope.row)" type="text" size="small">删除</el-button>
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
          petblogId: '',
          username: '',
          datetime: '',
          comment: ''
        }
      ],
      formLabelWidth: '100px',
      dialogFormVisible: false,
      currentPage: 1,
      total: null,
      form: {
        userId: '',
        title: '',
        images: '',
        introduce: '',
        datetime: '',
        pageview: '',
        comment: ''
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
      this.$axios.get('http://localhost:8081/findAllComment/' + currentPage + '/4').then(function (resp) {
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
      this.$axios.get('http://localhost:8081/findPetBlogById/' + row.id).then(function (resp) {
        _this.form = resp.data
      })
    },
    deleteComment (row) {
      const _this = this
      this.$axios.get('http://localhost:8081/deleteComment/' + row.id).then(function (resp) {
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
