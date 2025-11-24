package control;

import dao.DaoAnimal;
import dao.DaoBaixas;
import entidades.Animal;
import entidades.Baixas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import model.ModelAnimal;
import view.FCadAnimal;
import view.FCadBaixa;
import view.FConsAnimal;

public class ControlAnimal {

    private FCadAnimal fCadAnimal;
    private DaoAnimal daoAnimal;
    private FConsAnimal fConsAnimal;
    private DaoBaixas daoBaixas;
    private FCadBaixa fCadBaixa;
    private Animal animalSelecionado;
    private ModelAnimal modelAnimal;
    private ControlPrincipal controlPrincipal;

    public ControlAnimal() {
        this.controlPrincipal = new ControlPrincipal();
        this.fCadAnimal = new FCadAnimal(null, true);
        this.fConsAnimal = new FConsAnimal(null, true);
        this.modelAnimal = new ModelAnimal();
        this.fCadBaixa = new FCadBaixa(null, true);
        daoBaixas = new DaoBaixas();
        daoAnimal = new DaoAnimal();
        inicializarComponentes();
    }

    public void cadastrarAnimal() {
        this.fCadAnimal.setVisible(true);
    }

    public void inicializarComponentes() {
        fConsAnimal.AnimalTabela.setModel(modelAnimal);
        fCadBaixa.BaixaOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                darBaixa();
            }
        });
        fCadAnimal.AnimalOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gravarAnimal();
            }
        });

        fConsAnimal.animalBaixa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                darBaixa1();
            }
        });

        fConsAnimal.AnimalEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });

        fCadAnimal.AnimalCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });

        fCadBaixa.BaixaCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar2();
            }
        });
    }

    public void limpar2() {
        fCadBaixa.BaixaData.setText("");
        fCadBaixa.BaixaMotivo.setText("");
    }

    public void cancelar2() {
        animalSelecionado = null;
        limpar2();
        fCadBaixa.setVisible(false);
    }

    public void cancelar() {
        animalSelecionado = null;
        limpar();
        fCadAnimal.setVisible(false);
    }

    public void darBaixa1() {
        carregarAnimais();
        fCadBaixa.setVisible(true);
    }

    public void limpar() {
        fCadAnimal.AnimalNumero.setText("");
        fCadAnimal.AnimalNasc.setText("");
        fCadAnimal.AnimalRaca.setText("");
        fCadAnimal.AnimalSexo.setText("");
    }

    public void carregarAnimais() {
        fCadBaixa.animais.removeAllItems();
        for (Animal a : daoAnimal.listar()) {
            fCadBaixa.animais.addItem(Integer.toString(a.getNumero()));
        }
    }

    public void darBaixa() {
        int linhaSelecionada = fConsAnimal.AnimalTabela.getSelectedRow();
        if (JOptionPane.showConfirmDialog(null, "Deseja mesmo dar baixa no animal?") == 0) {
            Animal a = daoAnimal.selecionar(Integer.parseInt((String) fCadBaixa.animais.getModel().getSelectedItem()));
            String motivo = fCadBaixa.BaixaMotivo.getText();
            LocalDate data = controlPrincipal.converterDataBanco(fCadBaixa.BaixaData.getText());
            Baixas b = new Baixas(data, motivo, a);
            if (daoBaixas.inserir(b)) {
                JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
                limpar2();
                fCadBaixa.setVisible(false);
                Animal a1 = modelAnimal.getAnimal(linhaSelecionada);
                a1.setBaixado();
                daoAnimal.editar(a1);
                modelAnimal.ExcluirAnimal(linhaSelecionada);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao inserir!");
            }
        }
    }

    public void gravarAnimal() {
        if (animalSelecionado == null) {
            int numero = Integer.parseInt(fCadAnimal.AnimalNumero.getText());
            String dataNasc = fCadAnimal.AnimalNasc.getText();
            String raca = fCadAnimal.AnimalRaca.getText();
            String sexo = fCadAnimal.AnimalSexo.getText();
            LocalDate dataNascConvertida = controlPrincipal.converterDataBanco(dataNasc);
            Animal a = new Animal(numero, dataNascConvertida, raca, sexo);
            if (daoAnimal.inserir(a)) {
                JOptionPane.showMessageDialog(null, "Inserido com  sucesso!");
                limpar();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao inserir!");
            }
        } else {
            animalSelecionado.setNumero(Integer.parseInt(fCadAnimal.AnimalNumero.getText()));
            animalSelecionado.setDataNascimento(controlPrincipal.converterDataBanco(fCadAnimal.AnimalNasc.getText()));
            animalSelecionado.setRaca(fCadAnimal.AnimalRaca.getText());
            animalSelecionado.setSexo(fCadAnimal.AnimalSexo.getText());
            if (daoAnimal.editar(animalSelecionado)) {
                JOptionPane.showMessageDialog(null, "Editado com sucesso!");
                animalSelecionado = null;
                limpar();
                fCadAnimal.setVisible(false);
                fConsAnimal.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao editar!");
            }
        }
    }

    public void consultarAnimal() {
        carregarAnimal();
        this.fConsAnimal.setVisible(true);
    }

    public void carregarAnimal() {
        modelAnimal.Limpar();
        for (Animal a : daoAnimal.listar()) {
            modelAnimal.InserirAnimal(a);
        }
    }

    public void excluir() {
        int linhaSelecionada = fConsAnimal.AnimalTabela.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir o animal?") == 0) {
                Animal a = modelAnimal.getAnimal(linhaSelecionada);
                if (daoAnimal.remover(a)) {
                    JOptionPane.showMessageDialog(null, "Animal excluído!");
                    modelAnimal.ExcluirAnimal(linhaSelecionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }

    public void editar() {
        int linhaSelecionada = fConsAnimal.AnimalTabela.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo editar o animal?") == 0) {
                animalSelecionado = modelAnimal.getAnimal(linhaSelecionada);
                fCadAnimal.AnimalNumero.setText(Integer.toString((animalSelecionado.getNumero())));
                fCadAnimal.AnimalNasc.setText(controlPrincipal.converterDataBr(animalSelecionado.getDataNascimento()));
                fCadAnimal.AnimalRaca.setText(animalSelecionado.getRaca());
                fCadAnimal.AnimalSexo.setText(animalSelecionado.getSexo());
                fConsAnimal.setVisible(false);
                fCadAnimal.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }
}
