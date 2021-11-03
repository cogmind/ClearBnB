import { createRouter, createWebHistory } from 'vue-router';

import Start from "./views/Start.vue";
import ViewListings from "./views/ViewListings.vue";
import AddListing from "./views/AddListing.vue";
import Bookings from "./views/Bookings.vue";
import AddBooking from "./views/AddBooking.vue";
import Login from "./views/Login.vue";
import Logout from "./views/Logout.vue";
import Register from "./views/Register.vue";
import Chat from "./views/Chat.vue";
import MyProfile from "./views/MyProfile.vue";
import Profiles from "./views/Profiles.vue";
import Profile from "./views/Profile.vue";

export default createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: Start,
    }, {
      path: '/start',
      component: Start,
    }, {
      path: '/listings',
      component: ViewListings,
    }, {
      path: '/addlisting',
      component: AddListing,
    }, {
      path: '/bookings',
      component: Bookings,
    }, {
      path: '/:id#:start#:end#:fee#:guests',
      name: 'book',
      component: AddBooking,
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
    }, {
      path: '/my-profile',
      component: MyProfile,
    }, {
      path: '/profiles',
      component: Profiles,
    }, {
      path: '/profile/:username/:id',
      name: 'profile',
      component: Profile,
    },
  ],
});

