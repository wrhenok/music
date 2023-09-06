<template>
    <el-col :span="2" class="">
        <!-- <el-radio-group v-model="isCollapse">
            <el-radio-button :label=!isCollapse>expand</el-radio-button>
        </el-radio-group> -->
        <el-menu active-text-color="#ffd04b" background-color="#545c65" class="el-menu-vertical-demo" default-active="1"
            :collapse="isCollapse" text-color="#fff" @open="handleOpen" @close="handleClose">
            <el-menu-item index="1" @click="musicAll">
                <el-icon>
                    <Service />
                </el-icon>
                <span>音乐</span>
            </el-menu-item>
            <el-menu-item index="2" @click="isArtist">
                <el-icon>
                    <Microphone />
                </el-icon>
                <span>艺术家</span>
            </el-menu-item>
            <el-menu-item index="3" @click="musicAll">
                <el-icon>
                    <VideoPlay />
                </el-icon>
                <span>歌曲</span>
            </el-menu-item>
            <el-menu-item index="4" @click="musiclist">
                <el-icon>
                    <Finished />
                </el-icon>
                <span>播放列表</span>
            </el-menu-item>
            <el-menu-item index="5" @click="musicHeart">
                <el-icon><Apple /></el-icon>
                <span>我喜欢的歌</span>
            </el-menu-item>
            <el-menu-item index="6" @click="upload">
                <el-icon>
                    <UploadFilled />
                </el-icon>
                <span>上传音乐</span>
            </el-menu-item>
        </el-menu>
    </el-col>
</template>

<script>
export default {
    name: "sidebar",
    data() {
        return {
            isCollapse: false,
            userId: ""
        }
    },
    mounted() {
        this.userId = this.$store.state.user.id
    },
    methods: {
        musicAll() {
            this.$router.push("/")
        },
        musiclist() {
            this.$router.push("/list")
        },
        upload() {
            if(this.userId) {
                this.$router.push("/upload")
            }else {
                alert("请先登录")
                this.$router.push("/login")
            }
        },
        musicHeart() {
            if(this.userId) {
                this.$router.push("/heart")
            }else {
                alert("请先登录")
                this.$router.push("/login")
            }
        },
        isArtist() {
            if(this.userId) {
                this.$router.push("/artist")
            }else {
                alert("请先登录")
                this.$router.push("/login")
            }
            
        }

    },

}
</script>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 300px;
    min-height: 633px;
}
</style>

