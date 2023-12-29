<template>
  <v-app>
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

          </td>
        </tr>
      </template>
     
    </v-data-table>
  
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
        return [...this.headers];
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
  
    },
  
    created() {
      this.fetchData();
    },
  };
  </script>
  