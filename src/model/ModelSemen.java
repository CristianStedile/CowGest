package model;

import entidades.Semen;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelSemen extends AbstractTableModel {

    private List<Semen> semens = new ArrayList<>();

    @Override
    public int getRowCount() {
        return semens.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: {
                return "Touro";
            }
            case 1: {
                return "Doses";
            }
            case 2: {
                return "Pote";
            }
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Semen s = semens.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return " " + s.getReprodutor();
            }
            case 1: {
                return " " + s.getDoses();
            }
            case 2: {
                return " " + s.getPote();
            }
        }
        return null;
    }

    public void Limpar() {
        semens.clear();
    }

    public Semen getSemen(int rowIndex) {
        return semens.get(rowIndex);
    }

    public void ExcluirSemen(int rowIndex) {
        semens.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void InserirSemen(Semen s) {
        semens.add(s);
        fireTableRowsInserted(semens.size() - 1, semens.size() - 1);
    }
}
