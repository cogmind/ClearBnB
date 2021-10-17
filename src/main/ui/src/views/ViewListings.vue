<template>
  <p><router-link to="start">ClearBnB</router-link></p>
  <Navbar />
  <p>View Listings</p>
  <ul>
    <li v-for="(listing) in listings" v-bind:key="listing.listing_id">
      <img :src="listing.image_url" width="300" alt="listed propery"/>
      <h3>{{ listing.title }}</h3>
      <p>{{ listing.price }} €</p>
      <p>Guests: {{ listing.guests }}</p>
      <p>Available:
        {{ new Date(listing.start).toISOString().split('T')[0] }} ―
        {{ new Date(listing.end).toISOString().split('T')[0]}}</p>
      <p><br/><br/> {{listing.description}}</p>
      <button type="button" @click="book($event, listing)">Book</button>
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
      selectedListing: -1,
      listings: [],
    };
  },
  methods: {
    book(event, selectedListing){
      this.$router.push({
      name: 'book',
      params: {
        id: selectedListing.listing_id,
        start: selectedListing.start,
        end: selectedListing.end,
        fee: selectedListing.price,
        guests: selectedListing.guests,
        }
    });

    },
  },
  async beforeMount() {
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
button {
  padding: 20px;
  background-color: #009;
  color: white;
  border-radius: 12px;
  border: none;
}
</style>
