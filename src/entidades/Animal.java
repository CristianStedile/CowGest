package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "animal")
public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numero;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    private String raca;
    private String sexo;
    private boolean baixado;

    public Animal() {

    }

    public Animal(String numero, LocalDate dataNascimento, String raca, String sexo) {
        this.raca = raca;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.numero = numero;
        this.baixado = false;
    }

    public void setBaixado() {
        this.baixado = true;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getIdade() {
        Period periodo = Period.between(this.dataNascimento, LocalDate.now());
        int anos = periodo.getYears();
        int meses = periodo.getMonths();
        return anos + " anos e " + meses + " meses";
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if (String.valueOf(numero).length() == 6) {
            this.numero = numero;
            System.out.println("Sucesso ao setar número");
        } else {
            System.out.println("Erro ao setar número");
        }
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", numero=" + numero + ", dataNascimento=" + dataNascimento + ", raca=" + raca + ", sexo=" + sexo + ", baixado=" + baixado + '}';
    }

}
