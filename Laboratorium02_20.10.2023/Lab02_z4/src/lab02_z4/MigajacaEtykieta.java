/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lab02_z4;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author Ola
 */

public class MigajacaEtykieta extends javax.swing.JFrame {
    
 
    private Timer blinkTimer;
    private boolean isBlinking = false;
    private boolean isPaused = false;
    private Color onColor = Color.RED;
    private Color offColor = Color.WHITE;
    private int blinkInterval = 500;

    public MigajacaEtykieta() {
        initComponents();

        jLabel1.setForeground(onColor);
        jLabel1.setText("UWAGA");
        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 48));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        blinkTimer = new Timer(blinkInterval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleColor();
            }
        });
    }

    public void startBlink() {
        if (!isBlinking) {
            isPaused = false;
            isBlinking = true;
            blinkTimer.start();
        }
    }

    public void stopBlink() {
        if (isBlinking) {
            blinkTimer.stop();
            isBlinking = false;
            jLabel1.setForeground(onColor); // Reset to the onColor
        }
    }

    public void pauseBlink() {
        if (isBlinking && !isPaused) {
            blinkTimer.stop();
            isPaused = true;
        }
    }

    public void resumeBlink() {
        if (isBlinking && isPaused) {
            blinkTimer.start();
            isPaused = false;
        }
    }

    public void toggleColor() {
        if (jLabel1.getForeground() == onColor) {
            jLabel1.setForeground(offColor);
        } else {
            jLabel1.setForeground(onColor);
        }
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bStart = new javax.swing.JButton();
        bStop = new javax.swing.JButton();
        bPause = new javax.swing.JButton();
        bResume = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bStart.setText("Start");
        bStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStartActionPerformed(evt);
            }
        });

        bStop.setText("Stop");
        bStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStopActionPerformed(evt);
            }
        });

        bPause.setText("Pauza");
        bPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPauseActionPerformed(evt);
            }
        });

        bResume.setText("Wznów");
        bResume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResumeActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 48)); // NOI18N
        jLabel1.setText("UWAGA ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bResume)
                    .addComponent(bPause)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(bStop)
                        .addComponent(bStart)))
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(bStart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bStop)
                        .addGap(18, 18, 18)
                        .addComponent(bPause))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bResume)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void bStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStartActionPerformed
        // TODO add your handling code here:
        startBlink();
    }//GEN-LAST:event_bStartActionPerformed


    private void bStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStopActionPerformed
        // TODO add your handling code here:
        stopBlink();

    }//GEN-LAST:event_bStopActionPerformed

    private void bPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPauseActionPerformed
        // TODO add your handling code here:
        pauseBlink();
    }//GEN-LAST:event_bPauseActionPerformed

    private void bResumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResumeActionPerformed
        // TODO add your handling code here:
        resumeBlink();
    }//GEN-LAST:event_bResumeActionPerformed

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
            java.util.logging.Logger.getLogger(MigajacaEtykieta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MigajacaEtykieta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MigajacaEtykieta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MigajacaEtykieta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MigajacaEtykieta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bPause;
    private javax.swing.JButton bResume;
    private javax.swing.JButton bStart;
    private javax.swing.JButton bStop;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
