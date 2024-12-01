package com.devsuperior.gerenciamento.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.gerenciamento.dto.VendaRequestDTO;
import com.devsuperior.gerenciamento.dto.VendaResponseDTO;
import com.devsuperior.gerenciamento.entity.Vendas;
import com.devsuperior.gerenciamento.service.VendasService;

@RestController
@RequestMapping("/vendas")
public class VendasController {
    @Autowired
    private VendasService vendaService;

    @PostMapping
    public VendaResponseDTO criarVenda(@RequestBody VendaRequestDTO venda) {
        return vendaService.salvar(venda);
    }

	
	 @GetMapping("/periodo/{inicio}/{fim}") public List<VendaResponseDTO>
	 listarVendasPorPeriodo(@PathVariable LocalDate inicio, @PathVariable LocalDate fim) {
	 return vendaService.listarVendasPorPeriodo(inicio, fim); }
	 
}
