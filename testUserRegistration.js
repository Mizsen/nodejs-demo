// testUserRegistration.js
const axios = require('axios');

// 测试数据
const testUser = {
  username: 'testuser4',
  email: 'testuser4@example.com',
  password: 'Password123!',
  nickname: 'User4',
  avatarUrl: 'https://example.com/avatar4.jpg'
};

// 发送POST请求到用户注册接口
axios.post('http://localhost:8000/api/users/register', testUser)
  .then(response => {
    // 成功注册
    console.log('Status Code:', response.status);
    console.log('Response Body:', response.data);
    
    // 断言响应状态码为201
    if (response.status !== 201) {
      throw new Error('Registration failed 111');
    }
  })
  .catch(error => {
    // 处理错误
    console.error('Error:', error.message);
    if (error.response) {
      console.error('Status Code:', error.response.status);
      console.error('Response Body:', error.response.data);
    }
  });