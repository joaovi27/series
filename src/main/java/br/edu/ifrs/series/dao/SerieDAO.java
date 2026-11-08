package br.edu.ifrs.series.dao;

import java.util.List;

import br.edu.ifrs.series.dominio.Serie;

public interface SerieDAO {
	    List<Serie> listarTodas();
	    Serie buscarPorId(Long id);
	    void salvar(Serie serie);
	    void atualizar(Serie serie);
	    void remover(Long id);
	}

