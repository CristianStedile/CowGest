package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "leitevendido")
public class LeiteVendido implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double leiteVendido;
    private int dia;
    private int mes;
    private int ano;
    
    public LeiteVendido(){
    
    }

    public LeiteVendido(int id, double leiteVendido, int dia, int mes, int ano) {
        this.id = id;
        this.leiteVendido = leiteVendido;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLeiteVendido() {
        return leiteVendido;
    }

    public void setLeiteVendido(double leiteVendido) {
        this.leiteVendido = leiteVendido;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "leiteVendido{" + "id=" + id + ", leiteVendido=" + leiteVendido + ", dia=" + dia + ", mes=" + mes + ", ano=" + ano + '}';
    }

    
}
