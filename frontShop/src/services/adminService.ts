// src/services/adminService.ts
import axios from 'axios';

const API_URL = 'http://localhost:8888/api/admin'; // Zmień URL na odpowiedni adres backendu

const addProduct = async (product: { name: string; price: number; description: string; image: string }) => {
    try {
        const response = await axios.post(`${API_URL}/products`, product);
        return response.data;
    } catch (error) {
        console.error('Error adding product:', error);
        throw error;
    }
};

const adminService = {
    addProduct,
};

export default adminService;
