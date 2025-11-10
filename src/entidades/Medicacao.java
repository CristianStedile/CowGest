package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medicacao")
public class Medicacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String dosagem;

    public Medicacao() {

    }

    public Medicacao(int id, String nome, String dosagem) {
        this.id = id;
        this.nome = nome;
        this.dosagem = dosagem;
    }

    public String getNomeMedicacao() {
        return nome;
    }

    public void setNomeMedicacao(String nome) {
        if (!nome.equals("")) {
            this.nome = nome;
            System.out.println("Sucesso ao setar nome");
        } else {
            System.out.println("Erro ao setar nome");
        }
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        if (!dosagem.equals("")) {
            this.dosagem = dosagem;
            System.out.println("Sucesso ao setar dosagem");
        } else {
            System.out.println("Erro ao setar dosagem");
        }
    }

    @Override
    public String toString() {
        return "Medicacao{" + "id=" + id + ", nome=" + nome + ", dosagem=" + dosagem + '}';
    }

}
