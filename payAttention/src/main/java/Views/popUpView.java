/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import DAO.popUpDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Matheus
 */
public class popUpView extends javax.swing.JFrame {
    public static final long TEMPO_OPEN = (30000);
    public static final long TEMPO_CLOSE = (90000);
    static Timer timerOpen = null;
    static Timer timerClose = null;
    /**
     * Creates new form popUpView
     */
    public popUpView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Fredoka Light", 1, 24)); // NOI18N
        jButton1.setText("SIM!");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.setRequestFocusEnabled(false);
        jButton1.setRolloverEnabled(false);
        jButton1.setVerifyInputWhenFocusTarget(false);
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 210, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/popUp.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel1.setFocusCycleRoot(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 340));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * @param //the command line arguments*/
        public void iniciarPopUp() {
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
            java.util.logging.Logger.getLogger(popUpView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(popUpView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(popUpView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(popUpView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        popUpView view = new popUpView();
        popUpDAO cadastrarPontos = new popUpDAO();

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   cadastrarPontos.popUpTrue();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                view.setVisible(false);
            }
        });

        if (timerOpen == null) {
            timerOpen = new Timer();
            TimerTask tarefa = new TimerTask() {
                @Override
                public void run() {
                    try {
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                view.setVisible(true);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };
            timerOpen.scheduleAtFixedRate(tarefa, TEMPO_OPEN, TEMPO_OPEN);
        }
        if (timerClose == null) {
                timerClose = new Timer();
                TimerTask tarefa2 = new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            java.awt.EventQueue.invokeLater(new Runnable() {
                                public void run() {
                                    view.setVisible(false);
                                    try {
                                        cadastrarPontos.popUpFalse();
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                timerClose.scheduleAtFixedRate(tarefa2, TEMPO_CLOSE, TEMPO_CLOSE);
            }
        /* Create and display the form */
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
