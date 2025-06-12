import axios from './axios'

export const uploadFile = (file, type, userId) => {
  const formData = new FormData()
  formData.append('file', file)
  formData.append('type', type)
  formData.append('userId', userId)
  return axios.post('/upload', formData)
}

export const getUserFiles = userId => axios.get(`/upload/user/${userId}`) 