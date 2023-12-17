<template>
  <v-app>
    <v-app-bar app color="primary">
      <!-- <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon> -->
      <span class="headline mx-auto">Library System</span>
      <v-spacer></v-spacer>
      <router-link v-if="$route.path.includes('/login')" to="/register">
        <v-btn text class="white-text">Register</v-btn>
      </router-link>

      <router-link v-if="$route.path.includes('/register')" to="/login">
        <v-btn text class="white-text">Login</v-btn>
      </router-link>

      <router-link
        to="/admin"
        v-if="
          !(
            $route.path.includes('/register') ||
            $route.path.includes('/login') ||
            $route.path.includes('/aboutlibrary')
          )
        "
      >
        <v-btn text class="white-text">Home</v-btn>
      </router-link>

      <router-link
        to="/aboutlibrary"
        v-if="!$route.path.includes('/aboutlibrary')"
      >
        <v-btn text class="white-text">About</v-btn>
      </router-link>

      <v-btn
        @click="logoutUser"
        text
        class="white-text"
        v-if="
          !(
            $route.path.includes('/register') ||
            $route.path.includes('/login') ||
            $route.path.includes('/aboutlibrary')
          )
        "
        >Logout</v-btn
      >

      <v-btn text class="white-text" @click="goBack"> Back </v-btn>
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

    <v-content class="zeroHeight">
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
  methods: {
    logoutUser() {
      localStorage.setItem("userId", null);
      this.$router.push("/login");
    },
    goBack() {
      this.$router.go(-1);
    },
  },

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
  margin-top: 32px;
}
.custom-bg {
  background-image: url("@/assets/HomeWallpaper.jpg"); /* Path to your image */
  background-size: cover;
  background-position: center center;
  height: 100vh;
}
.headline {
  padding: 0 0 0 5%;
  font-size: 32px;
  font-weight: bold;
  margin-right: 48px;
}
.zeroHeight {
  height: 0px;
}
.white-text {
  color: white !important; /* Ensure text color remains white */
}
</style>
