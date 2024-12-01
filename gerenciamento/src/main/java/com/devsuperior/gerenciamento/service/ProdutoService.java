package com.devsuperior.gerenciamento.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devsuperior.gerenciamento.dto.ProdutoUpdateDTO;
import com.devsuperior.gerenciamento.entity.Produto;
import com.devsuperior.gerenciamento.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public void atualizarEstoque(Long produtoId, int quantidade) {
        Produto produto = buscarPorId(produtoId);
        if (produto != null) {
            produto.setQuantidade(produto.getQuantidade() - quantidade);
            produtoRepository.save(produto);
        }
    }

    public double calcularTempoMedioSaida(Long produtoId) {
        Produto produto = buscarPorId(produtoId);
        if (produto == null) {
            return 0;
        }

        LocalDate dataEntrada = produto.getDataCadastro();
        LocalDate hoje = LocalDate.now();

        long diasNoEstoque = ChronoUnit.DAYS.between(dataEntrada, hoje);
        int quantidadeVendida = produto.getQuantidade();

        return diasNoEstoque / (quantidadeVendida > 0 ? quantidadeVendida : 1);
    }
    
    public ResponseEntity<String> deletarPorId(Long id){
    	if (produtoRepository.findById(id).isEmpty()) throw new RuntimeException("Produto não encontrado!");
    	produtoRepository.deleteById(id);
    	return ResponseEntity.ok("Produto Deletado com sucesso!") ;
    }
    
    public Produto atualizarProduto(Long id, ProdutoUpdateDTO produto){
    	Optional<Produto> produtoOld = produtoRepository.findById(id);
    	if (produtoOld.isEmpty()) throw new RuntimeException("Produto não encontrado!");
    	if (produto.getNome() != null) {
    		produtoOld.get().setNome(produto.getNome());
    	}
    	if(produto.getPreco() != null) {
    		produtoOld.get().setPreco(produto.getPreco());
    	}
    	if(produto.getQuantidade() != null) {
    		produtoOld.get().setQuantidade(produto.getQuantidade());
    	}
    	return produtoRepository.save(produtoOld.get());
    }
}
