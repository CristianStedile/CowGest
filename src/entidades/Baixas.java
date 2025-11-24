package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "baixas")
public class Baixas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate data;
    private String motivo;
    @OneToOne
    @JoinColumn(name = "id_animal")
    private Animal animal;

    public Baixas() {

    }

    public Baixas(LocalDate data, String motivo, Animal animal) {
        this.data = data;
        this.motivo = motivo;
        this.animal = animal;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        if (data != null) {
            this.data = data;
            System.out.println("Sucesso ao setar data");
        } else {
            System.out.println("Erro ao setar data");
        }
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        if (!motivo.equals("")) {
            this.motivo = motivo;
            System.out.println("Sucesso ao setar motivo");
        } else {
            System.out.println("Erro ao setar animal");
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

    @Override
    public String toString() {
        return "Baixas{" + "id=" + id + ", data=" + data + ", motivo=" + motivo + ", animal=" + animal + '}';
    }

}
