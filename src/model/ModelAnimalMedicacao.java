package model;

import entidades.AnimalMedicacao;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelAnimalMedicacao extends AbstractTableModel {

    private List<AnimalMedicacao> aplicacoes = new ArrayList<>();

    public String converterDataBr(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataBr = data.format(formatter);
        return dataBr;
    }

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
        AnimalMedicacao am = aplicacoes.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return " " + am.getAnimal().getNumero();
            }
            case 1: {
                return " " + am.getMedicacao().getNome();
            }
            case 2: {
                return " " + converterDataBr(am.getData());
            }
        }
        return null;
    }

    public void Limpar() {
        aplicacoes.clear();
    }

    public AnimalMedicacao getAplicacao(int rowIndex) {
        return aplicacoes.get(rowIndex);
    }

    public void ExcluirAplicacao(int rowIndex) {
        aplicacoes.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void InserirAplicacao(AnimalMedicacao am) {
        aplicacoes.add(am);
        fireTableRowsInserted(aplicacoes.size() - 1, aplicacoes.size() - 1);
    }
}
