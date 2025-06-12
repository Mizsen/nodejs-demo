<template>
  <div class="blog-detail">
    <h1>{{ blog.title }}</h1>
    <p>{{ blog.content }}</p>
    <div class="comments">
      <h2>Comments</h2>
      <CommentSection :blogId="blog.id" />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from 'vue';
import CommentSection from './CommentSection.vue';

export default defineComponent({
  name: 'BlogDetail',
  components: {
    CommentSection,
  },
  setup() {
    const blog = ref({ title: '', content: '', id: null });

    const fetchBlogDetail = async (id: number) => {
      const response = await fetch(`/api/blogs/${id}`);
      if (response.ok) {
        blog.value = await response.json();
      }
    };

    onMounted(() => {
      const blogId = 1; // Replace with dynamic ID based on route params
      fetchBlogDetail(blogId);
    });

    return {
      blog,
    };
  },
});
</script>

<style scoped>
.blog-detail {
  padding: 20px;
}
.comments {
  margin-top: 20px;
}
</style>