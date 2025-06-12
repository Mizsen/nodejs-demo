<template>
  <div class="blog-list">
    <h1>Blog List</h1>
    <div v-if="blogs.length === 0">No blogs available.</div>
    <ul>
      <li v-for="blog in blogs" :key="blog.id">
        <h2>{{ blog.title }}</h2>
        <p>{{ blog.excerpt }}</p>
        <router-link :to="{ name: 'BlogDetail', params: { id: blog.id } }">Read More</router-link>
        <button @click="editBlog(blog.id)">Edit</button>
        <button @click="deleteBlog(blog.id)">Delete</button>
      </li>
    </ul>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from 'vue';
import axios from 'axios';

export default defineComponent({
  name: 'BlogList',
  setup() {
    const blogs = ref([]);

    const fetchBlogs = async () => {
      try {
        const response = await axios.get('/api/blogs');
        blogs.value = response.data;
      } catch (error) {
        console.error('Error fetching blogs:', error);
      }
    };

    const editBlog = (id: number) => {
      // Navigate to the blog editor with the blog ID
      // This assumes you have a route set up for editing blogs
      // Example: router.push({ name: 'BlogEditor', params: { id } });
    };

    const deleteBlog = async (id: number) => {
      try {
        await axios.delete(`/api/blogs/${id}`);
        fetchBlogs(); // Refresh the blog list after deletion
      } catch (error) {
        console.error('Error deleting blog:', error);
      }
    };

    onMounted(fetchBlogs);

    return {
      blogs,
      editBlog,
      deleteBlog,
    };
  },
});
</script>

<style scoped>
.blog-list {
  padding: 20px;
}

.blog-list h1 {
  font-size: 2em;
}

.blog-list ul {
  list-style-type: none;
  padding: 0;
}

.blog-list li {
  margin-bottom: 20px;
  border: 1px solid #ccc;
  padding: 10px;
}
</style>