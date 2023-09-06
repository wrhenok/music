<template>
    <el-table :data="musicData" style="width: 100%">
        <el-table-column prop="oldName" label="Title" />
        <el-table-column prop="artist" label="Artist" />
        <el-table-column prop="duration" label="Time" />
        <el-table-column label="❤❤❤❤">
            <template #default="scope">
                <el-button text @click="heart(scope.$index)">
                    <img v-if="musicData[scope.$index].isHeart == 0" src="../../assets/heart.png" />
                    <img v-else src="../../assets/isHeart.png" />
                </el-button>
            </template>
        </el-table-column>
        <el-table-column width="350">
            <template #header>
                <el-input v-model="search" size="small" placeholder="Type to search" />
            </template>
            <template #default="scope">
                <el-tooltip content="查看详情" placement="top">
                    <el-button @click="selectOne(scope.row.id)">
                        <el-icon>
                            <More />
                        </el-icon>
                    </el-button>
                </el-tooltip>
                <el-tooltip content="添加到播放列表" placement="top">
                    <el-button type="danger" @click="addlist(scope.row)">
                        <el-icon>
                            <CirclePlus />
                        </el-icon>
                    </el-button>
                </el-tooltip>
                <el-tooltip content="播放" placement="top">
                    <el-button type="danger" @click="startplay(scope.row.newName)">
                        <el-icon>
                            <VideoPlay />
                        </el-icon>
                    </el-button>
                </el-tooltip>
                <el-tooltip content="下载" placement="top">
                    <el-button type="danger" @click="download(scope.row.id)">
                        <el-icon>
                            <Download />
                        </el-icon>
                    </el-button>
                </el-tooltip>
                <el-tooltip content="删除" placement="top">
                    <el-button type="danger" @click="deleteMusic(scope.row.id)">
                        <el-icon>
                            <Delete />
                        </el-icon>
                    </el-button>
                </el-tooltip>
            </template>
        </el-table-column>
    </el-table>
    <div class="audio-play">
        <h3>音乐播放器</h3>
        <audio :src=src type="audio/mpeg" controls="controls"></audio>
    </div>
</template>

<script>
import { ElMessage } from 'element-plus';
export default {
    name: "allmusic",
    data() {
        return {
            musicData: [{}],
            search: '',
            src: "",
            user: {},
            musiclist: {}
        }
    },
    methods: {
        getMusic(a) {
            console.log("a的值为：", a);
            let that = this
            this.$axios({
                method: "GET",
                url: "/api/all/" + a,
                headers: {
                    'Content-Type': 'multipart/form-data'
                },
            }).then((res) => {
                console.log("成功", res);
                that.musicData = res.data
            })
        },
        addlist(a) {
            console.log(a);
            this.$store.commit('add1', a);
            ElMessage({
                message: '添加成功',
                type: 'success'
            })
            console.log("musiclist", this.$store.state.musicList);
        },

        selectOne(a) {
            console.log(a);
            this.$router.push({ path: '/one', query: { "id": a } })
        },

        heart(i) {
            console.log("okok", i);
            if (this.musicData[i].isHeart == 1) {
                this.musicData[i].isHeart = 0
            } else {
                this.musicData[i].isHeart = 1
            }
            var _this = this
            this.$axios({
                method: "POST",
                url: "/api/updHeart",
                data: JSON.stringify(_this.musicData[i]),
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8',
                },
            }).then((res) => {
                console.log("成功", res);
            })
        },

        startplay(i) {
            this.src = "http://121.4.93.149:8001/play/" + i + ".mp3"
        },

        deleteMusic(a) {
            this.$axios({
                method: "GET",
                url: "/api/delete/" + a,
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8',
                },
            }).then(() => {
                ElMessage({
                    message: '删除成功',
                    type: 'success'
                })
            })
            this.getMusic(a)
        },

        // getLikeMusic(a) {
        //     this.$axios({
        //         method: "GET",
        //         url: "/api/likeName?oldName=" + a + "&userId=" + this.user.id
        //     }).then((res) => {

        //     })
        // },

        async download(a) {
            window.location.href = '/api/download/' + a
        },

    },
    mounted() {
        this.user = this.$store.state.user;
        this.getMusic(this.user.id)
    }
}
</script>

<style scoped>
.audio-play {
    position: absolute;
    bottom: 10px;
    left: 40%;
}
</style>