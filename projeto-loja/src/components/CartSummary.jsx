import React, { useState } from 'react';
import { useCart } from '../context/CartContext';

const CartSummary = () => {
  const { cartItems, checkoutCart } = useCart(); 
  const [discount, setDiscount] = useState(0);
  
  const subtotal = cartItems.reduce((sum, item) => sum + item.price * item.quantity, 0);
  const total = subtotal - discount;

  return (
    <div className="cart-summary">
      <h3>Resumo do Carrinho</h3>
      <p>Subtotal: R$ {subtotal.toFixed(2)}</p>
      <input 
        type="number" 
        placeholder="Desconto" 
        value={discount} 
        onChange={(e) => setDiscount(Number(e.target.value))} 
      />
      <p>Desconto: R$ {discount.toFixed(2)}</p>
      <p>Total: R$ {total.toFixed(2)}</p>
      <button onClick={checkoutCart}>Finalizar Compra</button>
    </div>
  );
};

export default CartSummary;
