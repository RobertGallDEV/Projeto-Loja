import React, { createContext, useState } from 'react';

export const AuthContext = createContext();

const mockUsers = {
  gerente: { username: 'gerente', password: '123', role: 'GERENTE' },
  funcionario: { username: 'funcionario', password: '123', role: 'FUNCIONARIO' },
};

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  const login = async (username, password) => {
    const user = mockUsers[username];
    if (user && user.password === password) {
      setUser(user);
      return true;
    }
    return false;
  };

  return (
    <AuthContext.Provider value={{ user, login }}>
      {children}
    </AuthContext.Provider>
  );  
};
