package com.devsuperior.gerenciamento.dto;

import com.devsuperior.gerenciamento.entity.Usuario;
import com.devsuperior.gerenciamento.enuns.Role;

public class UsuarioRequestDTO {
	private Long id;
	private String nome;
	private String usuario;
	private boolean estado;
	private boolean isAuthenticated;
	private Role role;

	public UsuarioRequestDTO(Usuario usuario) {
		super();
		this.nome = usuario.getNome();
		this.usuario = usuario.getUsuario();
		this.estado = usuario.isEstado();
		this.role = usuario.getRole();
		this.id = usuario.getId();
	}
	
	
	public UsuarioRequestDTO() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	public boolean isAuthenticated() {
		return isAuthenticated;
	}


	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	
	

}
