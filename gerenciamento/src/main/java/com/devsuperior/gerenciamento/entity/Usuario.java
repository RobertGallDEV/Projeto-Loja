package com.devsuperior.gerenciamento.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.devsuperior.gerenciamento.enuns.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "VARCHAR(255)")
	private String nome;
	private String senha;
	private String confirmaSenha;
	private String usuario;
	private boolean estado;
	private LocalDateTime ultimoLogin;

	@Enumerated(EnumType.STRING)
	private Role role;

	public Usuario(Long id, String nome, String senha, String confirmaSenha, String usuario, boolean estado,
			LocalDateTime ultimoLogin, Role role) {
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.usuario = usuario;
		this.estado = estado;
		this.ultimoLogin = ultimoLogin;
		this.role = role;
	}

	public Usuario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public LocalDateTime getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(LocalDateTime ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


}
