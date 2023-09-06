<template>
    <table class="el-table">
        <tr class="el-tr">
            <th>Artist</th>
            <th>歌曲数量</th>
        </tr>
        <tr v-for="(value, key) in artistData" class="el-tr"> 
            <td>{{key}}</td>
            <td>
                <el-button type="danger" size="small">{{value}}</el-button>
            </td>
        </tr>
    </table>
</template>

<script>

export default {
    name:"artist",
    data() {
        return {
            artistData:{},
        }
    },
    methods:{
        mount(a){
            this.$axios({
                method: "GET",
                // url: "http://192.168.31.223:8001/all/"+a,
                url: "/api/art/"+a,
                headers: {
                    'Content-Type': 'multipart/form-data'
                },
            }).then((res) => {
                this.artistData = res.data
                console.log("artist111",this.artistData);
            })
        }
    },
    mounted() {
        this.user = this.$store.state.user; 
        this.mount(this.user.id)
    },
}

</script>

<style scoped>
.el-table{
    text-align: justify;
    /* border: 1px solid green; */
    padding-left: 3px;
}
.el-tr {
    height: 40px;
    border-bottom: 1px solid gray;
}


</style>