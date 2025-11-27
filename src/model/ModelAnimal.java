package model;

import entidades.Animal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelAnimal extends AbstractTableModel {

    private List<Animal> animais = new ArrayList<>();

    public String converterDataBr(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataBr = data.format(formatter);
        return dataBr;
    }

    @Override
    public int getRowCount() {
        return animais.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: {
                return "Número do Animal";
            }
            case 1: {
                return "Data de Nascimento";
            }
            case 2: {
                return "Raça";
            }
            case 3: {
                return "Sexo";
            }
            case 4: {
                return "Estado";
            }
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Animal a = animais.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return " " + a.getNumero();
            }
            case 1: {
                return " " + converterDataBr(a.getDataNascimento());
            }
            case 2: {
                return " " + a.getRaca();
            }
            case 3: {
                return " " + a.getSexo();
            }
            case 4: {
                return a.getEstado();
            }
        }
        return null;
    }

    public void Limpar() {
        animais.clear();
    }

    public Animal getAnimal(int rowIndex) {
        return animais.get(rowIndex);
    }

    public void ExcluirAnimal(int rowIndex) {
        animais.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void InserirAnimal(Animal a) {
        animais.add(a);
        fireTableRowsInserted(animais.size() - 1, animais.size() - 1);
    }
}
