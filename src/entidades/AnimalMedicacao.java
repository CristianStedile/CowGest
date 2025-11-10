package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "animalmedicacao")
public class AnimalMedicacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date data;
    @ManyToOne
    @JoinColumn(name = "fk_animal")
    private Animal animal;
    @ManyToOne
    @JoinColumn(name = "id_medicacao")
    private Medicacao medicacao;

    public AnimalMedicacao() {

    }

    public AnimalMedicacao(int id, Date data, Animal animal, Medicacao medicacao) {
        this.id = id;
        this.data = data;
        this.animal = animal;
        this.medicacao = medicacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        if (data != null) {
            this.data = data;
            System.out.println("Sucesso ao setar data");
        } else {
            System.out.println("Erro ao setar data");
        }
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        if (animal != null) {
            this.animal = animal;
            System.out.println("Sucesso ao setar animal");
        } else {
            System.out.println("Erro ao setar animal");
        }
    }

    public Medicacao getMedicacao() {
        return medicacao;
    }

    public void setMedicacao(Medicacao medicacao) {
        if (medicacao != null) {
            this.medicacao = medicacao;
            System.out.println("Sucesso ao setar animal");
        } else {
            System.out.println("Erro ao setar animal");
        }
    }

    @Override
    public String toString() {
        return "aplicacaoMedicacao{" + "id=" + id + ", data=" + data + ", animal=" + animal + ", medicacao=" + medicacao + '}';
    }

}
