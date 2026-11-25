package br.edu.ifrs.series.servico;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifrs.series.dao.AnimalDAO;
import br.edu.ifrs.series.dominio.Animal;

@Service
public class AnimalService {

    private final AnimalDAO dao;

    public AnimalService(AnimalDAO dao) {
        this.dao = dao;
    }

    public List<Animal> listarTodos() {
        return dao.listarTodos();
    }

    public Animal buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }

    public void salvar(Animal animal) {
        dao.salvar(animal);
    }

    public void atualizar(Animal animal) {
        dao.atualizar(animal);
    }

    public void remover(Long id) {
        dao.remover(id);
    }
}