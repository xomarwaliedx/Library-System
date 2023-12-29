<template>
  <v-app>
  <v-btn @click="goToMyBooks" class="mr-2 mt-2" color="primary">
      My Books
    </v-btn>
    <v-data-table
      :custom-filter="filterText"
      :headers="headersWithButton"
      :items="filteredItems"
      :search="search"
      item-value="name"
    >
      <template v-slot:top>
        <v-text-field v-model="search" label="Search (lower case only)" class="pa-2"></v-text-field>
      </template>
  
      <template v-slot:item="{ item }">
        <tr>
          <td>{{ item.bookName }}</td>
          <td>{{ item.author }}</td>
          <td>{{ item.genre }}</td>
          <td>{{ item.language }}</td>
          <td>{{ item.publicationYear }}</td>
          <td>{{ item.count }}</td>
          <td>{{ item.reserved }}</td>
          <td>
            <v-btn @click="openReserveDialog(item)" color="primary" dark>
              Reserve Book
            </v-btn>
          </td>
        </tr>
      </template>
     
    </v-data-table>
  
    <v-dialog v-model="reserveDialog" max-width="500px">
      <v-card>
        <v-card-title>Reserve Book</v-card-title>
        <v-card-text>
          <v-text-field v-model="duration" label="Duration (days)" type="number"></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-btn @click="closeReserveDialog">Cancel</v-btn>
          <v-btn @click="reserveCopy(selectedBook)" color="primary">Reserve</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  
    <v-snackbar v-model="snackbar.show" :color="snackbar.color" multi-line>
      {{ snackbar.message }}
      <template v-slot:action="{ attrs }">
        <v-btn color="white" text v-bind="attrs" @click="snackbar.show = false">
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-app>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    data: () => ({
      search: "",
      headers: [
        { title: "Book Name", align: "center", key: "bookName" },
        { title: "Author", align: "center", key: "author" },
        { title: "Genre", align: "center", key: "genre" },
        { title: "Language", align: "center", key: "language" },
        { title: "Publication Year", align: "center", key: "publicationYear" },
        { title: "Number of available copies", align: "center", key: "count" },
        { title: "", align: "center", key: "reserved" },
        { title: "", align: "center", key: "actions" },
      ],
      items: [],
      reserveDialog: false,
      duration: null,
      selectedBook: null,
      snackbar: {
        show: false,
        message: "",
        color: "",
      },
    }),
  
    computed: {
      headersWithButton() {
        return [...this.headers, { title: "", align: "end", key: "actions" }];
      },
  
      filteredItems() {
        if (!this.search) {
          return this.items;
        }
  
        const query = this.search.toLowerCase();
        return this.items.filter(
          (item) =>
            this.filterText(item.bookName, query) ||
            this.filterText(item.author, query) ||
            this.filterText(item.genre, query) ||
            this.filterText(item.language, query) ||
            this.filterText(item.publicationYear.toString(), query)
        );
      },
    },
  
    methods: {
      async fetchData() {
        try {
          const response = await axios.get("http://localhost:8080/api/admin/books/getAllBooks");
          this.items = response.data;
        } catch (error) {
          console.error("Error fetching data:", error.response || error.message || error);
        }
      },
  
      filterText(value, query) {
        if (value === null || query === null) {
          return false;
        }
  
        if (typeof value === "string" || typeof value === "number") {
          const lowerCaseValue = value.toString().toLowerCase();
          return lowerCaseValue.includes(query);
        }
  
        return false;
      },
  
      openReserveDialog(book) {
        this.selectedBook = book;
        this.duration = null; // Reset the duration input
        this.reserveDialog = true;
      },
  
      closeReserveDialog() {
        this.reserveDialog = false;
        this.duration = null; // Reset the duration input
      },

      goToMyBooks() {
      // Assuming you have a named route for "browsebooks"
      this.$router.push({ name: 'mybooks' });
    },
  
      async reserveCopy(book) {
        try {
          const userId = localStorage.getItem("userId");
          const bookId = book.id;
  
          const response = await axios.post("http://localhost:8080/api/user/borrowBook", {
            duration: this.duration,
            userId,
            bookId,
          });
  
          if (response.data) {
            this.showSnackbar("Reservation successful!", "success");
          } else {
            this.showSnackbar("Book currently unavailable", "error");
          }
        } catch (error) {
          console.error("Error during reservation:", error.response || error.message || error);
          this.showSnackbar("Error occurred during reservation", "error");
        } finally {
          this.closeReserveDialog();
          this.fetchData(); // Refresh the data after reservation
        }
      },
  
      showSnackbar(message, color) {
        this.snackbar.message = message;
        this.snackbar.color = color;
        this.snackbar.show = true;
      },
    },
  
    created() {
      this.fetchData();
    },
  };
  </script>
  