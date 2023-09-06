<template>
  <div class="common-layout">
    <el-container>
      <el-header class="el-header">
        <headtop></headtop>
      </el-header>
      <el-container class="el-container">
        <el-aside>
          <sidebar></sidebar>
        </el-aside>
        <el-main class="main">
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import headtop from '../components/head/headtop.vue';
import sidebar from '../components/aside/sidebar.vue';

export default {
  name: 'home',
  components: {
    headtop,
    sidebar
  },
}
</script>

<style scoped>
body{
  margin: 0px;
}
.el-header{
  position: relative;
  top: 0px;
  padding: 0%;
}
</style>
