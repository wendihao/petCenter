<template >
  <!--  <div class="roomStyle">-->
  <!--<br>欢迎使用<strong>VueTest</strong>极简聊天室：<br/><br/>-->

  <!--<textarea id="content" v-model="content" cols="60" rows="30" readonly="readonly"></textarea><br>-->
  <!--<input type="text" v-model="message">-->
  <!--<button type="button" @click="sendMsg()">发送消息</button>-->
  <!--    <button @click="joinRoom()">加入群聊</button>-->
  <!--    <button @click="exitRoom()">退出群聊</button>-->
  <!--<br/><br/>-->
  <!--</div>-->
<!--  <div class="chat-container">-->
<!--    <div class="chat-messages">-->
<!--      <div v-for="contact in contacts" :key="contact.id" class="chat-message">-->
<!--        <div v-if="contact.fromname === result.fromname" class="message-right" style="min-height: 50px;max-height: 500px;width: 100%;margin-top: 20px">-->
<!--          <img :src="contact.portrait" style="height:50px;width: 50px;float: right" alt=""/>-->
<!--          <span style="float: right; height: 30px; padding-right: 10px;padding-top: 20px" > {{ contact.msg}}</span>-->
<!--        </div>-->
<!--        <div v-if="contact.fromname === result.toname" class="message-left" style="min-height: 50px;max-height: 500px;width: 100%;margin-top: 20px">-->
<!--          <img :src="contact.portrait" style="height:50px;width: 50px;float: left" alt=""/>-->
<!--          <span style="float: left; height: 30px; padding-left: 10px;padding-top: 20px"> {{ contact.msg }}</span>-->
<!--        </div>-->
<!--      </div>-->
<!--    </div>-->
<!--    <div class="chat-input">-->
<!--      <input v-model="newMessage" @keypress.enter="sendMsg" placeholder="Type a message..." />-->
<!--      <button @click="sendMsg">Send</button>-->
<!--    </div>-->
<!--  </div>-->
  <div>
    <!--  <div class="roomStyle">-->
    <!--<br>欢迎使用<strong>VueTest</strong>极简聊天室：<br/><br/>-->

    <!--<textarea id="content" v-model="content" cols="60" rows="30" readonly="readonly"></textarea><br>-->
    <!--<input type="text" v-model="message">-->
    <!--<button type="button" @click="sendMsg()">发送消息</button>-->
    <!--    <button @click="joinRoom()">加入群聊</button>-->
    <!--    <button @click="exitRoom()">退出群聊</button>-->
    <!--<br/><br/>-->
    <!--</div>-->
    <div style="background-color: white;padding: 0.1rem;width: 100%">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item style="margin-top: 5%;padding-bottom: 3%"><a @click="back()" style="font-size: 15px; ">{{this.return}}返回</a></el-breadcrumb-item>
      </el-breadcrumb>
      <h1 style="margin-left: 44%;margin-top: -5%;font-size: 15px;font-weight: bolder">{{this.result.toname}}</h1>
      <hr style="margin-top: 2% ;margin-bottom: 0;">
    </div>
    <div class="chat-container" style="height:10rem;width: 100%">
      <div class="chat-messages" style="height: 10rem;background-color: whitesmoke;margin-top: -1%">
        <div v-for="contact in contacts" :key="contact.id" class="chat-message" >
          <div v-if="contact.fromname === result.fromname" class="message-right" style="overflow: auto;width: 100%;margin-top: 0.2rem">
            <img :src="contact.portrait" style="height:1rem;width: 1rem;float: right" alt=""/>
            <span style="height: 0.5rem;float: right;margin-right: 0px"> {{contact.fromname}}</span>
            <span style="float: right;margin-top:0.5rem;margin-right: -1rem;padding-right: 0.1rem;margin-left: 0.1rem;padding-top: 0.2rem;line-height: 0.5rem;
                   max-height: 50%;
                   max-width:  80%;
                   min-height:  1rem;
                   min-width:   1rem;
                   text-align: center;
                   border-radius: 0.2rem 0 0.2rem 0.2rem;
                  border-top-right-radius: 0px;
                   background-color: #4F7cff;
                  color: white;"> {{ contact.msg }}</span>
          </div>
          <div v-if="contact.fromname === result.toname" class="message-left" style="overflow: auto;width: 100%;margin-top: 0.2rem">
            <img :src="contact.portrait" style="height:1rem;width: 1rem;float: left" alt=""/>
            <span style="height: 0.5rem;float: left;margin-left: 0px"> {{contact.fromname}}</span>
            <span style="float: left;margin-top:0.5rem;margin-left: -1rem;padding-right: 0.1rem;margin-right: 0.1rem;padding-top: 0.2rem;line-height: 0.5rem;
                   max-height: 50%;
                   max-width:  80%;
                   min-height:  1rem;
                   min-width:   1rem;
                   text-align: center;
                   border-radius:0 0.2rem 0.2rem 0.2rem;
                  border-top-right-radius: 0px;
                   background-color:  #99a9bf;
                  color: black;"> {{ contact.msg }}</span>
          </div>
        </div>
      </div>
    </div>
    <div class="chat-input" style="height:40%;width:100%">
      <el-input
        type="textarea"
        :autosize="{ minRows: 2, maxRows: 8}"
        placeholder="请输入消息"
        v-model="newMessage"
        @keypress.enter="sendMsg"
        style="width:70%;">
      </el-input>
      <!--    <el-button type="success" @click="joinRoom()" style="margin-left: -300px;margin-top: -100px;width: 100px;height: 40px">加入聊天</el-button>-->
      <!--    <el-button type="info" @click="exitRoom()" style="margin-left: -100px;margin-bottom: -100px;width: 100px;height: 40px">退出聊天</el-button>-->
      <!--        <input v-model="newMessage" style="font-size: 16px" @keypress.enter="sendMsg" placeholder="请输入消息" >-->
      <!--        </input>-->
      <!--        <el-input-->
      <!--          type="textarea"-->
      <!--          :autosize="{ minRows: 10, maxRows: 150}"-->
      <!--          placeholder="请输入消息"-->
      <!--          v-model="newMessage">-->
      <!--        </el-input>-->
      <div style=" width:30% ;float: right;">
          <el-button type="primary" @click="sendMsg" style="height: 1rem;width: 100%">发送</el-button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'AllChat',
  data () {
    return {
      return: '<',
      textarea: '',
      username: '',
      messages: [],
      newMessage: '',
      contacts: [
        {
          id: '',
          fromname: '',
          toname: '',
          msg: '',
          portrait: ''
        }
      ],
      result: {
        fromname: '',
        toname: '',
        from_id: '',
        to_id: '',
        portrait: ' ',
        msg: ''
      },
      user: {
        username: '',
        portrait: ''
      },
      message: '',
      content: ''
    }
  },
  methods: {
    back () {
      this.$router.go(-1)
    },
    sendMsg () {
      const _this = this
      _this.result.msg = _this.newMessage
      _this.$axios.post('http://119.29.250.12:8081/contact', _this.result).then(function (resp) {
        if (resp.data === 'success') {
          _this.$router.go(0)
        }
      })
      this.newMessage = ''
    }
  },
  created () {
    const _this = this
    _this.username = this.$route.query.username
    this.$axios.get('http://119.29.250.12:8081/findAllContact/' + this.$route.query.username + '/' + this.$route.query.username1).then(function (resp) {
      _this.contacts = resp.data
    })
    this.$axios.get('http://119.29.250.12:8081/user/select/' + _this.$route.query.username1).then(function (resp) {
      _this.result.toname = resp.data.username
      _this.result.to_id = resp.data.id
    })
    this.$axios.get('http://119.29.250.12:8081/user/select/' + _this.$route.query.username).then(function (resp) {
      _this.user = resp.data
      _this.result.fromname = resp.data.username
      _this.result.portrait = resp.data.portrait
      _this.result.from_id = resp.data.id
    })
  }
}
</script>
<style scoped>
.chat-container {
  display: flex;
  flex-direction: column;
}

.chat-messages {
  flex: 1;
  overflow-y: auto;
}

.chat-message {
  margin: 5px;
}

.message-right {
  text-align: right;
}
.message-left {
  text-align: left;
}

.chat-input {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px;
  border-top: 1px solid #ccc;
}

.chat-input input {
  flex: 1;
  padding: 5px;
}

.chat-input button {
  margin-left: 10px;
}
</style>
