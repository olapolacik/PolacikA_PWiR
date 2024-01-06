/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package Laboratorium02.Lab02_z6.src.lab02_z6;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Ola
 */
public class PodswietlanaEtykieta extends javax.swing.JFrame {

    private Timer scrollTimer;
    private String labelText;
    private int scrollSpeed;
    private int currentPosition = 0;
    private boolean isScrolling = false;
    private boolean isPaused = false;
    private int scrollDirection = 1; //
    
    
    public PodswietlanaEtykieta() {
       
        initComponents();
        labelText = jText.getText(); // Początkowy tekst etykiety
        scrollSpeed = 100; // Domyślna prędkość w milisekundach

        scrollTimer = new Timer(scrollSpeed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollText();
            }
        });
        
        
      jStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startScroll();
            }
        });
    
     jStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopScroll();
            }
        });
     
     
     jComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDirection();
            }
        });

        jComboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSpeed();
            }
        });
        
     }
          
    private void startScroll() {
        if (!isScrolling) {
            currentPosition = 0;
            isPaused = false;
            isScrolling = true;
            scrollTimer.start();
        }
    }

    private void stopScroll() {
        if (isScrolling) {
            scrollTimer.stop();
            isScrolling = false;
        }
    }

    private void pauseScroll() {
        if (isScrolling) {
            scrollTimer.stop();
            isPaused = true;
        }
    }

    private void resumeScroll() {
        if (isPaused) {
            scrollTimer.start();
            isPaused = false;
        }
    }

  /**
     private void scrollText() {
        StringBuilder formattedText = new StringBuilder("<html>");

        for (int i = 0; i < labelText.length(); i++) {
            char currentChar = labelText.charAt(i);

            if (i == (labelText.length() - 1)) {
                formattedText.append("<font style='color: red;'>").append(currentChar).append("</font>");
            } else {
                formattedText.append(currentChar);
            }
        }

        formattedText.append("</html>");
        jText.setText(formattedText.toString());

        if (scrollDirection == 1) {
            labelText = labelText.charAt(labelText.length() - 1) + labelText.substring(0, labelText.length() - 1);
        } else {
            labelText = labelText.substring(1) + labelText.charAt(0);
        }
    }
     */
       private void scrollText() {
        StringBuilder formattedText = new StringBuilder("<html>");

        for (int i = 0; i < labelText.length(); i++) {
            char currentChar = labelText.charAt(i);

            if (i == (labelText.length() - 1)) {
                formattedText.append("<font style='color: red;'>").append(currentChar).append("</font>");
            } else {
                formattedText.append(currentChar);
            }
            formattedText.append("<br>");
        }

        formattedText.append("</html>");
        jText.setText(formattedText.toString());

        if (scrollDirection == 1) {
            labelText = labelText.charAt(labelText.length() - 1) + labelText.substring(0, labelText.length() - 1);
        } else {
            labelText = labelText.substring(1) + labelText.charAt(0);
        }
    }

    

    private void updateDirection() {
        if (jComboBox1.getSelectedIndex() == 0) {
            scrollDirection = 1; // w prawo
        } else {
            scrollDirection = -1; // w lewo
        }
    }

    private void updateSpeed() {
        if (jComboBox2.getSelectedIndex() == 0) {
            scrollSpeed = 100; // szybko
        } else {
            scrollSpeed = 700; // wolno
        }
        if (isScrolling) {
            scrollTimer.setDelay(scrollSpeed);
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jStart = new javax.swing.JButton();
        jStop = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jStart.setText("Start");

        jStop.setText("Stop");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Prawo", "Lewo" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Szybko", "Wolno" }));

        jText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jText.setText("PROGRAMOWANIE");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jText, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jStop)
                    .addComponent(jStart))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jStop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(267, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(PodswietlanaEtykieta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PodswietlanaEtykieta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PodswietlanaEtykieta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PodswietlanaEtykieta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PodswietlanaEtykieta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JButton jStart;
    private javax.swing.JButton jStop;
    private javax.swing.JLabel jText;
    // End of variables declaration//GEN-END:variables
}
