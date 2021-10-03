<template>
  <p>ClearBnB</p>
  <p>You have been logged out</p>
</template>

<script>
export default {
  name: "Logout",
  components: {},
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
