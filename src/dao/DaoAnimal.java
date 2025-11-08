package dao;

import entidades.Animal;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

public class DaoAnimal extends Dao {

    public boolean inserir(Animal a) {
        try {
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean remover(Animal a) {
        try {
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean editar(Animal a) {
        try {
            em.getTransaction().begin();
            em.merge(a);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public Animal selecionar(int numeroAnimal) {
        try {
            return (Animal) em.createQuery("select a from Animal a where a.numeroAnimal = :numeroAnimal").setParameter("numeroAnimal", numeroAnimal).getSingleResult();
        } catch (NoResultException n) {
            return null;
        }
    }
    
    public List<Animal> listar(){
    return em.createQuery("select a from Animal a where a.baixado=0").getResultList();
    }
}
