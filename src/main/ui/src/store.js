import { createStore } from 'vuex';

export default createStore({
  state: {
    user: null
  },
  mutations: {
    setUser(state, user) {
      state.user = user
      console.log("User set in store")
    }
  },
  getters: {
  },
  actions: {
    async login(store, credentials) {
      await fetch("http://localhost:4000/api/login", {
        method: "POST",
        body: JSON.stringify(credentials),
        credentials: "include",
      })
        .then(function (response) {
          return response.json();
        })
        .then(function (data) {
          console.log(data);
        });
    },
    async register(store, credentials) {
      await fetch("http://localhost:4000/api/register", {
        method: "POST",
        body: JSON.stringify(credentials),
      })
        .then(function (response) {
          return response.json();
        })
        .then(function (data) {
          console.log(data);
        });
    },
    async logout() {
      await fetch("http://localhost:4000/api/logout", {
        method: "GET",
      })
    },
  },
});
