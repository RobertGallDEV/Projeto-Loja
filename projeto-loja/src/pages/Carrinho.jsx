import React from 'react';
import { useCart } from '../context/CartContext'; 
import CartItem from '../components/CartItem'; 
import CartSummary from '../components/CartSummary'; 
import '../styles/Carrinho.css'; 

const Carrinho = () => {
  const { cartItems } = useCart(); 

  return (
    <div className="cart-page-container">
      <h1>Carrinho de Compras</h1>
      {cartItems.length > 0 ? (
        <div className="cart-content">
          <div className="cart-items-container">
              <CartItem /> 
          </div>
          <CartSummary />
        </div>
      ) : (
        <p>Seu carrinho está vazio.</p>
      )}
    </div>
  );
};

export default Carrinho;
