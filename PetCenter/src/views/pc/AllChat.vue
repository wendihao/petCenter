<template>
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
        <div v-for="message in messages" :key="message.id" class="chat-message" >
          <div v-if="message.sender === 'me'" class="message-right" style="min-height: 80px;max-height: 500px;width: 100%;margin-top: 20px">
              <img :src="message.portrait" style="height:60px;width: 60px;float: right" alt=""/>
            <span style="height: 20px;float: right;margin-right: 0px"> {{message.username}}</span>
              <span style="float: right; height: 30px; margin-top:30px;margin-right: -50px;padding-right: 5px;margin-left: 5px;padding-top: 10px;line-height: 25px;
                   max-height: 550px;
                   max-width:  250px;
                   min-height:  40px;
                   min-width:    50px;
                   text-align: center;
                   border-radius: 10px;
                  border-top-right-radius: 0px;
                   background-color: #4F7cff;
                  color: white;"> {{ message.content }}</span>
          </div>
          <div v-if="message.sender === 'other'" class="message-left" style="min-height: 80px;max-height: 500px;width: 100%;margin-top: 20px">
            <img :src="message.portrait" style="height:60px;width: 60px;float: left" alt=""/>
            <span style="height: 20px;float: left;margin-left: 0px"> {{message.username}}</span>
            <span style="float: left; height: 30px; margin-top:30px;margin-left: -50px;padding-left: 5px;margin-right:5px;padding-top: 10px;line-height: 25px;
                  max-height: 550px;
                   max-width:  250px;
                   min-height:  40px;
                   min-width:    50px;
                    text-align: center;
                 background-color: #f4f5f7;
                 border-radius: 10px;
                 border-top-left-radius: 0px;"> {{ message.content }}</span>
          </div>
          <div v-if="message.sender === 'system'" class="message-center" style="height: 40px;width: 100%">
            <span style="text-align: center;height: 40px;padding-top: 20px"> {{message.username+":"+ message.content }}</span>
          </div>
        </div>
      </div>
    </div>
  <div class="chat-input" style="margin-bottom: 0px;float: left">
<!--    <el-button type="success" @click="joinRoom()" style="margin-left: -300px;margin-top: -100px;width: 100px;height: 40px">加入聊天</el-button>-->
<!--    <el-button type="info" @click="exitRoom()" style="margin-left: -100px;margin-bottom: -100px;width: 100px;height: 40px">退出聊天</el-button>-->
    <textarea v-model="newMessage" style="font-size: 16px" cols="120" rows="10" @keypress.enter="sendMsg" placeholder="请输入消息" >
        </textarea>
    <!--        <el-input-->
    <!--          type="textarea"-->
    <!--          :autosize="{ minRows: 10, maxRows: 150}"-->
    <!--          placeholder="请输入消息"-->
    <!--          v-model="newMessage">-->
    <!--        </el-input>-->
        <el-button type="success" @click="joinRoom()" style="float:left;margin-top: -100px;width: 100px;height: 40px">加入聊天</el-button>
        <el-button type="info" @click="exitRoom()" style="margin-left: -98px;width: 100px;height: 40px">退出聊天</el-button>
    <el-button type="primary" @click="sendMsg" style="margin-left: -98px;margin-top:100px;height: 40px;width: 100px">发送</el-button>
  </div>
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
        window.alert('你已经在聊天室')
        return
      }
      let username = this.$route.query.username
      this.ws = new WebSocket('ws://119.29.250.12:8081/Room/' + username) // 后端的启动端口
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
      if (res.portrait === '0') {
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
    this.$axios.get('http://119.29.250.12:8081/user/select/' + _this.$route.query.username).then(function (resp) {
      _this.user = resp.data
    })
  }
}
</script>
<style scoped>
.chat-container {
  background-color: white;
  display: flex;
  flex-direction: column;
  height: 800px;
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
