<template>
  <div class="blog-editor">
    <h2>{{ isEditing ? 'Edit Blog' : 'Create Blog' }}</h2>
    <form @submit.prevent="submitForm">
      <div>
        <label for="title">Title:</label>
        <input type="text" v-model="blog.title" id="title" required />
      </div>
      <div>
        <label for="content">Content:</label>
        <textarea v-model="blog.content" id="content" required></textarea>
      </div>
      <button type="submit">{{ isEditing ? 'Update' : 'Publish' }}</button>
      <button type="button" @click="cancel">Cancel</button>
    </form>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, watch } from 'vue';

export default defineComponent({
  name: 'BlogEditor',
  props: {
    initialBlog: {
      type: Object,
      default: () => ({ title: '', content: '' }),
    },
    isEditing: {
      type: Boolean,
      default: false,
    },
  },
  setup(props) {
    const blog = ref({ ...props.initialBlog });

    watch(
      () => props.initialBlog,
      (newBlog) => {
        blog.value = { ...newBlog };
      }
    );

    const submitForm = () => {
      if (props.isEditing) {
        // Call API to update the blog
      } else {
        // Call API to create a new blog
      }
    };

    const cancel = () => {
      // Logic to cancel the editing/creation
    };

    return {
      blog,
      submitForm,
      cancel,
    };
  },
});
</script>

<style scoped>
.blog-editor {
  max-width: 600px;
  margin: auto;
}
.blog-editor h2 {
  text-align: center;
}
.blog-editor form {
  display: flex;
  flex-direction: column;
}
.blog-editor label {
  margin: 10px 0 5px;
}
.blog-editor input,
.blog-editor textarea {
  padding: 10px;
  margin-bottom: 20px;
}
.blog-editor button {
  padding: 10px;
  margin-top: 10px;
}
</style>