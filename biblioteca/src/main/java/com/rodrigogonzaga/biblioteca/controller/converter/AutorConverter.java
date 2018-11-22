package com.rodrigogonzaga.biblioteca.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.rodrigogonzaga.biblioteca.model.Autor;

public class AutorConverter implements Converter<String, Autor>{

	@Override
	public Autor convert(String strCodigo) {
		if(!StringUtils.isEmpty(strCodigo)) {
			Autor autor = new Autor();
			autor.setCodigo(Long.valueOf(strCodigo));
			return autor;
		}
		return null;
	}
}
