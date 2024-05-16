<template>
  <div style="">
    <div style="background-color: white;padding: 0.1rem;width: 100%">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item style="margin-top: 5%;padding-bottom: 3%"><a @click="back()" style="font-size: 15px; ">{{this.return}}返回</a></el-breadcrumb-item>
      </el-breadcrumb>
      <h1 style="margin-left: 40%;margin-top: -5%;font-size: 15px;font-weight: bolder">宠物圈管理</h1>
      <hr style="margin-bottom: -1%;width: 100%">
    </div>
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column fixed prop="title" label="分享标题" width = "80" >
      </el-table-column>
      <el-table-column prop="images" label="分享照片" width = "80">
        <template slot-scope="scope">
          <img :src="scope.row.images" style="width: 100% ;height:100% "/>
        </template>
      </el-table-column>
      <el-table-column prop="datetime" label="发布时间" width = "150">
      </el-table-column>
      <el-table-column prop="introduce" label="内容" width = "150">
      </el-table-column>
      <el-table-column prop="pageview" label="浏览数" width = "80">
      </el-table-column>
      <el-table-column prop="comment" label="评论数" width = "80">
      </el-table-column>
      <el-table-column label="操作" width = "100">
        <template slot-scope="scope">
          <el-button @click="getpetBlog(scope.row) " type="text" size="small">修改</el-button>
<!--          <el-dialog title="修改分享" :visible.sync="dialogFormVisible" :append-to-body="true">-->
<!--          </el-dialog>-->
          <el-button @click="deletepetBlog(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div v-show="MaskBe" class="MaskCss" @click="closeFun()"></div>
    <div  :class="['Popup',openBe?'openActive':'']" v-if="dialogFormVisible">
      <el-form :model="form" ref="form">
        <el-form-item label="标题" :label-width="formLabelWidth">
          <el-input v-model="form.title" autocomplete="off" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="图片" :label-width="formLabelWidth">
          <el-upload
            style="width: 40%"
            action="http://119.29.250.12:8081/file/upload"
            list-type="picture"
            :show-file-list="false"
            :on-remove="handlePreview"
            :on-change="uploadSectionFile"
            :on-success="success"
            :limit="1"
            ref="businessLicense">
            <el-image style="width: 100%; height: 100%" :src="form.images" ></el-image>
          </el-upload>
        </el-form-item>
<!--        <el-form-item label="内容" :label-width="formLabelWidth">-->
<!--          <el-input v-model="form.introduce" autocomplete="off"></el-input>-->
<!--        </el-form-item>-->
        <el-form-item label="内容" :label-width="formLabelWidth">
          <el-input
            type="textarea"
            :autosize="{ minRows: 6, maxRows: 10}"
            v-model="form.introduce"
            style="width: 80%">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updatepetBlog(row)" style="margin-left: 40%" type="primary">确 定</el-button>
      </div>
    </div>
<!--    <div>-->
<!--      <el-pagination-->
<!--        background-->
<!--        layout="prev, pager, next"-->
<!--        :page-size=5-->
<!--        :total="total"-->
<!--        :current-page="currentPage"-->
<!--        @current-change="handlerPageChange">-->
<!--      </el-pagination>-->
<!--    </div>-->
  </div>
</template>

<script>
export default {
  name: 'BlogManage',
  data () {
    return {
      return: '<',
      row: '',
      openBe: false, // 是否打开弹出层
      MaskBe: false, // 是否开启遮罩层
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
        images: '',
        time: '',
        pageview: '',
        comment: '',
        user_id: ''
      }
    }
  },
  methods: {
    back () {
      this.$router.go(-1)
    },
    openFun () {
      this.MaskBe = true
      this.openBe = true
    },
    // 点击遮罩则关闭弹层~
    closeFun () {
      this.MaskBe = false
      this.openBe = false
    },
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
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return extension && isLt2M
    },
    handlePreview (file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    getpetBlog (row) {
      this.dialogFormVisible = true
      const _this = this
      _this.openFun()
      this.$axios.get('http://119.29.250.12:8081/findpetBlogById/' + row.id).then(function (resp) {
        _this.form = resp.data
        _this.row = row
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
    const _this = this
    this.$axios.get('http://119.29.250.12:8081/findAllpetBlog/' + _this.$route.query.id).then(function (resp) {
      console.log(resp)
      _this.tableData = resp.data
    }).catch(function (error) {
      console.log(error)
    })
    this.userid = this.$route.query.id
    this.username = this.$route.query.username
  }
}
</script>

<style scoped>
>>>.el-carousel__container{
  height: 4rem;
}
.Popup{
  padding-top: 1rem;
  border-radius: 5% 5% 0 0;
  width: 100%;
  height: 80%;
  background: white;
  position: fixed;
  bottom: -15rem;
  z-index: 11000;
  transition: all 0.25s linear;
  overflow-y: auto;
}
/* 点击按钮是将盒子 bottom 值归零即可实现弹出效果,
同理，如需更改弹出方向只需将bottom改成top、left、right即可
(默认样式的方向也需一起更改哦) */
.openActive{
  bottom: 0px !important;
}
/* 遮罩层样式 */
.MaskCss {
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.3);
  z-index: 10000;
}

</style>
