<template>
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

      <template v-slot:item="{ item }">
      <tr>
        <td>{{ item.bookName }}</td>
        <td>{{ item.borrowDate }}</td>
        <td>{{ item.returnDate }}</td>
        <td>
          <!-- Add "Return Book" button -->
          <v-btn @click="returnBook(item)" color="primary">
            Return Book
          </v-btn>
        </td>
      </tr>
    </template>
    </v-data-table-server>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    data: () => ({
      itemsPerPage: 5,
      headers: [
        { title: 'Book Name', align: 'start', sortable: false, key: 'bookName' },
        { title: 'Borrow Date', key: 'borrowDate', align: 'end' },
        { title: 'Return Date', key: 'returnDate', align: 'end' },
      ],
      items: [],
      loading: true,
      totalItems: 0,
    }),
    methods: {

        async returnBook(item) {
        try {console.log('Returning book with ID:', item.id);
          // Make an HTTP request to return the book
          const response = await axios.post('http://localhost:8080/api/user/returnBook', {
           id: item.id,
          });


          // Handle the response if needed
          console.log('Book returned successfully:', response.data);

          // You may want to reload the data after returning the book
          this.loadItems({ page: 1, itemsPerPage: this.itemsPerPage, sortBy: [] });
        } catch (error) {
          console.error('Error returning book:', error.response || error.message || error);
        }
      },
      async loadItems({ page, itemsPerPage, sortBy }) {
        this.loading = true;
        try {
          
          const response = await axios.get(`http://localhost:8080/api/borrowedBooks`, {
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
  