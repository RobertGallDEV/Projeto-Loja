import React, { useContext, useState } from 'react';
import { useCart } from '../context/CartContext';
import { AuthContext } from '../context/AuthContext';

const CartSummary = () => {
  const { cartItems, checkoutCart } = useCart();
  const {user} = useContext(AuthContext) 
  const [discount, setDiscount] = useState(0);
  
  const subtotal = cartItems.reduce((sum, item) => sum + item.preco * item.quantidade, 0);
  const total = subtotal - discount;
  console.log(user.id);
  
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
      <button onClick={()=>checkoutCart(user.id,subtotal,total)}>Finalizar Compra</button>
    </div>
  );
};

export default CartSummary;
