<template>
    <div>
      <p><strong>Review</strong></p>
      <p class="comment">{{ comment }}</p>
      <p><strong>Rating</strong> <span v-for="star in rating" v-bind:key="star">⭐</span></p>
      <br><br>
    </div>
</template>

<script>
export default {
  name: "ReviewItem",
  components: {},
  props: {
    rating: Array,
    comment: String,
  },
  data() {
    return {
    }
  }, methods: {
    
  }
}
</script>

<style scoped>

</style>
