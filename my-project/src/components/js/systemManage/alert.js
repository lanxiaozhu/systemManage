const success = function (message, th) {
  th.$notify({
    title: '消息提示',
    message: message,
    type: 'success'
  })
}

const warning = function (message, th) {
  th.$notify({
    title: '消息提示',
    message: message,
    type: 'warning'
  })
}
// 根据返回code 统一js处理

export {
  success, warning
}
