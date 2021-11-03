<template>
<p><router-link to="/">ClearBnB</router-link></p>
<Navbar />
  <AnyProfile v-bind:userId="this.id"/>
</template>

<script>
import AnyProfile from './AnyProfile.vue';
import Navbar from '../components/Navbar.vue';

export default {
  components: {
    AnyProfile,
    Navbar
  },
  data () {
    return {
      username: this.$route.params.username,
      id: this.$route.params.id,
    }
  },
  methods:
  {

  },
}
</script>

<style>

</style>