import Mock from 'mockjs'

export default [
  {
    url: '/api/posts',
    method: 'get',
    response: ({ query }) => {
      const list = Mock.mock({
        'data|10': [{
          'id|+1': 1,
          title: '@ctitle(10, 20)',
          summary: '@csentence(10, 30)',
          content: '@cparagraph(2, 5)',
          authorNickname: '@cname',
          authorAvatar: '@image("32x32")',
          createdAt: '@datetime',
          tags: '前端,Vue',
          likesCount: '@integer(0,100)',
          commentCount: '@integer(0,20)'
        }]
      })
      return { code: 0, data: list.data, total: 100 }
    }
  },
  {
    url: /\/api\/posts\/\d+$/,
    method: 'get',
    response: () => ({
      code: 0,
      data: {
        id: 1,
        title: '示例博客标题',
        summary: '摘要内容',
        content: '## Markdown内容\n\n正文内容',
        authorNickname: '张三',
        authorAvatar: '',
        createdAt: '2023-01-01 12:00:00',
        tags: '前端,Vue',
        likesCount: 10,
        commentCount: 2
      }
    })
  }
] 