<template>
  <v-container>
    <h1>Add Book</h1>
    <v-form @submit.prevent="submitForm">
      <v-row>
        <v-col cols="12" sm="6">
          <v-text-field
            v-model="formData.bookName"
            label="Book Name"
            required
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="6">
          <v-text-field
            v-model="formData.author"
            label="Author"
            required
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="6">
          <v-select
            v-model="formData.genre"
            :items="genres"
            label="Genre"
            required
          ></v-select>
        </v-col>
        <v-col cols="12" sm="6">
          <v-select
            v-model="formData.language"
            :items="languages"
            label="Language"
            required
          ></v-select>
        </v-col>
        <v-col cols="12" sm="6">
          <v-text-field
            v-model="formData.publicationYear"
            label="Publication Year"
            type="number"
            required
            placeholder="2010"
            :rules="[yearValidation]"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="6">
          <v-text-field
            v-model="formData.count"
            label="Count"
            type="number"
            required
          ></v-text-field>
        </v-col>
      </v-row>
      <v-btn type="submit" color="primary">Submit</v-btn>
    </v-form>
  </v-container>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      formData: {
        bookName: "",
        author: "",
        genre: "",
        language: "",
        publicationYear: null,
        count: null,
      },
      genres: [
        "Fiction",
        "Non-Fiction",
        "Mystery",
        "Science Fiction",
        "Fantasy",
        "Romance",
        "Thriller",
        "Horror",
        "Others",
      ],
      languages: ["English","Arabic", "Spanish", "French", "German", "Other"],
    };
  },
  methods: {
    yearValidation(value) {
      const currentYear = new Date().getFullYear();
      const minYear = 1800;
      if (value >= minYear && value <= currentYear) {
        return true;
      }
      return "Year must be between 1800 and " + currentYear;
    },
    submitForm() {
      console.log("Form submitted!", this.formData);
      // Send form data using Axios
      axios
        .post("http://localhost:8080/api/admin/books/addBook", this.formData)
        .then((response) => {
          console.log("Form submitted!", response.data);
          this.$router.push("/admin");
          // Handle success response here
        })
        .catch((error) => {
          console.error("Error submitting form:", error);
          // Handle error here
        });
    },
  },
};
</script>

<style>
/* Add your CSS styles here if needed */
</style>
