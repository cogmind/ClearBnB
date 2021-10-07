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
  name: "ViewListings",
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
      start: "",
      end: "",
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
