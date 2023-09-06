<template>
    <div class="login">
        <!-- <img src="../../public/login.jpeg" class="image"/> -->
        <div class="loginPart">
            <h1 class="word">登录</h1>
            <el-divider />
            <el-form action="login" method="post" :model="userData" :rules="rules" ref="ruleForm" class="demo-ruleForm">
                <el-form-item prop="username" class="inputElement">
                    <el-input v-model="userData.username" placeholder="请输入您的用户名" prefix-icon="Avatar"></el-input>
                </el-form-item>
                <el-form-item prop="password" class="inputElement">
                    <el-input v-model="userData.password" placeholder="请输入密码" clearable type="password"
                        prefix-icon="Lock" show-password>
                    </el-input>
                </el-form-item>
                <span style="color: red">{{ msg }}</span>
                <el-form-item>
                    <el-button color="#3c9566" plain @click.native.prevent="submit()"><span
                            style="display: inline-block; width: 142px"></span>立即登录<span
                            style="display: inline-block; width: 142px"></span></el-button>
                    <el-link type="warning" @click="register()">没有账户？去注册</el-link>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
import register from './register.vue';
export default {
    name: "index",
    data() {
        return {
            userData: {
                username: "admin",
                password: "123456",
            },
            rules: {
                username: [
                    {
                        required: true,
                        message: "用户名不能为空",
                        trigger: "blur",
                    },
                ],
                password: [
                    {
                        required: true,
                        message: "密码不能为空",
                        trigger: "blur",
                    },
                ],
            },
            msg: "",
        };
    },
    methods: {
        submit() {
            if (this.userData.username && this.userData.password) {
                this.$axios({
                    method: "POST",
                    url: "/api/login",
                    data: JSON.stringify(this.userData),
                    headers: {
                        "Content-Type": "application/json;charset=UTF-8",
                    },
                }).then(res => {
                    console.log("11111", res.data);
                    this.$store.commit('add', res.data);
                    this.$router.push("/")
                    console.log("登录成功", this.$store.state.user.id);
                }).catch(err => {
                    console.log("登录失败", err);
                });
            } else {
                this.msg = "用户名或密码为空"
            }
        },
        register() {
            this.$router.push("/register")
        },
        components: { register },
    }
}
</script>

<style scoped>
.loginPart {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 450px;
    padding: 40px;
    background: rgba(0, 0, 0, 0.5);
    box-sizing: border-box;
    box-shadow: 0px 15px 25px rgba(1, 0, 0, 0.5);
    border-radius: 15px;
}

.loginPart h1 {
    margin: 0 0 30px;
    padding: 0;
    color: #fff;
    text-align: center;
}

.loginPart .inputbox {
    position: relative;
}

.loginPart .inputElement input {
    width: 100%;
    padding: 10px 0;
    font-size: 16px;
    color: #fff;
    letter-spacing: 1px;
    margin-bottom: 30px;
    border: none;
    border-bottom: 1px solid #fff;
    outline: none;
    background: transparent;
}

button {
    margin-top: 10px;
}

.login {
    background: url("../../public/login.jpeg") no-repeat;
    background-size: cover;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    min-width: 1000px;
    -webkit-background-size: cover;
    -o-background-size: cover;
    background-position: center 0;
}
</style>