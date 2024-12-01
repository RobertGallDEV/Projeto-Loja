import React, { createContext, useState, useContext, useEffect } from 'react';
import axios from '../axiosconfig'; 
import { api } from '../service/api';

const CartContext = createContext();

export const CartProvider = ({ children }) => {
  const [cartItems, setCartItems] = useState(() => {
    const savedCart = localStorage.getItem('cartItems');
    return savedCart ? JSON.parse(savedCart) : [];
  });
  const [itens, setItens] = useState([])
  const [quantidade, setQuantidade] = useState([])
  const total = cartItems.reduce((sum, item) => sum + item.price * item.quantidade, 0);

  useEffect(() => {
    localStorage.setItem('cartItems', JSON.stringify(cartItems));
  }, [cartItems]);

  const addToCart = (product) => {
    setCartItems((prevItems) => {
      const itemIndex = prevItems.findIndex((item) => item.id === product.id);
      let newItems;
      if (itemIndex > -1) {
        const updatedItems = [...prevItems];
        updatedItems[itemIndex].quantidade += product.quantidade;
        newItems = updatedItems;
      } else {
        newItems = [...prevItems, product];
      }

      return newItems;
    });
  };

  const removeFromCart = (id) => {
    setCartItems((prevItems) => prevItems.filter((item) => item.id !== id));
  };

  const clearCart = () => {
    setCartItems([]);
  };

  function converterItens() {
    const novosItens = []
    cartItems.forEach(item => {
      console.log(item);
      
      novosItens.push(item.id)
      quantidade.push(item.quantidade)
    });
    console.log(novosItens);
    console.log(quantidade);
    
    setItens(novosItens)
  }

  const checkoutCart = async (usuario,subTotal,total) => {
    converterItens()
    console.log(itens);
    
    const form = {
      usuarioId:{id:usuario},
      totalVenda:subTotal,
      valorPago:total,
      quantidade:quantidade,
      itensId:itens
    }
    console.log(form);
    
    try {
      console.log(cartItems);
      const response = await api.post('/vendas', form);
      
      console.log(response);
      clearCart(); 
      setQuantidade([])
    } catch (error) {
      console.error('Erro ao realizar a venda:', error);
    }
  };

  return (
    <CartContext.Provider value={{ cartItems, addToCart, removeFromCart, clearCart, checkoutCart, total }}>
      {children}
    </CartContext.Provider>
  );
};

export const useCart = () => useContext(CartContext);
