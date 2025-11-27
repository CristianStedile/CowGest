package control;

import dao.DaoAnimal;
import dao.DaoPesagemLeite;
import entidades.Animal;
import entidades.PesagemLeite;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import model.ModelPeso;
import view.FCadPesoLeite;
import view.FConsPesoLeite;

public class ControlPeso {

    private FCadPesoLeite fCadPesoLeite;
    private DaoPesagemLeite daoPesoLeite;
    private DaoAnimal daoAnimal;
    private FConsPesoLeite fConsPesoLeite;
    private PesagemLeite pesoSelecionado;
    private ModelPeso modelPeso;
    private ControlPrincipal controlPrincipal;
    
    public ControlPeso(ControlPrincipal controlPrincipal) {
        this.controlPrincipal = controlPrincipal;
        this.fCadPesoLeite = new FCadPesoLeite(null, true);
        this.fConsPesoLeite = new FConsPesoLeite(null, true);
        this.modelPeso = new ModelPeso();  
        this.daoAnimal = new DaoAnimal();
        daoPesoLeite = new DaoPesagemLeite();
        inicializarComponentes();
    }
    
    public void inicializarComponentes() {
        fConsPesoLeite.PesoTabela.setModel(modelPeso);
        fCadPesoLeite.PesoOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gravarPeso();
            }
        });
        
        fConsPesoLeite.PesoExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
        
        fConsPesoLeite.PesoEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
        
        fCadPesoLeite.PesoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });
    }
    
    public void cancelar() {
        pesoSelecionado = null;
        limpar();
        fCadPesoLeite.setVisible(false);
    }
    
    public void limpar() {
        fCadPesoLeite.PesoData.setText("");
        fCadPesoLeite.PesoPesagem.setText("");
    }
    
    public void cadastrarPeso() {
        carregarAnimais();
        this.fCadPesoLeite.setVisible(true);
    }
    
    public void gravarPeso() {
        if (pesoSelecionado == null) {
            String dataPesagem = fCadPesoLeite.PesoData.getText();
            Animal a = daoAnimal.selecionar(String.valueOf(fCadPesoLeite.animais.getModel().getSelectedItem()));
            double pesagem = Double.parseDouble(fCadPesoLeite.PesoPesagem.getText().replace(",", "."));
            LocalDate dataConvertida = controlPrincipal.converterDataBanco(dataPesagem);
            PesagemLeite p = new PesagemLeite(pesagem, dataConvertida, a);
            if (daoPesoLeite.inserir(p)) {
                JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
                limpar();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao inserir!");
            }
        } else {
            pesoSelecionado.setPesagemLeite(Double.parseDouble(fCadPesoLeite.PesoPesagem.getText()));
            pesoSelecionado.setData(controlPrincipal.converterDataBanco(fCadPesoLeite.PesoData.getText()));
            Animal a = daoAnimal.selecionar(String.valueOf(fCadPesoLeite.animais.getModel().getSelectedItem()));
            pesoSelecionado.setAnimal(a);
            if(daoPesoLeite.editar(pesoSelecionado)){
            JOptionPane.showMessageDialog(null, "Editado com sucesso!");
            pesoSelecionado = null;
            limpar();
            fCadPesoLeite.setVisible(false);
            fConsPesoLeite.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "Erro ao editar!");
            }  
        }
    }
    
    public void editar(){
        int linhaSelecionada = fConsPesoLeite.PesoTabela.getSelectedRow();
        if(linhaSelecionada >= 0){
            if(JOptionPane.showConfirmDialog(null, "Deseja mesmo editar a pesagem?") == 0){
                carregarAnimais();
                pesoSelecionado = modelPeso.getPeso(linhaSelecionada);
                fCadPesoLeite.animais.setSelectedItem(pesoSelecionado.getAnimal().getNumero());
                fCadPesoLeite.PesoPesagem.setText(Double.toString((pesoSelecionado.getPeso())));
                fCadPesoLeite.PesoData.setText(controlPrincipal.converterDataBr(pesoSelecionado.getData()));
                fConsPesoLeite.setVisible(false);
                fCadPesoLeite.setVisible(true);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }
    
    public void excluir(){
        int linhaSelecionada = fConsPesoLeite.PesoTabela.getSelectedRow();
        if(linhaSelecionada >= 0){
            if(JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir a pesagem?") == 0){
                PesagemLeite p = modelPeso.getPeso(linhaSelecionada);
                if(daoPesoLeite.excluir(p)){
                    JOptionPane.showMessageDialog(null, "Pesagem excluída!");
                    modelPeso.ExcluirPeso(linhaSelecionada);
                }else{
                    JOptionPane.showMessageDialog(null, "Erro ao excluir!");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }
    
    public void consultarPesos() {
        carregarPeso();
        this.fConsPesoLeite.setVisible(true);
    }
    
    public void carregarPeso() {
        modelPeso.Limpar();
        for(PesagemLeite p : daoPesoLeite.listar()){
            modelPeso.InserirPeso(p);
        }
    }
    
    public void carregarAnimais(){
        fCadPesoLeite.animais.removeAllItems();
        for(Animal a : daoAnimal.listar()){
            fCadPesoLeite.animais.addItem(a.getNumero());
        }
    }
}
