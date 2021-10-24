<template>
  <p>
    {{ listing.listing_id }} {{ listing.version }} {{ listing.owner_id }}
    {{ listing.audited_datetime }} &#60;&#60;&#60;{{
      listing.title
    }}&#62;&#62;&#62; {{ listing.description }} {{ listing.image_url }}
    {{ listing.guests }} {{ listing.price }} {{ listing.start }}
    {{ listing.end }}
  </p>
</template>
<script>
export default {
  props: ["listing"],

  data(){
    return {
      listing : { 
        listing_id: "1",
        version: 0,
        owner_id: "",
        audited_datetime: "",
        title: "",
        description: "",
        image_url: "",
        guests: 0,
        price: 0.0,
        start: "",
        end: "",}
    }
  }
};
</script>
>