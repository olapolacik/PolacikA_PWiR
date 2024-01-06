/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Laboratorium02;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Lap
 */
public class Minutnik1 extends javax.swing.JFrame {

    /**
     * Creates new form Minutnik1
     */
    int godzina1, minuta1, sekunda1, godz1, min1, sek1;
    Timer timer1;
    boolean flag1 = true;
    boolean ifStop1 = false;

    public Minutnik1() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldGodzina = new javax.swing.JTextField();
        jTextFieldMinuta = new javax.swing.JTextField();
        jTextFieldSekunda = new javax.swing.JTextField();
        cBoxGodzina = new javax.swing.JComboBox<>();
        cBoxMinuta = new javax.swing.JComboBox<>();
        jButtonStart = new javax.swing.JButton();
        jButtonStop = new javax.swing.JButton();
        cBoxSekunda = new javax.swing.JComboBox<>();
        jButtonReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextFieldGodzina.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextFieldGodzina.setText("00 :");

        jTextFieldMinuta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextFieldMinuta.setText("00 :");

        jTextFieldSekunda.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextFieldSekunda.setText("00");

        cBoxGodzina.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
                        "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));
        cBoxGodzina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cBoxGodzinaActionPerformed(evt);
            }
        });

        cBoxMinuta.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
                        "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                        "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
                        "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cBoxMinuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cBoxMinutaActionPerformed(evt);
            }
        });

        jButtonStart.setText("Start");
        jButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartActionPerformed(evt);
            }
        });

        jButtonStop.setText("Stop");
        jButtonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStopActionPerformed(evt);
            }
        });

        cBoxSekunda.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
                        "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                        "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
                        "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cBoxSekunda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cBoxSekundaActionPerformed(evt);
            }
        });

        jButtonReset.setText("RESET");
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButtonStart)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButtonStop))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(cBoxGodzina, 0, 1, Short.MAX_VALUE)
                                                        .addComponent(jTextFieldGodzina,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 51,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(jTextFieldMinuta,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 51,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cBoxMinuta,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 51,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(jTextFieldSekunda,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 51,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cBoxSekunda,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 51,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(jButtonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 66,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(57, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldGodzina, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldMinuta, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldSekunda, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cBoxGodzina, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cBoxMinuta, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cBoxSekunda, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonReset))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonStart)
                                        .addComponent(jButtonStop))
                                .addContainerGap(27, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cBoxGodzinaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cBoxGodzinaActionPerformed
        jTextFieldGodzina.setText("" + cBoxGodzina.getSelectedItem());
        godzina1 = Integer.parseInt(jTextFieldGodzina.getText());
    }// GEN-LAST:event_cBoxGodzinaActionPerformed

    private void cBoxMinutaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cBoxMinutaActionPerformed
        jTextFieldMinuta.setText("" + cBoxMinuta.getSelectedItem());
        minuta1 = Integer.parseInt(jTextFieldMinuta.getText());
    }// GEN-LAST:event_cBoxMinutaActionPerformed

    private void cBoxSekundaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cBoxSekundaActionPerformed
        jTextFieldSekunda.setText("" + cBoxSekunda.getSelectedItem());
        sekunda1 = Integer.parseInt(jTextFieldSekunda.getText());
    }// GEN-LAST:event_cBoxSekundaActionPerformed

    private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonStartActionPerformed
        timer1 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ifStop1) {
                    godzina1 = godz1;
                    minuta1 = min1;
                    sekunda1 = sek1;
                    ifStop1 = false;
                }

                if (sekunda1 == 0) {
                    if (minuta1 == 0) {
                        if (godzina1 == 0) {
                            JOptionPane.showMessageDialog(rootPane, "Czas minął", "Zatrzymano", 0);
                            timer1.stop();
                            return;
                        } else {
                            godzina1--;
                            minuta1 = 59;
                            sekunda1 = 59;
                        }
                    } else {
                        minuta1--;
                        sekunda1 = 59;
                    }
                } else {
                    sekunda1--;
                }

                if (sekunda1 < 10) {
                    jTextFieldSekunda.setText("0" + sekunda1);
                } else {
                    jTextFieldSekunda.setText("" + sekunda1);
                }

                if (godzina1 < 10) {
                    jTextFieldGodzina.setText("0" + godzina1);
                } else {
                    jTextFieldGodzina.setText("" + godzina1);
                }

                if (minuta1 < 10) {
                    jTextFieldMinuta.setText("0" + minuta1);
                } else {
                    jTextFieldMinuta.setText("" + minuta1);
                }

                if (godzina1 == 0 && minuta1 == 0 && sekunda1 < 60) {
                    jTextFieldGodzina.setForeground(Color.RED);
                    jTextFieldMinuta.setForeground(Color.RED);
                    jTextFieldSekunda.setForeground(Color.RED);
                } else {
                    jTextFieldGodzina.setForeground(Color.BLACK);
                    jTextFieldMinuta.setForeground(Color.BLACK);
                    jTextFieldSekunda.setForeground(Color.BLACK);
                }
            }
        });
        timer1.start();
    }// GEN-LAST:event_jButtonStartActionPerformed

    private void jButtonStopActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonStopActionPerformed
        godz1 = godzina1;
        min1 = minuta1;
        sek1 = sekunda1;
        ifStop1 = true;
        timer1.stop();
    }// GEN-LAST:event_jButtonStopActionPerformed

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonResetActionPerformed
        godzina1 = 0;
        minuta1 = 0;
        sekunda1 = 0;
        jTextFieldGodzina.setText("00 :");
        jTextFieldMinuta.setText("00 :");
        jTextFieldSekunda.setText("00");
        cBoxGodzina.setSelectedIndex(0);
        cBoxMinuta.setSelectedIndex(0);
        cBoxSekunda.setSelectedIndex(0);
        jTextFieldGodzina.setForeground(Color.BLACK);
        jTextFieldMinuta.setForeground(Color.BLACK);
        jTextFieldSekunda.setForeground(Color.BLACK);
        ifStop1 = false; // Ustawienie ifStop na false, aby uniknąć wyświetlania komunikatu o końcu czasu
    }// GEN-LAST:event_jButtonResetActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Minutnik1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Minutnik1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Minutnik1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Minutnik1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Minutnik1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cBoxGodzina;
    private javax.swing.JComboBox<String> cBoxMinuta;
    private javax.swing.JComboBox<String> cBoxSekunda;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonStart;
    private javax.swing.JButton jButtonStop;
    private javax.swing.JTextField jTextFieldGodzina;
    private javax.swing.JTextField jTextFieldMinuta;
    private javax.swing.JTextField jTextFieldSekunda;
    // End of variables declaration//GEN-END:variables
}