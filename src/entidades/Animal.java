package entidades;

import java.io.Serializable;
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
    private String estado;
    @Column(name = "repeticoes_cio")
    private int repeticoesCio;
    private int carencia;

    public Animal() {

    }

    public Animal(int numero) {
        this.numero = numero;
        this.estado = "Vazia";
        this.carencia = 0;
        this.repeticoesCio = 0;
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
        return "Animal{" + "id=" + id + ", numero=" + numero + ", estado=" + estado + ", repeticoesCio=" + repeticoesCio + ", carencia=" + carencia + '}';
    }

}
