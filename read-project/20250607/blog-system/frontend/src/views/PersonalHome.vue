<template>
  <div class="personal-home">
    <h1>Welcome to Your Personal Homepage</h1>
    <div class="user-info">
      <h2>Your Profile</h2>
      <UserProfile />
    </div>
    <div class="user-blogs">
      <h2>Your Blogs</h2>
      <BlogList :blogs="userBlogs" @edit="editBlog" @delete="deleteBlog" />
    </div>
    <div class="publish-blog">
      <BlogEditor @publish="publishBlog" />
    </div>
  </div>
</template>

<script>
import UserProfile from '@/components/UserProfile.vue';
import BlogList from '@/components/BlogList.vue';
import BlogEditor from '@/components/BlogEditor.vue';

export default {
  name: 'PersonalHome',
  components: {
    UserProfile,
    BlogList,
    BlogEditor,
  },
  data() {
    return {
      userBlogs: [],
    };
  },
  methods: {
    fetchUserBlogs() {
      // Fetch user's blogs from the backend or mock server
    },
    editBlog(blogId) {
      // Logic to edit the selected blog
    },
    deleteBlog(blogId) {
      // Logic to delete the selected blog
    },
    publishBlog(newBlog) {
      // Logic to publish a new blog
    },
  },
  mounted() {
    this.fetchUserBlogs();
  },
};
</script>

<style scoped>
.personal-home {
  padding: 20px;
}
.user-info, .user-blogs, .publish-blog {
  margin-bottom: 20px;
}
</style>