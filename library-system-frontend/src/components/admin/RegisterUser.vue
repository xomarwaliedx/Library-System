<template>
  <v-sheet width="300" class="mx-auto">
    <v-form @submit.prevent="submitForm">
      <v-text-field
        v-model="email"
        label="Email"
        :rules="emailRules"
      ></v-text-field>

      <v-text-field
        v-model="phone"
        label="Phone"
        :rules="phoneRules"
      ></v-text-field>

      <v-text-field
        v-model="fullName"
        label="Full Name"
        :rules="nameRules"
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

      <!-- Display error message to the user -->
      <v-alert
        v-if="errorMessage"
        type="error"
        dismissible
        @input="errorMessage = null"
      >
        {{ errorMessage }}
      </v-alert>
    </v-form>
  </v-sheet>
</template>

<script>
import axios from "axios";

export default {
  data: () => ({
    email: "",
    emailRules: [(value) => /.+@.+\..+/.test(value) || "E-mail must be valid"],
    phone: "",
    phoneRules: [
      (value) =>
        /^(\+?[0-9]){6,}$/.test(value) || "Phone must have more than 5 digits",
    ],
    fullName: "",
    nameRules: [(value) => !!value || "Name is required"],
    password: "",
    passwordRules: [
      (value) => value.length >= 6 || "Password must be at least 6 characters.",
    ],
    userType: null,
    userTypes: ["User", "Librarian"],
    userTypeRules: [(value) => !!value || "Please select a user type"],
    errorMessage: null, // New property to store error message
  }),
  methods: {
    async submitForm() {
      try {
        await axios.post(
          "http://localhost:8080/api/register",
          {
            email: this.email,
            phone: this.phone,
            fullName: this.fullName,
            password: this.password,
            isAdmin: this.userType=="User"?0:1,
          }
        );

        // Handle the response as needed (e.g., show success message)

        // Redirect to /login after a successful registration
        this.$router.push("/admin");
      } catch (error) {
        // Handle errors (e.g., show error message)
        console.error("Error submitting form:", error);

        // Update the errorMessage property to display to the user
        this.errorMessage = error.response
          ? error.response.data
          : "An unexpected error occurred";
      }
    },
  },
};
</script>
