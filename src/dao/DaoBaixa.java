package dao;

import entidades.Baixa;
import java.util.List;
import javax.persistence.PersistenceException;

public class DaoBaixa extends Dao {

    public boolean inserir(Baixa b) {
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

    public List<Baixa> listar() {
        return em.createQuery("select b from Baixa b").getResultList();
    }
}
