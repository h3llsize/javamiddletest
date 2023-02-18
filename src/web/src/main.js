import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import axios from "axios";

Vue.config.productionTip = false;
axios.defaults.headers.common['Authorization'] = `${localStorage.getItem('token')}`;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
