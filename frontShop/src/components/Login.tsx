import React from 'react';
import { GoogleOAuthProvider, GoogleLogin } from '@react-oauth/google';
import { loginWithGoogle } from '../services/userService';
import { useNavigate } from 'react-router-dom';

const Login: React.FC = () => {
  const navigate = useNavigate();

  const handleLogin = async (tokenResponse: any) => {
    try {
      const user = await loginWithGoogle(tokenResponse.credential);
      console.log('Logged in user:', user);
      navigate('/');
    } catch (error) {
      console.error('Login failed:', error);
    }
  };

  return (
    <GoogleOAuthProvider clientId={process.env.REACT_APP_GOOGLE_CLIENT_ID!}>
      <div>
        <h2>Login</h2>
        <GoogleLogin
          onSuccess={handleLogin}
          onError={() => console.error('Login failed')}
        />
      </div>
    </GoogleOAuthProvider>
  );
};

export default Login;
