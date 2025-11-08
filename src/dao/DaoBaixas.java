package dao;

import entidades.Baixas;
import java.util.List;
import javax.persistence.PersistenceException;

public class DaoBaixas extends Dao {

    public boolean inserir(Baixas b) {
        try {
            em.getTransaction().begin();
            em.persist(b);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public List<Baixas> listar() {
        return em.createQuery("select b from Baixas b").getResultList();
    }
}
