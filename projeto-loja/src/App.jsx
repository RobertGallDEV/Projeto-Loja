import React, { useContext } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Header from './components/Header';
import Sidebar from './components/Sidebar';
import Login from './pages/Login';
import Products from './pages/Products';
import Carrinho from './pages/Carrinho';
import Reports from './pages/Reports';
import UserForm from './pages/UserForm'; 
import { AuthContext } from './context/AuthContext';
import { CartProvider } from './context/CartContext'; 
import './styles/Header.css';
import './styles/Sidebar.css';
import './styles/Login.css';
import './styles/Products.css';
import './styles/Reports.css';
import './styles/UserForm.css';
import './styles/carrinho.css'; 

const App = () => {
  const { user } = useContext(AuthContext);

  return (
    <CartProvider> {}
      <Router>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="*" element={user ? (
            <div className="app-container">
              <Header />
              <Sidebar />
              <div className="main-content">
                <Routes>
                  <Route path="/products" element={<Products />} />
                  <Route path="/carrinho" element={<Carrinho />} />
                  {user.role === 'GERENTE' && <Route path="/reports" element={<Reports />} />}
                  {user.role === 'GERENTE' && <Route path="/register" element={<UserForm />} />}
                  <Route path="/" element={<Navigate to="/products" />} />
                </Routes>
              </div>
            </div>
          ) : (
            <Navigate to="/login" />
          )} />
        </Routes>
      </Router>
    </CartProvider>
  );
};

export default App;
