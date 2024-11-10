import React, { useContext, useState } from 'react';
import { NavLink } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';
import '../styles/Sidebar.css';

const Sidebar = () => {
  const { user } = useContext(AuthContext);
  const [isCollapsed, setIsCollapsed] = useState(false); 

  const toggleSidebar = () => {
    setIsCollapsed(!isCollapsed);
  };

  return (
    <nav className={`sidebar ${isCollapsed ? 'collapsed' : ''}`}>
      <button className="toggle-button" onClick={toggleSidebar}>
        {isCollapsed ? '>' : '<'}
      </button>
      <div className="links">
        {user.role === 'GERENTE' && (
          <>
            <NavLink to="/products">Produtos</NavLink>
            <NavLink to="/carrinho">Carrinho</NavLink>
            <NavLink to="/reports">Relatórios</NavLink>
            <NavLink to="/register">Cadastrar Usuário</NavLink>
          </>
        )}
        {user.role === 'FUNCIONARIO' && (
          <>
            <NavLink to="/products">Produtos</NavLink>
            <NavLink to="/carrinho">Carrinho</NavLink>
          </>
        )}
      </div>
    </nav>
  );
};

export default Sidebar;