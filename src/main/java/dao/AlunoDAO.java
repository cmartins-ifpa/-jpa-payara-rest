package dao;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;

import entidades.Aluno;

import static javax.transaction.Transactional.TxType.REQUIRED;

import java.util.List;

@ApplicationScoped
public class AlunoDAO   {
	@PersistenceContext ( type=PersistenceContextType.TRANSACTION)  //(unitName="MyAppPU")
	private EntityManager em;	
	 
	@Transactional (REQUIRED)
    public void create(Aluno aluno) {
        em.persist(aluno);
    }

	@Transactional(REQUIRED)
    public Aluno edit(Aluno aluno) {
        return em.merge(aluno);
    }

	@Transactional(REQUIRED)
    public void remove(Aluno aluno) {
        em.remove(em.merge(aluno));
    }

	public Aluno find(Long id) {
        return em.find(Aluno.class, id);
    }

	public List<Aluno> findAll() {
        return em.createQuery("SELECT p FROM Aluno p", Aluno.class).getResultList();
    }
}
