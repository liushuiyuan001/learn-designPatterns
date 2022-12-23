export const formatTree = (list: any[]) => {
  const hash:Map<string, any> = new Map()
  for(let i = 0; i < list.length; i++) {
    const item = list[i]
    item.children = []
    hash.set(item.id, item)
  }

  for(let i = 0; i < list.length; i++) {
    const item = list[i]
    // 一级节点跳过
    if(item.parent === 0) {
      continue
    } else {
      // 将当前节点的父节点从Map中取出
      const parent = hash.get(item.parent)
      // 将当前节点加入父节点的children中
      parent.children.push(item)
      hash.set(item.parent, parent)
    }
  }


  const res: any[] = []
  // 只需要将一
  for (const [_,v] of hash) {
    if(v.parent === 0) {
      res.push(v)
    }
  }
  
  return res
}
