<template>
  <div v-if="!userStore.loginName" class="login" @click="visible = true">登录</div>
  <div v-else class="login">
    {{ userStore.loginName }}
    <span @click="handleLogout">退出登录</span>
  </div>
  <a-modal v-model:visible="visible" title="登录" @ok="handleOk" :okButtonProps="{ loading: okLoading }">
    <a-form :model="ebook" :labelCol="{ span: 6 }">

      <a-form-item label="登录名">
        <a-input v-model:value="ebook.loginName" placeholder="input placeholder" />
      </a-form-item>
      <a-form-item label="密码">
        <a-input v-model:value="ebook.password" placeholder="input placeholder" />
      </a-form-item>

    </a-form>
  </a-modal>
</template>
<script lang="ts" setup>
import { ref, onMounted, computed, reactive, watch } from 'vue';
import { useUserStore } from '@/stores/user';
import axios from 'axios'
import { notification } from 'ant-design-vue';
import type { Resp } from '@/type';
import { uuid } from '@/util/index'
const userStore = useUserStore()
const visible = ref(false)
const initData = {
  loginName: '',
  password: '',
}

let ebook = ref(initData)
const okLoading = ref(false)

let websocket: any;
let token: any;
const onOpen = () => {
  console.log('WebSocket连接成功，状态码：', websocket.readyState)
};
const onMessage = (event: any) => {
  console.log('WebSocket收到消息：', event.data);
  notification['info']({
    message: '收到消息',
    description: event.data,
  });
};
const onError = () => {
  console.log('WebSocket连接错误，状态码：', websocket.readyState)
};
const onClose = () => {
  console.log('WebSocket连接关闭，状态码：', websocket.readyState)
};
const initWebSocket = () => {
  // 连接成功
  websocket.onopen = onOpen;
  // 收到消息的回调
  websocket.onmessage = onMessage;
  // 连接错误
  websocket.onerror = onError;
  // 连接关闭的回调
  websocket.onclose = onClose;
};

onMounted(() => {
  // WebSocket
  if ('WebSocket' in window) {
    token = uuid(10);
    // 连接地址：ws://127.0.0.1:8880/ws/xxx
    const WSSERVEER = import.meta.env.VITE_VUE_APP_WSSERVER
    websocket = new WebSocket(WSSERVEER + '/ws/' + token);
    initWebSocket()

    // 关闭
    // websocket.close();
  } else {
    alert('当前浏览器 不支持')
  }
});

const handleOk = async() => {
  okLoading.value = true;
  const res:Resp = await axios.post('/user/login', ebook.value)
  userStore.updateLoginName(res.content.loginName)
  userStore.updateLoginToken(res.content.token)
  okLoading.value = false;
  visible.value = false;
}

const handleLogout = async() => {
  const res:Resp = await axios.get('/user/logout/' + userStore.loginToken)
  userStore.updateLoginToken('')
  userStore.updateLoginName('')
}

</script>
<style>
.login {
  color: #fff;
}
</style>