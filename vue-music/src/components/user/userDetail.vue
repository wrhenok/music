<script>
export default {
    name: "userDetail",
    data() {
        return {
            detail: {},
        };
    },
    
    mounted() {
        this.detail = this.$store.state.user;
        console.log(this.detail);
    },
};
</script>

<template>
    <el-descriptions class="margin-top" :column="2" border>
        <el-descriptions-item>
            <template #label>
                <div class="cell-item">
                    <el-icon>
                        <user />
                    </el-icon>
                    姓名
                </div>
            </template>
            {{ detail.name }}
        </el-descriptions-item>

        <el-descriptions-item>
            <template #label>
                <div class="cell-item">
                    <el-icon>
                        <tickets />
                    </el-icon>
                    性别
                </div>
            </template>
            <span v-if="detail.gender=0">男</span>
            <span v-if="detail.gender=1">女</span>
        </el-descriptions-item>

        <el-descriptions-item>
            <template #label>
                <div class="cell-item">
                    <el-icon>
                        <tickets />
                    </el-icon>
                    用户名
                </div>
            </template>
            {{ detail.username }}
        </el-descriptions-item>

        <el-descriptions-item>
            <template #label>
                <div class="cell-item">
                    <el-icon>
                        <tickets />
                    </el-icon>
                    账号类型
                </div>
            </template>
            <span v-if="detail.usertype=0">会员</span>
            <span v-if="detail.usertype=1">普通用户</span>
        </el-descriptions-item>

        <el-descriptions-item>
            <template #label>
                <div class="cell-item">
                    <el-icon>
                        <tickets />
                    </el-icon>
                    年龄
                </div>
            </template>
            {{ detail.age }}
        </el-descriptions-item>

        <el-descriptions-item>
            <template #label>
                <div class="cell-item">
                    <el-icon>
                        <iphone />
                    </el-icon>
                    手机号
                </div>
            </template>
            {{ detail.telephone }}
        </el-descriptions-item>
    </el-descriptions>
</template>

<style>
</style>