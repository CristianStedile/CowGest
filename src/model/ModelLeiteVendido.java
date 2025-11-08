package model;

import entidades.LeiteVendido;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelLeiteVendido extends AbstractTableModel{

    private List<LeiteVendido> leiteVendido = new ArrayList<>();
    
    @Override
    public int getRowCount() {
        return leiteVendido.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:{
                return "Litros";
            }
            case 1:{
                return "Dia";
            }
            case 2:{
                return "Mês";
            }
            case 3:{
                return "Ano";
            }
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LeiteVendido lv = leiteVendido.get(rowIndex);
        switch(columnIndex){
            case 0:{
                return " "+lv.getLeiteVendido();
            }
            case 1:{
                return " "+lv.getDia();
            }
            case 2:{
                return " "+lv.getMes();
            }
            case 3:{
                return " "+lv.getAno();
            }
        }
        return null;
    }
    
    public void Limpar(){
        leiteVendido.clear();
    }
    
    public LeiteVendido getLeiteVendido(int rowIndex){
        return leiteVendido.get(rowIndex);
    }
    
    public void ExcluirLeiteVendido(int rowIndex){
        leiteVendido.remove(rowIndex);
    }
    
    public void InserirLeiteVendido(LeiteVendido lv){
        leiteVendido.add(lv);
        fireTableRowsInserted(leiteVendido.size()-1, leiteVendido.size()-1);
    }
}
