package com.rodrigogonzaga.biblioteca.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.rodrigogonzaga.biblioteca.thymeleaf.processor.ClassForErrorAttributeTagProcessor;

public class BibliotecaDialect extends AbstractProcessorDialect {
	
	public BibliotecaDialect() {		
		//super(String name, String prefix, int processorPrecedence)
		super("RodrigoGonzaga_Biblioteca", "biblioteca", StandardDialect.PROCESSOR_PRECEDENCE);		
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));	
		//processadores.add(new MessageElementTagProcessor(dialectPrefix));
		return processadores;
	}

}
