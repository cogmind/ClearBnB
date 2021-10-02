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
  async mounted() {},
  methods: {
    async login() {
      let credentials = {
        email: this.username,
        password: this.password,
      };
      console.log(credentials.email);
      let res = await fetch("http://localhost:4000/api/login", {
        method: "POST",
        body: JSON.stringify(credentials),
      })
        .then(function (response) {
          return response.json();
        })
        .then(function (data) {
          console.log(data);
        });
    },
  },
};
</script>

<style scoped>
</style>
