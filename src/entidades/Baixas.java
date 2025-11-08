package entidades;

import java.io.Serializable;
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
    private String data;
    private String motivo;
    @OneToOne
    @JoinColumn(name = "id_animal")
    private Animal animal;
    
    public Baixas(){
    
    }

    public Baixas(String data, String motivo, Animal animal) {
        this.data = data;
        this.motivo = motivo;
        this.animal = animal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "Baixas{" + "id=" + id + ", data=" + data + ", motivo=" + motivo + ", animal=" + animal + '}';
    }

}
