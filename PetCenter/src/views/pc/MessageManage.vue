<template>
  <div style="height:500px;">
    <el-table
      :data="tableData"
      border
      style="width: 100%">
      <el-table-column
        prop="name"
        label="名字"
        width="100">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址"
        width="180">
      </el-table-column>
      <el-table-column
        prop="phoneId"
        label="电话号码"
        width="180">
      </el-table-column>
      <el-table-column
        prop="wechatId"
        label="微信号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="email"
        label="Email"
        width="180">
      </el-table-column>
      <el-table-column
        prop="qqId"
        label="QQ号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="condit"
        label="领养条件"
        width="280">
      </el-table-column>
      <el-table-column
        prop="blogtitle"
        label="领养标题"
        width="180">
      </el-table-column>
      <el-table-column label="操作" width="120">
        <template slot-scope="scope">
          <el-button v-if="!rowid.includes(scope.row.id)" type="text" @click="open(scope.row)" size="small">接受</el-button>
          <el-button v-if="rowid.includes(scope.row.id)" type="text"  size="small">已接受</el-button>
          <el-button @click="deleteAdoption(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div>
      <el-pagination style="height:40px;width: 100%;float: left;margin-top: 20px"
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
  name: 'MessageManage',
  data () {
    return {
      currentPage: 1,
      total: null,
      flag: true,
      rowid: [],
      id: '',
      tableData: [
        {
          name: '',
          address: '',
          phoneId: '',
          wechatId: '',
          email: '',
          qqId: '',
          blogtitle: '',
          condit: '',
          blogId: '',
          userId: ''
        }
      ]
    }
  },
  methods: {
    handlerPageChange (currentPage) {
      const _this = this
      _this.currentPage = currentPage
      _this.getData(currentPage)
    },
    open (row) {
      this.$confirm('是否接受对方领养', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get('http://119.29.250.12:8081/blog/setpetadopt/' + row.blogId + '/' + row.name).then(function (resp) {
          console.log(resp)
        })
        this.$message({
          type: 'success',
          message: '接受成功!'
        })
        this.$router.go(0)
        this.rowid.push(row.id)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消接受'
        })
      })
    },
    getData (currentPage) {
      const _this = this
      this.$axios.get('http://119.29.250.12:8081/querry/alladoption/' + currentPage + '/4/' + _this.$route.query.id).then(function (resp) {
        _this.tableData = resp.data.records
        _this.total = resp.data.total
        for (let i = 0; i < resp.data.length; i++) {
          _this.tableData[i].blogtitle = '《' + resp.data[i].blogtitle + '》'
        }
      }).catch(function (error) {
        console.log(error)
      })
    },
    deleteAdoption (row) {
      this.$confirm('是否删除信息', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.$axios.get('http://119.29.250.12:8081/delete/adoption/' + row.id).then(function (resp) {
            console.log(resp)
          })
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.$router.go(0)
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    }
  },
  created () {
    this.id = this.$route.query.id
    const _this = this
    // this.currentTime = setInterval(() => {
    this.getData(_this.currentPage)
    this.$axios.get('http://119.29.250.12:8081/querry/flag/' + _this.$route.query.id).then(function (resp) {
      _this.rowid = resp.data
    })
    // }, 100)
  }
}
</script>

<style scoped>

</style>
