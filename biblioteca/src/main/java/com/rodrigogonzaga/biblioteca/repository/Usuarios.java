package com.rodrigogonzaga.biblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigogonzaga.biblioteca.model.Usuario;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long> {	 
	
	public Optional<Usuario> findByEmailIgnoreCase(String email); 
	
	
	
}
