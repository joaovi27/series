package br.edu.ifrs.series.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.ifrs.series.dominio.Jogo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Repository
@Transactional
public class JogoDAOJPA implements JogoDAO {

	 @PersistenceContext
	private EntityManager em;
	
	 @Override
	public List<Jogo> listarTodas(){
		return em
	            .createQuery("SELECT j FROM Jogo j", Jogo.class)
	            .getResultList();
	}
	
	 @Override
	    public Jogo buscarPorId(Long id) {
	        return em.find(Jogo.class, id);
	    }
	    
	    @Override
	    public void salvar(Jogo jogo) {
	        em.persist(jogo);
	    }
	    
	    @Override
	    public void atualizar(Jogo jogo) {
	        em.merge(jogo);
	    }
	    @Override
	    public void remover(Long id) {
	        Jogo jogo = em.find(Jogo.class, id);
	        if (jogo!= null) {
	            em.remove(jogo);
	        }
	    }
}
