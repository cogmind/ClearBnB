<template>
  <div>
    <p>ClearBnB</p>
    <p>Register</p>
    <form @submit.prevent="register">
      <p class="centeredText">Username/Email</p>
      <input v-model="username" class="username" type="text" />
      <p class="centeredText">Password</p>
      <input v-model="password" class="password" type="text" />
      <br /><br />
      <button type="submit">Register</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "Register",
  components: {},
  data() {
    return {};
  },
  methods: {
    async register() {
      let credentials = {
        username: this.username,
        email: this.username,
        password: this.password,
      };
      this.$store.dispatch("register", credentials);
    },
  },
};
</script>

<style scoped>
</style>
