<template>
  
</template>

<script setup>
import { ref, reactive, onMounted, onUnmounted } from 'vue'
const wsUrl = ref('ws://127.0.0.1:8002/')
const socket = ref(null)

const initWebSocket = () => {
  socket.value = new WebSocket(wsUrl.value)
  socket.value.onopen = () => {
    console.log("连接成功")
    sendMessage("xxx");
  }
  socket.value.onmessage = (e) => {
    console.log(e, "--广播返回的消息")
  }
  socket.value.onerror = (e) => {
    console.log("连接错误", e)
  }
}

const sendMessage = (msg) => {
  console.log(msg, "0");
  socket.value.send(msg);
}

const closeSocket = () => {
  socket.value.close();
}

onMounted(() => {
  initWebSocket()
})

onUnmounted(() => {
  closeSocket()
})

</script>

<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}

.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}

.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
