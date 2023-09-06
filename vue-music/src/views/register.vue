<template>
    <div class="login">
        <div class="loginPart">
            <h1 class="word">注册</h1>
            <el-divider />
            <el-form :model="user" :rules="rules" ref="user" class="demo-user">
                <el-form-item prop="username">
                    <el-input v-model="user.username" placeholder="请输入您的用户名" prefix-icon="Avatar"></el-input>
                </el-form-item>

                <el-form-item prop="gender" >
                    <el-radio-group v-model="user.gender" placeholder="请选择您的性别" class="ml-4">
                        <el-radio label="0" size="large">男</el-radio>
                        <el-radio label="1" size="large">女</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item prop="age" >
                    <el-input v-model="user.age" placeholder="请输入您的年龄" prefix-icon="Avatar"></el-input>
                </el-form-item>

                <el-form-item prop="phone">
                    <el-input v-model="user.telephone" placeholder="请输入您的手机号" prefix-icon="Avatar"></el-input>
                </el-form-item>

                <el-form-item prop="name">
                    <el-input v-model="user.name" placeholder="请输入真实姓名" clearable prefix-icon="Avatar">
                    </el-input>
                </el-form-item>
                <el-form-item prop="password" >
                    <el-input v-model="user.password" placeholder="请输入密码" clearable type="password" prefix-icon="Lock"
                        show-password>
                    </el-input>
                </el-form-item>
                <el-form-item prop="repassword" >
                    <el-input v-model="repassword" placeholder="请确认密码" clearable type="password" prefix-icon="Lock"
                        show-password>
                    </el-input>
                </el-form-item>

                <span style="color: red">{{ msg }}</span>
                <el-form-item>
                    <el-button color="#3c9566" plain @click="loginf()">
                        <span style="display: inline-block; width: 142px"></span>
                        点击注册
                        <span style="display: inline-block; width: 142px"></span>
                    </el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
export default {
    name: "register",
    data() {
        return {
            user: {
                username: "",
                name: "",
                password: "",
                gender: "",
                age: "",
                telephone: "",
            },
            repassword: "",
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
        loginf() {
            console.log(this.user);
            if (this.user.password == this.repassword) {
                var _this = this;
                this.$axios({
                    method: "post",
                    url: "/api/register",
                    headers: {
                        "Content-Type": "application/json;charset=UTF-8",
                    },
                    data: _this.user,
                }).then(res => {
                    console.log("成功", res.data);
                    alert("成功")
                    this.$router.push("/login")
                }).catch((err) => {
                    console.log("注册失败", err);
                });
            } else {
                alert("两次密码不一致")
            }

        }
    },



};
</script>

<style scoped>
.loginPart {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 450px;
    padding: 40px;
    background: rgba(201, 191, 191, 0.5);
    box-sizing: border-box;
    box-shadow: 0px 15px 25px rgba(1, 0, 0, 0.5);
    border-radius: 15px;
}

.loginPart h1 {
    margin: 0 0 30px;
    padding: 0;
    color: #fff;
    text-align: center;
    /*文字居中*/
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
    background: url("../../public/register.jpeg") no-repeat;
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
