import React, { useState } from 'react';
import { useLocation } from 'react-router-dom'; 
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify'
import { api } from '../service/api';

const Reports = () => {
  const location = useLocation();
  const [vendas, setVendas] = useState([])

  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  
  const handleGenerateReport = async () => {
    if (startDate && endDate) {
      toast.success(`Relatório gerado de ${startDate} a ${endDate}`);
      const res = await api.get(`/vendas/periodo/${startDate}/${endDate}`)
      console.log(res);
      
      setVendas(res.data)
      console.log(vendas);
      
    } else {
      toast.error('Por favor, selecione um período válido.');
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
        {vendas.length > 0 ? (
          <>
            <ul>
              {vendas.map((item, index) => (
                <li key={index}>
                  {item.name} - R$ {item.totalVenda.toFixed(2)} - {item.quantidade} unidades
                </li>
              ))}
            </ul>
            <h3>Total da Compra: R$ {total.toFixed(2)}</h3>
          </>
        ) : (
          <p>Nenhuma compra foi realizada.</p>
        )}
      </div>
      <ToastContainer/>
    </div>
  );
};

export default Reports;
