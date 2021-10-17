<template>
  <div>
    <p><router-link to="start">ClearBnB</router-link></p>
    <Navbar />
    <p>Login</p>
    <form @submit.prevent="login">
      <p class="centeredText">Username</p>
      <input v-model="username" class="username" type="text" />
      <p class="centeredText">Password</p>
      <input v-model="password" class="password" type="text" />
      <br /><br />
      <button type="submit">Login</button>
    </form>
  </div>
</template>

<script>
import Navbar from "../components/Navbar.vue";
export default {
  name: "Login",
  components: {
    Navbar,
  },
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    async login() {
      let credentials = {
        username: this.username,
        email: this.username,
        password: this.password,
      };
      let loginUser = this.$store.dispatch('login', credentials);
      console.log("USER ID: ", loginUser.id);
      this.$store.commit('setUser', credentials);
      localStorage.setItem('user', JSON.stringify(credentials));
      this.$router.push('listings');
    },
  },
};
</script>

<style scoped>
</style>
