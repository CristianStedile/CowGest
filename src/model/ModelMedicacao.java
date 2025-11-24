package model;

import entidades.Medicacao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelMedicacao extends AbstractTableModel {

    private List<Medicacao> medicacoes = new ArrayList<>();

    @Override
    public int getRowCount() {
        return medicacoes.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: {
                return "Nome da Medicação";
            }
            case 1: {
                return "Dosagem da Medicação";
            }
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Medicacao m = medicacoes.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return " "+m.getNome();
            }
            case 1: {
                return " "+m.getDosagem();
            }
        }
        return null;
    }

    public void Limpar() {
        medicacoes.clear();
    }

    public Medicacao getMedicacao(int rowIndex) {
        return medicacoes.get(rowIndex);
    }

    public void ExcluirMedicacao(int rowIndex) {
        medicacoes.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void InserirMedicacao(Medicacao m) {
        medicacoes.add(m);
        fireTableRowsInserted(medicacoes.size()-1, medicacoes.size()-1);
    }
}
