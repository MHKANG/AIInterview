import Vue from 'vue'
import App from './App.vue'

import store from './store'
import axios from './axios'

import './plugins/base'
import { router } from './router'
import vuetify from './plugins/vuetify';
import VueSession from 'vue-session'

Vue.config.productionTip = false
Vue.prototype.$axios = axios;

Vue.use(VueSession)

new Vue({
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
