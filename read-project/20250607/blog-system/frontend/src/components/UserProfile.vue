<template>
  <div class="user-profile">
    <h1>User Profile</h1>
    <div class="profile-info">
      <h2>{{ user.name }}</h2>
      <p>Email: {{ user.email }}</p>
      <p>Joined: {{ user.joinedDate }}</p>
    </div>
    <h3>Your Blogs</h3>
    <ul>
      <li v-for="blog in user.blogs" :key="blog.id">
        <router-link :to="{ name: 'BlogDetail', params: { id: blog.id } }">{{ blog.title }}</router-link>
        <button @click="editBlog(blog.id)">Edit</button>
        <button @click="deleteBlog(blog.id)">Delete</button>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: {
        name: '',
        email: '',
        joinedDate: '',
        blogs: []
      }
    };
  },
  methods: {
    fetchUserProfile() {
      // Fetch user profile data from the API
      // This is a placeholder for the actual API call
      this.user = {
        name: 'John Doe',
        email: 'john.doe@example.com',
        joinedDate: '2023-01-01',
        blogs: [
          { id: 1, title: 'My First Blog' },
          { id: 2, title: 'Another Day, Another Blog' }
        ]
      };
    },
    editBlog(blogId) {
      this.$router.push({ name: 'BlogEditor', params: { id: blogId } });
    },
    deleteBlog(blogId) {
      // Logic to delete the blog
      this.user.blogs = this.user.blogs.filter(blog => blog.id !== blogId);
    }
  },
  mounted() {
    this.fetchUserProfile();
  }
};
</script>

<style scoped>
.user-profile {
  padding: 20px;
}
.profile-info {
  margin-bottom: 20px;
}
</style>