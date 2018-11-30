package com.rodrigogonzaga.biblioteca.repository.helper.livro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import com.rodrigogonzaga.biblioteca.model.Livro;
import com.rodrigogonzaga.biblioteca.repository.filter.LivroFilter;

public class LivrosImpl implements LivrosQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override	
	@Transactional(readOnly = true)
	public Page<Livro> filtrar(LivroFilter filtro, Pageable pageable) {
		
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Livro.class);
		
		//Implementação de paginação no lado do servidor
		int totalRegistrosPorPagina = pageable.getPageSize();		
		int paginaAtual = pageable.getPageNumber();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		
		criteria.setMaxResults(totalRegistrosPorPagina);
		criteria.setFirstResult(primeiroRegistro);
		
		Sort sort = pageable.getSort();
		//System.out.println(">>>> sort: " + sort);
		if(sort != null) {
			Sort.Order order = sort.iterator().next();
			String property = order.getProperty();
			criteria.addOrder(order.isAscending() ? Order.asc(property) : Order.desc(property));
		}
		
		adicionarFiltro(filtro, criteria);		
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private void adicionarFiltro(LivroFilter filtro, Criteria criteria) {
		if(filtro != null) {			
			if (!StringUtils.isEmpty(filtro.getTitulo())) {				
				criteria.add(Restrictions.ilike("titulo", filtro.getTitulo(), MatchMode.ANYWHERE)); 				
			}
			
			if (!StringUtils.isEmpty(filtro.getIsbn())) {				
				criteria.add(Restrictions.ilike("isbn", filtro.getIsbn(), MatchMode.ANYWHERE)); 				
			}
			
			if (!StringUtils.isEmpty(filtro.getEditora())) {				
				criteria.add(Restrictions.ilike("editora", filtro.getEditora(), MatchMode.ANYWHERE)); 				
			}
						
			if (!StringUtils.isEmpty(filtro.getNomeAutor())) {				
				//JOIN with autor association and creates alias 'autor_livro' for it 
				criteria.createAlias("autor", "autor_livro");
				criteria.add(Restrictions.ilike("autor_livro.nome", filtro.getNomeAutor(), MatchMode.ANYWHERE)); 				
			}
		}
	}
	
	private Long total(LivroFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Livro.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long)criteria.uniqueResult();
	}

	

}
