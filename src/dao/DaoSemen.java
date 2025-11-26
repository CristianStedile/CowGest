package dao;

import entidades.Semen;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

public class DaoSemen extends Dao {

    public boolean inserir(Semen s) {
        try {
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean remover(Semen s) {
        try {
            em.getTransaction().begin();
            em.remove(s);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean editar(Semen s) {
        try {
            em.getTransaction().begin();
            em.merge(s);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }
    
    public Semen selecionar(String touro) {
        try {
            return (Semen) em.createQuery("select s from Semen s where s.touro = :touro").setParameter("touro", touro).getSingleResult();
        } catch (NoResultException n) {
            return null;
        }
    }

    public List<Semen> listar() {
        return em.createQuery("select s from Semen s").getResultList();
    }
}
