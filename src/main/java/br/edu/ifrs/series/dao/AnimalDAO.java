package br.edu.ifrs.series.dao;

import java.util.List;
import br.edu.ifrs.series.dominio.Animal;

public interface AnimalDAO {

    List<Animal> listarTodos();

    Animal buscarPorId(Long id);

    void salvar(Animal animal);

    void atualizar(Animal animal);

    void remover(Long id);
}