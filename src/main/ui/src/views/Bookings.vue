<template>
  <p><router-link to="start">ClearBnB</router-link></p>
  <Navbar />
  <p>Bookings</p>
  <p>
    {{ booking_id }}{{ listing_id }}{{ user }}{{ fee }}{{ start_date
    }}{{ end_date }}{{ cancelled }}
  </p>
</template>

<script>
import Navbar from "../components/Navbar.vue";

export default {
  name: "Bookings",
  components: {
    Navbar,
  },
  data() {
    return {
      booking_id: 0,
      listing_id: "",
      user: 0,
      fee: 0.0,
      start_date: "",
      end_date: "",
      cancelled: -1,
    };
  },
  methods: {},
};
</script>

<style scoped>
</style>
