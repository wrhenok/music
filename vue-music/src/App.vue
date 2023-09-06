<template>
  <!-- <div v-if="userId!=null">
    <home></home>
  </div>
  <div v-if="userId == null">
    <login></login>
  </div> -->
  <router-view></router-view>
</template>

<script>
// import login from './views/login.vue';
// import home from './views/HomeView.vue'
export default {
  // components: {
  //   login,
  //   home
  // },
  data() {
    return {
      userId: ""
    }
  },
  mounted() {
    this.userId = this.$store.state.user.id
  }
}

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
