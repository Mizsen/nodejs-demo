import axios from './axios'

export const getPosts = (page, size) => axios.get('/posts', { params: { page, size } })
export const getPost = id => axios.get(`/posts/${id}`)
export const createPost = data => axios.post('/posts', data)
export const updatePost = (id, data) => axios.put(`/posts/${id}`, data)
export const deletePost = id => axios.delete(`/posts/${id}`)
export const searchPosts = (keyword, tag, page, size) =>
  axios.get('/search/posts', { params: { keyword, tag, page, size } }) 