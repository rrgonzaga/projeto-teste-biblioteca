package com.rodrigogonzaga.biblioteca.repository;


import java.util.List;
import java.util.Optional;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rodrigogonzaga.biblioteca.model.Autor;
import com.rodrigogonzaga.biblioteca.repository.helper.autor.AutoresQueries;

@Repository
public interface Autores extends JpaRepository<Autor, Long>, AutoresQueries {
	
	public Optional<Autor> findByNomeIgnoreCase(String nome);

	public List<Autor> findAllByOrderByNomeAsc(); 
	

}
