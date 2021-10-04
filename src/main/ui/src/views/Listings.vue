<template>
  <p>ClearBnB</p>
  <Navbar />
  <p>Listings</p>
  <ListingItem
    v-for="(listing, index) of listings"
    :key="index"
    :current_listing="listing"
    @filter="filterListings"
  />
</template>

<script>
import Navbar from "../components/Navbar.vue";
export default {
  name: "Listings",
  components: {
    Navbar,
  },
  data() {
    return {
      listing_id: "1",
      version: 0,
      owner_id: "",
      audited_datetime: "",
      title: "",
      description: "",
      image_url: "",
      guests: 0,
      price: 0.0,
      listing_start_date: "",
      listing_end_date: "",
    };
  },
  methods: {},
  async beforeMount() {
    this.listings = await (await fetch("/api/listings")).json();
  },
};
</script>

<style scoped>
</style>
