type Content = {
  total: number,
  list: never[]
}
export type PageResp = {
  success: Boolean,
  message: String,
  content: Content
}

export type Resp = {
  success: Boolean,
  message: String,
  content: any
}