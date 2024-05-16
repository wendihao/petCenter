<template>
  <div>
    <div style="display: flex;justify-content: space-around;">
      <!--左边-->
      <div>
        <!--头部-->
        <!--内容-->
        <div>
          <div v-for="(blog,index) in petBlogs" :key="index">
<!--          <div v-for="blog in petBlogs">-->
            <div style="margin-top: 10px;" @click="toPetXiangQing(blog.id,blog.userId)" class="blog-title">
              <img :src="blog.user.portrait" style="width: 48px;height: 48px;float: left" alt="" >
              <div style="text-align: start; ">{{blog.user.name}}{{':'}}{{blog.title}}</div>
              <div style="display: flex;margin-top: 10px;">
                <img :src="blog.images" style="width: 300px;height:300px;margin-left: 15px;" >
              </div>
              <div style="display: flex;margin-top: 10px;margin-left:60px; font-size: 13px;color: #979fa8;">
<!--                <div style="margin: 0px 6px 0px 2px;" v-if="blog.user"><i>{{blog.user.name}}</i></div>-->
                <div><i class="el-icon-view" style="margin-left: 5px;"></i><span style="margin-left: 5px;">{{blog.pageview}}</span></div>
                <div><i class="el-icon-s-comment" style="margin-left: 5px;"></i><span style="margin-left: 5px;">{{blog.comment}}</span></div>
              </div>
            </div>
          </div>
        </div>
        <!--分页-->
        <div style="margin-left: 100px">
          <el-pagination
            background
            layout="prev, pager, next"
            :page-size=2
            :total="total"
            :current-page="currentPage"
            @current-change="handlerPageChange">
          </el-pagination>
        </div>
      </div>
      <!--右边-->
      <div>
        <div style="width: 300px;">
          <div>
            <el-button type="success" @click="dialogFormVisible = true" icon="el-icon-search" plain>发布分享</el-button>
            <el-dialog title="发布分享" :visible.sync="dialogFormVisible">
              <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="宠物相册" style="width: 500px;">
                  <div>
                    <el-upload
                      ref="upload"
                      multiple
                      list-type="picture"
                      :auto-upload="false"
                      :http-request="uploadFile"
                      action="http://119.29.250.12:8081/file/uploads">
                      <el-button slot="trigger" size="small" type="primary" style="margin-left: -300px;">选取文件</el-button>
                    </el-upload>
                  </div>
                  <div style="margin-top: 10px; margin-left: -10px;">
                    <el-button style="margin-left: -270px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
                    <div slot="tip" style="margin-left: -170px" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                  </div>
                </el-form-item>
                <el-form-item label="分享标题" prop="title">
                  <el-input v-model="ruleForm.title"
                            style="width: 50%;margin-left: -50%;"
                            placeholder="标题内容">
                  </el-input>
                </el-form-item>
                <el-form-item label="分享内容" prop="introduce">
                  <el-input
                    type="textarea"
                    :autosize="{ minRows: 6, maxRows: 10}"
                    placeholder="输入你想要分享的内容吧"
                    v-model="ruleForm.introduce">
                  </el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="info" round>返回</el-button>
                  <el-button type="success" round>保存</el-button>
                  <el-button type="primary" @click="onSubmit('ruleForm')" round>发布</el-button>
                </el-form-item>
              </el-form>
            </el-dialog>
          </div>
          <div style="text-align: left"><h3>我发布的</h3></div>
          <el-divider></el-divider>
          <div class="border-blog">
            <div  v-for="(pet,index) in myBlogs" :key="index" v-if="index<5" style="margin-top:-10px">
              <div @click="toPetXiangQing(pet.id,id)" class="blog-title">
                <div style="display: flex">
                  <div  v-if="user"><img :src="user.portrait" style="width: 48px;height: 48px" alt=""></div>
                  <div  v-if="user" style="margin-left: 10px;text-align: left">
                    <a style="text-decoration: none;color: #029789;">{{user.name}}</a>
                    <a style="text-decoration: none;margin-top: 10px"><div style="font-size: xx-small;color: #888">{{pet.title}}</div></a>
                  </div>
                </div>
                <div style="font-size: x-small;text-overflow: ellipsis;text-align: left;margin-top: -10px;" class="p-overflow"><p>{{pet.introduce}}</p></div>
              </div>
              <div style="margin-top: -20px">
                <el-divider></el-divider>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import axios from 'axios'
