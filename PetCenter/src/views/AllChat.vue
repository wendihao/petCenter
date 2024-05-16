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
    <div class="chat-container">
      <div class="chat-messages">
        <div v-for="message in messages" :key="message.id" class="chat-message">
          <div v-if="message.sender === 'me'" class="message-right" style="min-height: 50px;max-height: 500px;width: 100%;margin-top: 20px">
              <img :src="message.portrait" style="height:50px;width: 50px;float: right" alt=""/>
              <span style="float: right; height: 30px; padding-right: 10px;padding-top: 20px"> {{ message.content }}</span>
          </div>
          <div v-if="message.sender === 'other'" class="message-left" style="min-height: 50px;max-height: 500px;width: 100%;margin-top: 20px">
            <img :src="message.portrait" style="height:50px;width: 50px;float: left" alt=""/>
            <span style="float: left; height: 30px; padding-left: 10px;padding-top: 20px"> {{ message.content }}</span>
          </div>
          <div v-if="message.sender === 'system'" class="message-center" style="height: 40px;width: 100%">
            <span style="text-align: center;height: 40px;padding-top: 20px"> {{message.username+":"+ message.content }}</span>
          </div>
        </div>
      </div>
      <div class="chat-input">
        <input v-model="newMessage" @keypress.enter="sendMsg" placeholder="Type a message..." />
        <button @click="sendMsg">Send</button>
      </div>
      <button @click="joinRoom()">加入群聊</button>-->
      <button @click="exitRoom()">退出群聊</button>-->
    </div>
</template>
<script>
export default {
  name: 'AllChat',
  data () {
    return {
      url: 'ws://' + window.location.host + '/Room/',
      ws: null,
      textarea: '',
      username: '',
      messages: [],
      newMessage: '',
      result: {
        username: '',
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
    async joinRoom () {
      if (this.ws) {
        window.alert('你已经再聊天室')
        return
      }
      let username = this.$route.query.username
      this.ws = new WebSocket('ws://127.0.0.1:8081/Room/' + username) // 后端的启动端口
      this.ws.onopen = this.webscoketonopen
      this.ws.onmessage = this.webscoketonmessage
      // 发生错误触发
      this.ws.onerror = function () {
        console.log('连接错误')
      }
      // 正常关闭触发
      this.ws.onclose = function () {
        console.log('连接关闭')
      }
    },
    webscoketonopen () {
      console.log('与服务器成功建立连接')
    },
    webscoketonmessage (value) {
      let dataStr = value.data
      let res = JSON.parse(dataStr)
      this.result = res
      window.alert(this.result.msg)
      if (res.portrait == 0) {
        window.alert(666)
        this.messages.push({id: res.msg.length, username: res.username, portrait: res.portrait, content: res.msg, sender: 'system'})
      } else {
        this.messages.push({id: res.msg.length, username: res.username, portrait: res.portrait, content: res.msg, sender: 'other'})
      }
      console.log(value)
      // this.content += (res + '\r\n')
      // window.alert(value.data.portrait)
      // this.content += (value.data.portrait + value.data.username + value.data.msg + '\r\n')
    },
    exitRoom () {
      this.closeWebSocket()
    },
    sendMsg () {
      if (!this.ws) {
        alert('你已经掉线，请重新加入')
        return
      }
      if (this.ws.readyState === 1) {
        window.alert(this.user.portrait)
        this.messages.push({ id: this.messages.length, username: this.user.username, portrait: this.user.portrait, content: this.newMessage, sender: 'me' })
        this.ws.send(this.newMessage)
        this.newMessage = ''
      } else {
        alert('发送失败')
      }
    },
    closeWebSocket () {
      if (this.ws) {
        this.ws.close()
        this.ws = null
      }
    },
    talking (content) {
      console.log(content)
    }
  },
  created () {
    const _this = this
    _this.username = this.$route.query.username
    window.alert(this.username)
    this.$axios.get('http://localhost:8081/user/select/' + this.$route.query.username).then(function (resp) {
      _this.user = resp.data
    })
  }
}
</script>
<style>
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
.message-center {
  text-align: center;
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
