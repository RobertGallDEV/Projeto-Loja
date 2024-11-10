import React, { useContext, useState, useEffect } from 'react';
import { AuthContext } from '../context/AuthContext';
import { useCart } from '../context/CartContext';
import axios from '../axiosconfig'; 
import '../styles/Products.css';

const Products = () => {
  const { user } = useContext(AuthContext);
  const { addToCart } = useCart();
  const [products, setProducts] = useState([]);
  const [product, setProduct] = useState({ name: '', price: '', quantity: '' });
  const [editingProduct, setEditingProduct] = useState(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [showForm, setShowForm] = useState(false);

  // Função para buscar produtos do backend
  const fetchProducts = async () => {
    try {
      const response = await axios.get('/produtos'); 
      setProducts(response.data);
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
    if (!product.name || product.price <= 0 || product.quantity <= 0) {
      alert("Por favor, preencha todos os campos corretamente.");
      return;
    }
    
    try {
      if (editingProduct) {
        // Atualizar produto existente
        await axios.put(`/produtos/${editingProduct.id}`, product);
        setProducts((prevProducts) =>
          prevProducts.map(p => p.id === editingProduct.id ? { ...p, ...product } : p)
        );
        setEditingProduct(null);
      } else {
        // Adicionar novo produto
        const response = await axios.post('/produtos', product);
        setProducts((prevProducts) => [...prevProducts, response.data]);
      }
      setProduct({ name: '', price: '', quantity: '' });
      setShowForm(false);
    } catch (error) {
      console.error('Erro ao salvar produto:', error);
      alert('Erro ao salvar produto. Tente novamente.');
    }
  };

  const handleDeleteProduct = async (id) => {
    try {
      await axios.delete(`/produtos/${id}`); // Rota DELETE para remover produto
      setProducts((prevProducts) => prevProducts.filter(p => p.id !== id));
    } catch (error) {
      console.error('Erro ao excluir produto:', error);
      alert('Erro ao excluir produto. Tente novamente.');
    }
  };

  const handleEditProduct = (product) => {
    setProduct(product);
    setEditingProduct(product);
    setShowForm(true);
  };

  const handleAddToCart = (product) => {
    console.log('Tentando adicionar ao carrinho:', product);
    addToCart({ ...product, quantity: 1 });
  };

  const filteredProducts = products.filter(p =>
    p.name.toLowerCase().includes(searchTerm.toLowerCase())
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
                  value={product.name}
                  onChange={(e) => setProduct({ ...product, name: e.target.value })}
                />
                <input
                  type="number"
                  placeholder="Preço"
                  value={product.price}
                  onChange={(e) => setProduct({ ...product, price: parseFloat(e.target.value) })}
                />
                <input
                  type="number"
                  placeholder="Quantidade"
                  value={product.quantity}
                  onChange={(e) => setProduct({ ...product, quantity: parseInt(e.target.value, 10) })}
                />
                <button onClick={handleAddProduct}>
                  {editingProduct ? 'Atualizar Produto' : 'Adicionar Produto'}
                </button>
                {editingProduct && (
                  <button onClick={() => {
                    setEditingProduct(null);
                    setProduct({ name: '', price: '', quantity: '' }); // Limpa o formulário ao cancelar
                  }}>Cancelar</button>
                )}
              </div>
            )}
          </>
        )}

        {searchTerm && filteredProducts.length > 0 ? (
          <ul className="product-list">
            {filteredProducts.map(p => (
              <li key={p.id}>
                <span>{p.name}</span> - <span>R$ {p.price.toFixed(2).replace('.', ',')}</span> - <span>{p.quantity} unidades</span>
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
        ) : (
          searchTerm && <p>Nenhum produto encontrado.</p>
        )}
      </div>
    </div>
  );
};

export default Products;
