import Vue from 'vue';
import Vuex from 'vuex';
import router from '@/router';
import axios from 'axios';
Vue.prototype.$http = axios;
const token = localStorage.getItem('token123')
if (token) {
  Vue.prototype.$http.defaults.headers.common['Authorization'] = token;
}

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    authorized: false,
    token: null,
    validate: '',
    signup: '',
  },
  mutations: {
    updateAuth: function(state, value) {
      state.authorized = value;
    },
    updateToken: function(state, value) {
      state.token = value;
    },
    updateValidate: function(state, value) {
      state.validate = value;
    },
    updateSignup: function(state, value) {
      state.signup = value;
    },
  },
  actions: {
    validateToken(context) {
      axios.get(`http://localhost:8081/api/auth/validate`, {})
      .then(response => {
        context.commit('updateAuth', true);
        context.commit('updateToken', localStorage.getItem('token123'));
      })
      .catch(error => {
        context.commit('updateAuth', false);
        context.commit('updateToken', false);
        localStorage.setItem('token123', false);
      })
    },
    login: function(context, user) {
      axios.post(`http://localhost:8081/api/auth/login`, {
        email: user.email,
        password: user.password,
      })
      .then(response => {
        context.commit('updateAuth', true);
        context.commit('updateToken', response.data.Authorization);
        context.commit('updateValidate', true);
        localStorage.setItem('token123', response.data.Authorization);
        router.push({ name: 'main' });
      })
      .catch(error => {
        context.commit('updateAuth', false);
        context.commit('updateToken', false);
        context.commit('updateValidate', false);
        localStorage.setItem('token123', false);
      })
    },
    signup: function(context, user) {
      axios.post(`http://localhost:8081/api/auth/register`, {
        name: user.name,
        email: user.email,
        password: user.password,
      })
      .then(response => {
        context.commit('updateAuth', false);
        context.commit('updateValidate', false);
        context.commit('updateSignup', true);
      })
      .catch(error => {
        context.commit('updateAuth', false);
        context.commit('updateToken', false);
        context.commit('updateValidate', true);
        context.commit('updateSignup', false);
        localStorage.setItem('token123', false);
      })
    },
    logout: function(context) {
      context.commit('updateAuth', false);
      context.commit('updateToken', false)
      localStorage.setItem('token123', false);
    }
  },
});
