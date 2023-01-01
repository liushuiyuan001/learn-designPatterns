<template>
  <div class="header">
    <a-input class="search" v-model:value="name" placeholder="请输入名称"></a-input>
    <a-button type="primary" @click="handleAdd()">新增</a-button>
  </div>

  <a-table :columns="columns" :data-source="list" :pagination="pagination" @change="handlePageChange">
    <template #headerCell="{ column }">
      <template v-if="column.key === 'name'">
        <span>
          <smile-outlined />
          昵称
        </span>
      </template>
    </template>

    <template #bodyCell="{ column, record }">
      <template v-if="column.key === 'action'">
        <span>
          <a-divider type="vertical" />
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
  <a-modal v-model:visible="visible" title="用户表单" @ok="handleOk" :okButtonProps="{ loading: okLoading }">
    <a-form :model="ebook" :labelCol="{ span: 6 }">

      <a-form-item label="登录名">
        <a-input v-model:value="ebook.loginName" :disabled="!!ebook.id" placeholder="input placeholder" />
      </a-form-item>
      <a-form-item label="昵称">
        <a-input v-model:value="ebook.name" placeholder="input placeholder" />
      </a-form-item>
      <a-form-item label="密码">
        <a-input v-model:value="ebook.password" placeholder="input placeholder" />
      </a-form-item>

    </a-form>
  </a-modal>
</template>
<script lang="ts" setup>
import { ref, onMounted, reactive, watch } from 'vue';
import axios from 'axios'
import { SmileOutlined, DownOutlined } from '@ant-design/icons-vue';
import type { PageResp, Resp } from '@/type';

const list = ref([])
const pagination = reactive({
  pageSize: 4,
  current: 1,
  total: 0
})
const name = ref("")
const visible = ref(false)
const initData = {
  loginName: '',
  name:'',
  password: '',
}

let ebook = ref(initData)
const okLoading = ref(false)

onMounted(() => {
  handleQuery(1, pagination.pageSize )
})

watch(name, (newVal) => {
  handleQuery(pagination.current, pagination.pageSize)
})

const handleQuery = async(page: number , size: number) => {
  const res:PageResp = await axios.get('/user/list', {
    params: {page, size, loginName: name.value}
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

  const res = await axios.delete("/user/delete/" + record.id)
  pagination.current = 1
  handleQuery(1, pagination.pageSize)
}

const handleOk = async () => {
  okLoading.value = true;
  const res = await axios.post("/user/save",ebook.value)
  visible.value = false
  okLoading.value = false
  handleQuery(pagination.current, pagination.pageSize)
}

const columns = [
  {
    title: '登录名',
    dataIndex: 'loginName',
    key: 'loginName',
  },
  {
    title: '昵称',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: 'Action',
    key: 'action',
    width: '200px',
  },
];

</script>
<style scoped>
.header {
  display: flex;
  padding: 10px 20px;
  justify-content: space-between;
}
.search.ant-input {
  width: 400px;
}
</style>

