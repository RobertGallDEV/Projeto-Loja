package com.devsuperior.gerenciamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.gerenciamento.dto.ProdutoUpdateDTO;
import com.devsuperior.gerenciamento.entity.Produto;
import com.devsuperior.gerenciamento.service.ProdutoService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarTodos();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseEntity<String>> deletarProduto(@PathVariable Long id){
    	return ResponseEntity.ok(produtoService.deletarPorId(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoUpdateDTO produto){
        return ResponseEntity.ok(produtoService.atualizarProduto(id, produto));
    }
}
