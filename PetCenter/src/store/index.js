import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

const state = {
  isLogin: '0',
  token: sessionStorage.getItem('token') ? sessionStorage.getItem('token') : ''
}

export default new Vuex.Store({
  state,
  plugins: [createPersistedState(
    { storage: window.sessionStorage }
  )],
  getters: {
    getStorage (state) {
      if (!state.token) {
        state.token = JSON.parse(sessionStorage.getItem())
      }
      return state.token
    }
  },
  mutations: {
    set_Token (state, value) {
      state.token = value
      sessionStorage.setItem('token', value)
    },
    remove_Storage (state, value) {
      sessionStorage.removeItem('token')
    }
  }
})
