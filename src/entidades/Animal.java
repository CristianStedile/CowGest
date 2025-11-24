package entidades;

import java.io.Serializable;
import java.time.LocalDate;
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
    private int numero;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    private String raca;
    private String sexo;
    private String estado;
    private boolean baixado;
    @Column(name = "repeticoes_cio")
    private int repeticoesCio;
    private int carencia;

    public Animal() {

    }

    public Animal(int numero, LocalDate dataNascimento, String raca, String sexo) {
        this.raca = raca;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.numero = numero;
        this.estado = "Vazia";
        this.carencia = 0;
        this.repeticoesCio = 0;
        this.baixado = false;
    }
    
    public void setBaixado(){
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        if (String.valueOf(numero).length() == 6) {
            this.numero = numero;
            System.out.println("Sucesso ao setar número");
        } else {
            System.out.println("Erro ao setar número");
        }
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado.equals("Vazia") || estado.equals("Inseminada") || estado.equals("Recém parida") || estado.equals("Prenha")) {
            this.estado = estado;
            System.out.println("Sucesso ao setar estado");
        } else {
            System.out.println("Erro ao setar estado");
        }
    }

    public int getRepeticoesCio() {
        return repeticoesCio;
    }

    public void setRepeticoesCio(int repeticoesCio) {
        if (repeticoesCio >= 0) {
            this.repeticoesCio = repeticoesCio;
            System.out.println("Sucesso ao setar repetições de cio");
        } else {
            System.out.println("Erro ao setar repetições de cio");
        }
    }

    public int getCarencia() {
        return carencia;
    }

    public void setCarencia(int carencia) {
        if (carencia >= 0) {
            this.carencia = carencia;
            System.out.println("Sucesso ao setar carência");
        } else {
            System.out.println("Erro ao setar carência");
        }
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", numero=" + numero + ", dataNascimento=" + dataNascimento + ", raca=" + raca + ", sexo=" + sexo + ", estado=" + estado + ", repeticoesCio=" + repeticoesCio + ", carencia=" + carencia + '}';
    }

}
