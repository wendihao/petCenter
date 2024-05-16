<template>
  <div style="">
    <div style="background-color: white;padding: 0.1rem;width: 100%">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item style="margin-top: 5%;padding-bottom: 3%"><a @click="back()" style="font-size: 15px; ">{{this.return}}返回</a></el-breadcrumb-item>
      </el-breadcrumb>
      <h1 style="margin-left: 40%;margin-top: -5%;font-size: 15px;font-weight: bolder">招领管理</h1>
      <hr style="margin-bottom: -1%;width: 100%">
    </div>
    <el-table
      :data="tableData"
      border
      style="width: 100%">
      <el-table-column
        fixed prop="name"
        label="名字"
        width="90">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址"
        width="180">
      </el-table-column>
      <el-table-column
        prop="phoneId"
        label="电话号码"
        width="120">
      </el-table-column>
      <el-table-column
        prop="wechatId"
        label="微信号"
        width="150">
      </el-table-column>
      <el-table-column
        prop="email"
        label="Email"
        width="180">
      </el-table-column>
      <el-table-column
        prop="qqId"
        label="QQ号"
        width="120">
      </el-table-column>
      <el-table-column
        prop="condit"
        label="相关描述"
        width="250">
      </el-table-column>
      <el-table-column
        prop="homelesspettitle"
        label="标题"
        width="170">
      </el-table-column>
      <el-table-column label="操作" width="100">
        <template slot-scope="scope">
          <el-button v-if="!rowid.includes(scope.row.id)" type="text" @click="open(scope.row)" size="small">接受</el-button>
          <el-button v-if="rowid.includes(scope.row.id)" type="text" size="small">已接受</el-button>
          <el-button @click="deleteRenling(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
export default {
  name: 'MessageManage1',
  data () {
    return {
      return: '<',
      currentPage: 1,
      total: null,
      id: '',
      rowid: [],
      flag: true,
      tableData: [
        {
          name: '',
          address: '',
          phoneId: '',
          wechatId: '',
          email: '',
          qqId: '',
          homelesspettitle: '',
          condit: '',
          homelesspetId: '',
          userId: ''
        }
      ]
    }
  },
  methods: {
    back () {
      this.$router.go(-1)
    },
    handlerPageChange (currentPage) {
      const _this = this
      _this.currentPage = currentPage
      _this.getData(currentPage)
    },
    open (row) {
      this.$confirm('是否接受对方认领', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get('http://119.29.250.12:8081/sethomelesspet/' + row.homelesspetId + '/' + row.name).then(function (resp) {
          console.log(resp)
        })
        this.$message({
          type: 'success',
          message: '接受成功!'
        })
        this.$router.go(0)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消接受'
        })
      })
    },
    deleteRenling (row) {
      this.$confirm('是否删除信息', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.$axios.get('http://119.29.250.12:8081/delete/renling/' + row.id).then(function (resp) {
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
    this.$axios.get('http://119.29.250.12:8081/querry/allrenling/' + _this.$route.query.id).then(function (resp) {
      _this.tableData = []
      for (let i = 0; i < resp.data.length; i++) {
        _this.tableData[i] = resp.data[i]
        _this.tableData[i].homelesspettitle = '《' + resp.data[i].homelesspettitle + '》'
      }
    }).catch(function (error) {
      console.log(error)
    })
    this.$axios.get('http://119.29.250.12:8081/querry/flag1/' + _this.$route.query.id).then(function (resp) {
      _this.rowid = resp.data
    })
  }
}
</script>

<style scoped>

</style>
