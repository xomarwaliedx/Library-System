<template>
  <v-app>
    <v-app-bar app color="primary">
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <span class="headline  mx-auto">Library System</span>
      <v-spacer></v-spacer>
      <v-btn text>Home</v-btn>
      <v-btn text>About</v-btn>
      <v-btn text>Logout</v-btn>
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
  name: "App",
  // components: {
  //   LoginForm
  // }
};
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

.headline {
  font-size: 32px;
  font-weight: bold; 
  margin-right: 48px;
}
</style>
