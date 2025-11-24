package control;

import dao.DaoAnimal;
import dao.DaoBaixas;
import entidades.Animal;
import entidades.Baixas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ModelAnimalBaixa;
import view.FCadBaixa;
import view.FConsAnimalBaixa;

public class ControlAnimalBaixa {

    private FCadBaixa fCadBaixa;
    private DaoAnimal daoAnimal;
    private DaoBaixas daoBaixa;
    private FConsAnimalBaixa fConsAnimal;
    private Baixas baixaSelecionada;
    private ModelAnimalBaixa modelAnimal;

    public ControlAnimalBaixa() {
        this.fCadBaixa = new FCadBaixa(null, true);
        this.fConsAnimal = new FConsAnimalBaixa(null, true);
        this.modelAnimal = new ModelAnimalBaixa();
        daoAnimal = new DaoAnimal();
        daoBaixa = new DaoBaixas();
        inicializarComponentes();
    }

    public void cadastrarBaixa() {
        this.fCadBaixa.setVisible(true);
    }

    public void inicializarComponentes() {
        fConsAnimal.baixaTabela.setModel(modelAnimal);
        fCadBaixa.BaixaCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });
    }

    public void cancelar() {
        baixaSelecionada = null;
        limpar();
        fCadBaixa.setVisible(false);
    }

    public void limpar() {
        fCadBaixa.BaixaData.setText("");
        fCadBaixa.BaixaMotivo.setText("");
    }

    public void consultarAnimal() {
        carregarAnimal();
        this.fConsAnimal.setVisible(true);
    }

    public void carregarAnimal() {
        modelAnimal.Limpar();
        for (Baixas b : daoBaixa.listar()) {
            modelAnimal.InserirBaixa(b);
        }
    }
    
    public void carregarAnimais(){
        fCadBaixa.animais.removeAllItems();
        for(Animal a : daoAnimal.listar()){
            fCadBaixa.animais.addItem(Integer.toString(a.getNumero()));
        }
    }

}
