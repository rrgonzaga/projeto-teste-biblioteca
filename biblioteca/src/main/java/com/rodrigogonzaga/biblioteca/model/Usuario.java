package com.rodrigogonzaga.biblioteca.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.rodrigogonzaga.biblioteca.validation.AtributoConfirmacao;

@AtributoConfirmacao(atributo="senha", atributoConfirmacao = "confirmacaoSenha", message="Confirmação de Senha não confere com a senha")
@Entity
@Table(name="usuario")
public class Usuario implements Serializable{	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Size(min=2, max=80, message="O nome do usuário é obrigatório e deve ter entre 2 e 80 caracteres")
	private String nome;
	
	@NotBlank(message="O e-mail é obrigatório")
	@Email(message="E-mail inválido")
	private String email;		
	
	private String senha;
	
	@Transient
	private String confirmacaoSenha;
	
	
	@Column(name="perfil_administrador")
	private boolean perfilAdministrador;
		
	private boolean ativo;
	
	
	public boolean isNovo() {
		return codigo == null; 
	}
	
	

	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}	

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isPerfilAdministrador() {
		return perfilAdministrador;
	}

	public void setPerfilAdministrador(boolean perfilAdministrador) {
		this.perfilAdministrador = perfilAdministrador;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	} 
	
	

}
