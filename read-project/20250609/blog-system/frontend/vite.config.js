import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { viteMockServe } from 'vite-plugin-mock'
export default defineConfig({
  plugins: [vue(),viteMockServe({
    mockPath: 'mock',      // mock 文件目录
    localEnabled: true,    // 本地开发启用
    prodEnabled: false     // 生产环境关闭
  })],
  server: {
    proxy: {
      '/api': 'http://localhost:8080'
    }
  }
}) 