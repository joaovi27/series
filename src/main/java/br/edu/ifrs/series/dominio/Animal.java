package br.edu.ifrs.series.dominio;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Informe o nome do animal.")
    private String nome;

    @NotNull(message = "Selecione a espécie.")
    @Enumerated(EnumType.STRING)
    private Especie especie;

    @NotNull(message = "Selecione o porte.")
    @Enumerated(EnumType.STRING)
    private Porte porte;

    @NotNull(message = "Selecione o tutor.")
    @ManyToOne
    private Tutor tutor = new Tutor();

    public Animal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Porte getPorte() {
        return porte;
    }

    public void setPorte(Porte porte) {
        this.porte = porte;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
}