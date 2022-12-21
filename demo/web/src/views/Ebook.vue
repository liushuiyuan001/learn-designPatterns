<template>
  <a-table :columns="columns" :data-source="list" :pagination="pagination" @change="handlePageChange">
    <template #headerCell="{ column }">
      <template v-if="column.key === 'name'">
        <span>
          <smile-outlined />
          Name
        </span>
      </template>
    </template>

    <template #bodyCell="{ column, record }">
      <template v-if="column.key === 'name'">
        <a>
          {{ record.name }}
        </a>
      </template>
      <template v-else-if="column.key === 'tags'">
        <span>
          <a-tag
            v-for="tag in record.tags"
            :key="tag"
            :color="tag === 'loser' ? 'volcano' : tag.length > 5 ? 'geekblue' : 'green'"
          >
            {{ tag.toUpperCase() }}
          </a-tag>
        </span>
      </template>
      <template v-else-if="column.key === 'action'">
        <span>
          <a>Invite 一 {{ record.name }}</a>
          <a-divider type="vertical" />
          <a>Delete</a>
        </span>
      </template>
    </template>
  </a-table>
</template>
<script lang="ts" setup>
import { ref, onMounted, reactive } from 'vue';
import axios from 'axios'
import { SmileOutlined, DownOutlined } from '@ant-design/icons-vue';
import type { Resp } from '@/type';

const list = ref([])
const pagination = reactive({
  pageSize: 4,
  current: 1,
  total: 0
})
onMounted(() => {
  handleQuery(1, pagination.pageSize )
})

const handleQuery = async(page: number , size: number) => {
  const res:Resp = await axios.get('/ebook/list', {
    params: {page, size}
  })
  list.value = res.content.list
  pagination.total = res.content.total
}

const handlePageChange = (pageObj: any) => {
  pagination.current = pageObj.current
  pagination.total = pageObj.total
  pagination.pageSize = pageObj.pageSize

  handleQuery(
    pageObj.current,
    pageObj.pageSize
  )
}
const columns = [
{
    name: '封面',
    dataIndex: 'cover',
    key: 'cover',
  },
  {
    name: '名称',
    dataIndex: 'name',
    key: 'name',
  },
  {
    name: '封面一',
    key: 'category1Id',
  },
  {
    name: '封面二',
    key: 'category2Id',
  },
  {
    title: '描述',
    dataIndex: 'description',
    key: 'description',
  },
  {
    title: '文档数',
    dataIndex: 'docCount',
  },
  {
    title: '阅读数',
    dataIndex: 'viewCount',
  },
  {
    title: '点赞数',
    dataIndex: 'voteCount',
  },
  {
    title: 'Action',
    key: 'action',
  },
];

</script>

