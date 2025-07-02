import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

export default defineConfig({
  plugins: [vue()],
  server: {
    port: 5173,
    host: true, // 新增这一行
    open: true,
    proxy: {
      // 代理 API 请求到后端
      "/api": {
        target: "http://localhost:8001",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, "/api"),
      },
      "/upload": {
        target: "http://localhost:8001",
        changeOrigin: true,
        // 不需要 rewrite，直接代理
      },
    },
  },
  resolve: {
    alias: {
      "@": "/src",
    },
  },
});
