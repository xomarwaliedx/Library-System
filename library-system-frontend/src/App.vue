<template>
 
  <v-app>
    <v-app-bar app color="primary" >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>Library System</v-toolbar-title>
      <!-- <v-spacer></v-spacer> -->
      <v-btn text>Home</v-btn>
      <v-btn text>About</v-btn>
      <v-btn text>Contact</v-btn>
      <!-- Add more buttons or links as needed -->
    </v-app-bar>

    <v-navigation-drawer v-model="drawer" app>
      <v-list>
        <v-list-item v-for="(item, index) in items" :key="index" link>
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-content>
        <div id="app">
    <!-- Other app content here -->
    <router-view></router-view>
  </div>
    </v-content>
  </v-app>

</template>

<script>
//import LoginForm from './components/user/MyBooks.vue'

export default {
  name: 'App',
  // components: {
  //   LoginForm
  // }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.v-application.v-application--is-rtl .v-application__wrap,
.v-application.v-application--is-ltr .v-application__wrap {
  min-height: 0 !important;
}

</style>
