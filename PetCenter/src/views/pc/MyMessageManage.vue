<template>
  <div style="height:500px;">
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="category" label="上传类别" width="200">
      </el-table-column>
      <el-table-column prop="images" label="上传图片" align="center" width="200">
        <template slot-scope="scope">
          <img :src="scope.row.images" style="height: 100px;width: 100px"/>
        </template>
      </el-table-column>
      <el-table-column prop="introduce" label="上传信息" width="550">
      </el-table-column>
      <el-table-column prop="datetime" label="上传时间" width="300">
      </el-table-column>
      <el-table-column prop="varieties" label="上传动物类别" width="200">
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button @click="getBlog(scope.row) " type="text" size="small">修改</el-button>
          <el-dialog title="修改送养" :visible.sync="dialogFormVisible1" :append-to-body="true">
            <el-form :model="form1" ref="form">
              <el-form-item label="标题" :label-width="formLabelWidth">
                <el-input v-model="form1.title" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="地址" :label-width="formLabelWidth">
                <el-input v-model="form1.address" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="宠物名称" :label-width="formLabelWidth">
                <el-input v-model="form1.petname" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="宠物性别" prop="petsex" :label-width="formLabelWidth">
                <el-radio-group v-model="form1.petsex">
                  <el-radio :label="1">雄</el-radio>
                  <el-radio :label="0">雌</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="宠物年龄" :label-width="formLabelWidth">
                <el-input v-model="form1.petage" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="宠动品种" prop="varieties" :label-width="formLabelWidth">
                <el-radio-group v-model="form1.varieties" >
                  <el-radio-button label="1">猫</el-radio-button>
                  <el-radio-button label="0">狗</el-radio-button>
                  <el-radio-button label="2">其他</el-radio-button>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="是否免费" :label-width="formLabelWidth">
                <el-radio-group v-model="form1.free">
                  <el-radio :label="0">免费</el-radio>
                  <el-radio :label="1">有偿</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="图片" :label-width="formLabelWidth">
                <el-upload
                  action="http://119.29.250.12:8081/file/upload"
                  list-type="picture"
                  :show-file-list="false"
                  :on-remove="handlePreview"
                  :on-change="uploadSectionFile1"
                  :on-success="success1"
                  :limit="1"
                  ref="businessLicense">
                  <i class="el-icon-plus"></i>
                </el-upload>
                <el-image style="width: 100px; height: 100px" :src="form1.images" ></el-image>
              </el-form-item>
              <el-form-item label="宠物简介" :label-width="formLabelWidth">
                <el-input
                  type="textarea"
                  :autosize="{ minRows: 6, maxRows: 10}"
                  placeholder="请输入内容"
                  v-model="form1.introduce">
                </el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible1 = false">取 消</el-button>
              <el-button @click="update1(scope.row)" type="primary">确 定</el-button>
            </div>
          </el-dialog>
          <el-dialog title="修改招领" :visible.sync="dialogFormVisible2" :append-to-body="true">
            <el-form :model="form2" ref="form">
              <el-form-item label="标题" :label-width="formLabelWidth">
                <el-input v-model="form2.title" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="宠动品种" prop="varieties"  :label-width="formLabelWidth">
                <el-radio-group v-model="form2.varieties" >
                  <el-radio-button label="1">猫</el-radio-button>
                  <el-radio-button label="0">狗</el-radio-button>
                  <el-radio-button label="2">其他</el-radio-button>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="地点" :label-width="formLabelWidth">
                <el-input v-model="form2.address" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="时间" :label-width="formLabelWidth">
                <el-input v-model="form2.datetime" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="图片" :label-width="formLabelWidth">
                <el-upload
                  action="http://119.29.250.12:8081/file/upload"
                  list-type="picture"
                  :show-file-list="false"
                  :on-remove="handlePreview"
                  :on-change="uploadSectionFile2"
                  :on-success="success2"
                  :limit="1"
                  ref="businessLicense">
                  <i class="el-icon-plus"></i>
                </el-upload>
                <el-image style="width: 100px; height: 100px" :src="form2.images" ></el-image>
              </el-form-item>
              <el-form-item label="简介" :label-width="formLabelWidth">
                <el-input
                  type="textarea"
                  :autosize="{ minRows: 6, maxRows: 10}"
                  placeholder="请输入内容"
                  v-model="form2.introduce">
                </el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible2 = false">取 消</el-button>
              <el-button @click="update2(scope.row)" type="primary">确 定</el-button>
            </div>
          </el-dialog>
          <el-dialog title ="修改寻物启事" :visible.sync="dialogFormVisible3" :append-to-body="true">
            <el-form :model="form3" ref="form">
              <el-form-item label="宠动品种" prop="varieties" :label-width="formLabelWidth">
                <el-radio-group v-model="form3.varieties" >
                  <el-radio-button label="1">猫</el-radio-button>
                  <el-radio-button label="0">狗</el-radio-button>
                  <el-radio-button label="2">其他</el-radio-button>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="地点" :label-width="formLabelWidth">
                <el-input v-model="form3.address" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="时间" :label-width="formLabelWidth">
                <el-input v-model="form3.datetime" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="图片" :label-width="formLabelWidth">
                <el-upload
                  action="http://119.29.250.12:8081/file/upload"
                  list-type="picture"
                  :show-file-list="false"
                  :on-remove="handlePreview"
                  :on-change="uploadSectionFile3"
                  :on-success="success3"
                  :limit="1"
                  ref="businessLicense">
                  <i class="el-icon-plus"></i>
                </el-upload>
                <el-image style="width: 100px; height: 100px" :src="form3.images"/>
              </el-form-item>
              <el-form-item label="简介" :label-width="formLabelWidth">
                <el-input
                  type="textarea"
                  :autosize="{ minRows: 6, maxRows: 10}"
                  placeholder="请输入内容"
                  v-model="form3.introduce">
                </el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible3 = false">取 消</el-button>
              <el-button @click="update3(scope.row)" type="primary">确 定</el-button>
            </div>
          </el-dialog>
          <el-button @click="deleteBlog(scope.row)" type="text" size="small">删除</el-button>
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
      flag: '',
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
          varieties: '',
          success: ''
        }
      ],
      formLabelWidth: '100px',
      dialogFormVisible: false,
      dialogFormVisible1: false,
      dialogFormVisible2: false,
      dialogFormVisible3: false,
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
      },
      form1: {
        id: '',
        title: '',
        address: '',
        introduce: '',
        images: '',
        petname: '',
        petsex: '',
        petage: '',
        varieties: '',
        free: '',
        userId: ''
      },
      form2: {
        id: '',
        title: '',
        address: '',
        datetime: '',
        images: '',
        introduce: '',
        varieties: '',
        userId: ''
      },
      form3: {
        id: '',
        address: '',
        introduce: '',
        images: '',
        datetime: '',
        petsex: '',
        petage: '',
        varieties: '',
        time: '',
        userId: ''
      }
    }
  },
  methods: {
    // quxiao () {
    //   this.dialogFormVisible1 = false
    //   this.dialogFormVisible2 = false
    //   this.dialogFormVisible3 = false
    // },
    success1 (response, file) {
      console.log(response)
      if (response.success === true) {
        this.form1.images = response.message
        sessionStorage.setItem('imgUrl1', this.form1.images)
      }
    },
    uploadSectionFile1 (file) {
      this.form1.images = file.url
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
    success2 (response, file) {
      console.log(response)
      if (response.success === true) {
        this.form2.images = response.message
        sessionStorage.setItem('imgUrl2', this.form2.images)
      }
    },
    uploadSectionFile2 (file) {
      this.form2.images = file.url
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
    success3 (response, file) {
      console.log(response)
      if (response.success === true) {
        this.form3.images = response.message
        sessionStorage.setItem('imgUrl3', this.form3.images)
      }
    },
    uploadSectionFile3 (file) {
      this.form3.images = file.url
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
      const _this = this
      _this.currentPage = currentPage
      _this.getData(currentPage)
    },
    getData (currentPage) {
      const _this = this
      this.$axios.get('http://119.29.250.12:8081/findAll3/' + currentPage + '/5' + '/' + this.$route.query.id).then(function (resp) {
        console.log(resp)
        _this.tableData = resp.data.records
        _this.total = resp.data.total
      }).catch(function (error) {
        console.log(error)
      })
    },
    getBlog (row) {
      this.dialogFormVisible = true
      const _this = this
      if (row.category === '送养') {
        _this.dialogFormVisible1 = true
        this.$axios.get('http://119.29.250.12:8081/findUserUpload1/' + row.id).then(function (resp) {
          _this.form1 = resp.data
        })
      }
      if (row.category === '招领') {
        _this.dialogFormVisible2 = true
        this.$axios.get('http://119.29.250.12:8081/findUserUpload2/' + row.id).then(function (resp) {
          _this.form2 = resp.data
        })
      }
      if (row.category === '寻物启事') {
        _this.dialogFormVisible3 = true
        this.$axios.get('http://119.29.250.12:8081/findUserUpload3/' + row.id).then(function (resp) {
          _this.form3 = resp.data
        })
      }
    },
    update1 (row) {
      const _this = this
      if (sessionStorage.getItem('imgUrl1') !== '') {
        _this.form1.images = sessionStorage.getItem('imgUrl1')
      } else {
        _this.form1.images = row.images
      }
      this.$axios.post('http://119.29.250.12:8081/update1/' + row.id, _this.form1).then(function (resp) {
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
        } else {
          _this.$message({
            showClose: true,
            type: 'error',
            message: `修改失败`
          })
        }
        ;
      })
    },
    update2 (row) {
      const _this = this
      if (sessionStorage.getItem('imgUrl2') !== '') {
        _this.form2.images = sessionStorage.getItem('imgUrl2')
      } else {
        _this.form2.images = row.images
      }
      this.$axios.post('http://119.29.250.12:8081/update2/' + row.id, _this.form2).then(function (resp) {
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
        } else {
          _this.$message({
            showClose: true,
            type: 'error',
            message: `修改失败`
          })
        }
        ;
      })
    },
    update3 (row) {
      const _this = this
      if (sessionStorage.getItem('imgUrl3') !== '') {
        _this.form3.images = sessionStorage.getItem('imgUrl3')
      } else {
        _this.form3.images = row.images
      }
      this.$axios.post('http://119.29.250.12:8081/update3/' + row.id, _this.form3).then(function (resp) {
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
        } else {
          _this.$message({
            showClose: true,
            type: 'error',
            message: `修改失败`
          })
        }
        ;
      })
    },
    deleteBlog (row) {
      const _this = this
      this.$axios.get('http://119.29.250.12:8081/userUpload/delete/' + row.id).then(function (resp) {
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
    // delete (row) {
    //   this.$axios.get('http://119.29.250.12:8081/userUpload/delete/' + row.id).then(function (resp) {
    //   })
    // }
  },
  created () {
    this.userid = this.$route.query.id
    this.username = this.$route.query.username
    // this.currentTime = setInterval(() => {
    this.getData(this.currentPage)
    // }, 100)
  }
}
</script>

<style scoped>

</style>
