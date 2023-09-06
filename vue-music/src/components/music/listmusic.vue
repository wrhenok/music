<template>
    <el-table :data="musicList" style="width: 100%">
        <el-table-column prop="oldName" label="Title" />
        <el-table-column prop="artist" label="Artist" />
        <el-table-column prop="duration" label="Time">
        </el-table-column>
        <el-table-column>
            <template #header>
                <el-input v-model="search" size="small" placeholder="Type to search" />
            </template>
            <template #default="scope">
                <el-button size="small" @click="selectOne(scope.row.id)">查看详情</el-button>
                <el-button text @click="heart(scope.$index)">
                    <img v-if="musicList[scope.$index].isHeart == 0" src="../../assets/heart.png" />
                    <img v-else src="../../assets/isHeart.png" />
                </el-button>
                <el-button size="small" type="danger" @click="startplay(scope.row.newName)">播放</el-button>
                <el-button size="small" type="danger" @click="download(scope.row.id)">下载</el-button>
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
    name: "listmusic",
    data() {
        return {
            musicList: [{}],
            search: '',
            src: "",
            user:{},
        }
    },
    methods: {
        selectOne(a) {
            console.log(a);
            this.$router.push(
                {
                    path: '/one',
                    query: { "id": a }
                }
            )
        },
        heart(i) {
            console.log("okok", i);
            if (this.musicList[i].isHeart == 1) {
                this.musicList[i].isHeart = 0
            } else {
                this.musicList[i].isHeart = 1
            }
            var _this = this
            console.log(1111, this.musicList[i]);
            this.$axios({
                method: "POST",
                url: "http://121.4.93.149:8001/updHeart",
                data: JSON.stringify(_this.musicList[i]),
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8',
                },
            }).then((res) => {
                console.log("成功", res);
                console.log("success", this.musicList[i]);
            })
        },

        startplay(i) {
            this.src = "/api/play/"+ i + ".mp3"
        },

        async download(a) {
            window.location.href = '/api/download/' + a
        },
    },
    mounted() {
        this.user = this.$store.state.user;
        this.musicList = this.$store.state.musicList;
        console.log(this.musicList);
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