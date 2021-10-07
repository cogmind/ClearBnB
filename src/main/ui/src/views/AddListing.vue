<template>
  <p>ClearBnB</p>
  <Navbar />
  <p>Add Listing</p>
  <form @submit.prevent="addListing">
    <p>Title</p>
    <input v-model="title" type="text" />
    <p>Description</p>
    <input v-model="description" type="text" />
    <p>Image URL</p>
    <input v-model="image_url" type="text" />
    <p>Location</p>
    <input v-model="location" type="text" />
    <p>Guests</p>
    <input v-model="guests" type="text" />
    <p>Price</p>
    <input v-model="price" type="text" />
    <p>Available from (YYYYMMDD)</p>
    <input v-model="start" type="text" />
    <p>Available to (YYYYMMDD)</p>
    <input v-model="end" type="text" />
    <br /><br />
    <button type="submit">Add listing</button>
  </form>
</template>

<script>
import Navbar from "../components/Navbar.vue";

export default {
  name: "AddListings",
  components: {
    Navbar,
  },
  data() {
    return {
      title: "",
      description: "",
      image_url: "",
      guests: 0,
      price: 0.0,
      start: "",
      end: "",
    };
  },
  methods: {},
};
</script>

<style scoped>
</style>