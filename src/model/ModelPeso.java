package model;

import entidades.PesagemLeite;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelPeso extends AbstractTableModel {

    private List<PesagemLeite> pesos = new ArrayList<>();

    @Override
    public int getRowCount() {
        return pesos.size();
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
                return "Pesagem";
            }
            case 2: {
                return "Data da Pesagem";
            }
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PesagemLeite p = pesos.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return " " + p.getAnimal().getNumeroAnimal();
            }
            case 1: {
                return " " + p.getPesagemLeite();
            }
            case 2: {
                return " " + p.getDataPeso();
            }
        }
        return null;
    }

    public void Limpar() {
        pesos.clear();
    }

    public PesagemLeite getPeso(int rowIndex) {
        return pesos.get(rowIndex);
    }

    public void ExcluirPeso(int rowIndex) {
        pesos.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void InserirPeso(PesagemLeite p) {
        pesos.add(p);
        fireTableRowsInserted(pesos.size() - 1, pesos.size() - 1);
    }
}
