package com.rodrigogonzaga.biblioteca.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.rodrigogonzaga.biblioteca.model.Livro;
import com.rodrigogonzaga.biblioteca.repository.Livros;

//@ComponentScan(basePackageClasses = Livros.class)
@Configuration
@EnableJpaRepositories(basePackageClasses = Livros.class, enableDefaultTransactions=false)
@EnableTransactionManagement
public class JPAConfig {

	@Bean
	public DataSource dataSource() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup(); 
		dataSourceLookup.setResourceRef(true);
				
		return dataSourceLookup.getDataSource("jdbc/bibliotecaDB");
		
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter(); 
		adapter.setDatabase(Database.POSTGRESQL);
		adapter.setShowSql(false);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
		return adapter;		
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setJpaVendorAdapter(jpaVendorAdapter);
		factory.setPackagesToScan(Livro.class.getPackage().getName());		
		factory.afterPropertiesSet();
		
		return factory.getObject();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManager) {
		JpaTransactionManager jpaTransaction = new JpaTransactionManager();
		jpaTransaction.setEntityManagerFactory(entityManager);
		return jpaTransaction;
	}
	
	
	
	
}
