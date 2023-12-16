// // main.js
// import { createApp } from 'vue';
// // import { createRouter, createWebHistory } from 'vue-router'
// import App from './App.vue';
// import router from './router'; // Correct path to your router file
// import 'vuetify/styles';
// import { createVuetify } from 'vuetify';
// import * as components from 'vuetify/components';
// import * as directives from 'vuetify/directives';

// const vuetify = createVuetify({
//   components,
//   directives,
// });

// createApp(App)
//   .use(router)
//   .use(vuetify)
//   .mount('#app');
import { createApp } from 'vue';
import App from './App.vue';
import router from './router.js'; // Correct path to your router file
import 'vuetify/styles';
import 'vuetify/dist/vuetify.min.css';
import { createVuetify } from 'vuetify';
import * as components from 'vuetify/components';
import * as directives from 'vuetify/directives';

const vuetify = createVuetify({
  components,
  directives,
});


createApp(App)
  .use(router)
  .use(vuetify)
  .mount('#app');
