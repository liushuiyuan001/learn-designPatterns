export const formatTree = (list: any[]) => {
  const hash:Map<string, any> = new Map()
  for(let i = 0; i < list.length; i++) {
    const item = list[i]
    item.children = []
    item.parentId = item.parent
    item.key = item.id
    delete item.parent
    hash.set(item.id, item)
  }

  for(let i = 0; i < list.length; i++) {
    const item = list[i]
    // 一级节点跳过
    if(item.parentId === 0) {
      continue
    } else {
      // 将当前节点的父节点从Map中取出
      const pNode = hash.get(item.parentId)
      // 将当前节点加入父节点的children中
      pNode.children.push(item)
      hash.set(item.parentId, pNode)
    }
  }


  const res: any[] = []
  // 只需要将一
  for (const [_,v] of hash) {
    if(v.parentId === 0) {
      res.push(v)
    }
  }
  
  return res
}

/**
   * 随机生成[len]长度的[radix]进制数
   * @param len
   * @param radix 默认62
   * @returns {string}
   */
export const uuid = (len: number, radix = 62) => {
  const chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
  const uuid = [];
  radix = radix || chars.length;

  for (let i = 0; i < len; i++) {
    uuid[i] = chars[0 | Math.random() * radix];
  }

  return uuid.join('');
}