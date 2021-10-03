<template>
  <nav>
    <router-link to="/listings" class="link">Listings</router-link>
    <router-link to="/bookings" class="link">Bookings</router-link>
    <router-link to="/chat" class="link">Chat</router-link>
    <router-link v-if="!loggedIn" to="/login" class="link">Login</router-link>
    <router-link v-else to="/logout" class="link">Logout</router-link>
    <router-link to="/register">Register</router-link>
    {{ username }}
  </nav>
</template>

<script>
export default {
  name: "Navbar",
  components: {},
  data() {
    return {
      loggedIn: false,
      username: "",
    };
  },
  methods: {},
  mounted() {
    if (localStorage.getItem("user") !== null) {
      this.username = JSON.parse(localStorage.getItem("user")).username;
      this.loggedIn = true;
    } else {
      this.loggedIn = false;
    }
  },
};
</script>

<style scoped>
.link {
  margin-right: 10px;
}
</style>
