package com.rodrigogonzaga.biblioteca.repository.helper.livro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rodrigogonzaga.biblioteca.model.Livro;
import com.rodrigogonzaga.biblioteca.repository.filter.LivroFilter;

public interface LivrosQueries {
	
	public Page<Livro> filtrar(LivroFilter filtro, Pageable page);

}
