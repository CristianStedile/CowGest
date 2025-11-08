package dao;

import entidades.aplicacaoMedicacao;
import java.util.List;
import javax.persistence.PersistenceException;

public class DaoAplicacaoMedicacao extends Dao{
    public boolean inserir(aplicacaoMedicacao am){
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
    
    public boolean remover(aplicacaoMedicacao am) {
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

    public boolean editar(aplicacaoMedicacao am) {
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
    
    public List<aplicacaoMedicacao> listar(){
    return em.createQuery("select ap from aplicacaoMedicacao ap").getResultList();
    }
}
