<template>
  <p><router-link to="start">ClearBnB</router-link></p>
  <Navbar />
  <p>View Listings</p>
  <ul>
    <li v-for="(listing) in listings" v-bind:key="listing.listing_id">
      <img :src="listing.image_url" width="300" alt="listed property"/>
      <h3>{{ listing.title }}</h3>
      <p>{{ listing.price }} €</p>
      <p>Guests: {{ listing.guests }}</p>
      <p>Available:
        <!-- Hardcoded timezone offset -->
        {{ new Date(listing.start + 3600000).toISOString().split('T')[0] }} ―
        {{ new Date(listing.end + 3600000).toISOString().split('T')[0]}}</p>
      <p><br/><br/> {{listing.description}}</p>
      <button v-if="$store.getters.getUser !== null" type="button" @click="book($event, listing)">Book</button>
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
        start: selectedListing.start, // Hardcoded time-zone
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
