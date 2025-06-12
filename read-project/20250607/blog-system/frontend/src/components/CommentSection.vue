<template>
  <div class="comment-section">
    <h3>Comments</h3>
    <div v-for="comment in comments" :key="comment.id" class="comment">
      <p><strong>{{ comment.author }}:</strong> {{ comment.content }}</p>
    </div>
    <form @submit.prevent="submitComment">
      <textarea v-model="newComment" placeholder="Add a comment..." required></textarea>
      <button type="submit">Submit</button>
    </form>
  </div>
</template>

<script lang="ts">
import { ref } from 'vue';

export default {
  name: 'CommentSection',
  props: {
    blogId: {
      type: Number,
      required: true
    }
  },
  setup(props) {
    const comments = ref([]);
    const newComment = ref('');

    const fetchComments = async () => {
      // Fetch comments from the mock server or backend
      const response = await fetch(`/api/comments?blogId=${props.blogId}`);
      comments.value = await response.json();
    };

    const submitComment = async () => {
      const response = await fetch(`/api/comments`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          blogId: props.blogId,
          content: newComment.value,
          author: 'Anonymous' // Replace with actual user data
        })
      });
      if (response.ok) {
        newComment.value = '';
        fetchComments();
      }
    };

    fetchComments();

    return {
      comments,
      newComment,
      submitComment
    };
  }
};
</script>

<style scoped>
.comment-section {
  margin-top: 20px;
}

.comment {
  border-bottom: 1px solid #ccc;
  padding: 10px 0;
}

textarea {
  width: 100%;
  height: 60px;
  margin-bottom: 10px;
}

button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 15px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>