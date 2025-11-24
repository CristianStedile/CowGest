package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pesoleite")
public class PesagemLeite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double peso;
    private LocalDate data;
    @ManyToOne
    @JoinColumn(name = "id_animal")
    private Animal animal;

    public PesagemLeite() {

    }

    public PesagemLeite(double peso, LocalDate data, Animal animal) {
        this.peso = peso;
        this.data = data;
        this.animal = animal;
    }
    
    public double getRacao(){
        if(this.peso <= 5 && this.peso >= 1){
            return 0.5;
        }else if(this.peso <= 10 && this.peso > 5){
            return 1;
        }else if(this.peso <= 15 && this.peso > 10){
            return 1.5;
        }else if(this.peso <= 20 && this.peso > 15){
            return 2;
        }else if (this.peso > 20){
            return 2.5;
        }
        return 0;
    }

    public double getPeso() {
        return peso;
    }

    public void setPesagemLeite(double peso) {
        if (peso >= 0) {
            this.peso = peso;
            System.out.println("Sucesso ao setar peso");
        } else {
            System.out.println("Erro ao setar peso");
        }
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

    @Override
    public String toString() {
        return "pesoLeite{" + "id=" + id + ", peso=" + peso + ", data=" + data + ", animal=" + animal + '}';
    }

}
