type Content = {
  total: number,
  list: never[]
}
export type Resp = {
  success: Boolean,
  message: String,
  content: Content
}