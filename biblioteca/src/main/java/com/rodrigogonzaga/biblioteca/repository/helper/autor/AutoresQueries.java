package com.rodrigogonzaga.biblioteca.repository.helper.autor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rodrigogonzaga.biblioteca.model.Autor;
import com.rodrigogonzaga.biblioteca.repository.filter.AutorFilter;


public interface AutoresQueries {
	
	public Page<Autor> filtrar(AutorFilter filtro, Pageable page);

}
