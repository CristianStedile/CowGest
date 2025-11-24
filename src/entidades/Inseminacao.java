package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inseminacao")
public class Inseminacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate data;
    @ManyToOne
    @JoinColumn(name = "id_animal")
    private Animal animal;
    @ManyToOne
    @JoinColumn(name = "id_semen")
    private Semen semen;

    public Inseminacao() {

    }

    public Inseminacao(LocalDate data, Animal animal, Semen semen) {
        this.data = data;
        this.animal = animal;
        this.semen = semen;
    }
    
    public LocalDate getSecagem(){
        return this.data.plusDays(220);
    }
    
    public LocalDate getPreParto(){
        return this.data.plusDays(261);
    }
    
    public LocalDate getParto(){
        return this.data.plusDays(280);
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

    public Semen getSemen() {
        return semen;
    }

    public void setSemen(Semen semen) {
        if (semen != null) {
            this.semen = semen;
            System.out.println("Sucesso ao setar sêmen");
        } else {
            System.out.println("Erro ao setar sêmen");
        }
    }

}
