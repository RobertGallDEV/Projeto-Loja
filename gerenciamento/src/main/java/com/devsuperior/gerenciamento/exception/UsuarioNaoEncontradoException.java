package com.devsuperior.gerenciamento.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UsuarioNaoEncontradoException(String message) {
		System.out.println(message);
	}
}
