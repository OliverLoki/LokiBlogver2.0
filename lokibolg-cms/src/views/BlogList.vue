<template>
    <div>
        <f-header></f-header>
        <div class="center">
            <div class="container">
                <!-- 内容 -->
                <div class="box1" v-for="item in blogdata">
                    <div @click="toView(item.blogId)">
                        <h2>{{item.blogTitle}}</h2>
                        <h4>{{item.blogDesc}}</h4>
                        <span style="float: right">点赞数{{item.blogEmojicount}}</span>
                    </div>
                </div>

                <div class="pagination">
                    <el-pagination
                            background
                            layout="total, prev, pager, next"
                            :current-page="pagination.pageIndex"
                            :page-size="pagination.pageSize"
                            :total="pagination.pageTotal"
                            @current-change="handlePageChange"
                    ></el-pagination>
                </div>
            </div>


        </div>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        data() {
            return {
                blogdata: [],
                pagination: {
                    pageIndex: 1,
                    pageSize: 5,
                    pageTotal: 0
                }
            }

        },
        created() {
            this.getData();
        },
        methods: {
            // 获取数据
            getData() {
                axios({
                    method: 'POST',
                    url: '/getAllBlog/' + this.pagination.pageIndex,
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8'      //改这里就好了
                    }
                }).then(res => {
                    this.blogdata = res.data.data.records
                    this.pagination.pageTotal = res.data.data.total
                    this.pagination.pageIndex = res.data.data.current
                    this.pagination.pageSize = res.data.data.size

                })
            },
            // 分页导航
            handlePageChange(val) {
                this.$set(this.pagination, 'pageIndex', val)
                //设置当前页
                this.getData();
            },
            toView(id) {
                this.$router.push({
                    path: '/blogView',
                    query: {
                        id: id
                    }
                })
            }
        }
    }
</script>

<style scoped>
    .center {
        height: 1000px;
        width: 75%;
        opacity: 0.8;
        margin: 10px auto;
    }

    body {

        font-size: 11px;
        font-family: 'Open Sans', sans-serif;
        color: #4A4A4A;
        text-align: center;
    }

    .box1 {
        width: 300px;
        margin: 20px;
        padding: 10px;
        min-height: 200px;
        position: relative;
        display: inline-block;
        background: -webkit-gradient(linear, 0% 20%, 0% 1000%, from(#fff), to(#fff), color-stop(.1, #f3f3f3));
        border: 1px solid #ccc;
        -webkit-box-shadow: 0px 3px 30px rgba(0, 0, 0, 0.1) inset;
        -webkit-border-bottom-right-radius: 6px 50px;
    }

    .box1:before {
        content: '';
        width: 50px;
        height: 100px;
        position: absolute;
        bottom: 0;
        right: 0;
        -webkit-box-shadow: 20px 20px 10px rgba(0, 0, 0, 0.1);
        z-index: -1;
        -webkit-transform: translate(-35px, -40px) skew(0deg, 30deg) rotate(-25deg);
    }

    .box1:after {
        content: '';
        width: 100px;
        height: 100px;
        top: 0;
        left: 0;
        position: absolute;
        display: inline-block;
        z-index: -1;
        -webkit-box-shadow: -10px -10px 10px rgba(0, 0, 0, 0.2);
        -webkit-transform: rotate(2deg) translate(20px, 25px) skew(20deg);
    }

    .box1 img {
        width: 100%;
        margin-top: 15px;
    }

    p {
        margin-top: 15px;
        text-align: justify;
    }

    h1 {
        font-size: 20px;
        font-weight: bold;
        margin-top: 5px;
        text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.3);
    }

    a {
        text-decoration: none;
        color: #4A4A4A !important;
    }

    a:hover {
        text-decoration: underline;
        color: #6B6B6B !important;
    }
</style>