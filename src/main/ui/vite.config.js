import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';

export default defineConfig({
  plugins: [vue()],
  server: {
    proxy: {
      '/rest': 'http://localhost:4000',
      '/api': 'http://localhost:4000',
      '/chat': 'http://localhost:4000',
    },
  },
});