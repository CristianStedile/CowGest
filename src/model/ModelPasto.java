package model;

import entidades.Pasto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelPasto extends AbstractTableModel {

    private List<Pasto> pastos = new ArrayList<>();

    public String converterDataBr(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataBr = data.format(formatter);
        return dataBr;
    }

    @Override
    public int getRowCount() {
        return pastos.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: {
                return "Nome";
            }
            case 1: {
                return "Tipo de grama";
            }
            case 2: {
                return "Última roçada";
            }
            case 3: {
                return "Última adubação";
            }
            case 4: {
                return "Última sob-semeadura";
            }
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pasto p = pastos.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return " " + p.getNome();
            }
            case 1: {
                return " " + p.getTipoGrama();
            }
            case 2: {
                if (p.getUltimaRocada() != null) {
                    return " " + converterDataBr(p.getUltimaRocada());
                } else {
                    return "Nenhuma roçada";
                }
            }
            case 3: {
                if (p.getUltimaAdubacao() != null) {
                    return " " + converterDataBr(p.getUltimaAdubacao());
                } else {
                    return "Nenhuma adubação";
                }
            }
            case 4: {
                if (p.getUltimaSobSemadura() != null) {
                    return " " + converterDataBr(p.getUltimaSobSemadura());
                } else {
                    return "Nenhuma sob semeadura";
                }
            }
        }
        return null;
    }

    public void Limpar() {
        pastos.clear();
    }

    public Pasto getPasto(int rowIndex) {
        return pastos.get(rowIndex);
    }

    public void excluirPasto(int rowIndex) {
        pastos.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void inserirPasto(Pasto p) {
        pastos.add(p);
        fireTableRowsInserted(pastos.size() - 1, pastos.size() - 1);
    }
}
