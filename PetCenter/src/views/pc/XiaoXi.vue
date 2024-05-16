<template>
  <div style="display: flex">
    <div>
      <el-menu
        style="height: 100%;"
        default-active="1"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose">
        <div v-for="set in sets" :key="set.id" class="chat-message" style="width: 400px;height: 100px" @click="removeim(username,set.fromname)">
        <el-menu-item index="index" style="float: left;width: 100%;height: 0px">
<!--          <i class="el-icon-menu"></i>-->
          <img :src="set.portrait" style="width: 80px;height: 80px;float: left;margin-top: 10px" alt="">
          <span slot="title" style="font-size: 20px;float: left">{{set.fromname}}</span>
<!--          <span v-if="set.num!==0" style="float:right;width: 40px;height: 20px;background-color: red;border-radius: 20px;margin-top:15px;padding-top:5px;display: inline-block;text-align: center;line-height: 50%">{{set.num}}</span>-->
<!--          <span v-if="set.num>99" style="float:right;width: 40px;height: 20px;background-color: red;border-radius: 20px;margin-top:15px;padding-top:5px;display: inline-block;text-align: center;line-height: 50%">99+</span>-->
        </el-menu-item>
          <span v-if="set.num!==0" style="color:white;float:right;width: 40px;height: 20px;background-color: red;border-radius: 20px;margin-top:15px;padding-top:5px;display: inline-block;text-align: center;line-height: 50%">{{set.num}}</span>
          <span v-if="set.num>99" style="color:white;float:right;width: 40px;height: 20px;background-color: red;border-radius: 20px;margin-top:15px;padding-top:5px;display: inline-block;text-align: center;line-height: 50%">99+</span>
          <i class="el-icon-s-comment" style="float: left;margin-top: 30px;margin-left:310px;font-size: 50px"></i>
        </div>
      </el-menu>
    </div>
    <div>
      <router-view></router-view>
    </div>
  </div>
</template>
<script>
export default {
  name: 'XiaoXi',
  data () {
    return {
      imageUrl: '',
      username: '',
      sets: [],
      ruleForm: {
        id: '',
        username: ''
      },
      // messageNum: [{
      //   username: '',
      //   portrait: '',
      //   num: ''
      // }],
      contacts: [
        {
          id: '',
          fromname: '',
          toname: '',
          msg: '',
          portrait: ''
        }
      ]
    }
  },
  methods: {
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    },
    removeim (username, username1) {
      this.$axios.get('http://119.29.250.12:8081/updateContact/' + username + '/' + username1).then(function (resp) {
      })
      this.$router.push({
        path: '/im',
        query: {
          username: username,
          username1: username1
        }
      })
    }
  },
  created () {
    const _this = this
    _this.username = _this.$route.query.username
    // _this.currentTime = setInterval(() => {
    // this.$axios.get('http://119.29.250.12:8081/findAContactByUsername/' + this.$route.query.username).then(function (resp) {
    //   _this.sets = []
    //   for (let i = 0; i < resp.data.length; i++) {
    //     _this.sets.push({id: i, portrait: resp.data[i].portrait, fromname: resp.data[i].username})
    //   }
    // })
    _this.$axios.get('http://119.29.250.12:8081/findAContactByUsername/' + _this.$route.query.username).then(function (resp) {
      _this.sets = []
      for (let i = 0; i < resp.data.length; i++) {
        _this.sets.push({id: i, portrait: resp.data[i].portrait, fromname: resp.data[i].username, num: resp.data[i].num})
      }
    })
    // }, 100)
  }
}
</script>
<style >
</style>
