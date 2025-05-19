// filepath: /workspaces/nodejs-demo/test.js
const { saveText, readAllText } = require('./text-api');

saveText('Hello, world!');
console.log(readAllText());