package com.rodrigogonzaga.biblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigogonzaga.biblioteca.model.Livro;
import com.rodrigogonzaga.biblioteca.repository.helper.livro.LivrosQueries;

@Repository
public interface Livros extends JpaRepository<Livro, Long>, LivrosQueries {

	Optional<Livro> findByTituloIgnoreCase(String titulo);
	Optional<Livro> findByIsbn(String isbn);	
	
}
