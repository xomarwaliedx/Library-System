<template>
  <v-app>
    <v-main>
      <v-container fluid fill-height class="custom-bg">
        <v-row justify="end" align="center" style="height: 100%">
          <v-col cols="12" class="buttons-style">
            <v-hover
              v-for="(button, index) in buttons"
              :key="index"
              
            >
              <template v-slot:default="{ hover }" >
                <v-btn
                  @click="navigateTo(button.path)"
                  color="primary"
                  dark
                  class="custom-btn"
                  v-bind:class="{ 'custom-btn-hover': hover }"
                  elevation="15"
                  tile
                  large
                  depressed
                  block
                >
                  {{ button.text }}
                </v-btn>
              </template>
            </v-hover>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      buttons: [
        { text: "Add Book", path: "/admin/addbook" },
        { text: "Borrowed Books", path: "/admin/borrowedbooks" },
        { text: "Register User", path: "/admin/reguser" },
      ],
    };
  },
  methods: {
    navigateTo(path) {
      this.$router.push(path); // Use Vue Router's push method to navigate
    },
  },
};
</script>

<style>
.custom-bg {
  background-image: url("@/assets/HomeWallpaper.jpg");
  background-size: cover;
  background-position: center center;
  height: 100vh;
}

.custom-btn {
  margin: 10%;
  transition: all 0.3s ease;
}

.custom-btn-hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
}

.buttons-style {
  flex: 0 0 100%;
  max-width: 40% !important;
  padding: 0 10% 0 0 !important;
}

</style>
