package com.devsuperior.gerenciamento.dto;

import com.devsuperior.gerenciamento.entity.Usuario;
import com.devsuperior.gerenciamento.enuns.Role;

public class UsuarioCreateDTO {

	private String nome;
	private String senha;
	private String confirmaSenha;
	private String usuario;
	private boolean estado;
	private Role role;

	public UsuarioCreateDTO(Usuario usuario) {
		super();
		this.nome = usuario.getNome();
		this.senha = usuario.getSenha();
		this.confirmaSenha = usuario.getConfirmaSenha();
		this.usuario = usuario.getUsuario();
		this.estado = usuario.isEstado();
		this.role = usuario.getRole();
	}

	public UsuarioCreateDTO() {
		// TODO Auto-generated constructor stub
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	
}
