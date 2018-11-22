package com.rodrigogonzaga.biblioteca.service.exception;

public class TituloLivroJaCadastradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public TituloLivroJaCadastradoException(String mensagem) {
		super(mensagem);
	}

}
