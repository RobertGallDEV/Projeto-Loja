package com.devsuperior.gerenciamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.gerenciamento.entity.Vendas;
import com.devsuperior.gerenciamento.service.VendasService;

@RestController
@RequestMapping("/vendas")
public class VendasController {
    @Autowired
    private VendasService vendaService;

    @PostMapping
    public Vendas criarVenda(@RequestBody Vendas venda) {
        return vendaService.salvar(venda);
    }

	/*
	 * @GetMapping("/periodo") public Optional<List<Vendas>>
	 * listarVendasPorPeriodo(@PathVariable LocalDate inicio, LocalDate fim) {
	 * return vendaService.listarVendasPorPeriodo(inicio, fim); }
	 */
}
