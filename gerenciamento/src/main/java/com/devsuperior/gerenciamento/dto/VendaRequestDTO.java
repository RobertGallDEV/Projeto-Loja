package com.devsuperior.gerenciamento.dto;

import java.math.BigDecimal;
import java.util.List;

import com.devsuperior.gerenciamento.entity.Usuario;

public class VendaRequestDTO {
	private Usuario usuarioId;
	private BigDecimal totalVenda;
	private BigDecimal valorPago;
	private List<Long> itensId;
	private List<Integer> quantidade;
	public Usuario getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Usuario usuarioId) {
		this.usuarioId = usuarioId;
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
	public List<Long> getItensId() {
		return itensId;
	}
	public void setItensId(List<Long> itensId) {
		this.itensId = itensId;
	}
	public List<Integer> getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(List<Integer> quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
