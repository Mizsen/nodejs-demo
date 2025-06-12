export default [
  {
    url: '/api/users/login',
    method: 'post',
    response: ({ body }) => {
      if (body.usernameOrEmail === 'admin' && body.password === '123456') {
        return { code: 0, data: 'mock-jwt-token' }
      }
      return { code: 1, message: '账号或密码错误' }
    }
  },
  {
    url: '/api/users/register',
    method: 'post',
    response: () => ({ code: 0, data: { id: 1, username: 'admin' } })
  },
  {
    url: '/api/users/1',
    method: 'get',
    response: () => ({ code: 0, data: { id: 1, username: 'admin', nickname: '管理员', avatar: '', email: 'admin@example.com' } })
  }
] 