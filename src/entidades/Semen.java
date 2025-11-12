package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "semen")
public class Semen implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String reprodutor;
    private int doses;
    private int pote;
    
    public Semen(){
    
    }

    public Semen(String reprodutor, int doses, int pote) {
        this.reprodutor = reprodutor;
        this.doses = doses;
        this.pote = pote;
    }

    public String getReprodutor() {
        return reprodutor;
    }

    public void setReprodutor(String reprodutor) {
        if(!reprodutor.equals("")){
        this.reprodutor = reprodutor;
            System.out.println("Sucesso ao setar reprodutor");
        } else {
            System.out.println("Erro ao setar reprodutor");
        }
    }

    public int getDoses() {
        return doses;
    }
    
    public void addDoses(int quantidade){
        if(quantidade > 0){
            this.doses += quantidade;
            System.out.println("Sucesso ao adicionar doses");
        }else{
            System.out.println("Erro ao adicionar doses");
        }
    }
    
    public void removerDoses(int quantidade){
        if(quantidade > 0){
            this.doses -= quantidade;
            System.out.println("Sucesso ao remover doses");
        }else{
            System.out.println("Erro ao remover doses");
        }
    }

    public int getPote() {
        return pote;
    }

    public void setPote(int pote) {
        if(pote > 0){
        this.pote = pote;
        System.out.println("Sucesso ao setar pote");
        }else{
            System.out.println("Erro ao setar pote");
        }
    }
    
    
    
}
