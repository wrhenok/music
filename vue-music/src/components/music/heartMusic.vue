<template>
    <el-table :data="heartData" style="width: 100%">
        <el-table-column prop="oldName" label="Title" />
        <el-table-column prop="artist" label="Artist" />
        <el-table-column prop="duration" label="Time" />
        <el-table-column>
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
                <el-tooltip content="播放" placement="top">
                    <el-button type="danger" @click="startplay(scope.row.uploadPath)">
                        <el-icon>
                            <VideoPlay />
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

export default {
    name: "heartMusic",
    data() {
        return {
            userId: "",
            heartData: [{}],
            search: '',
            src: ''
        }
    },
    methods: {
        getHeart(a) {
            this.$axios({
                method: "GET",
                url: "/api/heart/" + a,
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                },
            }).then((res) => {
                console.log("成功", res);
                this.heartData = res.data
            }).catch((res) => {
                console.log("失败", res);
            })
        },
        selectOne(a) {
            console.log(a);
            this.$router.push(
                {
                    path: '/one',
                    query: { "id": a }
                }
            )
        },
        
        startplay(i) {
            this.src = "/api/play/"+ i + ".mp3"
        },
    },
    mounted() {
        this.userId = this.$store.state.user.id
        console.log("mounted", this.userId);
        this.getHeart(this.userId)
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