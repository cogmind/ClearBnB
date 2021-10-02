<template>
  <div>
    <p>ClearBnB</p>
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
export default {
  name: "Login",
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
      this.$store.dispatch("login", credentials);
    },
  },
};
</script>

<style scoped>
</style>
