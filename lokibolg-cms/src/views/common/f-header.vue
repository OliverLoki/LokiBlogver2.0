<template>
    <div class="headerbar">
        <el-menu :default-active="activeIndex" class="d-flex" mode="horizontal" router="true" type="flex">
            <router-link tag="header" to="/" class="mr-auto">Fan Blog</router-link>
            <router-link tag="header" to="/blogList">博客集</router-link>
            <router-link tag="header" to="/archive">归档</router-link>
            <router-link tag="header" to="/photograph">摄影作品</router-link>
            <router-link tag="header" to="/admin">登录</router-link>
        </el-menu>
    </div>
</template>

<script>

</script>

<style scoped>
    html, body {
        background-color:#e4ded3 ;
        margin: 0;
        padding: 0;
    }

    [v-cloak] {
        display: none;
    }

    .d-flex {
        display: flex !important;
    }
    .headerbar header{
        margin: 10px 20px;
        font-size: 38px;
        color: #909399;
        height: 60px;
        border-bottom: 2px solid transparent;
        line-height: 60px;
    }

    .mr-auto {
        margin-right: auto !important;
    }

    .ml-auto {
        margin-left: auto !important;
    }

    .align-items-center {
        align-items: center !important;
    }

    .flex-center {
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .center {
        height: 1000px;
        width: 75%;
        border: 1px black solid;
        margin: 20px auto;
    }

</style>