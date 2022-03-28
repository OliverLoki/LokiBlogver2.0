<template>
    <div>
    <f-header></f-header>
    <div class="center">
        <div class="block">
            <el-carousel height="650px">
                <el-carousel-item v-for="item in messages" :key="item">
                    <img :src="item.src" style="width: 100%">
                </el-carousel-item>
            </el-carousel>
        </div>
    </div>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                messages:[
                    {
                        id: '1',
                        src: 'https://img.zcool.cn/community/01fef358c2b9e0a801219c771506ea.jpg@1280w_1l_0o_100sh.jpg'
                    },
                    {
                        id: '2',
                        src: 'https://img.zcool.cn/community/0190f55ec0802ba801207200dd9843.jpg@1280w_1l_2o_100sh.jpg'
                    },
                    {
                        id: '3',
                        src: 'https://img.zcool.cn/community/01eaf35caadff9a801208f8b7f0e93.jpg@2o.jpg'
                    },
                    {
                        id: '4',
                        src: 'https://img.zcool.cn/community/0187875d916d3ba801211d53b8036b.jpg@1280w_1l_2o_100sh.jpg'
                    }
                ]
            }
        }
    }

</script>

<style scoped>
    .center{
        width: 75%;
        border: 1px black solid;
        margin: 20px auto;
    }
    .el-carousel__item h3 {
        color: #475669;
        font-size: 14px;
        opacity: 0.75;
        line-height: 150px;
        margin: 0;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }

</style>