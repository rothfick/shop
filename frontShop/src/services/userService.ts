import api from './apiService';

export const loginWithGoogle = async (googleToken: string) => {
  const response = await api.post('/auth/google', { token: googleToken });
  localStorage.setItem('token', response.data.token);
  return response.data.user;
};

export const getCurrentUser = async () => {
  const response = await api.get('/user/me');
  return response.data;
};
