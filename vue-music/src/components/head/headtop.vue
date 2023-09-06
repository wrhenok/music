<template>
    <el-menu :default-active="activeIndex2" class="el-menu-demo" mode="horizontal" background-color="#545c64"
        text-color="#fff" active-text-color="#ffd04b" @select="handleSelect">
        <el-menu-item index="1">
            <img src="../../assets/logo.png" />MUSIC
        </el-menu-item>
        <div class="flex-grow" />
        <el-menu-item index="2" @click="login" v-if="user.id == null">登录</el-menu-item>
        <el-sub-menu index="3" v-if="user.id != null">
            <template #title>
                <el-icon>
                    <UserFilled />
                </el-icon>
                {{ user.username }}
            </template>
            <el-menu-item index="3-1" @click="myinfo">我的信息</el-menu-item>
            <el-menu-item index="3-2" @click="uptinfo">修改信息</el-menu-item>
            <el-menu-item index="3-3" @click="exit">退出登录</el-menu-item>
        </el-sub-menu>
    </el-menu>
    <el-dialog v-model="dialogFormVisible" title="个人信息">
        <userDetail :detail="user"></userDetail>
    </el-dialog>
    <el-dialog v-model="updateInfo" title="修改信息">
        <el-form :model="user" ref="user">
            <el-form-item prop="username">
                <el-input v-model="user.username" prefix-icon="Avatar"></el-input>
            </el-form-item>
            <el-form-item prop="gender">
                <el-radio-group v-model="user.gender" class="ml-4">
                    <el-radio label="0" :value=user.gender size="large">男</el-radio>
                    <el-radio label="1" :value=user.gender size="large">女</el-radio>
                </el-radio-group>
            </el-form-item>

            <el-form-item prop="age">
                <el-input v-model="user.age" prefix-icon="Opportunity"></el-input>
            </el-form-item>

            <el-form-item prop="telephone">
                <el-input v-model="user.telephone" prefix-icon="Iphone"></el-input>
            </el-form-item>

            <el-form-item prop="name">
                <el-input v-model="user.name" clearable prefix-icon="Sunny">
                </el-input>
            </el-form-item>
            <el-button type="danger" plain @click="updateUser()">确定</el-button>
        </el-form>
    </el-dialog>
</template>

<script>
import userDetail from '../user/userDetail.vue'
export default {
    name: "header",
    components: {
        userDetail,
    },
    data() {
        return {
            islogin: false,
            user: {},
            dialogFormVisible: false,
            updateInfo: false
        }
    },
    methods: {
        login() {
            this.$router.push("/login");
        },
        exit() {
            var _this = this
            this.$confirm('确认退出吗?', '提示', {
                // type: 'warning'
            }).then(() => {
                _this.$store.commit("add", [{ id: null }]);
                this.userId = null;
                _this.$router.push('/login');
            });
        },
        myinfo() {
            this.dialogFormVisible = true;
        },
        uptinfo() {
            this.updateInfo = true;
        },
        updateUser() {
            console.log(this.user);
            var _this = this;
            this.$axios({
                method: "post",
                url: "/api/updateUser",
                headers: {
                    "Content-Type": "application/json;charset=UTF-8",
                },
                data: _this.user,
            }).then(res => {
                console.log("成功", res.data);
                alert("修改成功")
                this.updateInfo = false
                this.$router.push("/")
            });
        }
    },
    mounted() {
        this.user = {
            username: this.$store.state.user.username,
            id: this.$store.state.user.id
        }
        console.log("yonghu", this.user);
    }
}

</script>

<style scoped>
.icon {
    margin: 0 auto;
}

.flex-grow {
    flex-grow: 0.9;
}
</style>