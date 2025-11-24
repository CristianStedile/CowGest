package control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ControlPrincipal {

    public LocalDate converterDataBanco(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataFormatada = LocalDate.parse(data, formatter);
        return dataFormatada;
    }
    
    public String converterDataBr(LocalDate data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataBr = data.format(formatter);
        return dataBr;
    }
}
