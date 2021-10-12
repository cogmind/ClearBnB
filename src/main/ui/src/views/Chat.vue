<template>
  <p><router-link to="start">ClearBnB</router-link></p>
  <Navbar />
  <p>Chat</p>
</template>

<script>
import Navbar from "../components/Navbar.vue";
export default {
  name: "Chat",
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
