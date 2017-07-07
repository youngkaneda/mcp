
package com.ifpb.HiDiary.Visao;
import Excecoes.PreencheCamposException;
import com.ifpb.HiDiary.Controle.CompromissoDao;
import com.ifpb.HiDiary.Controle.CompromissoDaoBanco;
import com.ifpb.HiDiary.Controle.CompromissoDaoBinario;
import com.ifpb.HiDiary.Controle.UsuarioDao;
import com.ifpb.HiDiary.Controle.UsuarioDaoBinario;
import com.ifpb.HiDiary.Modelo.Compromisso;
import com.ifpb.HiDiary.Modelo.Usuario;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import com.toedter.calendar.JCalendar;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 *
 * @author Lyndemberg
 */
public class telaEditaCompromisso extends javax.swing.JFrame {

    private static Compromisso compromisso;
    private CompromissoDao daoComp;
    
    public telaEditaCompromisso(Compromisso c) throws SQLException{
        daoComp = new CompromissoDaoBanco();
        this.compromisso=c;
        initComponents();
        ImageIcon icon = new ImageIcon("imagens/icone.png");
        setIconImage(icon.getImage());
        setCampos(); 
    }


    public void setCampos(){
                    LocalDate data = compromisso.getData();
                    java.util.Date date = java.sql.Date.valueOf(data);
                    campoData.setDate(date);
                    campoHora.setValue(compromisso.getHora().getHour());
                    campoMinutos.setValue(compromisso.getHora().getMinute());
                    campoDescricao.setText(compromisso.getDescricao());
                    campoLocal.setText(compromisso.getLocal());
                    campoAgenda.setText(compromisso.getNomeAgenda());
                    campoData.setEnabled(true);
                    campoDescricao.setEditable(true);
                    campoLocal.setEditable(true);
                    campoAgenda.setEditable(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelData = new javax.swing.JLabel();
        labelHora = new javax.swing.JLabel();
        labelDescricao = new javax.swing.JLabel();
        labelLocal = new javax.swing.JLabel();
        campoData = new com.toedter.calendar.JDateChooser();
        campoDescricao = new javax.swing.JTextField();
        campoLocal = new javax.swing.JTextField();
        buttonSalvar = new javax.swing.JButton();
        labelAgenda = new javax.swing.JLabel();
        campoAgenda = new javax.swing.JTextField();
        campoHora = new javax.swing.JSpinner();
        campoMinutos = new javax.swing.JSpinner();
        buttonCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("HiDiary - Editar Compromisso");

        labelData.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        labelData.setText("Data");

        labelHora.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        labelHora.setText("Hora");

        labelDescricao.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        labelDescricao.setText("Descrição");

        labelLocal.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        labelLocal.setText("Local");

        campoDescricao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        campoLocal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        buttonSalvar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        buttonSalvar.setText("SALVAR");
        buttonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalvarActionPerformed(evt);
            }
        });

        labelAgenda.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        labelAgenda.setText("Agenda");

        campoHora.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));

        campoMinutos.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));

        buttonCancelar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        buttonCancelar.setText("CANCELAR");
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\lynde\\Desktop\\HiDiary\\imagens\\compromisso.png")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel2.setText("EDITAR");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setText("COMPROMISSO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelDescricao)
                        .addGap(18, 18, 18)
                        .addComponent(campoDescricao))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelHora)
                            .addComponent(labelLocal)
                            .addComponent(labelAgenda)
                            .addComponent(labelData))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(campoLocal)
                                .addComponent(campoAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(campoHora, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(campoMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(37, 37, 37))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(buttonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(buttonCancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {buttonCancelar, buttonSalvar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoData, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelHora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoHora)
                    .addComponent(campoMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoDescricao)
                    .addComponent(labelDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoLocal)
                    .addComponent(labelLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoAgenda)
                    .addComponent(labelAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCancelar))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {campoData, campoDescricao, campoLocal});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {buttonCancelar, buttonSalvar});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalvarActionPerformed
           Compromisso atual = new Compromisso();
           
        try{
            
            Date dataDate = campoData.getDate();
            LocalDate data = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(dataDate));
            atual.setData(data);
            LocalTime hora = LocalTime.of((int)campoHora.getValue(), (int) campoMinutos.getValue(),0);
            atual.setHora(hora);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
            
            atual.setDescricao(campoDescricao.getText());
            atual.setLocal(campoLocal.getText());
            atual.setEmailUsuario(PaginaInicial.usuarioLogado.getEmail());
            atual.setNomeAgenda(compromisso.getNomeAgenda());
                    
                    if(daoComp.create(atual)){
                        daoComp.delete(compromisso);
                        ImageIcon icon = new ImageIcon("imagens/confirm.png");
                        JOptionPane.showMessageDialog(null, "Compromisso atualizado com sucesso!", "Confirmação", JOptionPane.OK_OPTION,icon);
                        telaGerenciarCompromissos.inicializaJTable();
                        PaginaInicial.inicializarTabela();
                        this.dispose();
                    }else{
                        JOptionPane.showMessageDialog(null, "Você já tem um compromisso nesse dia e hora");
                    }
                    
                    
            
                    
                
            
            
        }catch(DateTimeException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (ClassNotFoundException | IOException  ex) {
            JOptionPane.showMessageDialog(null, "Falha na conexão");
        } catch(PreencheCamposException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }catch(NullPointerException ex){
            JOptionPane.showMessageDialog(null, "Data Inválida");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Você já tem um compromisso nesse dia e hora");
        }
        
        
    }//GEN-LAST:event_buttonSalvarActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
       this.dispose();
    }//GEN-LAST:event_buttonCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(telaEditaCompromisso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telaEditaCompromisso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telaEditaCompromisso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaEditaCompromisso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new telaEditaCompromisso(compromisso).setVisible(true);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha na conexão");
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonSalvar;
    private javax.swing.JTextField campoAgenda;
    private com.toedter.calendar.JDateChooser campoData;
    private javax.swing.JTextField campoDescricao;
    private javax.swing.JSpinner campoHora;
    private javax.swing.JTextField campoLocal;
    private javax.swing.JSpinner campoMinutos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel labelAgenda;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelHora;
    private javax.swing.JLabel labelLocal;
    // End of variables declaration//GEN-END:variables
}
