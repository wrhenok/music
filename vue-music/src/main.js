import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from 'axios'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import store from "./store"



const app = createApp(App);

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.config.globalProperties.$axios = axios;
app.use(router);
app.use(store);
app.use(ElementPlus);
app.mount('#app');
router.beforeEach((to, from, next) => {
    let user = store.state.user;
    if (to.meta.requireAuth) {   // 判断当前路由是否需要路由验证
        if (user) {  
            next()  
        } else {
            next('/login')  //不存在需要跳到登陆页
        }
    } else {   // 不需要验证路由，继续执行
        next()
    }
})

