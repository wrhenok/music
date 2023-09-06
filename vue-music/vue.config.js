const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/api': {
        target: 'http://121.4.93.149:8001',
        changeOrigin: true,
        ws: false,
        pathRewrite: {
          '^/api': ''
        }
      }
    },
  }
})