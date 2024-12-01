
import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';
import '../styles/Login.css';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const { login } = useContext(AuthContext);
  const navigate = useNavigate();
  const [message, setMessage] = useState('')
  const [showMessage, setShowMessage] = useState(false)

  const handleLogin = async (e) => {
    e.preventDefault();
    const success = await login(username, password);
    
    if (success == true) {
      navigate('/products');
    } else {
      setShowMessage(true)
      setMessage(success);
      setInterval(() => {
        setShowMessage(false)
      }, 3000);
    }
  };

  return (
    <div className="login-container">
      <form onSubmit={handleLogin} className="login-form">
        <h2>Login</h2>
        <input
          type="text"
          placeholder="Usuário"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          placeholder="Senha"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit">Entrar</button>
      {showMessage && (
          <p className='message'>{message}</p>
        )}
      
      </form>
    </div>
  );
};

export default Login;
