<template>
  <div style="height:500px;">
    <el-table
      :data="tableData"
      border
      style="width: 100%">
      <el-table-column
        prop="name"
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
        label="领养条件"
        width="250">
      </el-table-column>
      <el-table-column
        prop="blogtitle"
        label="领养标题"
        width="170">
      </el-table-column>
      <el-table-column label="操作" width="100">
        <template slot-scope="scope">
          <el-button type="text" @click="open(scope.row)" size="small">接受</el-button>
          <el-button @click="deleteAdoption(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'MessageManage',
  data () {
    return {
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
    open (row) {
      this.$confirm('是否接受对方领养', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get('http://localhost:8081/blog/setpetadopt/' + row.blogId + '/' + row.name).then(function (resp) {
          console.log(resp)
        })
        this.$message({
          type: 'success',
          message: '接受成功!'
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消接受'
        })
      })
    },
    deleteAdoption (row) {
      this.$confirm('是否删除信息', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.$axios.delete('http://localhost:8081/delete/adoption/' + row.id).then(function (resp) {
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
    window.alert(this.$route.query.id)
    const _this = this
    this.$axios.get('http://localhost:8081/querry/alladoption/' + _this.$route.query.id).then(function (resp) {
      _this.tableData = resp.data
      for (let i = 0; i < resp.data.length; i++) {
        _this.tableData[i].blogtitle = '《' + resp.data[i].blogtitle + '》'
      }
    })
  }
}
</script>

<style scoped>

</style>
