import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            //localhost:8080默认加载界面,应该是前端界面
            path: '/',
            component:()=>import('../views/About'),
            meta: {title: 'Fan blog | 首页'},
        },
        {
            path: '/photograph',
            component:()=>import('../views/Photograph'),
            meta: {title: 'Fan blog | 影集'},
        },
        {
            path: '/archive',
            component:()=>import('../views/Archive'),
            meta: {title: 'Fan blog | 归档页'}
        },
        {
            path: '/blogList',
            component:()=>import('../views/BlogList'),
            meta: {title: 'Fan blog | 博客集'}
        },
        {
            path: '/blogView',
            name: 'blogView',
            component:()=>import('../views/BlogView'),
            meta: {title: 'Fan blog | 博客详情'}
        },
        {
            path: '/home',
            component: () => import(/* webpackChunkName: "home" */ '../components/common/Home.vue'),
            meta: {title: '自述文件'},
            //以下界面都是home的子页面
            children: [
                {
                    path: '/dashboard',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/Dashboard.vue'),
                    meta: {title: '系统首页'}
                },
                {
                    path: '/album',
                    component: () => import(/* webpackChunkName: "table" */ '../components/page/Album.vue'),
                    meta: {title: '相册管理'}
                },
                {
                    path: '/publishBlog',
                    component: () => import(/* webpackChunkName: "form" */ '../components/page/PublishBlog.vue'),
                    meta: {title: '写博客'}
                },
                {
                    path:'/editBlog',
                    component:()=>import('../components/page/EditBlog'),
                    meta: {title: '内容管理'}
                },
                {
                    // 图片上传组件
                    path: '/upload',
                    component: () => import(/* webpackChunkName: "upload" */ '../components/page/Upload.vue'),
                    meta: {title: '文件上传'}
                },
                {
                    // vue-schart组件
                    path: '/charts',
                    component: () => import(/* webpackChunkName: "chart" */ '../components/page/BaseCharts.vue'),
                    meta: {title: 'schart图表'}
                },
                {
                    // 权限页面
                    path: '/permission',
                    component: () => import(/* webpackChunkName: "permission" */ '../components/page/Permission.vue'),
                    meta: {title: '用户管理', permission: true}
                },
                {
                    path: '/404',
                    component: () => import(/* webpackChunkName: "404" */ '../components/page/404.vue'),
                    meta: {title: '404'}
                },
                {
                    path: '/403',
                    component: () => import(/* webpackChunkName: "403" */ '../components/page/403.vue'),
                    meta: {title: '403'}
                },
                {
                    path: '/donate',
                    component: () => import(/* webpackChunkName: "donate" */ '../components/page/Donate.vue'),
                    meta: {title: '支持作者'}
                },
                {
                    path: '/editBlogInfo',
                    component:()=>import('../components/page/EditBlogInfo.vue'),
                    meta: {title: '编辑博客'}
                },
                {
                    path: '/photoUpload',
                    component:()=>import('../components/page/PhotoUpload'),
                    meta: {title: '上传照片'}
                }
            ]
        },
        {
            path: '/admin',
            component: () => import(/* webpackChunkName: "login" */ '../components/page/Login.vue'),
            meta: {title: '登录'}
        },
        {
            path: '/*',
            redirect: '/404'
        }


    ]
});
