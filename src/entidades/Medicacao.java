package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "medicacao")
public class Medicacao implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomeMedicacao;
    private String dosagem;

    public Medicacao(){
    
    }

    public Medicacao(int id, String nomeMedicacao, String dosagem) {
        this.id = id;
        this.nomeMedicacao = nomeMedicacao;
        this.dosagem = dosagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeMedicacao() {
        return nomeMedicacao;
    }

    public void setNomeMedicacao(String nomeMedicacao) {
        this.nomeMedicacao = nomeMedicacao;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    @Override
    public String toString() {
        return "Medicacao{" + "id=" + id + ", nomeMedicacao=" + nomeMedicacao + ", dosagem=" + dosagem + '}';
    }

    
}
