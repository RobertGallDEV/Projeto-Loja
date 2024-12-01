package com.devsuperior.gerenciamento.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.gerenciamento.dto.VendaRequestDTO;
import com.devsuperior.gerenciamento.dto.VendaResponseDTO;
import com.devsuperior.gerenciamento.entity.Produto;
import com.devsuperior.gerenciamento.entity.VendaItens;
import com.devsuperior.gerenciamento.entity.Vendas;
import com.devsuperior.gerenciamento.repository.UsuarioRepository;
import com.devsuperior.gerenciamento.repository.VendaRepository;

import jakarta.transaction.Transactional;

@Service
public class VendasService {
    @Autowired
    private VendaRepository vendaRepository;
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    
    public VendaResponseDTO salvar(VendaRequestDTO vendaRequest) {
    	List<VendaItens> produtos = new ArrayList<VendaItens>();
    	
    	Vendas venda = new Vendas();
    	venda.setUsuario(vendaRequest.getUsuarioId());
    	venda.setTotalVenda(vendaRequest.getTotalVenda());
    	venda.setValorPago(vendaRequest.getValorPago());
    	System.out.println(vendaRequest.getItensId());
    	for(int i = 0; vendaRequest.getItensId().size() > i ; i++ ) {
    		int quantidade = vendaRequest.getQuantidade().get(i);
    		Long produtoId = vendaRequest.getItensId().get(i);
    		Produto produto = produtoService.buscarPorId(produtoId);
    		
    		VendaItens item = new VendaItens(produto, venda, quantidade,produto.getPreco());
    		produtos.add(item);
    	}
    	venda.setItens(produtos);
        return new VendaResponseDTO(vendaRepository.save(venda));
    }

	
	  public List<VendaResponseDTO> listarVendasPorPeriodo(LocalDate inicio, LocalDate fim) {
		  return vendaRepository.findByDataBetween(inicio, fim)
                  .orElse(Collections.emptyList()) 
                  .stream()
                  .map(VendaResponseDTO::new) 
                  .collect(Collectors.toList());
}
	 
}
