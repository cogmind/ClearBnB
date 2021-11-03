c<template>
  <p><router-link to="start">ClearBnB</router-link></p>
  <Navbar />
  <FilterListings v-on:search="getFilteredListings" />
  <p>View Listings</p>
  <ul>
    <li v-for="listing in listings" v-bind:key="listing.listing_id">
      <img :src="listing.image_url" width="300" alt="listed property" />
      <h3>{{ listing.title }}</h3>
      <p><b>{{ listing.location }}</b></p>
      <p>{{ listing.price }} €</p>
      <p>Guests: {{ listing.guests }}</p>
      <p>
        Available:
        {{formatDate(listing.start)}} ― {{formatDate(listing.end)}}
      </p>
      <p>
        <br /><br />
        {{ listing.description }}
      </p>
      <button
        v-if="$store.getters.getUser !== null"
        type="button"
        @click="book($event, listing)"
      >
        Book
      </button>
      <hr />
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
import FilterListings from "../components/FilterListings.vue";

export default {
  name: "ViewListings",
  emits: ["search"],
  components: {
    Navbar,
    FilterListings,
  },
  data() {
    return {
      selectedListing: -1,
      listings: [],
    };
  },
  methods: {
    formatDate(date) {
      date = new Date(date);
      let month;
      if (date.getMonth().toString().length === 1) {
        month = '0' + date.getMonth();
      } else {
        month = date.getMonth().toString();
      }
      let day;
      if (date.getDay().toString().length === 1) {
        day = '0' + date.getDay();
      } else {
        day = date.getDay();
      }
      date = date.getFullYear() + '-' + month + '-' + day;
      return date;
    },
    async getFilteredListings(search) {
      let url = new URL("http://localhost:4000/api/search");
      for (let s in search) {
        url.searchParams.append(s, search[s]);
      }
      this.listings = await (
        await fetch(url)
      ).json();
    },
    book(event, selectedListing) {
      this.$router.push({
        name: "book",
        params: {
          id: selectedListing.listing_id,
          start: this.formatDate(selectedListing.start),
          end: this.formatDate(selectedListing.end),
          fee: selectedListing.price,
          guests: selectedListing.guests,
        },
      });
    },
  },
  async beforeMount() {
    this.listings = await (await fetch("/api/listings")).json();
    //console.log('Listings: ',this.listings);
  },
};
</script>

<style scoped>
ul {
  list-style-type: none;
  padding-left: 0;
}
button {
  padding: 20px;
  background-color: #009;
  color: white;
  border-radius: 12px;
  border: none;
}
</style>
