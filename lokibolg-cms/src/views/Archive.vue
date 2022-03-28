<template>
    <div>
        <f-header :class="{'navBarWrap':navBarFixed}"></f-header>
        <div class="center">
            <h1>归档页</h1>
        </div>
    </div>
</template>


<script>
    export default {
        name: "Archive"
    }
</script>

<style scoped>
    .center{
        height: 1000px;
        width: 75%;
        border: 1px black solid;
        margin: 20px auto;
    }
</style>