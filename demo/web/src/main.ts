import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';

import './assets/main.css'

import { message } from 'ant-design-vue';

import axios from 'axios'
import JsonBig from 'json-bigint'
axios.defaults.baseURL = import.meta.env.VITE_VUE_APP_SERVER

axios.defaults.transformResponse=[
  function (data:any) {
    const json = JsonBig({
      storeAsString: true,
    })
    const res = json.parse(data)
    return res
  }
]
/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
  console.log('请求参数：', config);
  // const token = store.state.user.token;
  // if (Tool.isNotEmpty(token)) {
  //   config.headers.token = token;
  //   console.log("请求headers增加token:", token);
  // }
  return config;
}, error => {
  return Promise.reject(error);
});
axios.interceptors.response.use(function (response) {
  console.log('返回结果：', response);
  if(!response.data.success) {
    message.error(response.data.message);
  }
  return response.data;
}, error => {
  console.log('返回错误：', error);
  const response = error.response;
  const status = response.status;
  if (status === 401) {
    // 判断状态码是401 跳转到首页或登录页
    console.log("未登录，跳到首页");
    // store.commit("setUser", {});
    // message.error("未登录或登录超时");
    router.push('/');
  }
  return Promise.reject(error);
});


const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(Antd)

app.mount('#app')

console.log('启动地址：', import.meta.env.MODE)
console.log('启动地址：', import.meta.env.VITE_VUE_APP_SERVER)