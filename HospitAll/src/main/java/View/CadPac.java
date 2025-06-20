/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.Doenca;
import Model.Paciente;
import Model.PacienteDAO;
import Model.TipoDoencaException;
import javax.swing.JOptionPane;

public class CadPac extends javax.swing.JFrame {
    static Paciente p = new Paciente();
    private static CadPac cadPacUnic;
    
    public CadPac() {
        initComponents();
    }

    public static CadPac getCadPac(){
        if(cadPacUnic == null){
            cadPacUnic = new CadPac();
        }
        return cadPacUnic;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rotCadPac = new javax.swing.JLabel();
        rotNome = new javax.swing.JLabel();
        rotIdade = new javax.swing.JLabel();
        rotCpf = new javax.swing.JLabel();
        rotId = new javax.swing.JLabel();
        rotTipo = new javax.swing.JLabel();
        rotSint = new javax.swing.JLabel();
        rotRest = new javax.swing.JLabel();
        cxNome = new javax.swing.JTextField();
        cxIdade = new javax.swing.JTextField();
        cxCpf = new javax.swing.JTextField();
        cxId = new javax.swing.JTextField();
        cxSint = new javax.swing.JTextField();
        cxRestr = new javax.swing.JTextField();
        cbTipo = new javax.swing.JComboBox<>();
        btIns = new javax.swing.JButton();
        btSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        rotCadPac.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        rotCadPac.setText("CADASTRO DE PACIENTE");

        rotNome.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        rotNome.setText("NOME:");

        rotIdade.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        rotIdade.setText("IDADE:");

        rotCpf.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        rotCpf.setText("CPF:");

        rotId.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        rotId.setText("ID:");

        rotTipo.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        rotTipo.setText("TIPO DE DOENCA:");

        rotSint.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        rotSint.setText("SINTOMAS:");

        rotRest.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        rotRest.setText("RESTRICOES:");

        cbTipo.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BACTERIA", "VIRUS" }));
        cbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoActionPerformed(evt);
            }
        });

        btIns.setText("INSERIR");
        btIns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInsActionPerformed(evt);
            }
        });

        btSair.setText("SAIR");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rotTipo)
                            .addComponent(rotNome, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rotIdade, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rotCpf, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rotId, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rotSint, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rotRest, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btIns)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btSair))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cxCpf, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                        .addComponent(cxId)
                                        .addComponent(cxSint)
                                        .addComponent(cxRestr)
                                        .addComponent(cxIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cxNome)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(73, Short.MAX_VALUE)
                        .addComponent(rotCadPac)))
                .addGap(71, 71, 71))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(rotCadPac)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotNome)
                    .addComponent(cxNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotIdade)
                    .addComponent(cxIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotCpf)
                    .addComponent(cxCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotId)
                    .addComponent(cxId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotTipo)
                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rotSint)
                    .addComponent(cxSint, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rotRest)
                    .addComponent(cxRestr, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btIns)
                    .addComponent(btSair))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoActionPerformed
        
    }//GEN-LAST:event_cbTipoActionPerformed

    private void btInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInsActionPerformed
        cadPac();
    }//GEN-LAST:event_btInsActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        sair();
    }//GEN-LAST:event_btSairActionPerformed

    public void cadPac(){
        try {
            
            String nome = cxNome.getText().trim();
            String cpfStr = cxCpf.getText().trim();
            String idadeStr = cxIdade.getText().trim();
            String sintomas = cxSint.getText().trim();
            String restricoes = cxRestr.getText().trim();

            
            if (nome.isEmpty() || cpfStr.isEmpty() || idadeStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome, CPF e Idade são obrigatórios.", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
                return;
            }

            

          
            Doenca novaDoenca = new Doenca();

         
            String tipoSelecionado = (String) cbTipo.getSelectedItem();
            novaDoenca.setTipo(tipoSelecionado);
            novaDoenca.setSint(sintomas);
            novaDoenca.setRestr(restricoes);

            
            Paciente novoPaciente = new Paciente();
            novoPaciente.setNome(nome);
            novoPaciente.setCpf(Integer.parseInt(cpfStr));
            novoPaciente.setIdade(Integer.parseInt(idadeStr));

            
            novoPaciente.setDoenca(novaDoenca);

            
            PacienteDAO pacienteDAO = new PacienteDAO();
            pacienteDAO.save(novoPaciente);

            
            JOptionPane.showMessageDialog(
                this,
                "PACIENTE CADASTRADO COM SUCESSO!\nID gerado pelo banco: " + novoPaciente.getId(),
                "CADASTRO DE PACIENTE",
                JOptionPane.INFORMATION_MESSAGE
            );

            limpar(); 

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Os campos CPF e IDADE devem ser números inteiros!", "ERRO DE ENTRADA", JOptionPane.ERROR_MESSAGE);
        } catch (TipoDoencaException tde) {
            JOptionPane.showMessageDialog(this, "Erro na validação da doença: " + tde.getMessage(), "ERRO DE VALIDAÇÃO", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao salvar o paciente:\n" + e.getMessage(), "ERRO DE BANCO DE DADOS", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); 
        }
    }
    
    public void sair(){
		int resp = JOptionPane.showConfirmDialog(
					null,
					"Deseja realmente sair?",
					"Saida",
					JOptionPane.YES_NO_OPTION
				);
			if(resp == 0){
				this.dispose();	
			}
    }
    
    public void limpar(){
		cxCpf.setText("");
		cxNome.setText("");
                cxIdade.setText("");
                cxId.setText("");
                cxRestr.setText("");
                cxSint.setText("");
                cxNome.requestFocus();
    }
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadPac.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadPac.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadPac.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadPac.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadPac().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btIns;
    private javax.swing.JButton btSair;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JTextField cxCpf;
    private javax.swing.JTextField cxId;
    private javax.swing.JTextField cxIdade;
    private javax.swing.JTextField cxNome;
    private javax.swing.JTextField cxRestr;
    private javax.swing.JTextField cxSint;
    private javax.swing.JLabel rotCadPac;
    private javax.swing.JLabel rotCpf;
    private javax.swing.JLabel rotId;
    private javax.swing.JLabel rotIdade;
    private javax.swing.JLabel rotNome;
    private javax.swing.JLabel rotRest;
    private javax.swing.JLabel rotSint;
    private javax.swing.JLabel rotTipo;
    // End of variables declaration//GEN-END:variables
}
