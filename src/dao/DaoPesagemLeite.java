package dao;

import entidades.PesagemLeite;
import java.util.List;
import javax.persistence.PersistenceException;

public class DaoPesagemLeite extends Dao {

    public boolean inserir(PesagemLeite p) {
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean excluir(PesagemLeite p) {
        try {
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean editar(PesagemLeite p) {
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public List<PesagemLeite> listar() {
        return em.createQuery("select p from pesoLeite p").getResultList();
    }
}
