package com.rodrigogonzaga.biblioteca.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.rodrigogonzaga.biblioteca.service.CadastroLivroService;

@Configuration
@ComponentScan(basePackageClasses = CadastroLivroService.class)
public class ServiceConfig {
	

}
