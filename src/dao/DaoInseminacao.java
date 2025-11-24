package dao;

import entidades.Inseminacao;
import java.util.List;
import javax.persistence.PersistenceException;

public class DaoInseminacao extends Dao {

    public boolean inserir(Inseminacao i) {
        try {
            em.getTransaction().begin();
            em.persist(i);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean excluir(Inseminacao i) {
        try {
            em.getTransaction().begin();
            em.remove(i);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean editar(Inseminacao i) {
        try {
            em.getTransaction().begin();
            em.merge(i);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public List<Inseminacao> listar() {
        return em.createQuery("select i from Inseminacao i").getResultList();
    }
}
