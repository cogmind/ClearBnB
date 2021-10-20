<template>
<div>
  <br>
  <form @submit.prevent="$emit('search')" action="submit">
    <label for="title">Title</label>
    <input name="title" type="text" size="10">
    <label for="description">Description</label>
    <input name="description" type="text">
    <label for="price">Price</label>
    <input name="price" type="text" size="4">
    <label for="location">Location</label>
    <input name="location" type="text" size="10">
    <label for="checkInDate">Check in</label>
    <input name="checkInDate" type="text" size="15">
    <label for="checkOutDate">Check out</label>
    <input name="checkOutDate" type="text" size="15">
    <button type="submit">Search</button>
  </form>
  <hr>
</div>
</template>

<script>
export default {

  constructor() {
    console.log('Loaded filter listings');
  }
}
</script>

<style>

</style>