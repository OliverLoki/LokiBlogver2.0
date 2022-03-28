<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="8">
                <el-card shadow="hover" class="mgb20" style="height:252px;">
                    <div class="user-info">
                        <img :src="avatar" class="user-avator" alt/>
                        <div class="user-info-cont">
                            <div class="user-info-name">{{name}}</div>
                            <div>{{role}}</div>
                        </div>
                    </div>
                    <div class="user-info-list">
                        当前时间：
                        <span>{{nowDate}}{{ nowTime }}</span>
                    </div>
                    <div class="user-info-list">
                        上次登录地点：
                        <span>辽宁省沈阳市</span>
                    </div>
                </el-card>
                <el-card shadow="hover" style="height:252px;">
                    <div slot="header" class="clearfix">
                        <span>语言详情</span>
                    </div>
                    Vue
                    <el-progress :percentage="71.3" color="#42b983"></el-progress>
                    JavaScript
                    <el-progress :percentage="24.1" color="#f1e05a"></el-progress>
                    CSS
                    <el-progress :percentage="13.7"></el-progress>
                    HTML
                    <el-progress :percentage="5.9" color="#f56c6c"></el-progress>
                </el-card>
            </el-col>
            <el-col :span="16">
                <el-row :gutter="20" class="mgb20">
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-1">
                                <i class="el-icon-lx-people grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">6</div>
                                    <div>文章数</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-2">
                                <i class="el-icon-tickets grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">8264</div>
                                    <div>文章总字数</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-3">
                                <i class="el-icon-star-on grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">2</div>
                                    <div>获赞数</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
                <el-card shadow="hover" style="height:403px;">
                    <div slot="header" class="clearfix">
                        <span>系统公告</span>
                        <el-button style="float: right; padding: 3px 0" type="text">添加</el-button>
                    </div>
                    <el-table :show-header="false" :data="todoList" style="width:100%;">
                        <el-table-column width="40">
                            <template slot-scope="scope">
                                <el-checkbox v-model="scope.row.status"></el-checkbox>
                            </template>
                        </el-table-column>
                        <el-table-column>
                            <template slot-scope="scope">
                                <div
                                        class="todo-item"
                                        :class="{'todo-item-del': scope.row.status}"
                                >{{scope.row.title}}
                                </div>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-card>
            </el-col>
        </el-row>

    </div>
</template>

<script>

    export default {
        name: 'dashboard',
        data() {
            return {
                name: localStorage.getItem('ms_username'),
                avatar: localStorage.getItem('avatar'),
                nowDate: null,
                nowTime: null, //存放时分秒变量
                timer: "", //定义一个定时器的变量
                currentTime: new Date(), // 获取当前时间
                todoList: [
                    {
                        title: '修复编辑博客时需要刷新才能正确显示的bug',
                        status: false
                    },
                    {
                        title: '修复关闭所有标签后退回登录页的bug',
                        status: false
                    },
                    {
                        title: '新增相册管理功能',
                        status: false
                    },
                    {
                        title: '整合shiro解决token做会话保持无法实现退出功能的bug',
                        status: false
                    },
                    {
                        title: '新增分类管理，实现多表查询',
                        status: true
                    },
                    {
                        title: '管理员用户可以编辑用户,修改系统公告',
                        status: true
                    }
                ]
            };
        },
        created() {
            //初始化当前时间
            this.timer = setInterval(this.getTime, 1000);
        },
        //Vue销毁对象
        beforeDestroy() {
            if (this.timer) {
                clearInterval(this.timer); // 在Vue实例销毁前，清除定时器
            }
        },
        computed: {
            role() {
                return this.name === 'admin' ? '超级管理员' : '普通用户';
            },

        },
        methods: {
            getTime() {
                const date = new Date();
                const year = date.getFullYear();
                const month = date.getMonth() + 1;
                const day = date.getDate();
                const hour = date.getHours();
                const minute = date.getMinutes();
                const second = date.getSeconds();
                const str = ''
                if (this.hour > 12) {
                    this.hour -= 12;
                    this.str = " PM";
                } else {
                    this.str = " AM";
                }
                this.month = check(month);
                this.day = check(day);
                this.hour = check(hour);
                this.minute = check(minute);
                this.second = check(second);

                function check(i) {
                    const num = (i < 10) ? ("0" + i) : i;
                    return num;
                }

                this.nowDate = year + "年" + this.month + "月" + this.day + "日";
                this.nowTime = this.hour + ":" + this.minute + ":" + this.second + this.str;
            },
        }
    };
</script>


<style scoped>
    .el-row {
        margin-bottom: 20px;
    }

    .grid-content {
        display: flex;
        align-items: center;
        height: 100px;
    }

    .grid-cont-right {
        flex: 1;
        text-align: center;
        font-size: 14px;
        color: #999;
    }

    .grid-num {
        font-size: 30px;
        font-weight: bold;
    }

    .grid-con-icon {
        font-size: 50px;
        width: 100px;
        height: 100px;
        text-align: center;
        line-height: 100px;
        color: #fff;
    }

    .grid-con-1 .grid-con-icon {
        background: rgb(45, 140, 240);
    }

    .grid-con-1 .grid-num {
        color: rgb(45, 140, 240);
    }

    .grid-con-2 .grid-con-icon {
        background: rgb(100, 213, 114);
    }

    .grid-con-2 .grid-num {
        color: rgb(45, 140, 240);
    }

    .grid-con-3 .grid-con-icon {
        background: rgb(242, 94, 67);
    }

    .grid-con-3 .grid-num {
        color: rgb(242, 94, 67);
    }

    .user-info {
        display: flex;
        align-items: center;
        padding-bottom: 20px;
        border-bottom: 2px solid #ccc;
        margin-bottom: 20px;
    }

    .user-avator {
        width: 120px;
        height: 120px;
        border-radius: 50%;
    }

    .user-info-cont {
        padding-left: 50px;
        flex: 1;
        font-size: 14px;
        color: #999;
    }

    .user-info-cont div:first-child {
        font-size: 30px;
        color: #222;
    }

    .user-info-list {
        font-size: 14px;
        color: #999;
        line-height: 25px;
    }

    .user-info-list span {
        margin-left: 70px;
    }

    .mgb20 {
        margin-bottom: 20px;
    }

    .todo-item {
        font-size: 14px;
    }

    .todo-item-del {
        text-decoration: line-through;
        color: #999;
    }

    .schart {
        width: 100%;
        height: 300px;
    }
</style>
