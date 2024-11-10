package com.devsuperior.gerenciamento.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
