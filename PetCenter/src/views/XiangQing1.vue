<template>
  <div  style="display: flex;justify-content: space-around;">
    <div style="width: 1120px;padding: 10px;">
      <!--标签页-->
      <div style="background-color: white;padding: 10px;margin-bottom: 10px;">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item ><a @click=" removeIndex()" class="blog-title">首页</a></el-breadcrumb-item>
          <el-breadcrumb-item ><a @click="removePetCenter()">认领中心</a></el-breadcrumb-item>
          <el-breadcrumb-item :to="{ path: '/' }">认领页面</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <!--标题-->
      <div style="background-color: white;padding: 10px;display:flex;justify-content: space-between;">
        <div style="text-align: initial">
          <h2 style="display: contents;">{{HomeLessPet.title}}</h2>
        </div>
        <div>
          <el-tag type="success" v-if="this.$route.query.status == 0" style="font-size: initial;">待认领</el-tag>
          <el-tag type="danger" v-if="this.$route.query.status == 1" style="font-size: initial;">已认领</el-tag>
        </div>
      </div>
      <!--领养内容-->
       <div style="background-color: white;padding: 10px;">
        <div style="display: flex;justify-content: space-around;">
          <div style="width: 55%;">
            <el-carousel height="300px;">
              <el-carousel-item v-for="(image,index) in HomeLessPet.images" :key="index">
                <img  :src="image" style="width: 100%;height: 100%;"/>
              </el-carousel-item>
            </el-carousel>
          </div>
          <div class="HomeLessPet-message">
            <div>
              <p><i class="el-icon-time blog-icon"></i>发布时间: {{HomeLessPet.datetime}}</p>
            </div>
            <div>
              <p><i class="el-icon-location-outline blog-icon"></i>地点: {{HomeLessPet.address}}</p>
            </div>
            <div>
              <p><i class="el-icon-user blog-icon"></i>联系人： {{HomeLessPet_user.name}}</p>
            </div>
            <div>
              <p><i class="el-icon-phone blog-icon"></i>联系电话: {{HomeLessPet_user.phoneid}}</p>
            </div>
          </div>
        </div>
        <div style="margin-top: 40px">
          <el-divider content-position="left"><h3>详细描述</h3></el-divider>
          <div style="text-align: start;">
            <p>{{HomeLessPet.introduce}}</p>
          </div>
        <div>
          <el-button type="success" v-show="this.$route.query.status == 0&&flag&&flag1" @click="dialogFormVisibles()" round>认领</el-button>
          <el-dialog title="" :visible.sync="dialogFormVisible">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
              <el-form-item label="联系人" prop="name">
                <el-input v-model="ruleForm.name"
                          style="width: 25%;margin-left: -75%;">
                </el-input>
              </el-form-item>
              <el-form-item label="我的地址" prop="address">
                <el-input v-model="ruleForm.address"
                          style="width: 50%;margin-left: -50%;"
                          placeholder="xx省xx市xxx小区">
                </el-input>
              </el-form-item>
              <el-form-item label="手机号码" prop="phoneId">
                <el-input v-model="ruleForm.phoneId"
                          style="width: 50%;margin-left: -50%;"
                          placeholder="13888888888">
                </el-input>
              </el-form-item>
              <el-form-item label="微信号码" prop="wechatId">
                <el-input v-model="ruleForm.wechatId"
                          style="width: 50%;margin-left: -50%;">
                </el-input>
              </el-form-item>
              <el-form-item label="邮箱" prop="email" >
                <el-input v-model="ruleForm.email"></el-input>
              </el-form-item>
              <el-form-item label="QQ号码" prop="qqId">
                <el-input v-model="ruleForm.qqId"
                          style="width: 50%;margin-left: -50%;"
                          placeholder="选填">
                </el-input>
              </el-form-item>
              <el-form-item label="认领描述" prop="condit">
                <el-input
                  type="textarea"
                  :autosize="{ minRows: 6, maxRows: 10}"
                  placeholder="输入你的描述"
                  v-model="ruleForm.condit">
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
      </div>
        <div>
          <el-divider content-position="left"><h3>评论区</h3></el-divider>
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入内容"
            :autosize="{ minRows: 3, maxRows: 6}"
            v-model="textarea">
          </el-input>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'XiangQing1',
  data () {
    return {
      flag: '',
      flag1: '',
      textarea: '',
      username: '',
      dialogFormVisible: false,
      userid: '',
      HomeLessPet: {
        id: '',
        datetime: '',
        address: '',
        introduce: '',
        title: '',
        images: [],
        varieties: '',
        userId: ''
      },
      HomeLessPet_user: {
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
        homelesspettitle: '',
        condit: '',
        address: '',
        homelesspetId: '',
        userId: '',
        renlingId: ''
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
        path: '/',
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
      window.alert(formName)
      this.$refs[formName].validate((valid) => {
        window.alert(valid)
        _this.ruleForm.homelesspettitle = _this.HomeLessPet.title
        _this.ruleForm.homelesspetId = _this.HomeLessPet.id
        window.alert(_this.$route.query.rendingid + '222')
        if (valid) {
          window.alert(111111)
          this.$axios.post('http://localhost:8081/insert/renling', _this.ruleForm).then(function (resp) {
            window.alert(resp + '1111111')
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
            } ;
            _this.$router.go(-1)
          })
        } else {
          return false
        }
      })
    }
  },
  created () {
    // this.status = this.$route.query.status this.username = this.$route.query.username
    window.alert(this.$route.query.status)
    this.HomeLessPet_user.id = this.$route.query.userId
    this.ruleForm.userId = this.$route.query.userId
    this.HomeLessPet.id = this.$route.query.id
    this.ruleForm.renlingId = this.$route.query.renlingId
    window.alert(this.$route.query.renlingId + 'aaaaaaaa')
    const _this = this
    this.$axios.get('http://localhost:8081/querryisexit/' + _this.$route.query.id + '/' + _this.$route.query.renlingId).then(function (resp) {
      _this.flag = resp.data
      window.alert(_this.flag)
    })
    this.$axios.get('http://localhost:8081/querryisexit3/' + _this.$route.query.id + '/' + _this.$route.query.renlingId).then(function (resp) {
      _this.flag1 = resp.data
      window.alert(_this.flag1)
    })
    this.$axios.get('http://localhost:8081/findHomeLessPet/' + _this.$route.query.id).then(function (resp) {
      _this.HomeLessPet = resp.data
      _this.HomeLessPet.images = resp.data.images.split(';')
      window.alert(_this.HomeLessPet.address)
      window.alert(_this.HomeLessPet.title)
    })
    this.$axios.get('http://localhost:8081/user/selectbyid/' + _this.$route.query.userId).then(function (resp) {
      _this.HomeLessPet_user = resp.data
    })
    this.$axios.get('http://localhost:8081/user/select/' + _this.username).then(function (resp) {
      _this.user = resp.data
    })
  }
}
</script>

<style scoped>
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
