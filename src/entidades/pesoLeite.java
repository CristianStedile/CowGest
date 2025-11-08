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
@Table(name = "pesoleite")
public class pesoLeite implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double pesagemLeite;
    private String dataPeso;
    @ManyToOne
    @JoinColumn(name = "id_animal")
    private Animal animal;
    
    
    public pesoLeite(){
    
    }

    public pesoLeite(int id, double pesagemLeite, String dataPeso, Animal animal) {
        this.id = id;
        this.pesagemLeite = pesagemLeite;
        this.dataPeso = dataPeso;
        this.animal = animal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPesagemLeite() {
        return pesagemLeite;
    }

    public void setPesagemLeite(double pesagemLeite) {
        this.pesagemLeite = pesagemLeite;
    }

    public String getDataPeso() {
        return dataPeso;
    }

    public void setDataPeso(String dataPeso) {
        this.dataPeso = dataPeso;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "pesoLeite{" + "id=" + id + ", pesagemLeite=" + pesagemLeite + ", dataPeso=" + dataPeso + ", animal=" + animal + '}';
    }
    
}
