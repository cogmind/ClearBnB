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
    this.$store.dispatch("logout");
    localStorage.setItem("user", null);
  },
};
</script>

<style scoped>
</style>
