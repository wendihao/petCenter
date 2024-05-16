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
    <div class="chat-container" style="height: 600px">
      <div class="chat-messages" style="height: 600px">
        <div v-for="contact in contacts" :key="contact.id" class="chat-message" >
          <div v-if="contact.fromname === result.fromname" class="message-right" style="min-height: 80px;max-height: 500px;width: 100%;margin-top: 20px">
            <img :src="contact.portrait" style="height:80px;width: 80px;float: right" alt=""/>
            <span style="height: 20px;float: right;margin-right: 0px"> {{contact.fromname}}</span>
            <span style="float: right; height: 30px; margin-top:30px;margin-right: -40px;padding-right: 5px;margin-left: 5px;padding-top: 10px;line-height: 25px;
                   max-height: 550px;
                   max-width:  250px;
                   min-height:  40px;
                   min-width:    50px;
                   text-align: center;
                   border-radius: 10px;
                  border-top-right-radius: 0px;
                   background-color: #4F7cff;
                  color: white;"> {{ contact.msg }}</span>
          </div>
          <div v-if="contact.fromname === result.toname" class="message-left" style="min-height: 80px;max-height: 500px;width: 100%;margin-top: 20px">
            <img :src="contact.portrait" style="height:80px;width: 80px;float: left" alt=""/>
            <span style="height: 20px;float: left;margin-left: 0px"> {{contact.fromname}}</span>
            <span style="float: left; height: 30px; margin-top:30px;margin-left: -40px;padding-left: 5px;margin-right:5px;padding-top: 10px;line-height: 25px;
                  max-height: 550px;
                   max-width:  250px;
                   min-height:  40px;
                   min-width:    50px;
                    text-align: center;
                 background-color: lightgoldenrodyellow;
                 border-radius: 10px;
                 border-top-left-radius: 0px;"> {{ contact.msg }}</span>
          </div>
        </div>
      </div>
    </div>
    <div class="chat-input" style="margin-bottom: 0px;float: left">
      <textarea v-model="newMessage" style="font-size: 16px;float: left" cols="150" rows="10;" @keypress.enter="sendMsg" placeholder="请输入消息" >
      </textarea>
      <!--        <el-input-->
      <!--          type="textarea"-->
      <!--          :autosize="{ minRows: 10, maxRows: 150}"-->
      <!--          placeholder="请输入消息"-->
      <!--          v-model="newMessage">-->
      <!--        </el-input>-->
      <el-button type="primary" @click="sendMsg" style="height: 40px;width: 100px">发送</el-button>
  </div>
  </div>
</template>
<script>
export default {
  name: 'AllChat',
  data () {
    return {
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
    this.currentTime = setInterval(() => {
      this.$axios.get('http://119.29.250.12:8081/findAllContact/' + this.$route.query.username + '/' + this.$route.query.username1).then(function (resp) {
        _this.contacts = resp.data
      })
    }, 100)
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
  height: 400px;
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
