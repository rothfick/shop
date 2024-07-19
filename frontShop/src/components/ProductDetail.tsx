import React, { useEffect, useState } from 'react';
import { getProductById } from '../services/productService';
import { addToCart } from '../services/cartService';
import { useParams, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { motion } from 'framer-motion';
import 'react-toastify/dist/ReactToastify.css';

const ProductDetail: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const [product, setProduct] = useState<any>(null);
  const navigate = useNavigate();

  useEffect(() => {
    if (!id) {
      toast.error('Invalid product ID');
      navigate('/'); // Przekierowanie na stronę główną lub inną odpowiednią stronę
      return;
    }

    const fetchProduct = async () => {
      try {
        const productId = parseInt(id, 10);
        if (isNaN(productId)) {
          throw new Error('Invalid product ID');
        }
        const product = await getProductById(productId);
        setProduct(product);
      } catch (error) {
        toast.error('Error fetching product details.');
        console.error('Error fetching product:', error);
      }
    };
    fetchProduct();
  }, [id, navigate]);

  const handleAddToCart = async () => {
    if (!id) {
      toast.error('Invalid product ID');
      return;
    }

    try {
      const productId = parseInt(id, 10);
      if (isNaN(productId)) {
        throw new Error('Invalid product ID');
      }
      await addToCart(productId, 1);
      toast.success('Product added to cart!');
    } catch (error) {
      toast.error('Error adding to cart.');
      console.error('Error adding to cart:', error);
    }
  };

  if (!product) {
    return <div>Loading...</div>;
  }

  return (
    <motion.div
      initial={{ opacity: 0, y: 50 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 0.5 }}
    >
      <h2>{product.name}</h2>
      <motion.img
        src={product.imageUrl}
        alt={product.name}
        initial={{ scale: 0.8 }}
        animate={{ scale: 1 }}
        transition={{ duration: 0.5 }}
      />
      <p>{product.description}</p>
      <p>Price: ${product.price}</p>
      <motion.button
        whileHover={{ scale: 1.1 }}
        whileTap={{ scale: 0.9 }}
        onClick={handleAddToCart}
      >
        Add to Cart
      </motion.button>
    </motion.div>
  );
};

export default ProductDetail;
