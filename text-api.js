const fs = require('fs');
const path = require('path');

const STORAGE_FILE = path.join(__dirname, 'data.txt');

// 保存文本到文件
function saveText(text) {
  fs.appendFileSync(STORAGE_FILE, text + '\n', 'utf8');
}

// 读取所有文本
function readAllText() {
  if (!fs.existsSync(STORAGE_FILE)) return '';
  return fs.readFileSync(STORAGE_FILE, 'utf8');
}

// 示例用法
// saveText('Hello, world!');
// console.log(readAllText());

module.exports = { saveText, readAllText };