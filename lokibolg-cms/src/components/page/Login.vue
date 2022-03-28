<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">博客后台管理系统</div>
            <el-form :model="param" :rules="rules" ref="login" label-width="0px" class="ms-content">
                <el-form-item prop="username">
                    <el-input v-model="param.username" placeholder="username">
                        <el-button slot="prepend" icon="el-icon-lx-people"></el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input
                            type="password"
                            placeholder="password"
                            v-model="param.password"
                            @keyup.enter.native="submitForm()"
                    >
                        <el-button slot="prepend" icon="el-icon-lx-lock"></el-button>
                    </el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm()">登录</el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        data: function () {
            return {
                param: {
                    username: 'loki',
                    password: '1031',
                },
                rules: {
                    username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
                    password: [{required: true, message: '请输入密码', trigger: 'blur'}],
                },
            };
        },
        methods: {
            submitForm() {
                this.$refs.login.validate(valid => {
                    if (valid) {
                        //保存vue全局this
                        const _this =this;

                        this.$axios.post('/login', this.param).then(res => {
                            //使用后置拦截,如果登录失败不进入以下逻辑
                            //登录后逻辑
                            //获取Token
                            const token = res.headers['authorization']
                            //获取用户数据
                            const userInfo = res.data.data
                            //存储头像
                            localStorage.setItem('avatar', res.data.data.avatar);

                            _this.$store.commit("SET_TOKEN",token);//在本地存储中
                            //这是json
                            _this.$store.commit("SET_USERINFO",userInfo);//在会话存储中
                        })


                        localStorage.setItem('ms_username', this.param.username);

                        //跳转
                        this.$router.push('/dashboard');
                    } else {
                        this.$message.error('请输入账号和密码');
                        return false;
                    }
                });
            },
        },
    };
</script>

<style scoped>
    .login-wrap {
        position: relative;
        width: 100%;
        height: 100%;

    }

    .ms-title {
        width: 100%;
        line-height: 50px;
        text-align: center;
        font-size: 20px;
        color: #fff;
        border-bottom: 1px solid #ddd;
    }

    .ms-login {
        position: absolute;
        left: 50%;
        top: 50%;
        width: 350px;
        margin: -190px 0 0 -175px;
        border-radius: 5px;
        background: rgba(255, 255, 255, 0.3);
        overflow: hidden;
    }

    .ms-content {
        padding: 30px 30px;
    }

    .login-btn {
        text-align: center;
    }

    .login-btn button {
        width: 100%;
        height: 36px;
        margin-bottom: 10px;
    }

    .login-tips {
        font-size: 12px;
        line-height: 30px;
        color: #fff;
    }
</style>