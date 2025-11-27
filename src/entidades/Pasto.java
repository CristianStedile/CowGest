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
@Table(name = "pasto")
public class Pasto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    @Column(name = "tipo_grama")
    private String tipoGrama;
    @Column(name = "ultima_rocada")
    private LocalDate ultimaRocada;
    @Column(name = "ultima_sobsemeadura")
    private LocalDate ultimaSobSemadura;
    @Column(name = "ultima_adubacao")
    private LocalDate ultimaAdubacao;

    public Pasto() {

    }

    public Pasto(String nome, String tipoGrama) {
        this.nome = nome;
        this.tipoGrama = tipoGrama;
        this.ultimaAdubacao = null;
        this.ultimaRocada = null;
        this.ultimaSobSemadura = null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (!nome.equals("")) {
            this.nome = nome;
            System.out.println("Sucesso ao setar nome");
        } else {
            System.out.println("Erro ao setar nome");
        }
    }

    public String getTipoGrama() {
        return tipoGrama;
    }

    public void setTipoGrama(String tipoGrama) {
        if (!tipoGrama.equals("")) {
            this.tipoGrama = tipoGrama;
            System.out.println("Sucesso ao setar tipo de grama");
        } else {
            System.out.println("Erro ao setar tipo de grama");
        }
    }

    public LocalDate getUltimaRocada() {
        return ultimaRocada;
    }

    public void setUltimaRocada(LocalDate ultimaRocada) {
        if (ultimaRocada != null) {
            this.ultimaRocada = ultimaRocada;
            System.out.println("Sucesso ao setar última roçada");
        } else {
            System.out.println("Erro ao setar última roçada");
        }
    }

    public LocalDate getUltimaSobSemadura() {
        return ultimaSobSemadura;
    }

    public void setUltimaSobSemadura(LocalDate ultimaSobSemadura) {
        if (ultimaSobSemadura != null) {
            this.ultimaSobSemadura = ultimaSobSemadura;
            System.out.println("Sucesso ao setar última sob semeadura");
        } else {
            System.out.println("Erro ao setar última sob semeadura");
        }
    }

    public LocalDate getUltimaAdubacao() {
        return ultimaAdubacao;
    }

    public void setUltimaAdubacao(LocalDate ultimaAdubacao) {
        if (ultimaAdubacao != null) {
            this.ultimaAdubacao = ultimaAdubacao;
            System.out.println("Sucesso ao setar última adubação");
        } else {
            System.out.println("Erro ao setar última adubção");
        }
    }

    @Override
    public String toString() {
        return "Pasto{" + "id=" + id + ", nome=" + nome + ", tipoGrama=" + tipoGrama + ", ultimaRocada=" + ultimaRocada + ", ultimaSobSemadura=" + ultimaSobSemadura + ", ultimaAdubacao=" + ultimaAdubacao + '}';
    }

}
