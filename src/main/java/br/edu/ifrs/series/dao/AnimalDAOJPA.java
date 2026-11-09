package br.edu.ifrs.series.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.ifrs.series.dominio.Animal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class AnimalDAOJPA implements AnimalDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Animal> listarTodos() {
        return em.createQuery(
            "SELECT a FROM Animal a",
            Animal.class
        ).getResultList();
    }

    @Override
    public Animal buscarPorId(Long id) {
        return em.find(Animal.class, id);
    }

    @Override
    public void salvar(Animal animal) {
        em.persist(animal);
    }

    @Override
    public void atualizar(Animal animal) {
        em.merge(animal);
    }

    @Override
    public void remover(Long id) {
        Animal animal = em.find(Animal.class, id);

        if (animal != null) {
            em.remove(animal);
        }
    }
}