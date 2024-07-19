// src/services/apiService.ts
import axios from 'axios';

// Podstawowa konfiguracja axios
const api = axios.create({
  baseURL: 'http://localhost:8080/api', // Upewnij się, że URL jest poprawny dla twojego backendu
  headers: {
    'Content-Type': 'application/json',
  },
});

export default api;
