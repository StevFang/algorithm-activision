import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    proxy: {
      '/api/v1': {
        changeOrigin: true
      }
    },
    host: '0.0.0.0',
    port: 8003
  }
})
