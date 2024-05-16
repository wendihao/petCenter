<template>
  <div style="height:500px;">
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column fixed prop="title" label="分享标题" width="200">
      </el-table-column>
      <el-table-column prop="images" label="分享照片" align="center" width="200">
        <template slot-scope="scope">
          <img :src="scope.row.images" style="height: 100px;width: 100px" />
        </template>
      </el-table-column>
      <el-table-column prop="datetime" label="发布时间" width="200">
      </el-table-column>
      <el-table-column prop="introduce" label="内容" width="500">
      </el-table-column>
      <el-table-column prop="pageview" label="浏览量" width="200">
      </el-table-column>
      <el-table-column prop="comment" label="评论量" width="200">
      </el-table-column>
      <el-table-column label="操作" width="100">
        <template slot-scope="scope">
          <el-button @click="getpetBlog(scope.row) " type="text" size="small">修改</el-button>
          <el-dialog title="修改分享" :visible.sync="dialogFormVisible" :append-to-body="true">
            <el-form :model="form" ref="form">
              <el-form-item label="标题" :label-width="formLabelWidth">
                <el-input v-model="form.title" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="图片" :label-width="formLabelWidth">
                <el-upload
                  action="http://119.29.250.12:8081/file/upload"
                  list-type="picture"
                  :show-file-list="false"
                  :on-remove="handlePreview"
                  :on-change="uploadSectionFile"
                  :on-success="success"
                  :limit="1"
                  ref="businessLicense">
                  <i class="el-icon-plus"></i>
                </el-upload>
                <el-image style="width: 100px; height: 100px" :src="form.images"/>
              </el-form-item>
              <el-form-item label="内容" :label-width="formLabelWidth">
                <el-input v-model="form.introduce" autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button @click="updatepetBlog(scope.row)" type="primary">确 定</el-button>
            </div>
          </el-dialog>
          <el-button @click="deletepetBlog(scope.row)" type="text" size="small">删除</el-button>
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
      dialogImageUrl: '',
      dialogVisible: false,
      userid: '',
      username: '',
      tableData: [
        {
          id: '',
          title: '',
          datetime: '',
          introduce: '',
          images: '',
          time: '',
          pageview: '',
          comment: '',
          user_id: ''
        }
      ],
      formLabelWidth: '100px',
      dialogFormVisible: false,
      currentPage: 1,
      total: null,
      form: {
        id: '',
        title: '',
        datetime: '',
        introduce: '',
        images: [],
        time: '',
        pageview: '',
        comment: '',
        user_id: ''
      }
    }
  },
  methods: {
    success (response, file) {
      console.log(response)
      if (response.success === true) {
        this.form.images = response.message
        sessionStorage.setItem('imgUrl4', this.form.images)
      }
    },
    uploadSectionFile (file) {
      this.form.images = file.url
      var testmsg = file.name.substring(file.name.lastIndexOf('.') + 1)
      const extension =
        testmsg === 'jpg' ||
        testmsg === 'JPG' ||
        testmsg === 'png' ||
        testmsg === 'PNG'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!extension) {
        this.$message.error('上传头像图片只能是 JPG/PNG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传图片大小不能超过 2MB!')
      }
      return extension && isLt2M
    },
    handlePreview (file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    handlerPageChange (currentPage) {
      this.getData(currentPage)
    },
    getData (currentPage) {
      const _this = this
      this.$axios.get('http://119.29.250.12:8081/findAllpetBlog/' + currentPage + '/5' + '/' + _this.$route.query.id).then(function (resp) {
        console.log(resp)
        _this.tableData = resp.data.records
        _this.total = resp.data.total
      }).catch(function (error) {
        console.log(error)
      })
    },
    getpetBlog (row) {
      this.dialogFormVisible = true
      const _this = this
      this.$axios.get('http://119.29.250.12:8081/findpetBlogById/' + row.id).then(function (resp) {
        _this.form = resp.data
      })
    },
    updatepetBlog (row) {
      const _this = this
      if (sessionStorage.getItem('imgUrl4') !== '') {
        _this.form.images = sessionStorage.getItem('imgUrl4')
      } else {
        _this.form.images = row.images
      }
      this.$axios.post('http://119.29.250.12:8081/updatepetBlog/', _this.form).then(function (resp) {
        if (resp.data === 'success') {
          _this.$alert('修改成功！', {
            confirmButtonText: '确定',
            callback: action => {
              _this.$message({
                showClose: true,
                type: 'success',
                message: `修改成功`
              })
            }
          })
          _this.$router.go(0)
        }
        ;
      })
    },
    deletepetBlog (row) {
      const _this = this
      this.$axios.get('http://119.29.250.12:8081/petblog/delete/' + row.id).then(function (resp) {
        if (resp.data === 'success') {
          _this.$alert('《' + row.title + '》', '删除成功', {
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
