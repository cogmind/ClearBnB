<template>
<div>
  <p><router-link to="start">ClearBnB</router-link></p>
  <Navbar />
  <p>Profile page for {{ username }}</p>
  <hr>
  <h2>Listings</h2>
  <ol>
    <li v-for="listing in listings" v-bind:key="listing.listing_id">
      <img :src="listing.image_url" width="300" alt="listed property" />
      <h3>{{ listing.title }}</h3>
      <p><strong>{{ listing.location }}</strong></p>
      <p>{{ listing.price }} €</p>
      <p>Guests: {{ listing.guests }}</p>
      <p>
        Available:
        {{ formatDate(listing.start) }} ― {{ formatDate(listing.end) }}
      </p>
      <p>
        <br /><br />
        {{ listing.description }}
      </p>
    </li>
  </ol>
  <hr>
  <h2>Bookings</h2>
  <ol>
    <li v-for="booking in bookings" v-bind:key="booking.booking_id">
      <p><strong> {{ getTitle(booking.listing_id) }} </strong></p>
      <img :src="getImage(booking.listing_id)" width="150" alt="booked property" />
      <br>
      <br>
      <p v-if="booking.cancelled" style="color: red">CANCELLED</p>
      Guests: {{ booking.guests }}
      <br>
      <p>Fee: {{ booking.fee }}</p>
      {{ formatDate(booking.start) }} ― {{ formatDate(booking.end )}}
      <br><br>
      <div v-if="!isReviewed()">
        <form @submit.prevent="review">
          <textarea rows="8" v-model="review.comment" cols="35" name="comment" placeholder="Add review"/>
          <br>
          <input type="text" v-model="review.rating" name="rating" placeholder="Rating (1-5)" size="5" maxlength="1"/>
          <br><br>
          <button type="submit">Add Review</button>
        </form>
        <br><br>
      </div>
      <div v-if="isReviewed(booking.booking_id)">
        <ReviewItem :comment="review.comment" :rating="review.rating" />
      </div>
    </li>
  </ol>
</div>
</template>

<script>
import Navbar from "../components/Navbar.vue";
import ReviewItem from "../components/ReviewItem.vue";

export default {
  name: 'MyProfile',
  components: { Navbar, ReviewItem },
  data() {
    return {
      username: "",
      userId: "",
      listings: [],
      bookings: [],
      bookedListings: [],
      review: {
        comment: "",
        rating: "",
        timestamp: "",
        author_id: -1,
        target_id: -1,
        version: 1,
      },
    }
  },
  async created() {
    
    this.username = this.$store.getters.getUser.username;
    this.userId = this.$store.getters.getUserId;
    
    // Get listings
    this.listings = await (await fetch('http://localhost:4000/api/user-listings/' + this.userId)).json();

    // Get bookings
    this.bookings = await (await fetch('http://localhost:4000/api/user-bookings/' + this.userId)).json();

    // Get booked listings
    let listingId;
    for (let booking of this.bookings) {
      listingId = booking.listing_id;
      this.bookedListings.push({id: listingId, listing: await (await fetch('http://localhost:4000/api/listing/' + listingId)).json()});
      console.log('pushed 1 listing onto bookedListings')
    }
    console.log('this.bookedListings ', this.bookedListings);
  },
  methods: {
    isReviewed() {
      return false;
    },
    async review() {

      console.log('Review');
      review.target_id = booking_id;

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
        review.author_id = data;
      });

      await fetch("http://localhost:4000/api/create-review", {
        method: "POST",
        body: JSON.stringify(review),
      })
        .then(function (response) {
          return response.json();
        });
    },
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
    getTitle(listingId) {
      let listing = this.bookedListings.find(currentListing => currentListing.id === listingId).listing;
      return listing.title;
    },
    getImage(listingId) {
      let listing = this.bookedListings.find(currentListing => currentListing.id === listingId).listing;
      return listing.image_url;
    },
  },
}
</script>

<style scoped>

</style>
