package com.rodrigogonzaga.biblioteca.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.rodrigogonzaga.biblioteca.model.Usuario;
import com.rodrigogonzaga.biblioteca.repository.Usuarios;
import com.rodrigogonzaga.biblioteca.service.exception.EmailUsuarioJaCadastradoException;
import com.rodrigogonzaga.biblioteca.service.exception.SenhaObrigatoriaUsuarioException;

@Service
public class CadastroUsuarioService {
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	@Transactional
	public void salvar(Usuario usuario) {
		
		Optional<Usuario> optionalUsuario = usuarios.findByEmailIgnoreCase(usuario.getEmail());
		
		if(optionalUsuario.isPresent()) {
			throw new EmailUsuarioJaCadastradoException("E-mail já cadastrado"); 
		}
		
		if(usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaObrigatoriaUsuarioException("A senha é obrigatória para novos usuários"); 
		}
		
		if(usuario.isNovo()) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
			usuario.setConfirmacaoSenha(usuario.getSenha());
			
		}		
		
		usuarios.save(usuario);
		
	}

}
