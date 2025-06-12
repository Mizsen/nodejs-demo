import Mock from 'mockjs'

export default [
  {
    url: /\/api\/comments\/post\/\d+$/,
    method: 'get',
    response: () => {
      const list = Mock.mock({
        'data|5': [{
          'id|+1': 1,
          userNickname: '@cname',
          userAvatar: '@image("32x32")',
          content: '@csentence(10, 30)',
          createdAt: '@datetime'
        }]
      })
      return { code: 0, data: list.data }
    }
  },
  {
    url: '/api/comments',
    method: 'post',
    response: () => ({ code: 0, data: { id: 100, content: 'mock评论' } })
  }
] 