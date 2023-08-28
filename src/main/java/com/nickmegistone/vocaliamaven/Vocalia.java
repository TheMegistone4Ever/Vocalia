package com.nickmegistone.vocaliamaven;

import com.nickmegistone.apputils.AppUtils;
import com.nickmegistone.form.Form;
import com.nickmegistone.form.InitForm;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Vocalia extends javax.swing.JFrame {

    private int mouseX, mouseY;
    private boolean isFullScreen = false;
    private final InitForm initForm;
    
    public Vocalia() {
        initComponents();
        setBackground(AppUtils.MAIN_BACKGROUND_COLOR);
        initForm = new InitForm();
        menu1.initMenu(index -> {
            if (index == 0) {
                showForm(initForm);
            } else {
                showForm(new Form(index));
            }
        });
        showForm(initForm);
        menu1.setAllTemporarilyOffExcept(0);
    }

    private void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.revalidate();
        body.repaint();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        com.nickmegistone.swing.RoundPanel roundPanel1 = new com.nickmegistone.swing.RoundPanel();
        menu1 = new com.nickmegistone.component.Menu();
        body = new com.nickmegistone.swing.RoundPanel();
        JButton jButton1 = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);

        roundPanel1.setBackground(AppUtils.LIGHT_BACKGROUND_COLOR);
        roundPanel1.setPreferredSize(new java.awt.Dimension(AppUtils.WIDTH, AppUtils.HEIGHT));
        roundPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                roundPanel1MouseDragged(evt);
            }
        });
        roundPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roundPanel1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                roundPanel1MousePressed(evt);
            }
        });

        menu1.setMinimumSize(new java.awt.Dimension(0, 0));
        menu1.setPreferredSize(new java.awt.Dimension(256, 680));

        body.setBackground(AppUtils.DARK_BACKGROUND_COLOR);
        body.setPreferredSize(new java.awt.Dimension(998, 680));
        body.setLayout(new java.awt.BorderLayout());

        jButton1.setBackground(new java.awt.Color(204, 0, 51));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new ImageIcon(System.getProperty("user.dir") + "/src/main/java/com/nickmegistone/resources/close.png"));
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.setMaximumSize(new java.awt.Dimension(32, 32));
        jButton1.setMinimumSize(new java.awt.Dimension(32, 32));
        jButton1.setPreferredSize(new java.awt.Dimension(24, 24));
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.out.println(evt);
        initForm.handleExitCommand();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void roundPanel1MouseDragged(@NotNull MouseEvent evt) {//GEN-FIRST:event_roundPanel1MouseDragged
        System.out.println(evt);
        setLocation(evt.getXOnScreen() - mouseX, evt.getYOnScreen() - mouseY);
    }//GEN-LAST:event_roundPanel1MouseDragged

    private void roundPanel1MousePressed(@NotNull MouseEvent evt) {//GEN-FIRST:event_roundPanel1MousePressed
        System.out.println(evt);
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_roundPanel1MousePressed

    private void roundPanel1MouseClicked(@NotNull MouseEvent evt) {//GEN-FIRST:event_roundPanel1MouseClicked
        System.out.println(evt);
        if (evt.getClickCount() > 1) {
            GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            if (isFullScreen) {
                // Exit full screen mode
                device.setFullScreenWindow(null);
                isFullScreen = false;
            } else {
                // Enter full screen mode
                device.setFullScreenWindow(this);
                isFullScreen = true;
            }
        }
    }//GEN-LAST:event_roundPanel1MouseClicked

    public static void main(String[] args) {
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vocalia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            Vocalia vocalia = new Vocalia();
            vocalia.setIconImage(new ImageIcon(System.getProperty("user.dir") + "/src/main/java/com/nickmegistone/resources/logo.png").getImage());
            vocalia.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.nickmegistone.swing.RoundPanel body;
    private com.nickmegistone.component.Menu menu1;
    // End of variables declaration//GEN-END:variables
}
