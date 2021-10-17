import { createStore } from 'vuex';

export default createStore({
  state: {
    user: null,
    userId: null
  },
  mutations: {
    setUser(state, user) {
      state.user = user;
      console.log("User set in store");
    },
    setUserId(state, userId) {
      state.userId = userId;
      console.log("User ID set in store:", userId);
    }
  },
  getters: {
    getUserId: state => {
      console.log("Retrieving user id...");
      return state.userId;
    },
    getUser: state => {
      console.log('Retrieving user...');
      return state.user;
    }
  },
  actions: {
    async login(store, credentials) {
      let response = await(await fetch("http://localhost:4000/api/login", {
        method: "POST",
        body: JSON.stringify(credentials),
        credentials: "include",
      })).json();
      store.commit('setUserId', response.userId);
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
    async logout(store) {
      await fetch("http://localhost:4000/api/logout", {
        method: "GET",
      })
    },
  },
});
