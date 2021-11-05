<template>
<div>
  <h1>Profile page for {{ username }}</h1>
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
        {{ formatDate(listing.start_date) }} ― {{ formatDate(listing.end_date) }}
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
      {{ formatDate(booking.start_date) }} ― {{ formatDate(booking.end_date )}}
      <p>Reviews</p>
      <p><strong>Rating</strong> <span v-for="star in reviewedBookings[0].reviews[0].rating" v-bind:key="star">⭐</span></p>
      {{ reviewedBookings[0].reviews[0].comment }}
      
      <p>
        <br /><br />
      </p>
    </li>
  </ol>
</div>
</template>

<script>
import Navbar from "../components/Navbar.vue";

export default {
  components: { Navbar },
  data() {
    return {
      username: "",
      userId: "",
      listings: [],
      bookings: [],
      bookedListings: [],
      reviewedBookings: [],
      reviews: [],
      }
  },
  async created() {
    
    this.userId = this.$route.params.id;
    this.username = this.$route.params.username;
    
    // Get listings
    this.listings = await (await fetch('http://localhost:4000/api/user-listings/' + this.userId)).json();

    // Get bookings
    this.bookings = await (await fetch('http://localhost:4000/api/user-bookings/' + this.userId)).json();

    // Get booked listings
    let listingId;
    for (let booking of this.bookings) {
      listingId = booking.listing_id;
      this.bookedListings.push({id: listingId, listing: await (await fetch('http://localhost:4000/api/listing/' + listingId)).json()});
    }

//      this.reviews = await (await fetch('http://localhost:4000/api/reviews')).json();

    // Get reviews

    this.reviews = await (await fetch('http://localhost:4000/api/reviews')).json();

    let bookingId;
    console.log(this.reviews);
    for (let review of this.reviews) {
      bookingId = review.targetId;
      console.log('bookingId ', bookingId);
      this.reviewedBookings.push({id: bookingId, reviews: await (await fetch('http://localhost:4000/api/booking-reviews/' + bookingId)).json()});
    }

    console.log('REVIEWTEST', this.reviewedBookings[0].reviews[0].comment);
    console.log('REVIEWTEST', this.reviewedBookings);
  },
  methods: {
    formatDate(in_date) {
      let date = new Date(in_date);

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
    getReviewComment(booking_id) {
      return this.reviews.find(currentReview => currentReview.reviewId === booking_id).comment;
    },
    getReviewRating(booking_id) {
      return this.reviews.find(currentReview => currentReview.reviewId === booking_id).rating;
    },
    getReview(booking_id) {
      return this.reviews.find(currentReview => currentReview.reviewId === booking_id);
    }
  },
}
</script>

<style>

</style>