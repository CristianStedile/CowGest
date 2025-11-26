package model;

import entidades.Inseminacao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelInseminacao extends AbstractTableModel {

    private List<Inseminacao> inseminacoes = new ArrayList<>();

    @Override
    public int getRowCount() {
        return inseminacoes.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: {
                return "Animal";
            }
            case 1: {
                return "Touro";
            }
            case 2: {
                return "Data";
            }
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Inseminacao i = inseminacoes.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return " " + i.getAnimal().getNumero();
            }
            case 1: {
                return " " + i.getSemen().getTouro();
            }
            case 2: {
                return " " + i.getData();
            }
        }
        return null;
    }

    public void Limpar() {
        inseminacoes.clear();
    }

    public Inseminacao getInseminacao(int rowIndex) {
        return inseminacoes.get(rowIndex);
    }

    public void ExcluirInseminacao(int rowIndex) {
        inseminacoes.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void InserirInseminacao(Inseminacao i) {
        inseminacoes.add(i);
        fireTableRowsInserted(inseminacoes.size() - 1, inseminacoes.size() - 1);
    }
}
