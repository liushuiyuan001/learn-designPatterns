<template>
  <a-layout-sider width="200" style="background: #fff">
  <a-menu
    v-model:selectedKeys="selectedKeys2"
    v-model:openKeys="openKeys"
    mode="inline"
    :style="{ height: '100%', borderRight: 0 }"
  >
    <a-sub-menu key="sub1">
      <template #title>
        <span>
          <user-outlined />
          subnav 1
        </span>
      </template>
      <a-menu-item key="1">option1</a-menu-item>
      <a-menu-item key="2">option2</a-menu-item>
      <a-menu-item key="3">option3</a-menu-item>
      <a-menu-item key="4">option4</a-menu-item>
    </a-sub-menu>
    <a-sub-menu key="sub2">
      <template #title>
        <span>
          <laptop-outlined />
          subnav 2
        </span>
      </template>
      <a-menu-item key="5">option5</a-menu-item>
      <a-menu-item key="6">option6</a-menu-item>
      <a-menu-item key="7">option7</a-menu-item>
      <a-menu-item key="8">option8</a-menu-item>
    </a-sub-menu>
    <a-sub-menu key="sub3">
      <template #title>
        <span>
          <notification-outlined />
          subnav 3
        </span>
      </template>
      <a-menu-item key="9">option9</a-menu-item>
      <a-menu-item key="10">option10</a-menu-item>
      <a-menu-item key="11">option11</a-menu-item>
      <a-menu-item key="12">option12</a-menu-item>
    </a-sub-menu>
  </a-menu>
  </a-layout-sider>
  <a-layout style="padding: 24px 24px 0px">
    <a-layout-content
      :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <a-list item-layout="vertical" size="large" :pagination="pagination" :grid="{ gutter: 20, column: 3 }" :data-source="ebookList">
          <template #renderItem="{ item }">
            <a-list-item key="item.name">
              <template #actions>
                <span v-for="{ type, text } in actions" :key="type">
                  <component :is="hashMap[type]" style="margin-right: 8px" />
                  {{ text }}
                </span>
              </template>
              <a-list-item-meta :description="item.description">
                <template #title>
                  <a :href="item.href">{{ item.name }}</a>
                </template>
                <template #avatar><a-avatar :size="50" shape="square" :src="item.cover" /></template>
              </a-list-item-meta>
            </a-list-item>
          </template>
      </a-list>
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts" setup>
import { StarOutlined, LikeOutlined, MessageOutlined } from '@ant-design/icons-vue';
import { ref, onMounted, reactive } from 'vue';
import axios from 'axios'
import { RouterLink, RouterView } from 'vue-router';
const selectedKeys2 = ref<string[]>(['1'])
const openKeys = ref<string[]>(['sub1'])
let ebookList = ref([])
onMounted(async() => {
  const res = await axios.get('/ebook/list')
  console.log('res.data.content', res)
  ebookList.value = res.content
})

const pagination = {
  onChange: (page: number) => {
    console.log(page);
  },
  pageSize: 3,
};

const hashMap: Record<string, any> = {
  'StarOutlined': StarOutlined,
  'LikeOutlined': LikeOutlined,
  'MessageOutlined': MessageOutlined
}

const actions: Record<string, string>[] = [
  { type: 'StarOutlined', text: '156' },
  { type: 'LikeOutlined', text: '156' },
  { type: 'MessageOutlined', text: '2' },
];

</script>
