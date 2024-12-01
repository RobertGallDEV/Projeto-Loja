import React, { useContext, useState, useEffect } from 'react';
import { AuthContext } from '../context/AuthContext';
import { useCart } from '../context/CartContext';
import axios from '../axiosconfig'; 
import '../styles/Products.css';
import { api } from '../service/api';
import instance from '../axiosconfig';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const Products = () => {
  const { user } = useContext(AuthContext);
  const { addToCart } = useCart();
  const [products, setProducts] = useState([]);
  const [product, setProduct] = useState({ nome: '', preco: '', quantidade: '' });
  const [editingProduct, setEditingProduct] = useState(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [showForm, setShowForm] = useState(false);

  // Função para buscar produtos do backend
  const fetchProducts = async () => {
    try {
      const response = await api.get('/produtos')
      console.log(response);
      
      
      setProducts(response.data);
      console.log(products);
      
    } catch (error) {
      console.error('Erro ao buscar produtos:', error);
     
    }
  };

  // Carregar produtos do backend ao iniciar o componente
  useEffect(() => {
    fetchProducts();
  }, []);

  // Função para adicionar ou editar produtos no backend
  const handleAddProduct = async () => {
    if (!product.nome || product.preco <= 0 || product.quantidade <= 0) {
      alert("Por favor, preencha todos os campos corretamente.");
      return;
    }
    try {
      if (editingProduct) {
        // Atualizar produto existente
        const updateInfo = {
          "nome":product.nome,
          "preco":product.preco,
          "quantidade":product.quantidade
        }
        await api.put(`/produtos/${editingProduct.id}`, updateInfo);
        setProducts((prevProducts) =>
          prevProducts.map(p => p.id === editingProduct.id ? { ...p, ...product } : p)
        );
        setEditingProduct(null);
      } else {
        // Adicionar novo produto
        const response = await api.post('/produtos', product);
        setProducts((prevProducts) => [...prevProducts, response.data]);
      }
      setProduct({ nome: '', preco: '', quantidade: '' });
      setShowForm(false);
      toast.success("Produto Cadastrado com sucesso!")
    } catch (error) {
      console.log(product);
      
      console.error('Erro ao salvar produto:', error);
      toast.error("Ocorreu um erro ao salvar o produto.")
    }
  };

  const handleDeleteProduct = async (id) => {
    try {
      console.log(id);
      
      await api.delete(`/produtos/${id}`); // Rota DELETE para remover produto
      setProducts((prevProducts) => prevProducts.filter(p => p.id !== id));
      toast.success("Produto removido com sucesso!")
    } catch (error) {
      console.error('Erro ao excluir produto:', error);
      toast.error("Ocorreu um erro ao deletar o produto.")
    }
  };

  const handleEditProduct = (product) => {
    setProduct(product);
    setEditingProduct(product);
    setShowForm(true);
  };

  const handleAddToCart = (product) => {
    console.log('Tentando adicionar ao carrinho:', product);
    addToCart({ ...product, quantidade: 1 });
    toast.success("Produto adicionado ao carrinho!")
  };

  const filteredProducts = products.filter(p =>
    p.nome.toLowerCase().includes(searchTerm.toLowerCase())
  );
  
  
  return (
    
    <div className="centered-container">
      <div className="products-container">
        <h1>Produtos</h1>
        
        
        <input
          type="text"
          placeholder="Buscar produtos..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="search-input"
        />

        {user?.role === 'GERENTE' && (
          <>
            <button onClick={() => setShowForm(!showForm)} className="toggle-form-button">
              {showForm ? 'Ocultar Formulário' : 'Adicionar Produto'}
            </button>
            {showForm && (
              <div className="product-form">
                <input
                  type="text"
                  placeholder="Nome"
                  value={product.nome}
                  onChange={(e) => setProduct({ ...product, nome: e.target.value })}
                />
                <input
                  type="number"
                  placeholder="Preço"
                  value={product.preco}
                  onChange={(e) => setProduct({ ...product, preco: parseFloat(e.target.value) })}
                />
                <input
                  type="number"
                  placeholder="Quantidade"
                  value={product.quantidade}
                  onChange={(e) => setProduct({ ...product, quantidade: parseInt(e.target.value, 10) })}
                />
                <button onClick={handleAddProduct}>
                  {editingProduct ? 'Atualizar Produto' : 'Adicionar Produto'}
                </button>
                {editingProduct && (
                  <button onClick={() => {
                    setEditingProduct(null);
                    setProduct({ nome: '', preco: '', quantidade: '' }); // Limpa o formulário ao cancelar
                  }}>Cancelar</button>
                )}
              </div>
            )}
          </>
        )}

        {searchTerm ? (
          <ul className="product-list">
            {filteredProducts.map(p => (
              <li key={p.id}>
                <span>{p.nome}</span>  <span>R$ {p.preco.toFixed(2).replace('.', ',')}</span>  <span>{p.quantidade} unidades</span>
                {user?.role === 'GERENTE' && (
                  <>
                    <button onClick={() => handleEditProduct(p)}>Editar</button>
                    <button onClick={() => handleDeleteProduct(p.id)}>Excluir</button>
                  </>
                )}
                <button onClick={() => handleAddToCart(p)}>Adicionar ao Carrinho</button>
              </li>
            ))}
          </ul>
        ) : products.length != 0? (
          <ul className="product-list">
          {products.map(p => (
            <li key={p.id}>
              <span>{p.nome}</span>  <span>R$ {p.preco.toFixed(2).replace('.', ',')}</span>  <span>{p.quantidade} unidades</span>
              {user?.role === 'GERENTE' && (
                <>
                  <button onClick={() => handleEditProduct(p)}>Editar</button>
                  <button onClick={() => handleDeleteProduct(p.id)}>Excluir</button>
                </>
              )}
              <button onClick={() => handleAddToCart(p)}>Adicionar ao Carrinho</button>
            </li>
          ))}
        </ul>
        ):(
        <p>Nenhum produto encontrado!</p>
        )}
      </div>
      <ToastContainer/>
    </div>
  );
};

export default Products;
