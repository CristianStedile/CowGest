package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import view.FPrincipal;

public class ControlPrincipal {

    private FPrincipal fPrincipal;
    private ControlAnimal controlAnimal;
    private ControlAnimalBaixa controlAnimalBaixa;
    private ControlPeso controlPeso;
    private ControlAnimalMedicacao controlAnimalMedicacao;
    private ControlInseminacao controlInseminacao;
    private ControlPasto controlPasto;
    private ControlMedicacao controlMedicacao;
    private ControlSemen controlSemen;
    private ControlLeiteVendido controlLeiteVendido;

    public ControlPrincipal() {
        fPrincipal = new FPrincipal();
        controlAnimal = new ControlAnimal(this);
        controlPeso = new ControlPeso(this);
        controlMedicacao = new ControlMedicacao();
        controlAnimalBaixa = new ControlAnimalBaixa();
        controlPasto = new ControlPasto();
        controlInseminacao = new ControlInseminacao(this);
        controlAnimalMedicacao = new ControlAnimalMedicacao(this);
        controlSemen = new ControlSemen();
        controlLeiteVendido = new ControlLeiteVendido();
        inicializarComponentes();
    }

    public void executar() {
        fPrincipal.setVisible(true);
    }

    public void inicializarComponentes() {
        fPrincipal.miCadAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlAnimal.cadastrarAnimal();
            }
        });

        fPrincipal.miInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlAnimal.consultarAnimal();
            }
        });

        fPrincipal.miBaixas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlAnimalBaixa.consultarAnimal();
            }
        });

        fPrincipal.miAplicações.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlAnimalMedicacao.consultarAplicacoes();
            }
        });

        fPrincipal.miCadAplicação.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlAnimalMedicacao.cadastrarAplicacao();
            }
        });

        fPrincipal.miCadInseminacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlInseminacao.cadastrarInseminacao();
            }
        });

        fPrincipal.miInseminacoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlInseminacao.consultarInseminacoes();
            }
        });

        fPrincipal.miSemens.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlSemen.consultarSemen();
            }
        });

        fPrincipal.miCadSemen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlSemen.cadastrarSemen();
            }
        });

        fPrincipal.miCadPesagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlPeso.cadastrarPeso();
            }
        });

        fPrincipal.miPesagens.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlPeso.consultarPesos();
            }
        });

        fPrincipal.miCadMedicacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlMedicacao.cadastrarMedicacao();
            }
        });

        fPrincipal.miMedicacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlMedicacao.consultarMedicacao();
            }
        });

        fPrincipal.miCadPasto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlPasto.cadastrarPasto();
            }
        });

        fPrincipal.miPastos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlPasto.consultarPasto();
            }
        });

    }

    public LocalDate converterDataBanco(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataFormatada = LocalDate.parse(data, formatter);
        return dataFormatada;
    }

    public String converterDataBr(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataBr = data.format(formatter);
        return dataBr;
    }
}
