import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { GoogleOAuthProvider } from '@react-oauth/google';
import AdminPanel from './components/AdminPanel';
import Cart from './components/Cart';
import Checkout from './components/Checkout';
import Login from './components/Login';
import ProductDetail from './components/ProductDetail';
import ProductList from './components/ProductList';
import { getProducts } from './services/productService';
import { loadStripe } from '@stripe/stripe-js';
import { Elements } from '@stripe/react-stripe-js';
import './App.css';

const stripePromise = loadStripe('your_stripe_public_key');

interface Product {
  id: number;
  name: string;
  description: string;
  price: number;
  imageUrl: string;
}

const App: React.FC = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const clientId = process.env.REACT_APP_GOOGLE_CLIENT_ID;

  useEffect(() => {
    async function fetchProducts() {
      const fetchedProducts: Product[] = await getProducts();
      setProducts(fetchedProducts);
    }

    fetchProducts();
  }, []);

  if (!clientId) {
    return <div>Error: Google Client ID is not defined</div>;
  }

  return (
    <GoogleOAuthProvider clientId={clientId}>
      <Router>
        <Routes>
          <Route path="/admin" element={<AdminPanel />} />
          <Route path="/cart" element={<Cart />} />
          <Route path="/checkout" element={
            <Elements stripe={stripePromise}>
              <Checkout />
            </Elements>
          } />
          <Route path="/login" element={<Login />} />
          <Route path="/product/:id" element={<ProductDetail />} />
          <Route path="/" element={<ProductList products={products} />} />
        </Routes>
      </Router>
    </GoogleOAuthProvider>
  );
};

export default App;
