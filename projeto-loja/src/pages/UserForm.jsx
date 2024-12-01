
import React, { useState } from 'react';
import '../styles/UserForm.css';
import { HandleRegister } from '../service/userService';

const UserForm = () => {
  const [name, setName] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [passwordConfirm, setPasswordConfirm] = useState('');
  const [role, setRole] = useState('FUNCIONARIO');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const registerInfo = {
      "nome":name,
      "usuario":username,
      "senha":password,
      "confirmaSenha":passwordConfirm,
      "role":role
    }
    try{
    const response = await HandleRegister(registerInfo)
    alert(`O usuário: ${name} foi criado com o cargo de: ${role.toLowerCase()}`)
  }catch(erro){
    console.log(erro.response);
    alert(erro?.response?.data?.titulo)
  }
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
        <input
          type="password"
          placeholder="Confirmar Senha"
          value={passwordConfirm}
          onChange={(e) => setPasswordConfirm(e.target.value)}
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
