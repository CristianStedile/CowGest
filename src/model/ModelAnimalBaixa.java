package model;

import entidades.Baixas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelAnimalBaixa extends AbstractTableModel {

    private List<Baixas> baixas = new ArrayList<>();

    @Override
    public int getRowCount() {
        return baixas.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: {
                return "Número do Animal";
            }
            case 1: {
                return "Data da Baixa";
            }
            case 2: {
                return "Motivo";
            }
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Baixas b = baixas.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return " " + b.getAnimal().getNumeroAnimal();
            }
            case 1: {
                return " " + b.getData();
            }
            case 2: {
                return " " + b.getMotivo();
            }
        }
        return null;
    }

    public void Limpar() {
        baixas.clear();
    }

    public Baixas getBaixa(int rowIndex) {
        return baixas.get(rowIndex);
    }

    public void ExcluirBaixa(int rowIndex) {
        baixas.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void InserirBaixa(Baixas b) {
        baixas.add(b);
        fireTableRowsInserted(baixas.size() - 1, baixas.size() - 1);
    }
}
