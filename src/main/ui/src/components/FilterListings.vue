<template>
<div>
  <br>
  <form @submit.prevent="$emit('search', this.search)" action="submit">
    <label for="title">Title</label>
    <input v-model="search.title" name="title" type="text" size="10">
    <label for="description">Description</label>
    <input v-model="search.description" name="description" type="text">
    <label for="price">Price</label>
    <input v-model="search.price" name="price" type="text" size="4">
    <label for="location">Location</label>
    <input v-model="search.location" name="location" type="text" size="10">
    <label for="checkInDate">Check in</label>
    <input v-model="search.checkInDate" name="checkInDate" type="text" size="15">
    <label for="checkOutDate">Check out</label>
    <input v-model="search.checkOutDate" name="checkOutDate" type="text" size="15">
    <button type="submit">Search</button>
  </form>
  <hr>
</div>
</template>

<script>
export default {

  constructor() {

  },
  name: 'FilterListings',
  data() {
    return {
      search: {
        title: this.title,
        description: this.description,
        price: this.price,
        location: this.location,
        checkInDate: this.checkInDate,
        checkOutDate: this.checkOutDate,
        }
    }
  }
}
</script>

<style>

</style>