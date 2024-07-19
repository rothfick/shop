import api from './apiService';

export const processPayment = async (paymentData: any) => {
  try {
    const response = await api.post('/payments', paymentData);
    return response.data;
  } catch (error) {
    console.error('Error processing payment:', error);
    throw error;
  }
};

export const createPaymentIntent = async (amount: number) => {
  try {
    const response = await api.post('/create-payment-intent', { amount });
    return response.data;
  } catch (error) {
    console.error('Error creating payment intent:', error);
    throw error;
  }
};
