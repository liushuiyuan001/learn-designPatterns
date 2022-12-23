<template>
  <a-layout-sider width="200" style="background: #fff">
  <a-menu
    mode="inline"
    :style="{ height: '100%', borderRight: 0 }"
    @click="handleMenuClick"
  >
    <a-sub-menu :key="p.id" v-for="p in categoryList">
      <template #title>
        <span>
          <user-outlined />
          {{ p.name }}
        </span>
      </template>
      <a-menu-item :key="c.id" v-for="c in p.children">{{ c.name }}</a-menu-item>
    </a-sub-menu>
  </a-menu>
  </a-layout-sider>
  <a-layout style="padding: 24px 24px 0px">
    <a-layout-content
      :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <a-list item-layout="vertical" size="large" :pagination="false" :grid="{ gutter: 20, column: 3 }" :data-source="ebookList">
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
import { formatTree  } from '@/util';
import { RouterLink, RouterView } from 'vue-router';
import type { Resp, PageResp } from '@/type';
const categoryList = ref(<any[]>[])
let ebookList = ref([])

onMounted(async() => {
  handleQuery()
  handleCategoryQuery()
})

const handleQuery = async(category2Id?:any) => {
  const res:PageResp = await axios.get('/ebook/list', {
    params: {
      page: 1,
      size: 10000,
      category2Id
    }
  })
  ebookList.value = res.content.list
}

const handleCategoryQuery = async() => {
  const res:Resp = await axios.get('/category/all')
  const data = formatTree(res.content)
  categoryList.value = data
}

const handleMenuClick = (item:any) => {
  console.log('111', item)
  handleQuery(item.key)
}

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
