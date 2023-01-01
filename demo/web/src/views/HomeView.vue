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
            <YuqueOutlined />
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
                <span>
                  <FileSearchOutlined style="margin-right: 8px" />
                  文档数 {{ item.docCount }}
                </span>
                <span>
                  <EyeOutlined style="margin-right: 8px" />
                  浏览数 {{ item.viewCount }}
                </span> 
                <span>
                  <LikeOutlined style="margin-right: 8px" />
                  点赞数 {{ item.voteCount }}
                </span>
              </template>
              <a-list-item-meta :description="item.description">
                <template #title>
                  <RouterLink :to="'/docView?ebookId=' + item.id  ">
                    {{ item.name }}
                  </RouterLink>
                </template>
                <template #avatar>
                  <a-avatar :size="50" style="background-color: #1890ff">{{ item.name[0] }}</a-avatar>
                </template>
              </a-list-item-meta>
            </a-list-item>
          </template>
      </a-list>
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts" setup>
import { YuqueOutlined, LikeOutlined, FileSearchOutlined, EyeOutlined } from '@ant-design/icons-vue';
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
  handleQuery(item.key)
}

</script>
