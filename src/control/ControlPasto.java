package control;

import dao.DaoPasto;
import entidades.Pasto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import model.ModelPasto;
import view.FCadPasto;
import view.FConsPasto;

public class ControlPasto {

    LocalDate dataAtual = LocalDate.now();
    private FCadPasto fCadPasto;
    private DaoPasto daoPasto;
    private FConsPasto fConsPasto;
    private ModelPasto modelPasto;
    private Pasto pastoSelecionado;

    public ControlPasto() {
        this.fCadPasto = new FCadPasto(null, true);
        this.fConsPasto = new FConsPasto(null, true);
        this.modelPasto = new ModelPasto();
        daoPasto = new DaoPasto();
        inicializarComponentes();
    }

    public void cadastrarPasto() {
        this.fCadPasto.setVisible(true);
    }

    public void inicializarComponentes() {
        fConsPasto.tbPastos.setModel(modelPasto);
        fCadPasto.btOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gravarPasto();
            }
        });;

        fConsPasto.btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });

        fConsPasto.btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });

        fCadPasto.btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });
        
        fConsPasto.btRocar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rocar();
            }
        });
        
        fConsPasto.btSobSemeadura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sobSemeadura();
            }
        });
        
        fConsPasto.btAdubar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adubar();
            }
        });
    }

    public void rocar() {
        int linhaSelecionada = fConsPasto.tbPastos.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo roçar o pasto?") == 0) {
                Pasto p = modelPasto.getPasto(linhaSelecionada);
                p.setUltimaRocada(dataAtual);
                if(daoPasto.editar(p)){
                    JOptionPane.showMessageDialog(null, "Roçado com sucesso!");
                    carregarPastos();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }
    
    public void sobSemeadura() {
        int linhaSelecionada = fConsPasto.tbPastos.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo realizar sob semeadura no pasto?") == 0) {
                Pasto p = modelPasto.getPasto(linhaSelecionada);
                p.setUltimaSobSemadura(dataAtual);
                if(daoPasto.editar(p)){
                    JOptionPane.showMessageDialog(null, "Sob semeadura realizada com sucesso!");
                    carregarPastos();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }
    
    public void adubar() {
        int linhaSelecionada = fConsPasto.tbPastos.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo adubar o pasto?") == 0) {
                Pasto p = modelPasto.getPasto(linhaSelecionada);
                p.setUltimaAdubacao(dataAtual);
                if(daoPasto.editar(p)){
                    JOptionPane.showMessageDialog(null, "Adubado com sucesso!");
                    carregarPastos();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }

    public void cancelar() {
        pastoSelecionado = null;
        limpar();
        fCadPasto.setVisible(false);
    }
    
    public void limpar() {
        fCadPasto.tfNome.setText("");
        fCadPasto.tfTipoGrama.setText("");
    }

    public void gravarPasto() {
        if (pastoSelecionado == null) {
            String nome = fCadPasto.tfNome.getText();
            String tipoGrama = fCadPasto.tfTipoGrama.getText();
            Pasto p = new Pasto(nome, tipoGrama);
            if (daoPasto.inserir(p)) {
                JOptionPane.showMessageDialog(null, "Inserido com  sucesso!");
                limpar();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao inserir!");
            }
        } else {
            pastoSelecionado.setNome(fCadPasto.tfNome.getText());
            pastoSelecionado.setTipoGrama(fCadPasto.tfTipoGrama.getText());
            if (daoPasto.editar(pastoSelecionado)) {
                JOptionPane.showMessageDialog(null, "Editado com sucesso!");
                pastoSelecionado = null;
                limpar();
                fCadPasto.setVisible(false);
                fConsPasto.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao editar!");
            }
        }
    }

    public void consultarPasto() {
        carregarPastos();
        this.fConsPasto.setVisible(true);
    }

    public void carregarPastos() {
        modelPasto.Limpar();
        for (Pasto p : daoPasto.listar()) {
            modelPasto.inserirPasto(p);
        }
    }

    public void excluir() {
        int linhaSelecionada = fConsPasto.tbPastos.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir o pasto?") == 0) {
                Pasto p = modelPasto.getPasto(linhaSelecionada);
                if (daoPasto.remover(p)) {
                    JOptionPane.showMessageDialog(null, "Pasto excluído!");
                    modelPasto.excluirPasto(linhaSelecionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }

    public void editar() {
        int linhaSelecionada = fConsPasto.tbPastos.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo editar o animal?") == 0) {
                pastoSelecionado = modelPasto.getPasto(linhaSelecionada);
                fCadPasto.tfNome.setText(pastoSelecionado.getNome());
                fCadPasto.tfTipoGrama.setText(pastoSelecionado.getTipoGrama());
                fConsPasto.setVisible(false);
                fCadPasto.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }
}
