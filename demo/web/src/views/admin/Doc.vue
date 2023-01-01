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
        <div @click="handleView(record)">
          {{ record.name }}
        </div>
      </template>
      <template v-else-if="column.key === 'action'">
        <span>
          <a-button type="link" @click="handleView(record)">查看内容</a-button>
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

  <a-modal v-model:visible="visible" title="分类管理" width="900px" @ok="handleOk" :okButtonProps="{ loading: okLoading }">
    <a-form :model="doc" :labelCol="{ span: 4 }">

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
      <a-form-item label="内容">
        <div style="border: 1px solid #ccc">
          <Toolbar
            style="border-bottom: 1px solid #ccc"
            :editor="editorRef"
          />
          <Editor
            style="height: 500px; overflow-y: hidden;"
            v-model="valueHtml"
            @onCreated="handleCreated"
          />
        </div>
      </a-form-item>
    </a-form>
  </a-modal>
  <a-drawer
    v-model:visible="viewVisible"
    class="custom-class"
    title="内容预览"
    placement="right"
  >
    <Editor
      style="height: 500px; overflow-y: hidden;"
      v-model="valueHtml"
      @onCreated="handleCreated"
    />
  </a-drawer>
</template>
<script lang="ts" setup>
import '@wangeditor/editor/dist/css/style.css' // 引入 css
import { onBeforeUnmount, ref, shallowRef, onMounted, watch, computed } from 'vue'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import axios from 'axios'
import { SmileOutlined, DownOutlined } from '@ant-design/icons-vue';
import { formatTree } from '@/util';
import type { Resp } from '@/type';
import { useRoute, useRouter } from 'vue-router';

const list = ref(<any[]>[])

const name = ref("")
const visible = ref(false)
const viewVisible = ref(false)
const initData = {
  name:'',
  parent: 0,
  ebookId: -1,
  content: undefined,
  sort: 0
}

let doc = ref(initData)
const okLoading = ref(false)

const route = useRoute()

const valueHtml = ref()
const editorRef = shallowRef()
const handleCreated = (editor: any) => {
  editorRef.value = editor
}
// 组件销毁时，也及时销毁编辑器
onBeforeUnmount(() => {
  const editor = editorRef.value
  if (editor == null) return
  editor.destroy()
})

onMounted(() => {
  handleQuery()
})

watch(name, (newVal) => {
  handleQuery()
})

const handleQuery = async() => {
  const res:Resp = await axios.get('/doc/all/' + route.query.ebookId)
  const data = formatTree(res.content)
  list.value = data
}

const handleQueryContent = async(id: number) => {
  const res:Resp = await axios.get('/doc/queryContent/' + id )
  valueHtml.value = res.content
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

const getIdList = (list: any[] = [], id: number ): Array<number> => {

  for (let i = 0; i < list.length; i++) {
    const item = list[i];
    if(item.id === id) {
      const t = getChildrenIdList(item.children, [])
      return [id, ...t]
    }else{
      const p = getIdList(item.children, id)
      if(p.length > 0) {
        return p
      }
    }

  }
  return []
}

const getChildrenIdList = (list: any[], arr: number[]) => {
  list.map(item => {
    arr.push(item.id)
    getChildrenIdList(item.children, arr)
  })
  return arr
}

const handleAdd = () => {
  visible.value = true;
  doc.value = initData
  doc.value.ebookId = Number(route.query.ebookId)
}

const handleView = (record: any) => {
  viewVisible.value = true;
  doc.value = { ...record }
  handleQueryContent(doc.value.id)
}

const handleEdit = (record: any) => {
  visible.value = true;
  doc.value = { ...record }
  handleQueryContent(doc.value.id)
}

const handleDelete = async(record: any) => {
  const temp = getIdList(list.value, record.id)
  const res = await axios.delete("/doc/delete/" + temp.join(','))
  handleQuery()
}

const handleOk = async () => {
  okLoading.value = true;
  doc.value.content = valueHtml.value
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