export default {
  name: 'PetCircle',
  data () {
    return {
      id: '',
      currentPage: 1,
      total: null,
      dialogFormVisible: false,
      username: '',
      images: [
        {img: require('../../assets/img/pet1.jpg')},
        {img: require('../../assets/img/pet2.jpg')}
      ],
      petBlogs: [
        {
          id: '',
          title: '',
          introduce: '',
          images: [],
          userId: '',
          pageview: '',
          comment: '',
          petuser: {
            name: ''
          },
          user: {
            name: '',
            portrait: ''
          }
        }
      ],
      myBlogs: [
        {
          id: '',
          title: '',
          introduce: '',
          images: []
        }
      ],
      user: {
        name: '',
        portrait: ''
      },
      ruleForm: {
        title: '',
        introduce: '',
        pageview: 0,
        comment: 0,
        images: '',
        userId: ''
      },
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' },
          { min: 3, message: '标题不少于3个字', trigger: 'blur' }
        ],
        introduce: [
          {required: true, message: '请输入内容', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    toPetXiangQing (id, userId) {
      this.$router.push({
        path: '/petxiangqing',
        query: {
          id: id,
          userId: userId,
          username: this.username
        }
      })
    },
    handlerPageChange (currentPage) {
      const _this = this
      _this.currentPage = currentPage
      _this.getData(currentPage)
    },
    getData (currentPage) {
      const _this = this
      this.$axios.get('http://119.29.250.12:8081/petBlog/querryAll/' + currentPage + '/2').then(function (resp) {
        _this.petBlogs = resp.data.records
        for (let i = 0; i < resp.data.records.length; i++) {
          _this.petBlogs[i].images = resp.data.records[i].images.split(';')
        }
        _this.total = resp.data.total
        console.log(_this.petBlogs)
      })
    },
    uploadFile (file) {
      this.formData.append('files', file.file)
    },
    submitUpload () {
      this.formData = new FormData()
      this.$refs.upload.submit()
      this.formData.append('WS_CODE', '12133')
      let config = {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }
      axios.post('http://119.29.250.12:8081/file/uploads', this.formData, config).then(res => {
        this.ruleForm.images = res.data.message
        if (res.data.success === true) {
          this.$message({
            message: '发布成功',
            type: 'success'
          })
        }
      }).catch(reason => {
        console.log(reason)
      })
    },
    onSubmit (formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post('http://119.29.250.12:8081/petBlog/insertPetBlog', _this.ruleForm).then(function (resp) {
            if (resp.data === 'success') {
              _this.$alert('《' + _this.ruleForm.title + '》', '添加成功', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$message({
                    showClose: true,
                    type: 'success',
                    message: `添加成功`
                  })
                  _this.$router.go(0)
                }
              })
            }
          })
        } else {
          return false
        }
      })
    }
  },
  created () {
    // this.currentTime = setInterval(() => {
    this.getData(this.currentPage)
    this.id = this.$route.query.id
    this.ruleForm.userId = this.id
    this.username = this.$route.query.username
    const _this = this
    this.$axios.get('http://119.29.250.12:8081/petBlog/querryAll/' + _this.$route.query.id).then(function (resp) {
      _this.myBlogs = resp.data
    })
    this.$axios.get('http://119.29.250.12:8081/user/selectbyid/' + _this.$route.query.id).then(function (resp) {
      _this.user = resp.data
    })
    // }, 100)
  }
}
</script>
<style scoped>
  .p-overflow{
    font-size: x-small;
    overflow:hidden;
    text-overflow:ellipsis;
    display:-webkit-box;
    -webkit-box-orient:vertical;
    -webkit-line-clamp: 2;
  }
  .blog-title:hover{
    cursor: pointer;
  }
  .border-blog{
    background-color: white;
    padding: 10px;
    border-style: groove;
    border-color: #ddd;
    border: 10px solid #00000000;
  }
  .blog-input{
    margin-left: -90px;
    width: 50%;
  }
</style>
