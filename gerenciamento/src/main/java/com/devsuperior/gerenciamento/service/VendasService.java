package com.devsuperior.gerenciamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.gerenciamento.entity.Vendas;
import com.devsuperior.gerenciamento.repository.VendaRepository;

@Service
public class VendasService {
    @Autowired
    private VendaRepository vendaRepository;

    public Vendas salvar(Vendas venda) {
        return vendaRepository.save(venda);
    }

	/*
	 * public Optional<List<Vendas>> listarVendasPorPeriodo(LocalDate inicio,
	 * LocalDate fim) { return vendaRepository.findByDataBetween(inicio, fim); }
	 */
}
