<template>
  <div class="header">
    <a-button type="primary" @click="handleAdd()">新增</a-button>
  </div>

  <a-table :columns="columns" :data-source="list" :pagination="false">
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
  <a-modal v-model:visible="visible" title="分类管理" @ok="handleOk" :okButtonProps="{ loading: okLoading }">
    <a-form :model="category" :labelCol="{ span: 6 }">

      <a-form-item label="名称">
        <a-input v-model:value="category.name" placeholder="请输入名称" />
      </a-form-item>
      <a-form-item label="父分类">
        <a-select
          ref="select"
          v-model:value="category.parent"
        >
          <a-select-option :value="0">无</a-select-option>
          <a-select-option :value="v.id" v-for="v in list" :key="v.id" :disabled="v.id === category.id">{{ v.name }}</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="排序">
        <a-input v-model:value="category.sort" placeholder="input placeholder" />
      </a-form-item>

    </a-form>
  </a-modal>
</template>
<script lang="ts" setup>
import { ref, onMounted, reactive, watch } from 'vue';
import axios from 'axios'
import { SmileOutlined, DownOutlined } from '@ant-design/icons-vue';
import { formatTree } from '@/util';
import type { Resp } from '@/type';

const list = ref(<any[]>[])

const visible = ref(false)
const initData = {
  id: 0,
  name:'',
  parent: 0,
  sort: 0
}

let category = ref(initData)
const okLoading = ref(false)

onMounted(() => {
  handleQuery()
})

const handleQuery = async() => {
  const res:Resp = await axios.get('/category/all')
  const data = formatTree(res.content)
  list.value = data
}

const handleAdd = () => {
  visible.value = true;
  category.value = initData
}

const handleEdit = (record: any) => {
  visible.value = true;
  category.value = { ...record }
}

const handleDelete = async(record: any) => {

  const res = await axios.delete("/category/delete/" + record.id)
  handleQuery()
}

const handleOk = async () => {
  okLoading.value = true;
  const res = await axios.post("/category/save",category.value)
  visible.value = false
  okLoading.value = false
  handleQuery()
}

const columns = [
  {
    title: '名称',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: '父分类',
    dataIndex: 'parentId',
  },
  {
    title: '排序',
    dataIndex: 'sort',
  },
  {
    title: 'Action',
    key: 'action',
  }
];

</script>
<style scoped>
.header {
  display: flex;
  padding: 10px 20px;
  justify-content: space-between;
}
</style>

