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
    private String descricao;
    private String tipo;
    private String nome;
    private String dosagem;

    public Medicacao() {

    }

    public Medicacao(int id, String nome, String dosagem, String tipo, String descricao) {
        this.id = id;
        this.nome = nome;
        this.dosagem = dosagem;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (!descricao.equals("")) {
            this.descricao = descricao;
            System.out.println("Sucesso ao setar descrição");
        } else {
            System.out.println("Erro ao setar descrição");
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (!tipo.equals("")) {
            this.tipo = tipo;
            System.out.println("Sucesso ao setar tipo");
        } else {
            System.out.println("Erro ao setar tipo");
        }
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
