package dao;

import entidades.Medicacao;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

public class DaoMedicacao extends Dao {

    public boolean inserir(Medicacao m) {
        try {
            em.getTransaction().begin();
            em.persist(m);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean remover(Medicacao m) {
        try {
            em.getTransaction().begin();
            em.remove(m);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean editar(Medicacao m) {
        try {
            em.getTransaction().begin();
            em.merge(m);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public Medicacao selecionar(String nomeMedicacao) {
        try {
            return (Medicacao) em.createQuery("select m from Medicacao m where m.nomeMedicacao = :nomeMedicacao").setParameter("nomeMedicacao", nomeMedicacao).getSingleResult();
        } catch (NoResultException n) {
            return null;
        }
    }

    public List<Medicacao> listar() {
        return em.createQuery("select m from Medicacao m").getResultList();
    }
}
