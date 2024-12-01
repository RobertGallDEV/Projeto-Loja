package com.devsuperior.gerenciamento.exception;

public class SenhaInvalidaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SenhaInvalidaException(String message) {
		System.out.println(message);
	}
}
