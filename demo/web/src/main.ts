import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';

import './assets/main.css'
import './util/customRequest'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(Antd)

app.mount('#app')

console.log('启动地址：', import.meta.env.MODE)
console.log('启动地址：', import.meta.env.VITE_VUE_APP_SERVER)