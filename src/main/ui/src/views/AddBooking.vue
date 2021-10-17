<template>
<Navbar/>
<h3>Book {{listing_id}}</h3>

</template>

<script>
import Navbar from '../components/Navbar.vue';

export default {
  components: Navbar,
  props: ['listing_id'],
  data(){
    return {
      booking_id: 1,
    }
  },
  methods: { 
  },
  mounted() {
  console.log('Params: ', this.$route.params);
  }
}
</script>

<style>

</style>