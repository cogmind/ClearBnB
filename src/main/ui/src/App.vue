<template>
  <router-view />
</template>

<script>
export default {
  name: "App",
  data() {
    return {};
  },
  async mounted(){
    console.log('mounted');
    // Keep login state
    let credentials = localStorage.getItem('user');
    this.$store.dispatch('login', credentials);
    // Logout if user exits tab or browser
    document.addEventListener('visibilitychange', () => {
      this.$store.dispatch('logout');
      localStorage.removeItem('user');
    });
  }
}
</script>

<style>
#app {
  font-family: sans-serif;
}
</style>
