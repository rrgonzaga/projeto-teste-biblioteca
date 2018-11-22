package com.rodrigogonzaga.biblioteca.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rodrigogonzaga.biblioteca.model.Autor;
import com.rodrigogonzaga.biblioteca.repository.Autores;
import com.rodrigogonzaga.biblioteca.service.exception.NomeAutorJaCadastradoException;

@Service
public class CadastroAutorService {
	
	@Autowired
	private Autores autores; 
	
	@Transactional
	public Autor salvar(Autor autor) {
		
		Optional<Autor> autorOptional = autores.findByNomeIgnoreCase(autor.getNome());
		
		if(autorOptional.isPresent())
		{
			throw new NomeAutorJaCadastradoException("Nome do autor(a) já cadastrado");
		}
		
		return autores.saveAndFlush(autor);		
	}

}
