package com.devsuperior.gerenciamento.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class VendaItens {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "produto_id")
	    private Produto produto;

	    @ManyToOne
	    @JoinColumn(name = "venda_id")
	    private Vendas venda;

	    private int quantidade;
	    private double precoUnitario;
	
	
	public VendaItens() {
		super();
	}


	public VendaItens(Long id, Produto produto, Vendas venda, int quantidade, double precoUnitario) {
		super();
		this.id = id;
		this.produto = produto;
		this.venda = venda;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public Vendas getVenda() {
		return venda;
	}


	public void setVenda(Vendas venda) {
		this.venda = venda;
	}


	public int getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	public double getPrecoUnitario() {
		return precoUnitario;
	}


	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}



	
	
}
