<template>
  <p><router-link to="start">ClearBnB</router-link></p>
  <Navbar />
  <p>View Listings</p>
  <ul>
    <li v-for="(listing) in listings" v-bind:key="listing.id">
      <img :src="listing.image_url" width="300"/>
      <h3>{{listing.title}}</h3>
      <p>Description<br/><br/> {{listing.description}}</p>
      <hr/>
      </li>
  </ul>

  <!-- <ListingItem
    v-for="(listing, index) of listings"
    :key="index"
    :listing="listing"
    @filter="filterListings"
  /> -->
</template>

<script>
import Navbar from "../components/Navbar.vue";
export default {
  name: "ViewListings",
  components: {
    Navbar,
  },
  data() {
    return {
    listings: [],
    };
  },
  methods: {},
  // async beforeMount() {
  //   this.listings = await (await fetch("/api/listings")).json();
  // },
  async mounted() {
    this.listings = await (await fetch("/api/listings")).json();
    console.log(this.listings)
  },
}
</script>

<style scoped>
ul {
  list-style-type: none;
  padding-left:0;
}
</style>
