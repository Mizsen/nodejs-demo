const express = require('express');
const http = require('http');
const fs = require('fs');
const { Server } = require('socket.io');
const path = require('path');

const app = express();
const server = http.createServer(app);
const io = new Server(server);

const LOG_FILE = path.join(__dirname, 'app.log');

app.get('/', (req, res) => {
  res.sendFile(__dirname + '/index.html');
});

// 实时推送新日志到前端
io.on('connection', (socket) => {
  // 发送当前日志内容
  fs.readFile(LOG_FILE, 'utf-8', (err, data) => {
    if (!err) socket.emit('log', data);
  });

  // 监听文件变化
  const watcher = fs.watch(LOG_FILE, () => {
    fs.readFile(LOG_FILE, 'utf-8', (err, data) => {
      if (!err) socket.emit('log', data);
    });
  });

  socket.on('disconnect', () => {
    watcher.close();
  });
});

// 模拟写日志接口
app.post('/log', express.json(), (req, res) => {
  const { message } = req.body;
  const logLine = `[${new Date().toISOString()}] ${message}\n`;
  fs.appendFile(LOG_FILE, logLine, () => {});
  res.json({ status: 'ok' });
});

server.listen(3003, () => {
  console.log('Log monitor running at http://localhost:3003');
});