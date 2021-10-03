<template>
  <p>ClearBnB</p>
  <Navbar />
  <p>You have been logged out</p>
</template>

<script>
import Navbar from "./Navbar.vue";
export default {
  name: "Logout",
  components: {
    Navbar,
  },
  data() {
    return {};
  },
  methods: {},
  mounted() {
    localStorage.removeItem("user");
    this.$store.dispatch("logout");
  },
};
</script>

<style scoped>
</style>
