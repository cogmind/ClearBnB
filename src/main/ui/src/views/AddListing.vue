<template>
  <p><router-link to="start">ClearBnB</router-link></p>
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
      audited_datetime: null,
      owner_id: null,
      title: "",
      description: "",
      image_url: "",
      guests: 0,
      price: 0.0,
      start: "",
      end: "",
    };
  },
  methods: {
    async addListing() {
      // Format date
      if (this.start.indexOf('-') < 0) {
      this.start =
        this.start.substring(0, 4) +
        "-" +
        this.start.substring(4, 6) +
        "-" +
        this.start.substring(6, 8);
      }

      if (this.end.indexOf('-') < 0) {
      this.end =
        this.end.substring(0, 4) +
        "-" +
        this.end.substring(4, 6) +
        "-" +
        this.end.substring(6, 8);
        }

      // Timestamp
      this.audited_datetime = Date.now();

      
      let listing = {
        owner_id: this.owner_id,
        title: this.title,
        description: this.description,
        image_url: this.image_url,
        location: this.location,
        guests: this.guests,
        price: this.price,
        start_date: this.start,
        end_date: this.end,
        audited_datetime: this.audited_datetime,
      };

      // Get owner id
      await fetch("http://localhost:4000/api/userid", {
        method: "GET",
        credentials: "include",
      }).then(function (response) {
      if (response.ok) {
        console.log("FETCH COMPLETE userid ");
        return response.json();
      } else {
        console.log("FETCH FAILED userId");
        return Promise.reject(response);
      }}).then( function (data) {
        listing.owner_id = data;
        console.log("FETCH createlisting");
      return fetch("http://localhost:4000/api/createlisting", {
        method: "POST",
        body: JSON.stringify(listing),
      })
        .then(function (response) {
          console.log(listing);
          console.log(data);
          return response.json();
        })
      })

      this.$router.push('listings');
    },
  },
};
</script>

<style scoped>
</style>