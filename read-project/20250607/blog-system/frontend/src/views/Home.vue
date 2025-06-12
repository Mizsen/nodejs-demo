<template>
  <div class="home">
    <h1>Welcome to the Blog System</h1>
    <p>Explore the latest blogs below:</p>
    <BlogList />
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import BlogList from '@/components/BlogList.vue';

export default defineComponent({
  name: 'Home',
  components: {
    BlogList
  }
});
</script>

<style scoped>
.home {
  text-align: center;
  padding: 20px;
}
</style>