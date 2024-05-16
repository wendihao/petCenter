<template>
<!--  <div>-->
<!--    <div>-->
<!--      &lt;!&ndash;左边&ndash;&gt;-->
<!--      <div>-->
        <!--头部-->
        <!--内容-->
        <div style="width: 100% ;height:120%;background-color: white;margin-top:0.5rem;margin-bottom: 1.5rem;">
          <div v-for="blog in petBlogs" :key="blog.id">
<!--          <div v-for="blog in petBlogs">-->
            <div style="margin-left: 0.2rem;margin-top:0.2rem" @click="toPetXiangQing(blog.id,blog.userId)" class="blog-title">
              <img :src="blog.user.portrait" style="width: 1rem;height: 1rem;float: left" alt="" >
              <div style="text-align: start; ">{{blog.user.username}}{{':'}}{{blog.title}}</div>
              <div style="display: flex;margin-top: 0.4rem;">
                <img :src="blog.images" style="width: 3rem;height:3rem;margin-left: 0.4rem;" >
              </div>
              <div style="display: flex;margin-top: 0.4rem;margin-left:1.5rem; font-size: 0.3rem;color: #979fa8;">
<!--                <div style="margin: 0px 6px 0px 2px;" v-if="blog.user"><i>{{blog.user.name}}</i></div>-->
                <div><i class="el-icon-view" style="margin-left: 0.2rem;"></i><span style="margin-left: 0.2rem;">{{blog.pageview}}</span></div>
                <div><i class="el-icon-s-comment" style="margin-left: 0.2rem;"></i><span style="margin-left: 0.2rem;">{{blog.comment}}</span></div>
              </div>
            </div>
          </div>
        </div>
        <!--分页-->
<!--        <div style="margin-left: 100px">-->
<!--          <el-pagination-->
<!--            background-->
<!--            layout="prev, pager, next"-->
<!--            :page-size=2-->
<!--            :total="total"-->
<!--            :current-page="currentPage"-->
<!--            @current-change="handlerPageChange">-->
<!--          </el-pagination>-->
<!--        </div>-->
<!--      </div>-->
      <!--右边-->
<!--      <div>-->
<!--        <div style="width: 7rem;">-->
<!--          <div>-->
<!--            <el-button type="success" @click="dialogFormVisible = true" icon="el-icon-search" plain>发布分享</el-button>-->
<!--            <el-dialog title="发布分享" :visible.sync="dialogFormVisible">-->
<!--              <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="2.5rem" class="demo-ruleForm">-->
<!--                <el-form-item label="宠物相册" style="width: 12rem;">-->
<!--                  <div>-->
<!--                    <el-upload-->
<!--                      ref="upload"-->
<!--                      multiple-->
<!--                      list-type="picture"-->
<!--                      :auto-upload="false"-->
<!--                      :http-request="uploadFile"-->
<!--                      action="http://119.29.250.12:8081/file/uploads">-->
<!--                      <el-button slot="trigger" size="small" type="primary" style="margin-left: -7rem;">选取文件</el-button>-->
<!--                    </el-upload>-->
<!--                  </div>-->
<!--                  <div style="margin-top:0.4rem; margin-left: -0.4rem;">-->
<!--                    <el-button style="margin-left: -6rem;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>-->
<!--                    <div slot="tip" style="margin-left: -4rem" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
<!--                  </div>-->
<!--                </el-form-item>-->
<!--                <el-form-item label="分享标题" prop="title">-->
<!--                  <el-input v-model="ruleForm.title"-->
<!--                            style="width: 50%;margin-left: -50%;"-->
<!--                            placeholder="标题内容">-->
<!--                  </el-input>-->
<!--                </el-form-item>-->
<!--                <el-form-item label="分享内容" prop="introduce">-->
<!--                  <el-input-->
<!--                    type="textarea"-->
<!--                    :autosize="{ minRows: 6, maxRows: 10}"-->
<!--                    placeholder="输入你想要分享的内容吧"-->
<!--                    v-model="ruleForm.introduce">-->
<!--                  </el-input>-->
<!--                </el-form-item>-->
<!--                <el-form-item>-->
<!--                  <el-button type="info" round>返回</el-button>-->
<!--                  <el-button type="success" round>保存</el-button>-->
<!--                  <el-button type="primary" @click="onSubmit('ruleForm')" round>发布</el-button>-->
<!--                </el-form-item>-->
<!--              </el-form>-->
<!--            </el-dialog>-->
<!--          </div>-->
<!--          <div style="text-align: left"><h3>我发布的</h3></div>-->
<!--          <el-divider></el-divider>-->
<!--          <div class="border-blog">-->
<!--            <div  v-for="(pet,index) in myBlogs" :key="index" v-if="index<5" style="margin-top:-0.4rem">-->
<!--              <div @click="toPetXiangQing(pet.id,id)" class="blog-title">-->
<!--                <div style="display: flex">-->
<!--                  <div  v-if="user"><img :src="user.portrait" style="width: 1rem;height: 1rem" alt=""></div>-->
<!--                  <div  v-if="user" style="margin-left: 0.4rem;text-align: left">-->
<!--                    <a style="text-decoration: none;color: #029789;">{{user.name}}</a>-->
<!--                    <a style="text-decoration: none;margin-top: 0.4rem"><div style="font-size: xx-small;color: #888">{{pet.title}}</div></a>-->
<!--                  </div>-->
<!--                </div>-->
<!--                <div style="font-size: x-small;text-overflow: ellipsis;text-align: left;margin-top: -0.4rem;" class="p-overflow"><p>{{pet.introduce}}</p></div>-->
<!--              </div>-->
<!--              <div style="margin-top: -0.5rem">-->
<!--                <el-divider></el-divider>-->
<!--              </div>-->
<!--            </div>-->
<!--          </div>-->
<!--        </div>-->
<!--      </div>-->
<!--    </div>-->
</template>

<script>
import axios from 'axios'
export default {
  name: 'PetCircle',
  data () {
    return {
      height: '',
      id: '',
      currentPage: 1,
      total: null,
      dialogFormVisible: false,
      username: '',
      petBlogs: [
        {
          id: '',
          title: '',
          introduce: '',
          images: [],
          userId: '',
          pageview: '',
          datetime: '',
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
        username: '',
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
        name: 'PetXiangQingM',
        query: {
          id: id,
          userId: userId,
          username: this.username
        }
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
            message: '上传成功',
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
    this.id = this.$route.query.id
    this.ruleForm.userId = this.id
    this.username = this.$route.query.username
    const _this = this
    this.$axios.get('http://119.29.250.12:8081/querryAllPetCircle/').then(function (resp) {
      _this.petBlogs = []
      for (let i = 0; i < resp.data.length; i++) {
        _this.petBlogs[i] = resp.data[i]
        _this.petBlogs[i].images = resp.data[i].images.split(';')
      }
      console.log(_this.petBlogs)
    })
    this.$axios.get('http://119.29.250.12:8081/petBlog/querryAll/' + _this.$route.query.id).then(function (resp) {
      _this.myBlogs = resp.data
    })
    this.$axios.get('http://119.29.250.12:8081/user/selectbyid/' + _this.$route.query.id).then(function (resp) {
      _this.user = resp.data
    })
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
