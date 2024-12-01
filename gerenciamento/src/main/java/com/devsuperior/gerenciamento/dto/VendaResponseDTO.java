package com.devsuperior.gerenciamento.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.devsuperior.gerenciamento.entity.VendaItens;
import com.devsuperior.gerenciamento.entity.Vendas;

public class VendaResponseDTO {
	private Long id;
	private	UsuarioRequestDTO usuario;
	private BigDecimal totalVenda;
	private BigDecimal valorPago;
	private List<VendaItens> itens;
	private LocalDate dataCadastro;
	private LocalDateTime ultimaAtualizacao;
	
	public VendaResponseDTO() {
		
	}
	
	

	public VendaResponseDTO(Vendas venda) {
		super();
		this.id = venda.getId();
		this.usuario = new UsuarioRequestDTO(venda.getUsuario());
		this.totalVenda = venda.getTotalVenda();
		this.valorPago = venda.getValorPago();
		this.dataCadastro = venda.getDataCadastro();
		this.ultimaAtualizacao = venda.getUltimaAtualizacao();
		this.itens = venda.getItens();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioRequestDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioRequestDTO usuario) {
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

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
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
