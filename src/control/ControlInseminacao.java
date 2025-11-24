package control;

import dao.DaoAnimal;
import dao.DaoInseminacao;
import dao.DaoSemen;
import entidades.Animal;
import entidades.Inseminacao;
import entidades.PesagemLeite;
import entidades.Semen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import model.ModelInseminacao;
import view.FCadInseminacao;
import view.FConsInseminacao;

public class ControlInseminacao {

    private FCadInseminacao fCadInseminacao;
    private FConsInseminacao fConsIneminacao;
    private DaoInseminacao daoInseminacao;
    private DaoAnimal daoAnimal;
    private DaoSemen daoSemen;
    private ModelInseminacao modelInseminacao;
    private Inseminacao inseminacaoSelecionada;
    private ControlPrincipal controlPrincipal;

    public ControlInseminacao() {
        this.controlPrincipal = new ControlPrincipal();
        this.fCadInseminacao = new FCadInseminacao(null, true);
        this.fConsIneminacao = new FConsInseminacao(null, true);
        this.modelInseminacao = new ModelInseminacao();
        this.daoInseminacao = new DaoInseminacao();
        this.daoAnimal = new DaoAnimal();
        this.daoSemen = new DaoSemen();
        inicializarComponentes();
    }

    public void inicializarComponentes() {
        fConsIneminacao.tbInseminacao.setModel(modelInseminacao);
        fCadInseminacao.btOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gravarInseminacao();
            }
        });

        fConsIneminacao.btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });

        fConsIneminacao.btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });

        fCadInseminacao.btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });
    }

    public void cancelar() {
        inseminacaoSelecionada = null;
        limpar();
        fCadInseminacao.setVisible(false);
    }

    public void limpar() {
        fCadInseminacao.tfData.setText("");
    }

    public void cadastrarInseminacao() {
        carregarAnimais();
        carregarSemens();
        this.fCadInseminacao.setVisible(true);
    }

    public void gravarInseminacao() {
        if (inseminacaoSelecionada == null) {
            String data = fCadInseminacao.tfData.getText();
            Animal a = daoAnimal.selecionar(Integer.parseInt((String) fCadInseminacao.cbAnimais.getModel().getSelectedItem()));
            Semen s = daoSemen.selecionar((String) fCadInseminacao.cbSemens.getModel().getSelectedItem());
            LocalDate dataConvertida = controlPrincipal.converterDataBanco(data);
            Inseminacao i = new Inseminacao(dataConvertida, a, s);
            if (daoInseminacao.inserir(i)) {
                JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
                limpar();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao inserir!");
            }
        } else {
            inseminacaoSelecionada.setAnimal((Animal) fCadInseminacao.cbAnimais.getModel().getSelectedItem());
            inseminacaoSelecionada.setSemen((Semen) fCadInseminacao.cbSemens.getModel().getSelectedItem());
            inseminacaoSelecionada.setData(controlPrincipal.converterDataBanco(fCadInseminacao.tfData.getText()));
            if (daoInseminacao.editar(inseminacaoSelecionada)) {
                JOptionPane.showMessageDialog(null, "Editado com sucesso!");
                inseminacaoSelecionada = null;
                limpar();
                fCadInseminacao.setVisible(false);
                fConsIneminacao.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao editar!");
            }
        }
    }

    public void editar() {
        int linhaSelecionada = fConsIneminacao.tbInseminacao.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo editar a inseminação?") == 0) {
                carregarAnimais();
                carregarSemens();
                inseminacaoSelecionada = modelInseminacao.getInseminacao(linhaSelecionada);
                fCadInseminacao.cbAnimais.setSelectedItem(inseminacaoSelecionada.getAnimal().getNumero());
                fCadInseminacao.cbSemens.setSelectedItem(inseminacaoSelecionada.getSemen().getReprodutor());
                fCadInseminacao.tfData.setText(controlPrincipal.converterDataBr(inseminacaoSelecionada.getData()));
                fConsIneminacao.setVisible(false);
                fCadInseminacao.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }

    public void excluir() {
        int linhaSelecionada = fConsIneminacao.tbInseminacao.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir a inseminação?") == 0) {
                Inseminacao i = modelInseminacao.getInseminacao(linhaSelecionada);
                if (daoInseminacao.excluir(i)) {
                    JOptionPane.showMessageDialog(null, "Inseminação excluída!");
                    modelInseminacao.ExcluirInseminacao(linhaSelecionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }

    public void consultarInseminacoes() {
        carregarInseminacao();
        this.fConsIneminacao.setVisible(true);
    }

    public void carregarInseminacao() {
        modelInseminacao.Limpar();
        for (Inseminacao i : daoInseminacao.listar()) {
            modelInseminacao.InserirInseminacao(i);
        }
    }

    public void carregarAnimais() {
        fCadInseminacao.cbAnimais.removeAllItems();
        for (Animal a : daoAnimal.listar()) {
            fCadInseminacao.cbAnimais.addItem(Integer.toString(a.getNumero()));
        }
    }

    public void carregarSemens() {
        fCadInseminacao.cbSemens.removeAllItems();
        for (Semen s : daoSemen.listar()) {
            fCadInseminacao.cbSemens.addItem(s.getReprodutor());
        }
    }
}
