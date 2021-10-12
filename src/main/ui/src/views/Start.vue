<template>
  <p><router-link to="/">ClearBnB</router-link></p>
  <Navbar />
</template>

<script>
import Navbar from "../components/Navbar.vue";
export default {
  name: "Start",
  components: {
    Navbar,
  },
  data() {
    return {};
  },
  methods: {},
};
</script>

<style scoped>
</style>
