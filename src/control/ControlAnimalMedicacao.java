package control;

import dao.DaoAnimal;
import dao.DaoAnimalMedicacao;
import dao.DaoMedicacao;
import entidades.Animal;
import entidades.Medicacao;
import entidades.AnimalMedicacao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import model.ModelAnimalMedicacao;
import view.FCadAplicacaoMedicacao;
import view.FConsAplicacaoMedicacao;

public class ControlAnimalMedicacao {

    private FCadAplicacaoMedicacao fCadAplicacaoMedicacao;
    private DaoAnimalMedicacao daoAplicacaoMedicacao;
    private FConsAplicacaoMedicacao fConsAplicacaoMedicacao;
    private AnimalMedicacao aplicacaoSelecionada;
    private ModelAnimalMedicacao modelAplicacaoMedicacao;
    private DaoAnimal daoAnimal;
    private DaoMedicacao daoMedicacao;
    private ControlPrincipal controlPrincipal;

    public ControlAnimalMedicacao(ControlPrincipal controlPrincipal) {
        this.controlPrincipal = controlPrincipal;
        this.fCadAplicacaoMedicacao = new FCadAplicacaoMedicacao(null, true);
        this.fConsAplicacaoMedicacao = new FConsAplicacaoMedicacao(null, true);
        this.modelAplicacaoMedicacao = new ModelAnimalMedicacao();
        this.daoAnimal = new DaoAnimal();
        this.daoMedicacao = new DaoMedicacao();
        daoAplicacaoMedicacao = new DaoAnimalMedicacao();
        inicializarComponentes();
    }

    public void inicializarComponentes() {
        fConsAplicacaoMedicacao.AplicacaoTabela.setModel(modelAplicacaoMedicacao);
        fCadAplicacaoMedicacao.AMedicacaoOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gravarAplicacao();
            }
        });
        fConsAplicacaoMedicacao.AplicacaoExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
        fConsAplicacaoMedicacao.AplicacaoEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
        fCadAplicacaoMedicacao.AMedicacaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });
    }

    public void cancelar() {
        aplicacaoSelecionada = null;
        limpar();
        fCadAplicacaoMedicacao.setVisible(false);
    }

    public void limpar() {
        fCadAplicacaoMedicacao.AplicacaoData.setText("");
    }

    public void cadastrarAplicacao() {
        carregarAnimais();
        carregarMedicacoes();
        carregarAplicacoes();
        this.fCadAplicacaoMedicacao.setVisible(true);
    }

    public void gravarAplicacao() {
        if (aplicacaoSelecionada == null) {
            LocalDate data = controlPrincipal.converterDataBanco(fCadAplicacaoMedicacao.AplicacaoData.getText());
            Animal a = daoAnimal.selecionar(Integer.parseInt((String) fCadAplicacaoMedicacao.AplicacaoNumero.getModel().getSelectedItem()));
            Medicacao m = daoMedicacao.selecionar((String) fCadAplicacaoMedicacao.AplicacaoNome.getModel().getSelectedItem());
            AnimalMedicacao am = new AnimalMedicacao(0, data, a, m);
            if (daoAplicacaoMedicacao.inserir(am)) {
                JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
                limpar();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao inserir!");
            }
        } else {
            aplicacaoSelecionada.setAnimal((Animal) fCadAplicacaoMedicacao.AplicacaoNumero.getModel().getSelectedItem());
            aplicacaoSelecionada.setMedicacao((Medicacao) fCadAplicacaoMedicacao.AplicacaoNome.getModel().getSelectedItem());
            aplicacaoSelecionada.setData(controlPrincipal.converterDataBanco(fCadAplicacaoMedicacao.AplicacaoData.getText()));
            if (daoAplicacaoMedicacao.editar(aplicacaoSelecionada)) {
                JOptionPane.showMessageDialog(null, "Editado com sucesso!");
                limpar();
                aplicacaoSelecionada = null;
                fCadAplicacaoMedicacao.setVisible(false);
                fConsAplicacaoMedicacao.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao editar!");
            }
        }
    }

    public void editar() {
        int linhaSelecionada = fConsAplicacaoMedicacao.AplicacaoTabela.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo editar a aplicação de medicação?") == 0) {
                carregarAnimais();
                carregarMedicacoes();
                aplicacaoSelecionada = modelAplicacaoMedicacao.getAplicacao(linhaSelecionada);
                fConsAplicacaoMedicacao.setVisible(false);
                fCadAplicacaoMedicacao.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }

    public void excluir() {
        int linhaSelecionada = fConsAplicacaoMedicacao.AplicacaoTabela.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir a aplicação da medicação?") == 0) {
                AnimalMedicacao am = modelAplicacaoMedicacao.getAplicacao(linhaSelecionada);
                if (daoAplicacaoMedicacao.remover(am)) {
                    JOptionPane.showMessageDialog(null, "Aplicação excluída");
                    modelAplicacaoMedicacao.ExcluirAplicacao(linhaSelecionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }

    public void consultarAplicacoes() {
        carregarAplicacoes();
        this.fConsAplicacaoMedicacao.setVisible(true);
    }

    public void carregarAplicacoes() {
        modelAplicacaoMedicacao.Limpar();
        for (AnimalMedicacao ap : daoAplicacaoMedicacao.listar()) {
            modelAplicacaoMedicacao.InserirAplicacao(ap);
        }
    }

    public void carregarAnimais() {
        fCadAplicacaoMedicacao.AplicacaoNumero.removeAllItems();
        for (Animal a : daoAnimal.listar()) {
            fCadAplicacaoMedicacao.AplicacaoNumero.addItem(Integer.toString(a.getNumero()));
        }
    }

    public void carregarMedicacoes() {
        fCadAplicacaoMedicacao.AplicacaoNome.removeAllItems();
        for (Medicacao m : daoMedicacao.listar()) {
            fCadAplicacaoMedicacao.AplicacaoNome.addItem(m.getNome());
        }
    }
}
