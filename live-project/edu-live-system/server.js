const express = require('express');
const http = require('http');
const { Server } = require('socket.io');

const app = express();
const server = http.createServer(app);
const io = new Server(server);

app.get('/', (req, res) => {
  res.sendFile(__dirname + '/index.html');
});

// 用户类型：teacher 或 student
io.on('connection', (socket) => {
  socket.on('join', (role) => {
    socket.role = role;
    if (role === 'teacher') {
      socket.join('teachers');
    } else {
      socket.join('students');
    }
  });

  // 老师广播讲课内容
  socket.on('lecture', (msg) => {
    if (socket.role === 'teacher') {
      io.to('students').emit('lecture', msg);
    }
  });

  // 学生提问
  socket.on('question', (msg) => {
    if (socket.role === 'student') {
      io.to('teachers').emit('question', msg);
    }
  });
});

server.listen(3002, () => {
  console.log('Edu live server running at http://localhost:3002');
});