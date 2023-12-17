<!-- libraryAbout.vue -->
<template>
  <div>
    <h1>About Our Library</h1>
    <div>
      <h2>Overview</h2>
      <p>Welcome to our library! We strive to provide a wide range of books and resources to our readers.</p>
    </div>
    <div>
      <h2>Location</h2>
      <p>Our library is located at:</p>
      <address>
        123 Library Street,<br>
        City, Country<br>
        Postal Code
      </address>
    </div>
    <div>
      <h2>Operating Hours</h2>
      <p>Our library is open:</p>
      <ul>
        <li>Monday to Friday: 9:00 AM - 6:00 PM</li>
        <li>Saturday: 10:00 AM - 4:00 PM</li>
        <li>Sunday: Closed</li>
      </ul>
    </div>
    <div>
      <h2>Contact Us</h2>
      <p>If you have any questions, feel free to contact us at:</p>
      <p>Email: library@example.com</p>
      <p>Phone: +1234567890</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LibraryAbout'
}
</script>

<!-- Styles can be added as required -->
<style scoped>
/* Add your styles here */
h1 {
  font-size: 24px;
  margin-bottom: 20px;
}
h2 {
  font-size: 20px;
  margin-bottom: 10px;
}
p {
  font-size: 16px;
  line-height: 1.5;
}
ul {
  list-style-type: none;
  padding: 0;
}
address {
  font-style: normal;
}
</style>
