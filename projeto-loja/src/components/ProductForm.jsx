import React, { useState, useEffect } from 'react';
import { useCart } from '../context/CartContext';
import axios from '../axiosconfig';
import '../styles/ProductForm.css';

const ProductForm = () => {
  const { addToCart } = useCart();
  const [product, setProduct] = useState({ name: '', price: '', quantity: '' });
  const [showForm, setShowForm] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('/produtos', {
        name: product.name,
        price: parseFloat(product.price),
        quantity: parseInt(product.quantity),
      });

      addToCart({ ...response.data, quantity: 1 });
      setProduct({ name: '', price: '', quantity: '' });
      setShowForm(false);
    } catch (error) {
      console.error('Erro ao adicionar produto:', error);
    }
  };

  return (
    <div className="product-form-container">
      <button onClick={() => setShowForm(!showForm)} className="toggle-form-button">
        {showForm ? 'Ocultar Formulário' : 'Adicionar Produto'}
      </button>
      {showForm && (
        <form onSubmit={handleSubmit} className="product-form">
          <h3>Adicionar Produto</h3>
          <input
            type="text"
            value={product.name}
            onChange={(e) => setProduct({ ...product, name: e.target.value })}
            placeholder="Nome"
            required
          />
          <input
            type="number"
            value={product.price}
            onChange={(e) => setProduct({ ...product, price: e.target.value })}
            placeholder="Preço"
            required
          />
          <input
            type="number"
            value={product.quantity}
            onChange={(e) => setProduct({ ...product, quantity: e.target.value })}
            placeholder="Quantidade"
            required
          />
          <button type="submit">Adicionar Produto</button>
        </form>
      )}
    </div>
  );
};

export default ProductForm;
