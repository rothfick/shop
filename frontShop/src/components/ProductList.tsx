import React from 'react';
import { motion } from 'framer-motion';
import { Product } from '../types/Product';

interface ProductListProps {
  products: Product[];
}

const ProductList: React.FC<ProductListProps> = ({ products }) => {
    return (
        <div>
            <h1>Product List</h1>
            <motion.ul
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                transition={{ duration: 0.5 }}
            >
                {products.map((product) => (
                    <motion.li
                        key={product.id}
                        whileHover={{ scale: 1.1 }}
                        whileTap={{ scale: 0.9 }}
                    >
                        {product.name} - {product.price}
                    </motion.li>
                ))}
            </motion.ul>
        </div>
    );
};

export default ProductList;
