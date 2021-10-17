<template>
<Navbar/>
<h3>Book Listing no. {{ this.id }}</h3>
<p>Max guests: {{ this.guests }}</p>
<input type="text" placeholder="Guests">
<br><br><br>
{{ new Date(+this.start).toISOString().split('T')[0] }}
<br>
<input type="text" placeholder="From">
<br><br><br>
{{ new Date(+this.end).toISOString().split('T')[0] }}
<br>
<input type="text" placeholder="To">
<br><br><br>
<p>Fee: {{ this.fee }}</p>
<br><br><br>
<button type="button">Book</button>
</template>

<script>
import Navbar from '../components/Navbar.vue';

export default {
  components: Navbar,
  data(){
    return {
      id: this.$route.params.id,
      start: this.$route.params.start,
      end: this.$route.params.end,
      fee: this.$route.params.fee,
      guests: this.$route.params.guests,
    }
  },
  methods: { 
  },
  mounted() {
    this.listing = this.$route.params;
    console.log('Params: ', this.$route.params);
  },
}
</script>

<style>

</style>