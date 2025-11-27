package model;

import control.ControlPrincipal;
import entidades.Inseminacao;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelInseminacao extends AbstractTableModel {

    private List<Inseminacao> inseminacoes = new ArrayList<>();

    public String converterDataBr(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataBr = data.format(formatter);
        return dataBr;
    }

    @Override
    public int getRowCount() {
        return inseminacoes.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
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
            case 3: {
                return "Data de secagem";
            }
            case 4: {
                return "Data de pré parto";
            }
            case 5: {
                return "Data de parto";
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
                return " " + converterDataBr(i.getData());
            }
            case 3: {
                return " " + converterDataBr(i.getSecagem());
            }
            case 4: {
                return " " + converterDataBr(i.getPreParto());
            }
            case 5: {
                return " " + converterDataBr(i.getParto());
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
