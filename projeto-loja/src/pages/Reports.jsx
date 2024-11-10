import React, { useState } from 'react';
import { useLocation } from 'react-router-dom'; 

const Reports = () => {
  const location = useLocation();
  const { cartItems = [], total = 0 } = location.state || {};

  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');

  const handleGenerateReport = () => {
    if (startDate && endDate) {
      alert(`Relatório gerado de ${startDate} a ${endDate}`);
    } else {
      alert('Por favor, selecione um período válido.');
    }
  };

  return (
    <div className="reports-container">
      <h2>Relatórios de Compras</h2>

      <div className="date-selection">
        <label>Data de Início:</label>
        <input
          type="date"
          value={startDate}
          onChange={(e) => setStartDate(e.target.value)}
        />

        <label>Data de Fim:</label>
        <input
          type="date"
          value={endDate}
          onChange={(e) => setEndDate(e.target.value)}
        />

        <button onClick={handleGenerateReport}>Gerar Relatório por Período</button>
      </div>

      <div className="report-details">
        <h3>Relatório da Compra</h3>
        {cartItems.length > 0 ? (
          <>
            <ul>
              {cartItems.map((item, index) => (
                <li key={index}>
                  {item.name} - R$ {item.price.toFixed(2)} - {item.quantity} unidades
                </li>
              ))}
            </ul>
            <h3>Total da Compra: R$ {total.toFixed(2)}</h3>
          </>
        ) : (
          <p>Nenhuma compra foi realizada.</p>
        )}
      </div>
    </div>
  );
};

export default Reports;
