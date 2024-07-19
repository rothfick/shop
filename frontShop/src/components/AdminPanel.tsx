import React, { useState } from 'react';
import { motion } from 'framer-motion';
import adminService from '../services/adminService';

const AdminPanel: React.FC = () => {
    const [name, setName] = useState('');
    const [price, setPrice] = useState('');
    const [description, setDescription] = useState('');
    const [image, setImage] = useState('');
    const [successMessage, setSuccessMessage] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const handleAddProduct = (e: React.FormEvent) => {
        e.preventDefault();
        setSuccessMessage('');
        setErrorMessage('');

        const priceAsNumber = parseFloat(price); // Konwersja price na number

        adminService.addProduct({ name, price: priceAsNumber, description, image })
            .then(response => {
                setSuccessMessage('Product added successfully!');
                setName('');
                setPrice('');
                setDescription('');
                setImage('');
            })
            .catch(error => {
                setErrorMessage('Error adding product: ' + error.message);
            });
    };

    return (
        <motion.div
            initial={{ opacity: 0, scale: 0.9 }}
            animate={{ opacity: 1, scale: 1 }}
            transition={{ duration: 0.5 }}
        >
            <h1>Admin Panel</h1>
            <form onSubmit={handleAddProduct}>
                <input
                    type="text"
                    placeholder="Name"
                    value={name}
                    onChange={e => setName(e.target.value)}
                    required
                />
                <input
                    type="number"
                    placeholder="Price"
                    value={price}
                    onChange={e => setPrice(e.target.value)}
                    required
                />
                <textarea
                    placeholder="Description"
                    value={description}
                    onChange={e => setDescription(e.target.value)}
                    required
                />
                <input
                    type="text"
                    placeholder="Image URL"
                    value={image}
                    onChange={e => setImage(e.target.value)}
                    required
                />
                <button type="submit">Add Product</button>
            </form>
            {successMessage && <p style={{ color: 'green' }}>{successMessage}</p>}
            {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
        </motion.div>
    );
};

export default AdminPanel;
