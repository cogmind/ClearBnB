<template>
  <div>
    <p>ClearBnB</p>
    <p>Login</p>
    <form @submit.prevent="login">
      <p class="centeredText">Username</p>
      <input class="username" type="text" />
      <p class="centeredText">Password</p>
      <input class="password" type="text" />
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
        username: this.username,
        password: this.password,
      };
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
