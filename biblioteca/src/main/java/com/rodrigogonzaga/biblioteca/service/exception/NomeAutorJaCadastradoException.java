package com.rodrigogonzaga.biblioteca.service.exception;

public class NomeAutorJaCadastradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public NomeAutorJaCadastradoException(String mensagem) {
		super(mensagem);
	}
			

}
