package br.edu.ifrs.series.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//Cada jogo tem nome, desenvolvedora, ano de lançamento e plataforma.

@Entity
public class Jogo {

	@NotBlank(message = "Informe o nome do jogo.")
	private String nome;
	
	@NotBlank(message = "Informe a plataforma.")
	private String desenvolvedora;
	
	@NotNull(message = "Informe o ano de lançamento.")
	@Min(value = 1970, message = "O ano de lançamento deve ser 1970 ou posterior.")
	private Integer anoLancamento;
	
	@NotBlank(message = "Informe a plataforma.")
	private String plataforma;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDesenvolvedora() {
		return desenvolvedora;
	}
	public void setDesenvolvedora(String desenvolvedora) {
		this.desenvolvedora = desenvolvedora;
	}
	public Integer getAnoLancamento() {
		return anoLancamento;
	}
	public void setAnoLancamento(Integer anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Jogo() {
	}
	
}
