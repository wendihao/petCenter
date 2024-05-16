<template>
  <div style="display: flex">
    <div>
      <el-menu
        style="height: 100%;"
        default-active="1"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose">
        <div v-for="set in sets" :key="set.id" class="chat-message">
        <el-menu-item index="index"  @click="removeim(username,set.fromname)">
          <i class="el-icon-menu"></i>
          <span slot="title">{{set.fromname}}</span>
        </el-menu-item>
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
    // this.currentTime = setInterval(() => {
    this.$axios.get('http://localhost:8081/findAContactByUsername/' + this.$route.query.username).then(function (resp) {
      window.alert(resp.data.length)
      for (let i = 0; i < resp.data.length; i++) {
        window.alert(resp.data[i].length)
        _this.sets.push({id: resp.data[i].length, fromname: resp.data[i]})
      }
    })
    // }, 2000)
  }
}
</script>
<style >
</style>
