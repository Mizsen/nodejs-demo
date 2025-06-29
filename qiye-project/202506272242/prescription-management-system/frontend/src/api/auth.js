import axios from 'axios';

export function login(data) {
  return axios.post('/api/auth/login', data);
}

export function register(data) {
  return axios.post('/api/auth/register', data);
}

export function getMe() {
  return axios.get('/api/auth/me');
}

export function refreshToken() {
  return axios.post('/api/auth/refresh-token');
}
