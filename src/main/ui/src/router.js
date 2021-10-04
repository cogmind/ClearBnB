import { createRouter, createWebHistory } from 'vue-router';

import Start from "./views/Start.vue";
import Listings from "./views/Listings.vue";
import Bookings from "./views/Bookings.vue";
import Login from "./views/Login.vue";
import Logout from "./views/Logout.vue";
import Register from "./views/Register.vue";
import Chat from "./views/Chat.vue";

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
      path: '/logout',
      component: Logout,
    }, {
      path: '/register',
      component: Register,
    }, {
      path: '/chat',
      component: Chat,
    },
  ],
});

