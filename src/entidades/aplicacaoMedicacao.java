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
@Table(name = "aplicacaoMedicacao")
public class aplicacaoMedicacao implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String dataAplicacao;
    @ManyToOne
    @JoinColumn(name = "fk_animal")
    private Animal animal;
    @ManyToOne
    @JoinColumn(name = "id_medicacao")
    private Medicacao medicacao;
    
    public aplicacaoMedicacao(){
    
    }

    public aplicacaoMedicacao(int id, String dataAplicacao, Animal animal, Medicacao medicacao) {
        this.id = id;
        this.dataAplicacao = dataAplicacao;
        this.animal = animal;
        this.medicacao = medicacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(String dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Medicacao getMedicacao() {
        return medicacao;
    }

    public void setMedicacao(Medicacao medicacao) {
        this.medicacao = medicacao;
    }

    @Override
    public String toString() {
        return "aplicacaoMedicacao{" + "id=" + id + ", dataAplicacao=" + dataAplicacao + ", animal=" + animal + ", medicacao=" + medicacao + '}';
    }

    
}
