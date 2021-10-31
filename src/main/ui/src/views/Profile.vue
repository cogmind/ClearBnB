<template>
  <p>Profile</p>
  <p>{{ this.$route.params.username }}</p>
  <MyProfile v-bind:userId="this.$route.params.id"/>
</template>

<script>
import './MyProfile.vue';

export default {
  components: {
    MyProfile,
  },
  methods:
  {

  },
}
</script>

<style>

</style>