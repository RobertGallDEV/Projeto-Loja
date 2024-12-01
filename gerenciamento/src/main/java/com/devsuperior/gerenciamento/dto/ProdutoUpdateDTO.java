package com.devsuperior.gerenciamento.dto;

import java.math.BigDecimal;

public class ProdutoUpdateDTO {
	private String nome;
	private Integer quantidade;
	private BigDecimal preco;
	
	public ProdutoUpdateDTO() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	
}
