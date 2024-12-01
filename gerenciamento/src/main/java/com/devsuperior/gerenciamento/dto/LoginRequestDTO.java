package com.devsuperior.gerenciamento.dto;

public class LoginRequestDTO {
	private String username;
	private String senha;
	
	public LoginRequestDTO() {
	
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String login) {
		this.username = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
