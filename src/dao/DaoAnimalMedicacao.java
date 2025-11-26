package dao;

import entidades.AnimalMedicacao;
import java.util.List;
import javax.persistence.PersistenceException;

public class DaoAnimalMedicacao extends Dao{
    public boolean inserir(AnimalMedicacao am){
        try{
            em.getTransaction().begin();
            em.persist(am);
            em.getTransaction().commit();
        return true;
        }catch(PersistenceException e){
            if(em.getTransaction().isActive()){
            em.getTransaction().rollback();
            }
        return false;
        }
    }
    
    public boolean remover(AnimalMedicacao am) {
        try {
            em.getTransaction().begin();
            em.remove(am);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean editar(AnimalMedicacao am) {
        try {
            em.getTransaction().begin();
            em.merge(am);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }
    
    public List<AnimalMedicacao> listar(){
    return em.createQuery("select a from AnimalMedicacao a").getResultList();
    }
}
