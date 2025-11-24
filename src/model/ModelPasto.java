package model;

import entidades.Pasto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelPasto extends AbstractTableModel{
    private List<Pasto> pastos = new ArrayList<>();

    @Override
    public int getRowCount() {
        return pastos.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    
    @Override
    public String getColumnName(int column){
        switch (column){
            case 0:{
                return "Nome";
            }
            case 1:{
                return "Tipo de grama";
            }
            case 2:{
                return "Última roçada";
            }
            case 3:{
                return "Última adubação";
            }
            case 4:{
                return "Última sob-semeadura";
            }
        }
        return "";
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pasto p = pastos.get(rowIndex);
        switch(columnIndex){
            case 0:{
                return " "+p.getNome();
            }
            case 1:{
                return " "+p.getTipoGrama();
            }
            case 2:{
                return " "+p.getUltimaRocada();
            }
            case 3:{
                return " "+p.getUltimaAdubacao();
            }
            case 4:{
                return " "+p.getUltimaSobSemadura();
            }
        }
        return null;
    }
    
    public void Limpar(){
        pastos.clear();
    }
    
    public Pasto getPasto(int rowIndex){
        return pastos.get(rowIndex);
    }
    
    public void excluirPasto(int rowIndex){
        pastos.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    public void inserirPasto(Pasto p){
        pastos.add(p);
        fireTableRowsInserted(pastos.size()-1, pastos.size()-1);
    }
}
