<template>
  <p>ClearBnB</p>
  <Navbar />
  <p>Listings</p>
  <p>
    <!--Isolate to separate component? Consider using v-for -->
    {{ listing_id }} {{ version }} {{ owner_id }} {{ audited_datetime }}
    {{ title }} {{ description }} {{ image_url }} {{ guests }} {{ price }}
    {{ listing_start_date }} {{ listing_end_date }}
  </p>
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
};
</script>

<style scoped>
</style>
