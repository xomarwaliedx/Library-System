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
            <td>{{ item.email }}</td>
            <td>{{ item.phone }}</td>
            <td>{{ item.fullName }}</td>
            <td>{{ item.isAdmin?"Librarian":"User" }}</td>
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
          { title: "E-mail", align: "center", key: "email" },
          { title: "Phone", align: "center", key: "phone" },
          { title: "Full Name", align: "center", key: "fullName" },
          { title: "Type", align: "center", key: "isAdmin" },
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
              this.filterText(item.email, query) ||
              this.filterText(item.phone, query) ||
              this.filterText(item.fullName, query)
          );
        },
      },
    
      methods: {
        async fetchData() {
          try {
            const response = await axios.get("http://localhost:8080/api/admin/getAllUsers");
            console.log('response', response)
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
    