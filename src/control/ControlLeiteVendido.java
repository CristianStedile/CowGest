package control;

import dao.DaoLeiteVendido;
import entidades.LeiteVendido;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.ModelLeiteVendido;
import view.FCadFiltrar;
import view.FCadLeiteVendido;
import view.FConsLeiteVendido;

public class ControlLeiteVendido {

    private FCadLeiteVendido fCadLeiteVendido;
    private DaoLeiteVendido daoLeiteVendido;
    private FConsLeiteVendido fConsLeiteVendido;
    private FCadFiltrar fCadFiltrar;
    private LeiteVendido leiteSelecionado;
    private ModelLeiteVendido modelLeiteVendido;

    public ControlLeiteVendido() {
        this.fCadLeiteVendido = new FCadLeiteVendido(null, true);
        this.fConsLeiteVendido = new FConsLeiteVendido(null, true);
        this.fCadFiltrar = new FCadFiltrar(null, true);
        this.modelLeiteVendido = new ModelLeiteVendido();
        daoLeiteVendido = new DaoLeiteVendido();
        inicializarComponentes();
    }

    public void inicializarComponentes() {
        fConsLeiteVendido.LeiteTabela.setModel(modelLeiteVendido);
        fCadLeiteVendido.LeiteOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gravarLeiteVendido();
            }
        });
        fConsLeiteVendido.LeiteEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
        fCadLeiteVendido.LeiteCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });
        fConsLeiteVendido.leiteFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrar();
            }
        });
        fCadFiltrar.filtrarOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarMes();
            }
        });
        fCadFiltrar.filtrarCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar2();
            }
        });
    }
    
    public void cancelar2(){
        fCadFiltrar.mes.setText("");
    }
    
    public void filtrar(){
        fCadFiltrar.setVisible(true);
    }
    
    public void cancelar() {
        leiteSelecionado = null;
        limpar();
        fCadLeiteVendido.setVisible(false);
    }

    public void limpar() {
        fCadLeiteVendido.Litros.setText("");
        fCadLeiteVendido.dia.setText("");
        fCadLeiteVendido.ano.setText("");
        fCadLeiteVendido.mes.setText("");
    }

    public void cadastrarLeiteVendido() {
        this.fCadLeiteVendido.setVisible(true);
    }
    
    public void filtrarMes(){
        int mes = Integer.parseInt(fCadFiltrar.mes.getText());
        modelLeiteVendido.Limpar();
        for(LeiteVendido lv : daoLeiteVendido.listarMes(mes)){
            modelLeiteVendido.InserirLeiteVendido(lv);
            fCadFiltrar.setVisible(false);
            cancelar2();
        }
    }
    
    public void carregarLeiteVendido(){
        modelLeiteVendido.Limpar();
        for(LeiteVendido lv : daoLeiteVendido.listar()){
            modelLeiteVendido.InserirLeiteVendido(lv);
        }
    }
    
    public void consultarLeiteVendido(){
        carregarLeiteVendido();
        this.fConsLeiteVendido.setVisible(true);
    }

    public void gravarLeiteVendido() {
        if (leiteSelecionado == null) {
            double Litros = Double.parseDouble(fCadLeiteVendido.Litros.getText().replace(",", "."));
            int dia = Integer.parseInt(fCadLeiteVendido.dia.getText());
            int mes = Integer.parseInt(fCadLeiteVendido.mes.getText());
            int ano = Integer.parseInt(fCadLeiteVendido.ano.getText());
            LeiteVendido lv = new LeiteVendido(0, Litros, dia, mes, ano);
            if (daoLeiteVendido.inserir(lv)) {
                JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
                limpar();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao inserir!");
            }
        } else {
            leiteSelecionado.setLeiteVendido(Double.parseDouble(fCadLeiteVendido.Litros.getText().replace(",", ".")));
            leiteSelecionado.setDia(Integer.parseInt(fCadLeiteVendido.dia.getText()));
            leiteSelecionado.setMes(Integer.parseInt(fCadLeiteVendido.mes.getText()));
            leiteSelecionado.setAno(Integer.parseInt(fCadLeiteVendido.ano.getText()));
            if (daoLeiteVendido.editar(leiteSelecionado)) {
                JOptionPane.showMessageDialog(null, "Editado com sucesso!");
                leiteSelecionado = null;
                limpar();
                fCadLeiteVendido.setVisible(false);
                fConsLeiteVendido.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao editar!");
            }
        }
    }

    public void editar() {
        int linhaSelecionada = fConsLeiteVendido.LeiteTabela.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo editar a venda de leite?") == 0) {
                leiteSelecionado = modelLeiteVendido.getLeiteVendido(linhaSelecionada);
                fCadLeiteVendido.Litros.setText(Double.toString((leiteSelecionado.getLeiteVendido())));
                fCadLeiteVendido.dia.setText(Integer.toString(leiteSelecionado.getDia()));
                fCadLeiteVendido.mes.setText(Integer.toString(leiteSelecionado.getMes()));
                fCadLeiteVendido.ano.setText(Integer.toString(leiteSelecionado.getAno()));
                fConsLeiteVendido.setVisible(false);
                fCadLeiteVendido.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }
}
