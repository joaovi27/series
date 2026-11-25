package br.edu.ifrs.series.servico;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifrs.series.dao.TutorDAO;
import br.edu.ifrs.series.dao.AnimalDAO;
import br.edu.ifrs.series.dominio.Tutor;
import br.edu.ifrs.series.dominio.Animal;

@Service
public class TutorService {

    private final TutorDAO dao;
    private final AnimalDAO animalDAO;

    public TutorService(TutorDAO dao, AnimalDAO animalDAO) {
        this.dao = dao;
        this.animalDAO = animalDAO;
    }

    public List<Tutor> listarTodos() {
        return dao.listarTodos();
    }

    public Tutor buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }

    public void salvar(Tutor tutor) {

        if (!dao.listarPorTelefone(tutor.getTelefone()).isEmpty()) {
            throw new RegraDeNegocioException(
                "Este telefone já está cadastrado."
            );
        }
        dao.salvar(tutor);
    }

    public void atualizar(Tutor tutor) {
        for (Tutor existente : dao.listarPorTelefone(tutor.getTelefone())) {
            if (!existente.getId().equals(tutor.getId())) {
                throw new RegraDeNegocioException("Este telefone já está cadastrado.");
            }
        }
        dao.atualizar(tutor);
    }

    public List<Animal> listarAnimais(Long id) {
        return animalDAO.listarPorTutor(id);
    }

    public void remover(Long id) {
        if (!animalDAO.listarPorTutor(id).isEmpty()) {
            throw new RegraDeNegocioException("Este tutor tem animais cadastrados e não pode ser removido.");
        }
        dao.remover(id);
    }
}