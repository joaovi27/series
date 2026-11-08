package br.edu.ifrs.series.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import br.edu.ifrs.series.dominio.Tutor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Repository
@Transactional
public class TutorDAOJPA implements TutorDAO {

	 @PersistenceContext
	private EntityManager em;
	
	 @Override
	public List<Tutor> listarTodos(){
		return em
	            .createQuery("SELECT t FROM Tutor t", Tutor.class)
	            .getResultList();
	}
	
	 @Override
	    public Tutor buscarPorId(Long id) {
	        return em.find(Tutor.class, id);
	    }
	    
	    @Override
	    public void salvar(Tutor tutor) {
	        em.persist(tutor);
	    }
	    
	    @Override
	    public void atualizar(Tutor tutor) {
	        em.merge(tutor);
	    }
	    @Override
	    public void remover(Long id) {
	        Tutor tutor = em.find(Tutor.class, id);
	        if (tutor!= null) {
	            em.remove(tutor);
	        }
	    }
}
