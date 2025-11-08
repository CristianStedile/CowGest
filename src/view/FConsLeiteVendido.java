package view;
public class FConsLeiteVendido extends javax.swing.JDialog {
public FConsLeiteVendido(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        LeiteTabela = new javax.swing.JTable();
        LeiteEditar = new javax.swing.JButton();
        leiteFiltrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta Vendas de Leite");
        setResizable(false);

        LeiteTabela.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        LeiteTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        LeiteTabela.setRowHeight(30);
        LeiteTabela.setShowGrid(false);
        LeiteTabela.setShowHorizontalLines(true);
        LeiteTabela.setShowVerticalLines(true);
        jScrollPane1.setViewportView(LeiteTabela);

        LeiteEditar.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        LeiteEditar.setText("EDITAR");

        leiteFiltrar.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        leiteFiltrar.setText("FILTRAR POR MÊS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(leiteFiltrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LeiteEditar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LeiteEditar)
                    .addComponent(leiteFiltrar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton LeiteEditar;
    public javax.swing.JTable LeiteTabela;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JButton leiteFiltrar;
    // End of variables declaration//GEN-END:variables
}
