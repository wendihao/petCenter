<template>
  <div  style="display: flex;justify-content: space-around;">
    <div style="width: 100%">
      <!--标签页-->
      <div style="background-color: white;padding: 0.2rem;margin-bottom: 0.2rem;">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item style="margin-top: 5%"><a @click="back()" style="font-size: 15px;">{{this.return}}返回</a></el-breadcrumb-item>
        </el-breadcrumb>
        <el-divider></el-divider>
      </div>
      <!--标题-->
      <div style="background-color: white;padding: 0.2rem;display:flex;justify-content: space-between;">
        <div style="text-align: initial">
          <h2 style="display: contents;">{{blog.title}}</h2>
        </div>
        <div>
          <el-tag type="success" v-if="this.$route.query.status == 0" style="font-size: initial;">待领养</el-tag>
          <el-tag type="danger" v-if="this.$route.query.status == 1" style="font-size: initial;">已领养</el-tag>
        </div>
      </div>
      <!--领养内容-->
       <div style="background-color: beige;padding: 0.2rem;">
        <div style="display: flex;justify-content: space-around;">
          <div style="width: 55%;height: 100%">
            <el-carousel style="height:100%">
              <el-carousel-item v-for="(image,index) in blog.images" :key="index">
                <img  :src="image" style="width: 80%;height: 80%;margin-left: 10%;margin-top: 10%"/>
              </el-carousel-item>
            </el-carousel>
          </div>
          <div class="blog-message">
            <div>
              <el-tag effect="dark" type="success" style="font-size: 14px;padding: 0px;" v-if="blog.free===0">免费领养</el-tag>
              <el-tag effect="dark" type="warning" style="font-size: 14px;padding: 0px;" v-if="blog.free===1">有偿领养</el-tag>
            </div>
            <div>
              <p style="font-size: 14px"><i class="el-icon-time blog-icon"></i>发布时间: {{blog.datetime}}</p>
            </div>
            <div>
              <p style="font-size: 14px"><i class="el-icon-location-outline blog-icon"></i>地点: {{blog.address}}</p>
            </div>
            <div>
              <p style="font-size: 14px" v-if="blog.varieties===1"><i class="el-icon-position blog-icon" ></i>品种: 猫 ({{blog.petsex===1?'公':'母'}})</p>
              <p style="font-size: 14px" v-if="blog.varieties===0"><i class="el-icon-position blog-icon" ></i>品种: 狗 ({{blog.petsex===1?'公':'母'}})</p>
              <p style="font-size: 14px" v-if="blog.varieties===2"><i class="el-icon-position blog-icon" ></i>品种: 其他 ({{blog.petsex===1?'公':'母'}})</p>
            </div>
            <div>
              <p style="font-size: 14px"><i class="el-icon-c-scale-to-original blog-icon"></i>年龄: {{blog.petage}}</p>
            </div>
            <div>
              <p style="font-size: 14px"><i class="el-icon-user blog-icon"></i>联系人： {{blog_user.name}}</p>
            </div>
            <div>
              <p style="font-size: 14px"><i class="el-icon-phone blog-icon"></i>联系电话: {{blog_user.phoneid}}</p>
            </div>
          </div>
        </div>
        <div style="margin-top: 0.5rem">
          <el-divider content-position="left"><h3>宠物介绍</h3></el-divider>
          <div style="text-align: start;">
            <p>{{blog.introduce}}</p>
          </div>
        <div>
          <el-button type="success" style="margin-left: 40%" v-show="this.$route.query.status == 0&&this.$route.query.userId!=this.$route.query.adopterId&&flag" @click="openFun()" round>领养</el-button>
        </div>
        </div>
       </div>
      <div v-show="MaskBe" class="MaskCss" @click="closeFun()"></div>
          <div  :class="['Popup',openBe?'openActive':'']">
<!--          <el-dialog title="" :visible.sync="dialogFormVisible">-->
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="2rem" class="demo-ruleForm">
              <el-form-item label="联系人" prop="name">
                <el-input v-model="ruleForm.name"
                          style="width: 40%;">
                </el-input>
              </el-form-item>
              <el-form-item label="我的地址" prop="address">
                <el-input v-model="ruleForm.address"
                          style="width: 80%;"
                          placeholder="xx省xx市xxx小区">
                </el-input>
              </el-form-item>
              <el-form-item label="手机号码" prop="phoneId">
                <el-input v-model="ruleForm.phoneId"
                          style="width: 80%;"
                          placeholder="13888888888">
                </el-input>
              </el-form-item>
              <el-form-item label="微信号码" prop="wechatId">
                <el-input v-model="ruleForm.wechatId"
                          style="width: 80%;">
                </el-input>
              </el-form-item>
              <el-form-item label="邮箱" prop="email" >
                <el-input v-model="ruleForm.email"  style="width: 80%;"></el-input>
              </el-form-item>
              <el-form-item label="QQ号码" prop="qqId">
                <el-input v-model="ruleForm.qqId"
                          style="width: 80%;"
                          placeholder="选填">
                </el-input>
              </el-form-item>
              <el-form-item label="领养条件" prop="condit">
                <el-input
                  style="width: 80%;"
                  type="textarea"
                  :autosize="{ minRows: 3, maxRows: 4}"
                  placeholder="输入你领养的条件"
                  v-model="ruleForm.condit">
                </el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="info" round>返回</el-button>
                <el-button type="success" round>保存</el-button>
                <el-button type="primary" @click="onSubmit('ruleForm')" round>发布</el-button>
              </el-form-item>
            </el-form>
        </div>
      </div>
      </div>
