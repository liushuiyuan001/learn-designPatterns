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
import type { Resp } from '@/type';
const userStore = useUserStore()
const visible = ref(false)
const initData = {
  loginName: '',
  password: '',
}

let ebook = ref(initData)
const okLoading = ref(false)

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