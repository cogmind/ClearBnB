<template>
  <nav>
    <router-link to="/listings" class="link">Listings</router-link>
    <router-link to="/bookings" class="link">Bookings</router-link>
    <router-link to="/chat" class="link">Chat</router-link>
    <router-link to="/login" class="link">Login</router-link>
    <router-link to="/register">Register</router-link>
  </nav>
</template>

<script>
export default {
  name: "Navbar",
  components: {},
  data() {
    return {};
  },
  methods: {},
};
</script>

<style scoped>
.link {
  margin-right: 10px;
}
</style>
