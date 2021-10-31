<template>
  <p>Profile</p>
  <p>{{ this.$route.params.user-id }}</p>
</template>

<script>
export default {
  methods:
  {
    load() {
      console.log(this.$route.params.user-id);
    }
  },
}
</script>

<style>

</style>