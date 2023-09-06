<template>
    <el-upload class="upload-demo" drag @change="getfile">
        <el-icon class="el-icon--upload">
            <upload-filled />
        </el-icon>
        <div class="el-upload__text">
            Drop file here or<em>click to upload</em>
        </div>
    </el-upload>
    <el-form-item label="歌手：">
        <el-input v-model="artist" placeholder="请输入歌手" />
    </el-form-item>
    <el-button type="primary" plain @click.prevent="upload">click to upload</el-button>
</template>

<script>
import { UploadFilled } from '@element-plus/icons-vue'

export default {
    name: "uploadmusic",
    data() {
        return {
            formData: '',
            musicData: '',
            artist:""
        }
    },
    methods: {
        getfile(e) {
            let files = e.raw;
            this.formData = files;
        },
        upload() {
            let that = this;
            if (this.formData == "") {
                console.log('请先添加文件');
                return;
            }
            console.log(this.formData);
            this.$axios({
                url: "/api/upload",
                method: 'post',
                headers: {
                    'Content-Type': 'multipart/form-data'
                },
                data: { multipartFile: that.formData,
                    userId: this.$store.state.user.id,
                    artist: this.artist
                },
            }).then((res) => {
                this.musicData = res
                alert("上传成功");
            }).catch((res) => {
                console.log(res);
            })
        }
    },
}

</script>

<style>

</style>