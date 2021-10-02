import { createRouter, createWebHistory } from 'vue-router';

import Start from "./components/Start.vue";
import Listings from "./components/Listings.vue";
import Bookings from "./components/Bookings.vue";
import Login from "./components/Login.vue";
import Register from "./components/Register.vue";
import Chat from "./components/Chat.vue";

export default createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: Start,
    }, {
      path: '/listings',
      component: Listings,
    }, {
      path: '/bookings',
      component: Bookings,
    }, {
      path: '/login',
      component: Login,
    }, {
      path: '/register',
      component: Register,
    }, {
      path: '/chat',
      component: Chat,
    },
  ],
});

