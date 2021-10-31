<template>
  <p><router-link to="start">ClearBnB</router-link></p>
  <Navbar />
  <p>Browse Profiles</p>
  <p v-for="user in users" v-bind:key="user.userId">
    <router-link :to="'/profile/' + user.username + user.userId">{{ user.username }}</router-link>
  </p>
</template>

<script>

import Navbar from "../components/Navbar.vue";

export default {
  name: "Profiles",
  components: {
    Navbar,
  },
  data() {
    return {
      users: [],
    };
  },
  async beforeMount() {
    this.users = await (await fetch('http://localhost:4000/api/users')).json();
    console.log(this.users);
  },
  methods: {
    getUserLink() {
      return 'test';
    },
  },
}
</script>

<style scoped>

</style>