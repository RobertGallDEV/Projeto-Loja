
import React, { useState } from 'react';
import '../styles/UserForm.css';

const UserForm = () => {
  const [name, setName] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('FUNCIONARIO');

  const handleSubmit = (e) => {
    e.preventDefault();
    alert(`Usuário criado: ${name}, ${username}, ${role}`);
  };

  return (
    <div className="user-form">
      <form onSubmit={handleSubmit}>
        <h2>Cadastrar Usuário</h2>
        <input
          type="text"
          placeholder="Nome"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
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
        <select value={role} onChange={(e) => setRole(e.target.value)}>
          <option value="FUNCIONARIO">Funcionário</option>
          <option value="GERENTE">Gerente</option>
        </select>
        <button type="submit">Cadastrar</button>
      </form>
    </div>
  );
};

export default UserForm;
