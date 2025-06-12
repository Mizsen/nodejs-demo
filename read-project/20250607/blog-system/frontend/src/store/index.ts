import { createStore } from 'vuex';

const store = createStore({
  state: {
    user: null,
    blogs: [],
    comments: {},
  },
  mutations: {
    setUser(state, user) {
      state.user = user;
    },
    setBlogs(state, blogs) {
      state.blogs = blogs;
    },
    addBlog(state, blog) {
      state.blogs.push(blog);
    },
    updateBlog(state, updatedBlog) {
      const index = state.blogs.findIndex(blog => blog.id === updatedBlog.id);
      if (index !== -1) {
        state.blogs.splice(index, 1, updatedBlog);
      }
    },
    deleteBlog(state, blogId) {
      state.blogs = state.blogs.filter(blog => blog.id !== blogId);
    },
    addComment(state, { blogId, comment }) {
      if (!state.comments[blogId]) {
        state.comments[blogId] = [];
      }
      state.comments[blogId].push(comment);
    },
  },
  actions: {
    fetchBlogs({ commit }) {
      // Fetch blogs from the mock server or API
    },
    fetchUser({ commit }) {
      // Fetch user data from the mock server or API
    },
  },
  getters: {
    isAuthenticated(state) {
      return !!state.user;
    },
    getBlogs(state) {
      return state.blogs;
    },
    getComments: (state) => (blogId) => {
      return state.comments[blogId] || [];
    },
  },
});

export default store;