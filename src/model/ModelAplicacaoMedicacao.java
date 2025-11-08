package model;

import entidades.aplicacaoMedicacao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelAplicacaoMedicacao extends AbstractTableModel {

    private List<aplicacaoMedicacao> aplicacoes = new ArrayList<>();

    @Override
    public int getRowCount() {
        return aplicacoes.size();
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
                return "Nome da Medicação";
            }
            case 2: {
                return "Data da Aplicação";
            }
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        aplicacaoMedicacao am = aplicacoes.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return " "+am.getAnimal().getNumeroAnimal();
            }
            case 1: {
                return " "+am.getMedicacao().getNomeMedicacao();
            }
            case 2: {
                return " "+am.getDataAplicacao();
            }
        }
        return null;
    }

    public void Limpar() {
        aplicacoes.clear();
    }

    public aplicacaoMedicacao getAplicacao(int rowIndex) {
        return aplicacoes.get(rowIndex);
    }

    public void ExcluirAplicacao(int rowIndex) {
        aplicacoes.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void InserirAplicacao(aplicacaoMedicacao am) {
        aplicacoes.add(am);
        fireTableRowsInserted(aplicacoes.size() - 1, aplicacoes.size() - 1);
    }
}
