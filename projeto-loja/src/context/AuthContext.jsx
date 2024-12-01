import React, { createContext, useEffect, useState } from 'react';
import { HandleLogin } from '../service/userService';
import { useNavigate } from 'react-router-dom';
export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState({});

  useEffect( ()=>{
    const recuperado = JSON.parse(localStorage.getItem('usuario'))
    setUser(recuperado)
    if(!user){
      window.location.href = "/login"
    }
    
  },[])
  
  console.log(user);
  const login = async (username, password) => {
    const info = {
      username: username,
      senha: password
    }
    try{
    const res = await HandleLogin(info);
    setUser(res.data)
      localStorage.setItem('usuario', JSON.stringify(res.data))
      return true;
    }catch(err){
      console.log(err);
      
      return err.response.data.titulo
    }
    
 
  };

  const logout = () =>{
    localStorage.removeItem('usuario')
    window.location.href = "/login"
  }


  return (
    <AuthContext.Provider value={{ user, logout, login }}>
      {children}
    </AuthContext.Provider>
  );  
};