</template>

<script>
export default {
  name: 'XiangQing',
  data () {
    return {
      return: '<',
      openBe: false, // 是否打开弹出层
      MaskBe: false, // 是否开启遮罩层
      flag: '',
      flag1: '',
      textarea: '',
      username: '',
      dialogFormVisible: false,
      userid: '',
      blog: {
        id: '',
        datetime: '',
        address: '',
        introduce: '',
        title: '',
        images: [],
        varieties: '',
        free: 0,
        petadopt: '',
        petname: '',
        petsex: '',
        petage: '',
        userId: ''
      },
      blog_user: {
        id: '',
        address: '',
        email: '',
        name: '',
        phoneid: ''
      },
      user: {
        id: '',
        name: '',
        phoneid: '',
        email: ''
      },
      ruleForm: {
        name: '',
        phoneId: '',
        qqId: '',
        wechatId: '',
        email: '',
        blogtitle: '',
        condit: '',
        address: '',
        blogId: '',
        userId: '',
        adopterId: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入名字', trigger: 'blur' }
        ],
        condit: [
          {required: true, message: '请输入内容', trigger: 'blur'}
        ],
        address: [
          {required: true, message: '请输入地址', trigger: 'blur'}
        ],
        phoneId: [
          {required: true, message: '请输入电话号码', trigger: 'blur'},
          { min: 11, max: 11, message: '电话格式不正确', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ],
        wechatId: [
          {required: true, message: '请输入微信号', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    openFun () {
      this.MaskBe = true
      this.openBe = true
    },
    // 点击遮罩则关闭弹层~
    closeFun () {
      this.MaskBe = false
      this.openBe = false
    },
    back () {
      this.$router.go(-1)
    },
    toPetXiangQing (id) {
      this.$router.push({
        path: '/petxiangqing',
        query: {
          id: id,
          username: this.username
        }
      })
    },
    removeIndex () {
      this.$router.push({
        path: '/indexM',
        query: {
          id: this.blog.userId,
          username: this.user.username
        }
      })
    },
    removePetCenter () {
      this.$router.push({
        path: '/shouyangpet',
        query: {
          id: this.userid,
          username: this.user.username
        }
      })
    },
    dialogFormVisibles () {
      if (this.$store.state.token === '') {
        this.$router.push('/login')
        this.$message({
          type: 'error',
          message: '你还没登录'
        })
      } else {
        this.dialogFormVisible = true
        this.ruleForm.name = this.user.name
        this.ruleForm.phoneId = this.user.phoneid
        this.ruleForm.email = this.user.email
      }
    },
    onSubmit (formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        _this.ruleForm.blogtitle = _this.blog.title
        _this.ruleForm.blogId = _this.blog.id
        _this.ruleForm.adopterId = _this.$route.query.adopterId
        if (valid) {
          this.$axios.post('http://119.29.250.12:8081/insert/adoption', _this.ruleForm).then(function (resp) {
            if (resp.data === 'success') {
              _this.$alert('提交成功', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$message({
                    showClose: true,
                    type: 'success',
                    message: `提交成功`
                  })
                }
              })
              _this.$router.go(-1)
            } ;
          })
        }
      })
    }
  },
  created () {
    // this.status = this.$route.query.status
    // window.alert(this.status)
    this.username = this.$route.query.username
    this.blog_user.id = this.$route.query.userId
    this.ruleForm.userId = this.$route.query.userId
    this.blog.id = this.$route.query.id
    const _this = this
    this.$axios.get('http://119.29.250.12:8081/querryisexit1/' + _this.$route.query.id + '/' + _this.$route.query.adopterId).then(function (resp) {
      _this.flag = resp.data
    })
    // this.$axios.get('http://119.29.250.12:8081/querryisexit2/' + _this.$route.query.id + '/' + _this.$route.query.adopterId).then(function (resp) {
    //   _this.flag1 = resp.data
    //   window.alert(_this.flag1)
    // })
    this.$axios.get('http://119.29.250.12:8081/findBlog/' + _this.$route.query.id).then(function (resp) {
      _this.blog = resp.data
      _this.blog.images = resp.data.images.split(';')
    })
    this.$axios.get('http://119.29.250.12:8081/user/selectbyid/' + _this.$route.query.userId).then(function (resp) {
      _this.blog_user = resp.data
    })
    this.$axios.get('http://119.29.250.12:8081/user/select/' + _this.username).then(function (resp) {
      _this.user = resp.data
    })
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
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.3);
  z-index: 10000;
}
.world{
  font-family: "PingFang SC", "Helvetica Neue", Helvetica, "Hiragino Sans GB", Arial, "Microsoft YaHei", 微软雅黑, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol";
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}
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
.blog-message{
  font-size: medium;
  margin-top: 20px;
  width: 50%;
  text-align: initial;
  padding-left: 20px;
}
.blog-icon{
  margin-right: 5px;
  color: #ff6363;
}
.blog-title:hover{
  cursor: pointer;
}
</style>
