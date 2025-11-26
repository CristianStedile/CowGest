package control;

import dao.DaoSemen;
import entidades.Semen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.ModelSemen;
import view.FCadSemen;
import view.FConsSemen;

public class ControlSemen {

    private FCadSemen fCadSemen;
    private FConsSemen fConsSemen;
    private DaoSemen daoSemen;
    private ModelSemen modelSemen;
    private Semen semenSelecionado;

    public ControlSemen() {
        this.fCadSemen = new FCadSemen(null, true);
        this.fConsSemen = new FConsSemen(null, true);
        this.modelSemen = new ModelSemen();
        daoSemen = new DaoSemen();
        inicializarComponentes();
    }

    public void cadastrarSemen() {
        this.fCadSemen.setVisible(true);
    }

    public void inicializarComponentes() {
        fConsSemen.tbSemen.setModel(modelSemen);
        fCadSemen.btOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gravarSemen();
            }
        });;

        fConsSemen.btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });

        fConsSemen.btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });

        fCadSemen.btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });

        fConsSemen.btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDose();
            }
        });

        fConsSemen.btRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerDose();
            }
        });
    }

    public void cancelar() {
        semenSelecionado = null;
        limpar();
        fCadSemen.setVisible(false);
    }

    public void limpar() {
        fCadSemen.tfDoses.setText("");
        fCadSemen.tfPote.setText("");
        fCadSemen.tfTouro.setText("");
        fConsSemen.tfQuantidade.setText("");
    }

    public void gravarSemen() {
        if (semenSelecionado == null) {
            String touro = fCadSemen.tfTouro.getText();
            int pote = Integer.parseInt(fCadSemen.tfPote.getText());
            int doses = Integer.parseInt(fCadSemen.tfDoses.getText());
            Semen s = new Semen(touro, doses, pote);
            if (daoSemen.inserir(s)) {
                JOptionPane.showMessageDialog(null, "Inserido com  sucesso!");
                limpar();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao inserir!");
            }
        } else {
            semenSelecionado.setPote(Integer.parseInt(fCadSemen.tfPote.getText()));
            semenSelecionado.setReprodutor(fCadSemen.tfTouro.getText());
            if (daoSemen.editar(semenSelecionado)) {
                JOptionPane.showMessageDialog(null, "Editado com sucesso!");
                semenSelecionado = null;
                limpar();
                carregarSemens();
                fCadSemen.setVisible(false);
                fConsSemen.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao editar!");
            }
        }
    }

    public void consultarSemen() {
        carregarSemens();
        this.fConsSemen.setVisible(true);
    }

    public void carregarSemens() {
        modelSemen.Limpar();
        for (Semen s : daoSemen.listar()) {
            modelSemen.InserirSemen(s);
        }
    }

    public void removerDose() {
        int linhaSelecionada = fConsSemen.tbSemen.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo remover doses?") == 0) {
                Semen s = modelSemen.getSemen(linhaSelecionada);
                s.removerDoses(Integer.parseInt(fConsSemen.tfQuantidade.getText()));
                if (daoSemen.editar(s)) {
                    carregarSemens();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }

    public void addDose() {
        int linhaSelecionada = fConsSemen.tbSemen.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo adicionar doses?") == 0) {
                Semen s = modelSemen.getSemen(linhaSelecionada);
                s.addDoses(Integer.parseInt(fConsSemen.tfQuantidade.getText()));
                if (daoSemen.editar(s)) {
                    carregarSemens();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }

    public void excluir() {
        int linhaSelecionada = fConsSemen.tbSemen.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir o sêmen?") == 0) {
                Semen s = modelSemen.getSemen(linhaSelecionada);
                if (daoSemen.remover(s)) {
                    JOptionPane.showMessageDialog(null, "Sêmen excluído!");
                    modelSemen.ExcluirSemen(linhaSelecionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }

    public void editar() {
        int linhaSelecionada = fConsSemen.tbSemen.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo editar o sêmen?") == 0) {
                semenSelecionado = modelSemen.getSemen(linhaSelecionada);
                fCadSemen.tfDoses.setText(String.valueOf(semenSelecionado.getDoses()));
                fCadSemen.tfPote.setText(String.valueOf(semenSelecionado.getPote()));
                fCadSemen.tfTouro.setText(semenSelecionado.getTouro());
                fConsSemen.setVisible(false);
                fCadSemen.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }
}
