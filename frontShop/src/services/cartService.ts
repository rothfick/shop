import api from './apiService';

export const getCart = async () => {
  try {
    const response = await api.get('/cart');
    return response.data.items; // Zakładamy, że dane koszyka są zwracane w polu `items`
  } catch (error) {
    console.error('Error fetching cart:', error);
    throw new Error('Unable to fetch cart. Please try again later.');
  }
};

export const addToCart = async (productId: number, quantity: number) => {
  try {
    const response = await api.post('/cart', { productId, quantity });
    return response.data;
  } catch (error) {
    console.error('Error adding to cart:', error);
    throw new Error('Unable to add item to cart. Please try again later.');
  }
};

export const updateCart = async (cartItemId: number, quantity: number) => {
  try {
    const response = await api.put(`/cart/${cartItemId}`, { quantity });
    return response.data;
  } catch (error) {
    console.error('Error updating cart:', error);
    throw new Error('Unable to update cart item. Please try again later.');
  }
};

export const removeFromCart = async (cartItemId: number) => {
  try {
    const response = await api.delete(`/cart/${cartItemId}`);
    return response.data;
  } catch (error) {
    console.error('Error removing from cart:', error);
    throw new Error('Unable to remove item from cart. Please try again later.');
  }
};
