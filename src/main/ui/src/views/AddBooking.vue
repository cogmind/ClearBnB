<template>
<Navbar />
<h3>Book Listing no. {{ this.id }}</h3>
<p>Fee: {{ this.fee }}</p>
<p>Max guests: {{ this.max_guests }}</p>
<input v-model="guests" type="text" placeholder="Guests">
<br><br><br>
Earliest {{ this.available_start }} (YYYYMMDD)
<br>
<input v-model="start" type="text" placeholder="From">
<br><br><br>
Latest {{ this.available_end }} (YYYYMMDD)
<br>
<input v-model="end" type="text" placeholder="To">
<br><br>
<br>
<button type="button" @click="book()">Book</button>
<br><br>

</template>

<script>
import Navbar from '../components/Navbar.vue';

export default {
  name: 'AddBooking',
  components: {Navbar},
  data(){
    return {
      id: this.$route.params.id,
      available_start: this.$route.params.start,
      available_end: this.$route.params.end,
      fee: this.$route.params.fee,
      max_guests: this.$route.params.guests,
      start: null,
      end: null,
      guests: null,
      user: this.$store.getters.getUserId,
    }
  },
  methods: { 
    async book() {

      // Check available funds
      let funds = await (await fetch("http://localhost:4000/api/user-balance/" + this.$store.getters.getUserId)).json();

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
      let booking = {
        listing_id: this.$route.params.id,
        user: this.user,
        fee: this.$route.params.fee,
        guests: this.guests,
        start: this.toDate(this.start),
        end: this.toDate(this.end),
        available_funds: funds,
      }
      console.log(booking);

      let response = await(await fetch('http://localhost:4000/api/createbooking', {
        method: 'POST',
        body: JSON.stringify(booking),
        credentials: 'include',
      })).json();
      console.log('JSON: ', response);
      if (response === "Not enough money in account.") {
          alert('Not enough money in account.');
      }
    },
    toDate(isoString) {
      let year = isoString.substring(0, 4);
      let month = isoString.substring(5, 7);
      if (month[0] === '0') {
        month = month[1];
      }
      month = month - 1;
      let day = isoString.substring(8, 10);
      if (day[0] === '0') {
        day = day[1];
      }
      return new Date(year, month, day);
    },
  },
  mounted() {
    this.listing = this.$route.params;
  },
}
</script>

<style>

</style>