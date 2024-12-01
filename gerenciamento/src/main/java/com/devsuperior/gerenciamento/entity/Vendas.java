package com.devsuperior.gerenciamento.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@Entity
public class Vendas {
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
	private	Usuario usuario;
	private BigDecimal totalVenda;
	private BigDecimal valorPago;
	private LocalDate dataCadastro;
	private LocalDateTime ultimaAtualizacao;
	
	@JsonIgnore
	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
	private List<VendaItens> itens;
	
	
	public Vendas() {
		super();
	}


	public Vendas(Long id, Usuario usuario, BigDecimal totalVenda, BigDecimal valorPago, LocalDate dataCadastro,
			LocalDateTime ultimaAtualizacao, List<VendaItens> itens) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.totalVenda = totalVenda;
		this.valorPago = valorPago;
		this.dataCadastro = dataCadastro;
		this.ultimaAtualizacao = ultimaAtualizacao;
		this.itens = itens;
	}

	@PrePersist
	public void onPrePersist() {
		this.setDataCadastro(LocalDate.now());
		this.setUltimaAtualizacao(LocalDateTime.now());
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public BigDecimal getTotalVenda() {
		return totalVenda;
	}


	public void setTotalVenda(BigDecimal totalVenda) {
		this.totalVenda = totalVenda;
	}


	public BigDecimal getValorPago() {
		return valorPago;
	}


	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}


	public LocalDate getDataCadastro() {
		return dataCadastro;
	}


	public void setDataCadastro(LocalDate localDate) {
		this.dataCadastro = localDate;
	}


	public LocalDateTime getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}


	public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}


	public List<VendaItens> getItens() {
		return itens;
	}


	public void setItens(List<VendaItens> itens) {
		this.itens = itens;
	}


	
}
