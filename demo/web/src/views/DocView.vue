<template>
  <a-row>
    <a-col :span="4">
      <a-tree
        v-if="list.length > 0"
        v-model:selectedKeys="selectedKeys"
        :fieldNames="{
          title: 'name',
          key:'id'
        }"
        @select="handleSelect"
        defaultExpandAll
        :tree-data="list"
      >
      </a-tree>
    </a-col>
    <a-col :span="18">
      <Editor
        style="height: 500px; overflow-y: hidden;"
        v-model="valueHtml"
        @onCreated="handleCreated"
        :defaultConfig="{
          readOnly: true
        }"
      /> 
    </a-col>
  </a-row>
</template>
<script lang="ts" setup>
import '@wangeditor/editor/dist/css/style.css' // 引入 css
import { onBeforeUnmount, ref, shallowRef, onMounted, watch, computed } from 'vue'
import axios from 'axios'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import type { Resp } from '@/type';
import { useRoute } from 'vue-router';
import { formatTree } from '@/util';
const list = ref(<any[]>[])
const selectedKeys = ref(<any[]>[])
const valueHtml = ref()
const editorRef = shallowRef()
const route = useRoute()

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

const handleQuery = async() => {
  const res:Resp = await axios.get('/doc/all/' + route.query.ebookId)
  const data = formatTree(res.content)
  list.value = data
}

const handleSelect = (selectedKeys: number[]) => {
  handleQueryContent(selectedKeys[0])
}

const handleQueryContent = async(id: number) => {
  const res:Resp = await axios.get('/doc/queryContent/' + id )
  valueHtml.value = res.content
}

</script>
<style>
.ant-tree {
  height: 100%;
  border-right: 1px solid #eee;
}
</style>