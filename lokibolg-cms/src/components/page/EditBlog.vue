<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 博客内容管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button
                        type="primary"
                        icon="el-icon-delete"
                        class="handle-del mr10"
                        @click="delAllSelection"
                >批量删除
                </el-button>

                <el-input v-model="pagination.name" placeholder="用户名" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
            </div>
            <!--从数据库中查询数据返回-->
            <el-table
                    :data="tableData"
                    border
                    class="table"
                    ref="multipleTable"
                    header-cell-class-name="table-header"
                    @selection-change="handleSelectionChange"
            >
                <!--处理多选操作-->
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <!--博客ID-->
                <el-table-column prop="blogId" label="ID" width="55" align="center">
                </el-table-column>
                <!--博客标题-->
                <el-table-column prop="blogTitle" label="博客标题"></el-table-column>
                <!--博客作者-->
                <el-table-column label="作者">
                    {{username}}
                </el-table-column>
                <!--点赞数-->
                <el-table-column prop="blogEmojicount" label="点赞数">
                </el-table-column>
                <!--评论数-->
                <el-table-column prop="blogCommentcount" label="评论数" align="center"></el-table-column>
                <!--发表日期-->
                <el-table-column prop="gmtCreate" label="发表日期" align="center"></el-table-column>
                <!--更新日期-->
                <el-table-column prop="gmtModified" label="更新日期" align="center"></el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <!--todo:这个scope的值获取不到-->
                    <template scope="scope">
                        <el-button
                                type="text"
                                icon="el-icon-edit"
                                @click="handleEdit(scope.$index, scope.row)"
                        >编辑
                        </el-button>
                        <el-button
                                type="text"
                                icon="el-icon-delete"
                                class="red"
                                @click="handleDelete(scope.$index, scope.row)"
                        >删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>

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
</template>

<script>
    import axios from 'axios'

    export default {
        name: 'editBlog',
        data() {
            return {
                username: localStorage.getItem('ms_username'),
                pagination: {
                    pageIndex: 1,
                    pageSize: 5,
                    pageTotal: 0
                },
                tableData: [],
                multipleSelection: [],
                delList: [],
                form: {},
                idx: -1,
                id: -1,
            };
        },
        created() {//在加载页面前执行的页面初始化操作
            this.getData()
        },
        methods: {
            // 获取数据
            getData() {
                let a = sessionStorage.getItem('userInfo')
                let b = JSON.parse(a)
                let c = b.userId
                let d = parseInt(c)
                axios({
                    method: 'POST',
                    //url: '/getAllBlog/' + this.pagination.pageIndex,
                    url: '/getUserBlog/' + this.pagination.pageIndex + "/"+d,
                    headers: {
                        token: localStorage.getItem('token'),
                        'Content-Type': 'application/json;charset=utf-8'      //改这里就好了

                    }
                }).then(res => {
                    this.tableData = res.data.data.records
                    this.pagination.pageTotal = res.data.data.total
                    this.pagination.pageIndex = res.data.data.current
                    this.pagination.pageSize = res.data.data.size

                })
            },
            // 触发搜索按钮
            handleSearch() {
                this.$set(this.pagination, 'pageIndex', 1);
                this.getData();
            },
            // 删除操作
            handleDelete(index, row) {
                let id = row.blogId
                // 二次确认删除
                this.$confirm('确定要删除吗？', '提示', {
                    type: 'warning'
                })
                    .then(() => {
                        axios({
                            method: 'POST',
                            url: '/deleteBlogById/' + id,
                            headers: {
                                'Content-Type': 'application/json;charset=utf-8'      //改这里就好了
                            }
                        })
                        this.$message.success('删除成功');
                        this.tableData.splice(index, 1);
                    })
                    .catch(() => {
                    });
            },
            // 多选操作
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            delAllSelection() {
                const length = this.multipleSelection.length;
                let str = '';
                this.delList = this.delList.concat(this.multipleSelection);
                for (let i = 0; i < length; i++) {
                    str += this.multipleSelection[i].name + ' ';
                }
                this.$message.error(`删除了${str}`);
                this.multipleSelection = [];
            },
            // 编辑操作
            handleEdit(index, row) {
                let id = row.blogId
                console.log("传入的"+id)
                this.$router.push({
                    path:'/editBlogInfo',
                    query:{
                        id : id
                    }
                });
            },
            // 分页导航
            handlePageChange(val) {
                this.$set(this.pagination, 'pageIndex', val)
                //设置当前页
                this.getData();
            }
        }
    };
</script>

<style scoped>
    .handle-box {
        margin-bottom: 20px;
    }

    .handle-select {
        width: 120px;
    }

    .handle-input {
        width: 300px;
        display: inline-block;
    }

    .table {
        width: 100%;
        font-size: 14px;
    }

    .red {
        color: #ff0000;
    }

    .mr10 {
        margin-right: 10px;
    }

    .table-td-thumb {
        display: block;
        margin: auto;
        width: 40px;
        height: 40px;
    }
</style>
