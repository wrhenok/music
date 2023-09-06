<template>
    <div>
        <div class="detail">
            <img src="../../assets/page.jpg" class="img-item" />
            <div class="detail-item">
                <el-descriptions :title="oneMusic.oldName" :column="1">
                    <el-descriptions-item>{{ oneMusic.artist }}</el-descriptions-item><br />
                    <el-descriptions-item>
                        <el-button text @click="heart">
                            <img v-if="oneMusic.isHeart == 0" src="../../assets/heart.png" />
                            <img v-else src="../../assets/isHeart.png" />
                        </el-button>
                    </el-descriptions-item>
                    <el-descriptions-item>{{ (oneMusic.musicSize / 1024 / 1024).toFixed(2) }} MB</el-descriptions-item>
                    <el-descriptions-item>{{ oneMusic.duration }}</el-descriptions-item>
                </el-descriptions>
            </div>
        </div>
        <el-divider />
        <div class="flex justify-space-between mb-4 flex-wrap gap-4">
            <el-button type="primary" text>
                <el-icon :size="25" @click="startplay(oneMusic.newName)">
                    <VideoPlay />
                </el-icon>播放
            </el-button>
            <el-button type="warning" text @click="addlist(oneMusic)">添加到播放列表</el-button>
            <el-button text type="success" @click="download(oneMusic.id)">下载（{{ (oneMusic.musicSize / 1024 /
                    1024).toFixed(2)
            }} MB）
            </el-button>
        </div>
        <el-divider />
        <el-descriptions direction="vertical" :column="10" size="large" border>
            <el-descriptions-item label="Title">{{ oneMusic.oldName }}</el-descriptions-item>
            <el-descriptions-item label="Artist">{{ oneMusic.artist }}</el-descriptions-item>
            <el-descriptions-item label="Size" :span="2">{{ (oneMusic.musicSize / 1024 / 1024).toFixed(2) }} MB
            </el-descriptions-item>
            <el-descriptions-item label="isHeart">
                <el-button text @click="heart">
                    <img v-if="oneMusic.isHeart == 0" src="../../assets/heart.png" />
                    <img v-else src="../../assets/isHeart.png" />
                </el-button>
            </el-descriptions-item>
            <el-descriptions-item label="操作">
                <el-tooltip content="播放" placement="top">
                    <el-button type="danger" @click="startplay(oneMusic.newName)">
                        <el-icon>
                            <VideoPlay />
                        </el-icon>
                    </el-button>
                </el-tooltip>
                <el-tooltip content="下载" placement="top">
                    <el-button type="danger" @click="download(oneMusic.id)">
                        <el-icon>
                            <Download />
                        </el-icon>
                    </el-button>
                </el-tooltip>
            </el-descriptions-item>
        </el-descriptions>
        <h3>音乐播放器</h3>
        <div class="audio-play">
            <audio :src=src type="audio/mpeg" controls="controls" autoplay="autoplay"></audio>
        </div>
    </div>
</template>

<script>
import { useRoute } from 'vue-router'
import { ElMessage } from 'element-plus'

export default {
    name: "onemusic",
    data() {
        return {
            oneMusic: {},
            dialogVisible: false,
            src: ""
        }
    },
    methods: {
        mount() {
            const route = useRoute()
            this.$axios({
                method: "GET",
                url: "/api/select/" + route.query.id,
                headers: {
                    'Content-Type': 'multipart/form-data'
                },
            }).then((res) => {
                console.log("成功", res.data);
                this.oneMusic = res.data
            }).catch((res) => {
                console.log("失败", res);
            })
        },
        startplay(i) {
            this.src = "http://121.4.93.149:8001/play/" + i + ".mp3"
        },

        addlist(a) {
            console.log(a);
            this.$store.commit('add1', a);
            ElMessage({
                message: '修改成功',
                type: 'success'
            })
            console.log("musiclist", this.$store.state.musicList);
        },

        heart() {
            if (this.oneMusic.isHeart == 1) {
                this.oneMusic.isHeart = 0
            } else {
                this.oneMusic.isHeart = 1
            }
            const that = this
            console.log(that.oneMusic);
            this.$axios({
                method: "POST",
                url: "/api/updHeart",
                data: JSON.stringify(that.oneMusic),
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8',
                },
            }).then((res) => {
                console.log("成功", res.data);
                this.oneMusic = res.data
            }).catch((res) => {
                console.log("失败", res);
            })
        },

        async download(a) {
            window.location.href = '/api/download/' + a
        }

    },
    mounted() {
        console.log("mounted");
        this.mount()
    }

}

</script>

<style scoped>
.detail {
    display: flex;
    border-radius: 15px;
    box-shadow: 0px 15px 25px rgba(1, 0, 0, 0.5);
}

.img-item {
    height: 200px;
    margin-left: 15px;
}

.input {
    width: 10%;
    display: inline-block;
}

.detail-item {
    margin: 5px 10px;
}

.audio-play {
    position: absolute;
    bottom: 10px;
    left: 40%;
}
</style>