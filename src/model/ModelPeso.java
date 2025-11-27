package model;

import entidades.PesagemLeite;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelPeso extends AbstractTableModel {

    private List<PesagemLeite> pesos = new ArrayList<>();

    public String converterDataBr(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataBr = data.format(formatter);
        return dataBr;
    }

    @Override
    public int getRowCount() {
        return pesos.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
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
            case 3: {
                return "Quantidade de Ração";
            }
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PesagemLeite p = pesos.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return " " + p.getAnimal().getNumero();
            }
            case 1: {
                return " " + p.getPeso();
            }
            case 2: {
                return " " + converterDataBr(p.getData());
            }
            case 3: {
                return " " + p.getRacao();
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
