import React, { useState, useEffect } from 'react';
import { useCart } from '../context/CartContext';
import '../styles/CartItem.css'; // Importando o CSS fornecido

const CartItem = () => {
  const { cartItems, removeFromCart, addToCart } = useCart();
  const [quantity, setQuantity] = useState({});

  // Inicializa as quantidades com base nos itens do carrinho
  useEffect(() => {
    const initialQuantities = cartItems.reduce((acc, item) => {
      acc[item.id] = item.quantity;
      return acc;
    }, {});
    setQuantity(initialQuantities);
  }, [cartItems]);

  // Atualiza a quantidade no estado e no carrinho
  const handleQuantityChange = (id, delta) => {
    setQuantity(prev => {
      const newQuantity = (prev[id] || 1) + delta;
      if (newQuantity > 0) {
        const updatedItem = cartItems.find(item => item.id === id);
        addToCart({ ...updatedItem, quantity: newQuantity - updatedItem.quantity }); // Apenas adiciona a diferença de quantidade
        return { ...prev, [id]: newQuantity };
      } else {
        removeFromCart(id); // Remove o item se a quantidade for zero
        return prev;
      }
    });
  };

  return (
    <div className="cart-item-container">
      <h3>Itens no Carrinho</h3>
      {cartItems.length > 0 ? (
        <div className="cart-item-list">
          <ul>
            {cartItems.map(item => (
              <li key={item.id}>
                <span>{item.name}</span>
                <span>R$ {item.price.toFixed(2)}</span>
                <div className="quantity-controls">
                  <button onClick={() => handleQuantityChange(item.id, -1)}>-</button>
                  <span>{quantity[item.id] || item.quantity}</span>
                  <button onClick={() => handleQuantityChange(item.id, 1)}>+</button>
                </div>
                <button onClick={() => removeFromCart(item.id)} className="remove-button">Remover</button>
              </li>
            ))}
          </ul>
        </div>
      ) : (
        <p>Seu carrinho está vazio.</p>
      )}
    </div>
  );
};

export default CartItem;
