import { createStore } from 'vuex'

import createPersistedstate from 'vuex-persistedstate'

export default createStore({
  state: {
    user:[{id:null}],
    musicList:[]
  },
  getters: {
  },
  mutations: {
    add(state, users) {
      state.user = users
    },
    add1(state, music) {
      state.musicList.push(music) 
    }
  },
  plugins: [createPersistedstate({
    storage:window.sessionStorage
  })],
  actions: {
  },
  modules: {
  }
})
