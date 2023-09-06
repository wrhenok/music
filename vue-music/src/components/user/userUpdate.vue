<template>
    <el-form :model="user" ref="user">
        <el-form-item prop="username" >昵称：<el-input v-model="user.username"/></el-form-item>
        <el-form-item prop="gender" >
            <el-radio-group v-model="user.gender" class="ml-4">
                <el-radio label="0" :value=user.gender size="large">男</el-radio>
                <el-radio label="1" :value=user.gender size="large">女</el-radio>
            </el-radio-group>
        </el-form-item>
        <el-form-item prop="age" >年龄：<el-input v-model="user.age"/></el-form-item>
        <el-form-item prop="telephone" >手机：<el-input v-model="user.telephone"/></el-form-item>
        <el-form-item prop="name" >姓名：<el-input v-model="user.name"/></el-form-item>
        <el-button type="danger" plain @click="updateUser()">确定</el-button>
    </el-form>
</template>

<script>
import { ElMessage } from 'element-plus'
export default {
    name: "register",
    data() {
        return {
            user: {},
            updateInfo: true
        };
    },
    mounted() {
        this.user = this.$store.state.user
        console.log(this.user);
    },
    methods: {
        updateUser() {
            console.log(this.user);
            var _this = this;
            this.$axios({
                method: "post",
                url: "http://localhost:8001/updateUser",
                headers: {
                    "Content-Type": "application/json;charset=UTF-8",
                },
                data: _this.user,
            }).then(res => {
                console.log(res.data);
                ElMessage({
                    message: '修改成功',
                    type: 'success'
                })
                this.updateInfo = false
                this.$router.push("/")
            });
        }
    }
};
</script>