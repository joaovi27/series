package br.edu.ifrs.series.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Tutor {

	@NotBlank(message = "Informe o nome do cliente.") 
	private String nome;
	
	@NotNull(message ="Informe o numéro para contato")
    private Integer telefone;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}
    
	public Long getId() {
	    return id;
	}

	public void setId(Long id) {
	    this.id = id;
	}

}
