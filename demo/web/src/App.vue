<template>
  <a-layout>
    <a-layout-header class="header">
      <div class="logo" />
      <a-menu
        v-model:selectedKeys="selectedKeys1"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
      >
        <a-menu-item key="1">
          <RouterLink to="/">Home</RouterLink>
        </a-menu-item>
        <a-menu-item key="ebook">
          <RouterLink to="/ebook">电子书管理</RouterLink>
        </a-menu-item>
        <a-menu-item key="category">
          <RouterLink to="/category">分类管理</RouterLink>
        </a-menu-item>
        <a-menu-item key="2">
          <RouterLink to="/about">About</RouterLink>
        </a-menu-item>
      </a-menu>
    </a-layout-header>
    <a-layout>

      <RouterView />
    </a-layout>
    <a-layout-footer style="text-align: center">
      Ant Design ©2018 Created by Ant UED
    </a-layout-footer>
  </a-layout>
</template>
<script lang="ts" setup>
import { ref } from 'vue';
import { RouterLink, RouterView } from 'vue-router';
const selectedKeys1 = ref<string[]>(['1'])
</script>
<style>
.ant-layout {
  height: 100%;
}
#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>
