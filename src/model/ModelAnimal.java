package model;

import entidades.Animal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelAnimal extends AbstractTableModel{
    private List<Animal> animais = new ArrayList<>();

    @Override
    public int getRowCount() {
        return animais.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    
    @Override
    public String getColumnName(int column){
        switch (column){
            case 0:{
                return "Número do Animal";
            }
            case 1:{
                return "Data de Nascimento";
            }
            case 2:{
                return "Espécie";
            }
            case 3:{
                return "Sexo";
            }
        }
        return "";
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Animal a = animais.get(rowIndex);
        switch(columnIndex){
            case 0:{
                return " "+a.getNumeroAnimal();
            }
            case 1:{
                return " "+a.getDataNascAnimal();
            }
            case 2:{
                return " "+a.getEspecieAnimal();
            }
            case 3:{
                return " "+a.getSexoAnimal();
            }
        }
        return null;
    }
    
    public void Limpar(){
        animais.clear();
    }
    
    public Animal getAnimal(int rowIndex){
        return animais.get(rowIndex);
    }
    
    public void ExcluirAnimal(int rowIndex){
        animais.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    public void InserirAnimal(Animal a){
        animais.add(a);
        fireTableRowsInserted(animais.size()-1, animais.size()-1);
    }
}
