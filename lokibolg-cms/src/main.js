import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store'
import ElementUI from 'element-ui';
import './utils/request'
import './assets/css/theme-green/index.css';
import './assets/css/icon.css';
import './components/common/directives';
import 'babel-polyfill';
import axios from 'axios'
//前端
import fHeader from './views/common/f-header'
Vue.component("f-header",fHeader)


Vue.prototype.$axios = axios
Vue.config.productionTip = false;
Vue.use(ElementUI, {
    size: 'small'
});

//使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
    document.title = `${to.meta.title}`;
    const role = localStorage.getItem('ms_username');
    if (!role && to.path !== '/admin') {
        next('/admin');
    } else if (to.meta.permission) {
        // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已
        role === 'admin' ? next() : next('/403');
    } else {
        // 简单的判断IE10及以下不进入富文本编辑器，该组件不兼容
        if (navigator.userAgent.indexOf('MSIE') > -1 && to.path === '/editor') {
            Vue.prototype.$alert('vue-quill-editor组件不兼容IE10及以下浏览器，请使用更高版本的浏览器查看', '浏览器不兼容通知', {
                confirmButtonText: '确定'
            });
        } else {
            next();
        }
    }
});
new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');
