package dao;

import entidades.LeiteVendido;
import java.util.List;
import javax.persistence.PersistenceException;

public class DaoLeiteVendido extends Dao {

    public boolean inserir(LeiteVendido lv) {
        try {
            em.getTransaction().begin();
            em.persist(lv);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean remover(LeiteVendido lv) {
        try {
            em.getTransaction().begin();
            em.remove(lv);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean editar(LeiteVendido lv) {
        try {
            em.getTransaction().begin();
            em.merge(lv);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }
    
    public List<LeiteVendido> listarMes(int mes){
        return em.createQuery("select lv from LeiteVendido lv where lv.mes = :mes").setParameter("mes", mes).getResultList();
    }

    public List<LeiteVendido> listar() {
        return em.createQuery("select lv from LeiteVendido lv").getResultList();
    }
}
