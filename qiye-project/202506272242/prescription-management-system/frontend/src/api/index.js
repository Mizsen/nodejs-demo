import axios from 'axios';

// 请求拦截器：自动带上token
axios.interceptors.request.use(config => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
}, error => Promise.reject(error));

// 响应拦截器：401自动跳转登录
axios.interceptors.response.use(
  response => response,
  error => {
    if (error.response && error.response.status === 401) {
      localStorage.removeItem('token');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

const apiClient = axios.create({
  baseURL: 'http://localhost:8080/api', // Adjust the base URL as needed
  headers: {
    'Content-Type': 'application/json',
  },
});

// Prescription API
export const prescriptionApi = {
  createPrescription(data) {
    return apiClient.post('/prescriptions', data);
  },
  getPrescription(id) {
    return apiClient.get(`/prescriptions/${id}`);
  },
  getPrescriptions(params) {
    return apiClient.get('/prescriptions', { params });
  },
  updatePrescription(id, data) {
    return apiClient.put(`/prescriptions/${id}`, data);
  },
  deletePrescription(id) {
    return apiClient.delete(`/prescriptions/${id}`);
  },
  uploadPrescriptionImage(id, formData) {
    return apiClient.post(`/prescriptions/${id}/images`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
  },
};

// Drug API
export const drugApi = {
  createDrug(data) {
    return apiClient.post('/drugs', data);
  },
  getDrug(id) {
    return apiClient.get(`/drugs/${id}`);
  },
  getDrugs(params) {
    return apiClient.get('/drugs', { params });
  },
  updateDrug(id, data) {
    return apiClient.put(`/drugs/${id}`, data);
  },
  deleteDrug(id) {
    return apiClient.delete(`/drugs/${id}`);
  },
  uploadDrugImage(id, formData) {
    return apiClient.post(`/drugs/${id}/images`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
  },
};

export default apiClient;