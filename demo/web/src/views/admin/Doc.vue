<template>
  <div class="header">
    <a-button type="primary" @click="handleAdd()">新增</a-button>
  </div>

  <a-table :columns="columns" :data-source="list" :pagination="false">
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
  <a-modal v-model:visible="visible" title="分类管理" @ok="handleOk" :okButtonProps="{ loading: okLoading }">
    <a-form :model="doc" :labelCol="{ span: 6 }">

      <a-form-item label="名称">
        <a-input v-model:value="doc.name" placeholder="请输入名称" />
      </a-form-item>
      <a-form-item label="父分类">
        <a-tree-select
          v-model:value="doc.parent"
          style="width: 100%"
          :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
          placeholder="Please select"
          allow-clear
          :fieldNames="{
            children: 'children',
            label: 'name',
            key: 'id',
            value: 'id'
          }"
          :tree-data="docTreeList"
        >
          <template #title="{ name }">
            <div>{{ name }}</div>
          </template>
        </a-tree-select>
      </a-form-item>
      <a-form-item label="排序">
        <a-input v-model:value="doc.sort" placeholder="input placeholder" />
      </a-form-item>

    </a-form>
    </a-modal>
</template>
<script lang="ts" setup>
import { ref, onMounted, reactive, watch, computed } from 'vue';
import axios from 'axios'
import { SmileOutlined, DownOutlined } from '@ant-design/icons-vue';
import { formatTree } from '@/util';
import type { Resp } from '@/type';
import { useRoute } from 'vue-router';

const list = ref(<any[]>[])

const name = ref("")
const visible = ref(false)
const initData = {
  // id: 0,
  name:'',
  parent: 0,
  ebookId: -1,
  sort: 0
}

let doc = ref(initData)
const okLoading = ref(false)

const route = useRoute()
console.log('route.query.ebookId', route.query.ebookId);

onMounted(() => {
  handleQuery()
})

watch(name, (newVal) => {
  handleQuery()
})

const handleQuery = async() => {
  const res:Resp = await axios.get('/doc/all')
  const data = formatTree(res.content)
  list.value = data
}

const docTreeList = computed(() => {
  const arr = formatTreeList(list.value);
  return [
    {
      id: 0,
      name: '无'
    },
    ...arr
  ]
})

const formatTreeList = (arr: any[] = [], disabled: boolean = false): any[] => {
  return arr.map((child: any) => {
    const dis = disabled || doc.value.id === child.id
    const children = formatTreeList(child.children, dis)
    return {
      id: child.id,
      name: child.name,
      disabled: dis,
      children
    } 
  })
}

const handleAdd = () => {
  visible.value = true;
  doc.value = initData
  doc.value.ebookId = Number(route.query.ebookId)
}

const handleEdit = (record: any) => {
  visible.value = true;
  doc.value = { ...record }
}

const handleDelete = async(record: any) => {

  const res = await axios.delete("/doc/delete/" + record.id)
  handleQuery()
}

const handleOk = async () => {
  okLoading.value = true;
  const res = await axios.post("/doc/save",doc.value)
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
    dataIndex: 'parent',
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

