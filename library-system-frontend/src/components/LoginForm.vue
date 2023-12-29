<template>
  <v-sheet width="300" class="mx-auto">
    <v-form @submit.prevent="submitForm">
      <v-text-field
        v-model="email"
        label="Email"
        :rules="emailRules"
      ></v-text-field>

      <v-text-field
        v-model="password"
        label="Password"
        :rules="passwordRules"
        type="password"
      ></v-text-field>

      <v-select
        v-model="userType"
        :items="userTypes"
        label="User Type"
        :rules="userTypeRules"
      ></v-select>

      <v-btn type="submit" block class="mt-2">Submit</v-btn>

      <!-- Display error message -->
      <v-alert v-if="loginError" type="error" class="mt-2">{{
        loginError
      }}</v-alert>
    </v-form>
  </v-sheet>
</template>

<script>
import axios from "axios";

export default {
  data: () => ({
    email: "",
    emailRules: [(value) => /.+@.+\..+/.test(value) || "E-mail must be valid"],
    password: "",
    passwordRules: [
      (value) => value.length >= 6 || "Password must be at least 6 characters.",
    ],
    userType: null,
    userTypes: ["User", "Librarian"],
    userTypeRules: [(value) => !!value || "Please select a user type"],
    loginError: null,
  }),
  methods: {
    async submitForm() {
      try {
        const response = await axios.post(
          "http://localhost:8080/api/user/users/login",
          {
            email: this.email,
            password: this.password,
            isAdmin: this.userType === "Librarian",
          }
        );
        // localStorage.setItem("userId", response.data.id);
        // localStorage.setItem("isAdmin", response.data.isAdmin === "Librarian");

        await Promise.all([
          localStorage.setItem("userId", response.data.id),
          localStorage.setItem(
            "isAdmin",
            response.data.isAdmin
          ),
        ]);

        this.loginError = null;
        if (response.data.isAdmin) {
          this.$router.push({
            name: "admin",
          });
        } else {
          this.$router.push({
            name: "mybooks",
          });
        }
      } catch (error) {
        this.loginError = error.response
          ? error.response.data
          : "Login failed. Please try again.";
        console.error("Login failed:", this.loginError);
      }
    },
  },
};
</script>
