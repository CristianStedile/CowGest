package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "animal")
public class Animal implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numeroAnimal;
    private String dataNascAnimal;
    private String especieAnimal;
    private String sexoAnimal;
    private int baixado;

    public Animal(){
    
    }
    
    public Animal(int id, int numeroAnimal, String dataNascAnimal, String especieAnimal, String sexoAnimal, int baixado) {
        this.id = id;
        this.numeroAnimal = numeroAnimal;
        this.dataNascAnimal = dataNascAnimal;
        this.especieAnimal = especieAnimal;
        this.sexoAnimal = sexoAnimal;
        this.baixado = baixado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroAnimal() {
        return numeroAnimal;
    }

    public void setNumeroAnimal(int numeroAnimal) {
        this.numeroAnimal = numeroAnimal;
    }

    public String getDataNascAnimal() {
        return dataNascAnimal;
    }

    public void setDataNascAnimal(String dataNascAnimal) {
        this.dataNascAnimal = dataNascAnimal;
    }

    public String getEspecieAnimal() {
        return especieAnimal;
    }

    public void setEspecieAnimal(String especieAnimal) {
        this.especieAnimal = especieAnimal;
    }

    public String getSexoAnimal() {
        return sexoAnimal;
    }

    public void setSexoAnimal(String sexoAnimal) {
        this.sexoAnimal = sexoAnimal;
    }

    public int getBaixado() {
        return baixado;
    }

    public void setBaixado(int baixado) {
        this.baixado = baixado;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", numeroAnimal=" + numeroAnimal + ", dataNascAnimal=" + dataNascAnimal + ", especieAnimal=" + especieAnimal + ", sexoAnimal=" + sexoAnimal + ", baixado=" + baixado + '}';
    }
    
    
}
