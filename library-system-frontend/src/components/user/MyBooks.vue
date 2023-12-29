<template>
  <v-app>
  <v-btn @click="goToBrowseBooks" class="mr-2 mt-2" color="primary">
      Browse Books
    </v-btn>
  <v-data-table-server
    v-model:items-per-page="itemsPerPage"
    :headers="headers"
    :items-length="totalItems"
    :items="items"
    :loading="loading"
    item-value="bookName"
    @update:options="loadItems"
  >
    <template v-slot:header>
      <div class="title">My Books</div>
    </template>
  </v-data-table-server>
</v-app>
</template>

<script>
import axios from "axios";

export default {
  data: () => ({
    itemsPerPage: 5,
    headers: [
      { title: 'Book Name', align: 'center', sortable: false, key: 'bookName' },
      { title: 'Borrow Date', key: 'borrowDate', align: 'center' },
      { title: 'Return Date', key: 'returnDate', align: 'center' },
    ],
    items: [],
    loading: true,
    totalItems: 0,
  }),
  methods: {
    goToBrowseBooks() {
      // Assuming you have a named route for "browsebooks"
      this.$router.push({ name: 'browsebooks' });
    },
    async loadItems({ page, itemsPerPage, sortBy }) {
      this.loading = true;
      try {
        const userId = localStorage.getItem('userId');
        const response = await axios.get(`http://localhost:8080/api/user/borrowedBooks/${userId}`, {
          params: { page, itemsPerPage, sortBy },
        });

        const data = response.data;
        const total = data.length;

        if (Array.isArray(data)) {
          if (sortBy.length) {
            const sortKey = sortBy[0].key;
            const sortOrder = sortBy[0].order;
            data.sort((a, b) => {
              const aValue = a[sortKey];
              const bValue = b[sortKey];
              return sortOrder === 'desc' ? bValue - aValue : aValue - bValue;
            });
          }

          const start = (page - 1) * itemsPerPage;
          const end = start + itemsPerPage;
          const paginated = data.slice(start, end);

          // Fetch bookName and calculate returnDate for each bookId
          const fetchBookNamePromises = paginated.map(async (item) => {
            const bookId = item.bookId;

            // Fetch bookName
            const bookResponse = await axios.get(`http://localhost:8080/api/admin/books/getBook/${bookId}`);
            const bookName = bookResponse.data;
            item.bookName = bookName;

            // Calculate returnDate based on borrowDate and duration
            const borrowDate = new Date(item.borrowDate);
            const durationDays = item.duration;
            const returnDate = new Date(borrowDate.getTime() + durationDays * 24 * 60 * 60 * 1000);
            item.returnDate = returnDate.toISOString().split('T')[0];
          });

          // Wait for all bookName fetch promises to resolve
          await Promise.all(fetchBookNamePromises);

          this.items = paginated;
          this.totalItems = total;
        } else {
          // Handle the case where data is not an array
          console.error('Invalid data format:', data);
          this.items = [];
          this.totalItems = 0;
        }
      } catch (error) {
        console.error('Error fetching data:', error.response || error.message || error);
        this.serverItems = [];
        this.totalItems = 0;
      } finally {
        this.loading = false;
      }
    },
  },
};
</script>
