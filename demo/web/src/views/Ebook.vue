<template>
  <a-button type="primary" @click="handleAdd()" size="large">点击</a-button>
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
      <template v-else-if="column.key === 'action'">
        <span>
          <a-button type="primary" @click="handleEdit(record)">编辑</a-button>
          <a-divider type="vertical" />
          <a-popconfirm
            title="Are you sure delete this task?"
            ok-text="Yes"
            cancel-text="No"
            @confirm="handleDelete(record)"
          >
            <a-button type="danger">删除</a-button>
          </a-popconfirm>
        </span>
      </template>
    </template>
  </a-table>
  <a-modal v-model:visible="visible" title="Basic Modal" @ok="handleOk" :okButtonProps="{ loading: okLoading }">
    <a-form :model="ebook" :labelCol="{ span: 6 }">

      <a-form-item label="封面">
        <a-input v-model:value="ebook.cover" placeholder="input placeholder" />
      </a-form-item>
      <a-form-item label="名称">
        <a-input v-model:value="ebook.name" placeholder="input placeholder" />
      </a-form-item>
      <a-form-item label="分类一">
        <a-input v-model:value="ebook.category1Id" placeholder="input placeholder" />
      </a-form-item>
      <a-form-item label="分类二">
        <a-input v-model:value="ebook.category2Id" placeholder="input placeholder" />
      </a-form-item>
      <a-form-item label="描述">
        <a-input v-model:value="ebook.description" placeholder="input placeholder" />
      </a-form-item>

    </a-form>
    </a-modal>
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
const visible = ref(false)
const initData = {
  cover: '',
  name:'',
  category1Id: '',
  category2Id: '',
  description: '',
  docCount: 0,
  viewCount: 0,
  voteCount: 0
}

let ebook = ref(initData)
const okLoading = ref(false)

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

const handleAdd = () => {
  visible.value = true;
  ebook.value = initData
}

const handleEdit = (record: any) => {
  visible.value = true;
  ebook.value = { ...record }
}

const handleDelete = async(record: any) => {

  const res = await axios.delete("/ebook/delete/" + record.id)
  pagination.current = 1
  handleQuery(1, pagination.pageSize)
}

const handleOk = async () => {
  okLoading.value = true;
  const res = await axios.post("/ebook/save",ebook.value)
  visible.value = false
  okLoading.value = false
  handleQuery(pagination.current, pagination.pageSize)
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

