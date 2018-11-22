package com.rodrigogonzaga.biblioteca.service.exception;

public class IsbnLivroJaCadastradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public IsbnLivroJaCadastradoException(String mensagem) {
		super(mensagem);
	}

}
