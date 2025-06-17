//VICTOR EHITI ITIMURA TAMAY 2485561

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projetop;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.util.List;


public class ImpMed extends javax.swing.JFrame {
    private static ImpMed impMedUnic;
    

    public ImpMed() {
        initComponents();
    }

    public static ImpMed getImpMed(){
        if(impMedUnic == null){
            impMedUnic = new ImpMed();
        }
        return impMedUnic;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabMed = new javax.swing.JTable();
        btAttTab = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel1.setText("MEDICOS");

        tabMed.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "NOME", "IDADE", "CPF", "SALARIO", "ESPECIALIZACAO", "CRM"
            }
        ));
        jScrollPane1.setViewportView(tabMed);

        btAttTab.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btAttTab.setText("ATUALIZAR TABELA");
        btAttTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAttTabActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(249, 249, 249)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(224, 224, 224)
                                .addComponent(btAttTab)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAttTab)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAttTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAttTabActionPerformed
        listarTab();
    }//GEN-LAST:event_btAttTabActionPerformed

    public void listarTab(){
        DefaultTableModel tabModel = (DefaultTableModel) tabMed.getModel();
        tabModel.setRowCount(0); // Limpa a tabela

        // Usa o MedicoDAO para buscar os dados do banco
        MedicoDAO medicoDAO = new MedicoDAO();
        try {
            List<Medico> listaDeMedicos = medicoDAO.findAll();
            
            // Itera sobre a lista e adiciona cada médico à tabela
            for(Medico m : listaDeMedicos){
                tabModel.addRow(new Object[]{
                    m.getNome(),
                    m.getCpf(),
                    m.getIdade(),
                    m.getSalario(),
                    m.getEspec(),
                    m.getCrm()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados dos médicos: " + e.getMessage(), "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImpMed().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAttTab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabMed;
    // End of variables declaration//GEN-END:variables
}
