export default [
  {
    url: /\/api\/likes\/post\/\d+\/user\/\d+$/,
    method: 'post',
    response: () => ({ code: 0 })
  },
  {
    url: /\/api\/likes\/post\/\d+\/user\/\d+$/,
    method: 'delete',
    response: () => ({ code: 0 })
  },
  {
    url: /\/api\/likes\/post\/\d+\/count$/,
    method: 'get',
    response: () => ({ code: 0, data: 10 })
  }
] 