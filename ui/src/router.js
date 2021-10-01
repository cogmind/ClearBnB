import { createRouter, createWebHistory } from 'vue-router';

import Start from "./components/Start.vue";

export default createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: Start,
    },
  ],
});

