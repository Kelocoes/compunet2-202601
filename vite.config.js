import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  base: '/iaslab/compu2/profe',
  server: {
    port: 6767,
  },
});
