package com.rodrigogonzaga.biblioteca.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rodrigogonzaga.biblioteca.service.exception.EmailUsuarioJaCadastradoException;
import com.rodrigogonzaga.biblioteca.service.exception.IsbnLivroJaCadastradoException;
import com.rodrigogonzaga.biblioteca.service.exception.NomeAutorJaCadastradoException;
import com.rodrigogonzaga.biblioteca.service.exception.SenhaObrigatoriaUsuarioException;
import com.rodrigogonzaga.biblioteca.service.exception.TituloLivroJaCadastradoException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {
	
	@ExceptionHandler(NomeAutorJaCadastradoException.class)
	public ResponseEntity<String> handleNomeAutorJaCadastradoException(NomeAutorJaCadastradoException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());		
	}
	
	@ExceptionHandler(TituloLivroJaCadastradoException.class)
	public ResponseEntity<String> handleTituloLivroJaCadastradoException(TituloLivroJaCadastradoException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ExceptionHandler(IsbnLivroJaCadastradoException.class)
	public ResponseEntity<String> handleIsbnLivroJaCadastradoException(IsbnLivroJaCadastradoException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ExceptionHandler(EmailUsuarioJaCadastradoException.class)
	public ResponseEntity<String> handleEmailUsuarioJaCadastradoException(EmailUsuarioJaCadastradoException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}	
	
	@ExceptionHandler(SenhaObrigatoriaUsuarioException.class)
	public ResponseEntity<String> handleSenhaObrigatoriaUsuarioException(SenhaObrigatoriaUsuarioException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

}
