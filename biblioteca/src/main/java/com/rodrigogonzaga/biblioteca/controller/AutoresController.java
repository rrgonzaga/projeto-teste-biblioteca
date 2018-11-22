package com.rodrigogonzaga.biblioteca.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rodrigogonzaga.biblioteca.controller.page.PageWrapper;
import com.rodrigogonzaga.biblioteca.model.Autor;
import com.rodrigogonzaga.biblioteca.repository.Autores;
import com.rodrigogonzaga.biblioteca.repository.filter.AutorFilter;
import com.rodrigogonzaga.biblioteca.service.CadastroAutorService;
import com.rodrigogonzaga.biblioteca.service.exception.NomeAutorJaCadastradoException;

@Controller
@RequestMapping("/autores")
public class AutoresController {
	
	@Autowired
	private CadastroAutorService cadastroAutorService; 
	
	@Autowired
	private Autores autores; 
	
	@RequestMapping(value = "/novo", method=RequestMethod.GET)	
	public ModelAndView novo(Autor autor) {
		ModelAndView mv = new ModelAndView("autor/CadastroAutor"); 
		return mv;
	}
	
	@RequestMapping(value = "/novo", method=RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Autor autor, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return novo(autor);
		}	
		
		try {
			
			cadastroAutorService.salvar(autor);
			
		} catch (NomeAutorJaCadastradoException ex) {
			result.rejectValue("nome", ex.getMessage(), ex.getMessage());
			return novo(autor);			
		}		
		
		attributes.addFlashAttribute("mensagem", "Autor(a) cadastrado com sucesso");
		
		return new ModelAndView("redirect:/autores/novo");
	}
	
		
	@RequestMapping(method=RequestMethod.POST, consumes= {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> cadastrarViaAjaxCadastroRapido(@RequestBody @Valid Autor autor, BindingResult result) {			
		
		if(result.hasErrors()) {			
			//return ResponseEntity.badRequest().body(result.getFieldErrors());
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		} 
							
		//Caso haja exceção na linha de comando abaixo, quem tratará será o manipulador de exceções será a classe ControllerAdviceExceptionHandler
		autor = cadastroAutorService.salvar(autor);
				
		return ResponseEntity.ok(autor);		
	}
	
	@GetMapping
	public ModelAndView pesquisar(AutorFilter autorFilter, BindingResult result, @PageableDefault(size=2) Pageable page, 
									HttpServletRequest httpServletRequest) {
		
		ModelAndView mv = new ModelAndView("autor/PesquisaAutores");
				
		//mv.addObject("autores",autores.findAll(page));		
		PageWrapper<Autor> pageWrapper = new PageWrapper<>(autores.filtrar(autorFilter,page), httpServletRequest);
		mv.addObject("pagina", pageWrapper);		
		
		return mv;
	}
		
	
}
