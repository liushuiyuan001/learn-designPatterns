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
          名称
        </span>
      </template>
    </template>

    <template #bodyCell="{ column, record }">
      <template v-if="column.key === 'name'">
        <a>
          <RouterLink :to="'/docView?ebookId=' + record.id  ">
            {{ record.name }}
          </RouterLink>
        </a>
      </template>
      <template v-if="column.key === 'category'">
        {{ getCategoryName(record.category1Id) + '/' + getCategoryName(record.category2Id) }}
      </template>
      <template v-else-if="column.key === 'action'">
        <span>
          <RouterLink :to="'/docView?ebookId=' + record.id  ">
            <a-button type="link">查看文档</a-button>
          </RouterLink>
          <a-divider type="vertical" />
          <RouterLink :to="'/doc?ebookId=' + record.id  ">
            <a-button type="normal" @click="handleEdit(record)">文档管理</a-button>
          </RouterLink>
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
  <a-modal v-model:visible="visible" title="电子书表单" @ok="handleOk" :okButtonProps="{ loading: okLoading }">
    <a-form :model="ebook" :labelCol="{ span: 6 }">

      <a-form-item label="封面">
        <a-input v-model:value="ebook.cover" placeholder="input placeholder" />
      </a-form-item>
      <a-form-item label="名称">
        <a-input v-model:value="ebook.name" placeholder="input placeholder" />
      </a-form-item>
      <a-form-item label="分类">
        <a-cascader v-model:value="categoryIds" :fieldNames="{label: 'name', value: 'id'}" :options="categoryList" placeholder="Please select" />
      </a-form-item>
      <a-form-item label="描述">
        <a-input v-model:value="ebook.description" placeholder="input placeholder" />
      </a-form-item>

    </a-form>
  </a-modal>
</template>
<script lang="ts" setup>
import { ref, onMounted, reactive, watch } from 'vue';
import axios from 'axios'
import { SmileOutlined, DownOutlined } from '@ant-design/icons-vue';
import type { PageResp, Resp } from '@/type';
import { formatTree  } from '@/util';
import { RouterLink } from 'vue-router';

const list = ref([])
const categoryIds = ref(<any[]>[])
const categoryList = ref(<any[]>[])
const pagination = reactive({
  pageSize: 4,
  current: 1,
  total: 0
})
const name = ref("")
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
  handleCategoryQuery()
})

watch(name, () => {
  handleQuery(pagination.current, pagination.pageSize)
})

const handleCategoryQuery = async() => {
  const res:Resp = await axios.get('/category/all')
  const data = formatTree(res.content)
  categoryList.value = data
}

const handleQuery = async(page: number , size: number) => {
  const res:PageResp = await axios.get('/ebook/list', {
    params: {page, size, name: name.value}
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
  categoryIds.value = [record.category1Id, record.category2Id]
  ebook.value = { ...record }
}

const handleDelete = async(record: any) => {

  const res = await axios.delete("/ebook/delete/" + record.id)
  pagination.current = 1
  handleQuery(1, pagination.pageSize)
}

const handleOk = async () => {
  okLoading.value = true;
  ebook.value.category1Id = categoryIds.value[0]
  ebook.value.category2Id = categoryIds.value[1]
  const res = await axios.post("/ebook/save",ebook.value)
  visible.value = false
  okLoading.value = false
  handleQuery(pagination.current, pagination.pageSize)
}

const getCategoryName = (id: number) => {
  return getCategoryNameById(categoryList.value,id)
}

const getCategoryNameById = (cateList: any[], id: number): string|undefined => {
  for (let i = 0; i < cateList.length; i++) {
    const item = cateList[i];
    if(item.id == id){
      return item.name
    }
    const name = getCategoryNameById(item.children || [], id)
    if(name !== '') {
      return name
    }
  }
  return ''
}

const columns = [
  {
    title: '封面',
    dataIndex: 'cover',
    key: 'cover',
  },
  {
    title: '名称',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: '分类',
    key: 'category',
  },
  {
    title: '描述',
    dataIndex: 'description',
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

