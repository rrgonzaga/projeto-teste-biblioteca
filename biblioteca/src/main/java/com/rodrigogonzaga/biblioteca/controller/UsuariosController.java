package com.rodrigogonzaga.biblioteca.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rodrigogonzaga.biblioteca.model.Usuario;
import com.rodrigogonzaga.biblioteca.service.CadastroUsuarioService;
import com.rodrigogonzaga.biblioteca.service.exception.EmailUsuarioJaCadastradoException;
import com.rodrigogonzaga.biblioteca.service.exception.SenhaObrigatoriaUsuarioException;

@Controller
public class UsuariosController {
	
	@Autowired
	CadastroUsuarioService cadastroUsuarioService;
	
	@RequestMapping(value = "/usuarios/novo", method=RequestMethod.GET)	
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		return mv;
		
	}
	
	@RequestMapping(value = "/usuarios/novo", method=RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return novo(usuario);
		}
		
		try {
			
			cadastroUsuarioService.salvar(usuario);
		}
		catch(EmailUsuarioJaCadastradoException ex) {
			result.rejectValue("email", ex.getMessage(), ex.getMessage());
			return novo(usuario);
		}
		catch(SenhaObrigatoriaUsuarioException ex) {
			result.rejectValue("senha", ex.getMessage(), ex.getMessage());
			return novo(usuario);
		}
		
		attributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso");
		
		return new ModelAndView("redirect:/usuarios/novo");
	}

}
