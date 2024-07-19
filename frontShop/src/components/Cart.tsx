import React, { useEffect, useState } from 'react';
import { getCart, updateCart, removeFromCart } from '../services/cartService';
import { motion } from 'framer-motion';

const Cart: React.FC = () => {
  const [cartItems, setCartItems] = useState<any[]>([]);

  useEffect(() => {
    const fetchCartItems = async () => {
      try {
        const items = await getCart();
        setCartItems(items);
      } catch (error) {
        console.error('Error fetching cart items:', error);
      }
    };

    fetchCartItems();
  }, []);

  const handleUpdate = async (cartItemId: number, quantity: number) => {
    const updatedItem = await updateCart(cartItemId, quantity);
    setCartItems((items) =>
      items.map((item) => (item.id === cartItemId ? updatedItem : item))
    );
  };

  const handleRemove = async (cartItemId: number) => {
    await removeFromCart(cartItemId);
    setCartItems((items) => items.filter((item) => item.id !== cartItemId));
  };

  return (
    <motion.div
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      transition={{ duration: 0.5 }}
    >
      <h1>Cart</h1>
      {cartItems.length === 0 ? (
        <p>Your cart is empty</p>
      ) : (
        <motion.ul
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ duration: 0.5 }}
        >
          {cartItems.map((item) => (
            <motion.li
              key={item.id}
              initial={{ y: 20, opacity: 0 }}
              animate={{ y: 0, opacity: 1 }}
              exit={{ y: -20, opacity: 0 }}
              whileHover={{ scale: 1.05 }}
              whileTap={{ scale: 0.95 }}
            >
              <h2>{item.name}</h2>
              <p>Price: ${item.price}</p>
              <p>Quantity: {item.quantity}</p>
              <p>Total: ${item.price * item.quantity}</p>
              <button onClick={() => handleUpdate(item.id, item.quantity + 1)}>+</button>
              <button onClick={() => handleUpdate(item.id, item.quantity - 1)}>-</button>
              <button onClick={() => handleRemove(item.id)}>Remove</button>
            </motion.li>
          ))}
        </motion.ul>
      )}
    </motion.div>
  );
};

export default Cart;
