package model;

import entidades.Baixa;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelAnimalBaixa extends AbstractTableModel {

    private List<Baixa> baixas = new ArrayList<>();

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
        Baixa b = baixas.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return " " + b.getAnimal().getNumero();
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

    public Baixa getBaixa(int rowIndex) {
        return baixas.get(rowIndex);
    }

    public void ExcluirBaixa(int rowIndex) {
        baixas.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void InserirBaixa(Baixa b) {
        baixas.add(b);
        fireTableRowsInserted(baixas.size() - 1, baixas.size() - 1);
    }
}
