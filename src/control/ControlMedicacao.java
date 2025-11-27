package control;

import dao.DaoMedicacao;
import entidades.Medicacao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.ModelMedicacao;
import view.FCadMedicacao;
import view.FConsMedicacao;

public class ControlMedicacao {

    private FCadMedicacao fCadMedicacao;
    private DaoMedicacao daoMedicacao;
    private FConsMedicacao fConsMedicacao;
    private Medicacao medicacaoSelecionada;
    private ModelMedicacao modelMedicacao;

    public ControlMedicacao() {
        this.fCadMedicacao = new FCadMedicacao(null, true);
        this.fConsMedicacao = new FConsMedicacao(null, true);
        this.modelMedicacao = new ModelMedicacao();
        daoMedicacao = new DaoMedicacao();
        inicializarComponentes();
    }

    public void cadastrarMedicacao() {
        carregarMedicacao();
        this.fCadMedicacao.setVisible(true);
    }

    public void inicializarComponentes() {
        fConsMedicacao.MedicacaoTabela.setModel(modelMedicacao);
        fCadMedicacao.MedicacaoOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gravarMedicacao();
            }
        });

        fConsMedicacao.MedicacaoExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });

        fConsMedicacao.MedicacaoEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });

        fCadMedicacao.MedicacaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });
    }

    public void cancelar() {
        medicacaoSelecionada = null;
        limpar();
        fCadMedicacao.setVisible(false);
    }

    public void limpar() {
        fCadMedicacao.MedicacaoNome.setText("");
        fCadMedicacao.MedicacaoDosagem.setText("");
        fCadMedicacao.MedicacaoTipo.setText("");
        fCadMedicacao.MedicacaoDescricao.setText("");
    }

    public void gravarMedicacao() {
        if (medicacaoSelecionada == null) {
            String nomeMedicacao = fCadMedicacao.MedicacaoNome.getText();
            String dosagem = fCadMedicacao.MedicacaoDosagem.getText();
            String tipo = fCadMedicacao.MedicacaoTipo.getText();
            String descricao = fCadMedicacao.MedicacaoDescricao.getText();
            Medicacao m = new Medicacao(nomeMedicacao, dosagem, tipo, descricao);
            if (daoMedicacao.inserir(m)) {
                JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
                limpar();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao inserir!");
            }
        } else {
            medicacaoSelecionada.setNome(fCadMedicacao.MedicacaoNome.getText());
            medicacaoSelecionada.setDosagem(fCadMedicacao.MedicacaoDosagem.getText());
            medicacaoSelecionada.setDescricao(fCadMedicacao.MedicacaoDescricao.getText());
            medicacaoSelecionada.setTipo(fCadMedicacao.sla.getText());
            if (daoMedicacao.editar(medicacaoSelecionada)) {
                medicacaoSelecionada = null;
                limpar();
                fCadMedicacao.setVisible(false);
                fConsMedicacao.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao editar!");
            }
        }
    }

    public void editar() {
        int linhaSelecionada = fConsMedicacao.MedicacaoTabela.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo editar a medicação?") == 0) {
                medicacaoSelecionada = modelMedicacao.getMedicacao(linhaSelecionada);
                fCadMedicacao.MedicacaoNome.setText(medicacaoSelecionada.getNome());
                fCadMedicacao.MedicacaoDosagem.setText(medicacaoSelecionada.getDosagem());
                fCadMedicacao.sla.setText(medicacaoSelecionada.getTipo());
                fCadMedicacao.MedicacaoDescricao.setText(medicacaoSelecionada.getDescricao());
                fConsMedicacao.setVisible(false);
                fCadMedicacao.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }

    public void excluir() {
        int linhaSelecionada = fConsMedicacao.MedicacaoTabela.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir a medicação?") == 0) {
                Medicacao m = modelMedicacao.getMedicacao(linhaSelecionada);
                if (daoMedicacao.remover(m)) {
                    JOptionPane.showMessageDialog(null, "Medicação excluída!");
                    modelMedicacao.ExcluirMedicacao(linhaSelecionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }

    public void consultarMedicacao() {
        carregarMedicacao();
        this.fConsMedicacao.setVisible(true);
    }

    public void carregarMedicacao() {
        modelMedicacao.Limpar();
        for (Medicacao m : daoMedicacao.listar()) {
            modelMedicacao.InserirMedicacao(m);
        }
    }
}
