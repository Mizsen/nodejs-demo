import axios from './axios'

export const login = data => axios.post('/users/login', data)
export const register = data => axios.post('/users/register', data)
export const getUserProfile = id => axios.get(`/users/${id}`)
export const updateUserProfile = (id, data) => axios.put(`/users/${id}`, data)
